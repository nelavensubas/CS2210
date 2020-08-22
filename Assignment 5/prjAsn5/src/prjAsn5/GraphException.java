/*
 * Assignment 5: This class will represent the exceptions thrown when a node doesn't 
 * exist.
 */
package prjAsn5;

public class GraphException extends RuntimeException {
	public GraphException() {
		super("Graph Error");
	}
}
