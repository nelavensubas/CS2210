/*
 * Assignment 5: This class will represent the road map.
 */

package prjAsn5;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Stack;

public class RoadMap {

	/* Attributes */
	private Graph graph;
	private Stack<Node> path;
	private int start, end, width, length, budget, toll, gain, size;

	/*
	 * Constructor that will build a graph from the given input file.
	 * 
	 * @param inputFile the input file to build the graph
	 */
	public RoadMap(String inputFile) {
		// Read two lines at a time
		String horizontalLine;
		String verticalLine;

		try {
			// Read from the inputed file
			BufferedReader readFile = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)));
			// Skip the scale factor horizontalLine
			readFile.readLine();

			start = Integer.parseInt(readFile.readLine());
			end = Integer.parseInt(readFile.readLine());
			width = Integer.parseInt(readFile.readLine());
			length = Integer.parseInt(readFile.readLine());
			budget = Integer.parseInt(readFile.readLine());
			toll = Integer.parseInt(readFile.readLine());
			gain = Integer.parseInt(readFile.readLine());
			size = width * length;

			// Create a graph based on the size from the inputed width and length
			graph = new Graph(size);

			horizontalLine = readFile.readLine();

			// Horizontal horizontalLine counter to track of the nodes to be connected
			int horizontalNodes = 0;
			// Vertical horizontalLine counter to keep track of the nodes to be connected
			int verticalNodes = 0;
			while (horizontalLine != null) {
				// Horizontal line check
				// Nodes occur on even positions so increase the counter by 2 to get the node
				for (int i = 0; i < horizontalLine.length(); i += 2) {
					if (horizontalLine.charAt(i) == '+') {
						// Check to see if it's within the line length
						if (i + 2 < horizontalLine.length()) {
							// If the next char is not an X, the nodes need to be connected
							if (horizontalLine.charAt(i + 1) != 'X') {
								graph.insertEdge(graph.getNode(horizontalNodes), graph.getNode(horizontalNodes + 1),
										findEdgeType(horizontalLine.charAt(i + 1)));

							}
						}
						// Increase the horizontal counter to connect the next nodes
						horizontalNodes += 1;
					}
				}

				// Read the next line to see which nodes need to be connected vertically
				verticalLine = readFile.readLine();
				// Check to see if we reached the end of the file
				if (verticalLine == null)
					break;
				// Vertical line check
				for (int j = 0; j < verticalLine.length(); j += 2) {
					if (verticalLine.charAt(j) != 'X') {
						graph.insertEdge(graph.getNode(verticalNodes), graph.getNode(verticalNodes + width),
								findEdgeType(verticalLine.charAt(j)));
						verticalNodes += 1;
						// If the char is an X, increase the counter since there could be two X's in a
						// row
					} else {
						verticalNodes += 1;
					}
				}

				horizontalLine = readFile.readLine();
			}

			// Stop reading the file and close it
			readFile.close();
		} catch (FileNotFoundException e) {
			throw new MapException();
		} catch (GraphException e) {
			throw new GraphException();
		} catch (NumberFormatException e) {
			throw new MapException();
		} catch (IOException e) {
			throw new MapException();
		}
	}

	/*
	 * getGraph method will give the graph representing the road map.
	 * 
	 * @return the graph
	 */
	public Graph getGraph() {
		return graph;
	}

	/*
	 * getStartingNode method will give the starting node.
	 * 
	 * @return the starting node
	 */
	public int getStartingNode() {
		return start;
	}

	/*
	 * getDestinationNode method will give the destination node.
	 * 
	 * @return the destination node
	 */
	public int getDestinationNode() {
		return end;
	}

	/*
	 * getInitialMoney method will give the initial amount of money available to pay
	 * tolls.
	 * 
	 * @return the initial amount of money
	 */
	public int getInitialMoney() {
		return budget;
	}

	/*
	 * findPath method will give an iterator containing the nodes of a path from one
	 * endpoint to another endpoint if the path exists.
	 * 
	 * @param start the first node
	 * 
	 * @param destination the second node
	 * 
	 * @param initialMoney the amount of money to begin with
	 * 
	 * @return the iterator containing the nodes of a valid path
	 */
	public Iterator findPath(int start, int destination, int initialMoney) {
		Node s = graph.getNode(start);
		Node e = graph.getNode(destination);
		// Store the connected nodes for that valid path
		path = new Stack<Node>();
		// Make a call to the find helper method to find a valid path
		return find(s, e, initialMoney);
	}

	/*
	 * find method will be a helper to find a valid path..
	 * 
	 * @param start the first node
	 * 
	 * @param destination the second node
	 * 
	 * @param initialMoney the amount of money to begin with
	 * 
	 * @return the iterator containing the nodes of a valid path
	 */
	private Iterator find(Node start, Node destination, int initialMoney) {
		// Mark the starting node and push it into the stackS
		start.setMark(true);
		path.push(start);

		// If the starting point and destination point are equal, we can give the
		// iterator
		if (start.getName() == destination.getName()) {
			return path.iterator();
		}

		// Initialize edges to the edges incident on the starting node
		Iterator<Edge> edgeIter = graph.incidentEdges(start);
		// Go through all of the nodes
		while (edgeIter.hasNext()) {
			int money = initialMoney;
			Edge road = edgeIter.next();
			Node next = road.secondEndpoint();

			// Check to see if the next node is the current node
			if (next == start) {
				next = road.firstEndpoint();
			}

			// For toll roads money is lost
			if (!next.getMark() && road.getType() == 1 && (money - toll >= 0)) {
				money = money - toll;
				Iterator result = find(next, destination, money);
				if (result != null)
					return result;
				// For reward roads money is gained
			} else if (!next.getMark() && road.getType() == -1) {
				money += gain;
				Iterator result = find(next, destination, money);
				if (result != null)
					return result;
				// For public roads, no money is lost or gained
			} else if (!next.getMark() && road.getType() == 0) {
				Iterator result = find(next, destination, money);
				if (result != null)
					return result;
			}
		}

		start.setMark(false);
		path.pop();

		// Path has ended
		return null;
	}

	/*
	 * findEdgeType method will give the type of edge.
	 * 
	 * @param letter the character from the input file
	 * 
	 * @return the number that corresponds to the letter
	 */
	private int findEdgeType(char letter) {
		if (letter == 'T')
			return 1;
		else if (letter == 'C')
			return -1;
		return 0;
	}

}
