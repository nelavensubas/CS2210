/*
 * Assignment 5: This class will represent a node of the graph.
 */

package prjAsn5;

public class Node {
	/* Attributes */
	private int name;
	private boolean mark;

	/*
	 * Constructor that will create a node with the given name.
	 * 
	 * @param name an integer value between 0 and n-1, where n is the number of
	 * nodes in the graph
	 */
	public Node(int name) {
		this.name = name;
		this.mark = false;
	}

	/*
	 * setMark method will set the mark for the node.
	 * 
	 * @param mark true or false value to mark the node
	 */
	public void setMark(boolean mark) {
		this.mark = mark;
	}

	/*
	 * getMark method will give the value for the node that has been marked or not.
	 * 
	 * @return true if the node has been marked, otherwise false
	 */
	public boolean getMark() {
		return mark;
	}

	/*
	 * getName method will give the name of the vertex.
	 * 
	 * @return the name of the vertex
	 */
	public int getName() {
		return name;
	}
}
