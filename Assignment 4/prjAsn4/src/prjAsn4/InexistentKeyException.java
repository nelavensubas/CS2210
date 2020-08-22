/*
 * Assignment 4: This is the class that will represent the exceptions thrown by the
 * insert, remove, smallest and largest methods.
 */

package prjAsn4;

public class InexistentKeyException extends RuntimeException {
	public InexistentKeyException() {
		super("Inexisted Key Error");
	}
}
