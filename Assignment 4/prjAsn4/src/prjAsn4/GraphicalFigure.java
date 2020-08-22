/*
 * Assignment 4: This is the class that will have the 
 * position of a pixel.
 */

package prjAsn4;

public class GraphicalFigure implements GraphicalFigureADT {
	/*
	 * The identifier of the figure, the width and height of the enclosing rectangle
	 * for the figure.
	 */
	private int id, width, height;
	/*
	 * The type of figure.
	 */
	private String type;
	/*
	 * The offset of the figure.
	 */
	private Location pos;
	/*
	 * The binary search tree where the pixels of the figure will be stored.
	 */
	private BinarySearchTree tree;

	/*
	 * Constructor that will create an empty binary search tree where the pixels of
	 * the figure will be stored and initialize the the identifier, the width, the
	 * height, the type and offset of the figure.
	 */
	public GraphicalFigure(int id, int width, int height, String type, Location pos) {
		this.id = id;
		this.width = width;
		this.height = height;
		this.type = type;
		this.pos = pos;
		tree = new BinarySearchTree();
	}

	/*
	 * setType method will set the type of the figure to the specified value.
	 * 
	 * @param type the type of the figure
	 */
	public void setType(String type) {
		this.type = type;
	}

	/*
	 * getWidth method will give the width of the enclosing rectangle for the
	 * figure.
	 * 
	 * @return the width of the enclosing rectangle
	 */
	public int getWidth() {
		return width;
	}

	/*
	 * getHeight method will give the height of the enclosing rectangle for the
	 * figure.
	 * 
	 * @return the height of the enclosing rectangle
	 */
	public int getHeight() {
		return height;
	}

	/*
	 * getType method will give the type of the figure.
	 * 
	 * @return the type of the figure.
	 */
	public String getType() {
		return type;
	}

	/*
	 * getId method will give the id of the figure.
	 * 
	 * @return the id of the figure.
	 */
	public int getId() {
		return id;
	}

	/*
	 * getOffset method will give the offset of the figure.
	 * 
	 * @return the offset of the figure.
	 */
	public Location getOffset() {
		return pos;
	}

	/*
	 * setOffset method will change the offset of the figure.
	 * 
	 * @param value the specified value for the new offset for the figure.
	 */
	public void setOffset(Location value) {
		pos = value;
	}

	/*
	 * addPixel method will insert the pixel into the binary search tree associated
	 * with this figure.
	 * 
	 * @param pix the pixel to insert in the binary search tree
	 */
	public void addPixel(Pixel pix) throws DuplicatedKeyException {
		tree.put(tree.getRoot(), pix);
	}

	/*
	 * findPixel method will look for a pixel within a figure with matching
	 * position.
	 * 
	 * @param p the location of the current pixel
	 * 
	 * @return true if the pixel at the given location is already in the figure,
	 * otherwise false
	 */
	private boolean findPixel(Location p) {
		Pixel pix = tree.get(tree.getRoot(), p);
		if (pix == null) {
			return false;
		}
		return true;
	}

	/*
	 * intersects method will check to see if the figure intersects with the
	 * specified graphical figure.
	 * 
	 * @param gobj the graphical figure to check
	 * 
	 * @return true if the figures intersects, otherwise false
	 */
	public boolean intersects(GraphicalFigure gobj) {
		int x = getOffset().xCoord();
		int y = getOffset().yCoord();
		int x_coord = gobj.getOffset().xCoord();
		int y_coord = gobj.getOffset().yCoord();
		Pixel smallest = null;

		smallest = gobj.tree.smallest(tree.getRoot());

		// Go through the entire tree starting from smallest to largest
		while (tree.successor(tree.getRoot(), smallest.getLocation()) != null) {
			int xVal, yVal;
			xVal = smallest.getLocation().xCoord() + x - x_coord;
			yVal = smallest.getLocation().yCoord() + y - y_coord;
			// Call findPixel on the passed figure using a new position
			if (gobj.findPixel(new Location(xVal, yVal))) {
				return true;
			}
			smallest = tree.successor(tree.getRoot(), smallest.getLocation());
		}

		return false;
	}

}
