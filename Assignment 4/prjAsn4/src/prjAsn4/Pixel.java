/*
 * Assignment 4: This is the class that will represent the
 * data items that will be stored in the binary search tree.
 */

package prjAsn4;

public class Pixel {
	/*
	 * The location of the pixel.
	 */
	private Location p;
	/*
	 * The color of the pixel.
	 */
	private int color;

	/*
	 * Constructor that will initialize the new pixel with its coordinates and
	 * color.
	 * 
	 * @param p the coordinates for the pixel
	 * 
	 * @param color the color of the pixel
	 */
	public Pixel(Location p, int color) {
		this.p = p;
		this.color = color;
	}

	/*
	 * getLocation method will give the location of the pixel.
	 * 
	 * @return the location of the pixel
	 */
	public Location getLocation() {
		return p;
	}

	/*
	 * getColor method will give the color of the pixel.
	 * 
	 * @return the color of the pixel
	 */
	public int getColor() {
		return color;
	}
}
