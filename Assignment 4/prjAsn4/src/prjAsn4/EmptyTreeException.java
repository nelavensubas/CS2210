/*
 * Assignment 4: This is the class that will represent the exceptions thrown by the
 * insert, remove, smallest and largest methods.
 */

package prjAsn4;

public class EmptyTreeException extends RuntimeException {
	public EmptyTreeException() {
		super("Empty Tree Error");
	}
}
