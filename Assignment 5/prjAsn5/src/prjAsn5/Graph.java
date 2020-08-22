/*
 * Assignment 5: This class will represent an undirected graph.
 */

package prjAsn5;

import java.util.Iterator;
import java.util.Stack;

public class Graph implements GraphADT {
	/* Attributes */
	private int numNodes;
	private Node[] graph;
	private Edge[][] edges;

	/*
	 * Constructor that will create a graph with a specified number of nodes and no
	 * edges.
	 * 
	 * @param n the number of nodes
	 */
	public Graph(int n) {
		numNodes = n; // set the number of nodes
		graph = new Node[n]; // initialize the node array
		edges = new Edge[n][n]; // initialize the adjacency matrix

		// Initialize the nodes
		for (int i = 0; i < n; i++) {
			graph[i] = new Node(i);
		}
	}

	/*
	 * getNode method will give the node with the specified name if it exists.
	 * 
	 * @param name the name of the node
	 * 
	 * @return the node with the specified name
	 */
	@Override
	public Node getNode(int name) throws GraphException {
		if (name >= numNodes || name < 0) {
			throw new GraphException();
		}
		// Return the corresponding node
		return graph[name];
	}

	/*
	 * insertEdge method will add an edge of the given type connecting u and v.
	 * 
	 * @param u the first node
	 * 
	 * @param v the second node
	 * 
	 * @param edgeType the type of edge either being a public road, private road, or
	 * a reward road
	 */
	@Override
	public void insertEdge(Node u, Node v, int edgeType) throws GraphException {
		if (u.getName() >= numNodes || u.getName() < 0 || v.getName() >= numNodes || v.getName() < 0) {
			throw new GraphException();
		}

		// Add the edge
		edges[u.getName()][v.getName()] = new Edge(u, v, edgeType); // Create an edge between the first and second node
																	// with the passed data
		edges[v.getName()][u.getName()] = new Edge(u, v, edgeType); // Create and edge between the second and first node
																	// with the passed data
	}

	/*
	 * incidentEdges method will give a java iterator storing all the edges incident
	 * on node u
	 * 
	 * @param u the node storing all the edges incident
	 * 
	 * @return a java iterator storing all the edges incident on node u if the node
	 * exists
	 */
	@Override
	public Iterator incidentEdges(Node u) throws GraphException {
		if (u.getName() >= numNodes || u.getName() < 0) {
			throw new GraphException();
		}

		// Set up a new stack
		Stack edgeStack = new Stack();
		// Go through the row that corresponds to this node
		for (int i = 0; i < edges[u.getName()].length; i++) {
			// If the element at the current index is connected to another node, push it
			// into the stack
			if (edges[u.getName()][i] != null) {
				edgeStack.push(edges[u.getName()][i]);
			}
		}
		// return the iterator of the stack
		return edgeStack.iterator();
	}

	/*
	 * getEdge method will give an edge connecting nodes u and v.
	 * 
	 * @param u the first node
	 * 
	 * @param v the second node
	 * 
	 * @return the edge connecting nodes u and v if it exists
	 */
	@Override
	public Edge getEdge(Node u, Node v) throws GraphException {
		if (u.getName() >= numNodes || u.getName() < 0 || v.getName() >= numNodes || v.getName() < 0
				|| edges[u.getName()][v.getName()] == null && edges[v.getName()][u.getName()] == null
				|| areAdjacent(u, v) == false) {
			throw new GraphException();
		}
		// Return the edge between the two nodes
		return edges[u.getName()][v.getName()];
	}

	/*
	 * areAdjacent method will check to see if nodes u and v are adjacent.
	 * 
	 * @param u the first node
	 * 
	 * @param v the second node
	 * 
	 * @return true if nodes u and v are adjacent, otherwise false
	 */
	@Override
	public boolean areAdjacent(Node u, Node v) throws GraphException {
		if (u.getName() >= numNodes || u.getName() < 0 || v.getName() >= numNodes || v.getName() < 0) {
			throw new GraphException();
			// Check to see if the edges are connected
		} else if (edges[u.getName()][v.getName()] != null) {
			return true;
		}
		return false;
	}
}
