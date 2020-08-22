/*
 * Assignment 5: This class will represent an edge of the graph.
 */

package prjAsn5;

public class Edge {
	/* Attributes */
	private Node firstEndpoint, secondEndpoint;
	private int type;

	/*
	 * Constructor that will create a edge that can be either a public road, a
	 * private road, or a reward road.
	 * 
	 * @param u the first endpoint of the edge
	 * 
	 * @param v the second endpoint of the edge
	 * 
	 * @param type the type of edge
	 */
	public Edge(Node u, Node v, int type) {
		firstEndpoint = u;
		secondEndpoint = v;
		this.type = type;
	}

	/*
	 * firstEndpoint method will give the first endpoint of the edge.
	 * 
	 * @return the first endpoint of the edge
	 */
	public Node firstEndpoint() {
		return firstEndpoint;
	}

	/*
	 * secondEndpoint method will give the second endpoint of the edge.
	 * 
	 * @return the second endpoint of the edge
	 */
	public Node secondEndpoint() {
		return secondEndpoint;
	}

	/*
	 * getType method will give the type of the edge.
	 * 
	 * @return the type of the edge
	 */
	public int getType() {
		return type;
	}
}
