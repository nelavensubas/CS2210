/*
 * Assignment 4: This is the class that will have the 
 * position of a pixel.
 */

package prjAsn4;

public class Location {
	/*
	 * The x and y coordinates of the pixel.
	 */
	private int x, y;

	/*
	 * Constructor that will initialize the location of the pixel with specific
	 * coordinates.
	 * 
	 * @param x x coordinate of the pixel
	 * 
	 * @param y y coordinate of the pixel
	 */
	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/*
	 * xCoord method will give the x coordinate of this pixel.
	 * 
	 * @return the x coordinate of this pixel
	 */
	public int xCoord() {
		return x;
	}

	/*
	 * yCoord method will give the y coordinate of this pixel.
	 * 
	 * @return the y coordinate of this pixel
	 */
	public int yCoord() {
		return y;
	}

	/*
	 * compareTo method will compare the location of this pixel with another pixel
	 * using column order.
	 * 
	 * @param p the location of the current pixel
	 * 
	 * @return -1, 0, 1 depending on the coordinates of the two pixels
	 */
	public int compareTo(Location p) {
		if (x < p.xCoord()) {
			return -1;
		} else if (x == p.xCoord()) {
			if (y == p.yCoord()) {
				return 0;
			} else if (y < p.yCoord()) {
				return -1;
			}
		}
		return 1;
	}
}
