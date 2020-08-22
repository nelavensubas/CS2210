/*
 * Assignment 4: This is the class that will represent the exceptions thrown by the
 * insert, remove, smallest and largest methods.
 */

package prjAsn4;

public class DuplicatedKeyException extends RuntimeException {

	public String getMessage() {
		return "Duplicate key";
	}

	public DuplicatedKeyException() {
		super("Duplicate Key Error");
	}

}
