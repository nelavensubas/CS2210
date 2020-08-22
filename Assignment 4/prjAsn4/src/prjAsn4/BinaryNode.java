/*
 * Assignment 4: This is the class that will represent the nodes of the
 * binary search tree.
 */

package prjAsn4;

public class BinaryNode {
	/*
	 * The pixel to store inside the node.
	 */
	private Pixel value;
	/*
	 * The left child of the node.
	 */
	private BinaryNode left;
	/*
	 * The right child of the node.
	 */
	private BinaryNode right;
	/*
	 * The parent of the node.
	 */
	private BinaryNode parent;

	/*
	 * Constructor that will store the given pixel inside a nod with references to
	 * its left child, right child and its parent.
	 */
	public BinaryNode(Pixel value, BinaryNode left, BinaryNode right, BinaryNode parent) {
		this.value = value;
		this.left = left;
		this.right = right;
		this.parent = parent;
	}

	/*
	 * Constructor that will initialize a leaf node.
	 */
	public BinaryNode() {
		value = null;
		left = null;
		right = null;
		parent = null;
	}

	/*
	 * getParent method will give the parent of the node.
	 * 
	 * @return the parent of the node.
	 */
	public BinaryNode getParent() {
		return parent;
	}

	/*
	 * setParent method will set the parent of the node to the given value.
	 * 
	 * @param parent the parent of the node
	 */
	public void setParent(BinaryNode parent) {
		this.parent = parent;
	}

	/*
	 * setLeft method will set the left child of the node with the given value.
	 * 
	 * @param p the left child of the node
	 */
	public void setLeft(BinaryNode p) {
		left = p;
	}

	/*
	 * setRight method will set the right child of the node with the given value.
	 * 
	 * @param p the right child of the node
	 */
	public void setRight(BinaryNode p) {
		right = p;
	}

	/*
	 * setData method will store the given pixel in the node.
	 * 
	 * @param value the pixel to store
	 */
	public void setData(Pixel value) {
		this.value = value;
	}

	/*
	 * isLeaf method will figure out this node is leaf or not.
	 * 
	 * @return true if the node is a leaf, otherwise false
	 */
	public boolean isLeaf() {
		return value == null && left == null && right == null;
	}

	/*
	 * getData method will give the pixel stored in the node.
	 * 
	 * @return the pixel stored in the node
	 */
	public Pixel getData() {
		return value;
	}

	/*
	 * getLeft method will give the left child of the node.
	 * 
	 * @return the left child of the node
	 */
	public BinaryNode getLeft() {
		return left;
	}

	/*
	 * getRight method will give the right child of the node.
	 * 
	 * @return the right child of the node
	 */
	public BinaryNode getRight() {
		return right;
	}
}
