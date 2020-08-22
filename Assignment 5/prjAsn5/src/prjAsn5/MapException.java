/*
 * Assignment 5: This class will represent the exceptions thrown when a map doesn't 
 * exist.
 */

package prjAsn5;

public class MapException extends RuntimeException {
	public MapException() {
		super("Map Error");
	}
}
