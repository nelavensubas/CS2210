/*
 * Assignment 4: This is the class will implement an ordered dictionary
 * using a binary search tree.
 */

package prjAsn4;

public class BinarySearchTree implements BinarySearchTreeADT {
	/*
	 * The root of the binary search tree.
	 */
	private BinaryNode root;

	/*
	 * Constructor that will create a tree whose root is a leaf node.
	 */
	public BinarySearchTree() {
		root = new BinaryNode();
	}

	/*
	 * get method will give the pixel storing the given key if it is stored in the
	 * tree.
	 * 
	 * @param r the node
	 * 
	 * @param key the location of the pixel
	 * 
	 * @return the pixel that stores the given key
	 */
	@Override
	public Pixel get(BinaryNode r, Location key) {
		return getNode(r, key).getData();
	}

	/*
	 * put method will insert the given pixel into the tree.
	 * 
	 * @param r the node
	 * 
	 * @param key the location of the pixel
	 */
	@Override
	public void put(BinaryNode r, Pixel data) throws DuplicatedKeyException {
		// Figure out the position of where the pixel should be inserted
		BinaryNode node = getNode(r, data.getLocation());

		if (!node.isLeaf()) {
			// Throw this exception if the node is not a leaf
			throw new DuplicatedKeyException();
		} else {
			/*
			 * Store the pixel by setting the data into the node and setting the parent of
			 * each child equal to this node
			 */
			node.setData(data);
			BinaryNode left = new BinaryNode();
			BinaryNode right = new BinaryNode();
			node.setLeft(left);
			node.setRight(right);
			left.setParent(node);
			right.setParent(node);
		}
	}

	/*
	 * remove method will remove the given data item with its given key.
	 * 
	 * @param r the node
	 * 
	 * @param key the location of the key
	 */
	@Override
	public void remove(BinaryNode r, Location key) throws InexistentKeyException {
		// Figure out the right node
		BinaryNode node = getNode(r, key);

		if (node.isLeaf()) {
			// Throw this exception if the pixel doesn't exist
			throw new InexistentKeyException();
		} else {
			// Check to see if at least one of the children is a leaf
			if (node.getLeft().isLeaf() || node.getRight().isLeaf()) {
				// Leaf child is a leaf
				if (node.getLeft().isLeaf()) {
					BinaryNode right = node.getRight();
					BinaryNode parent = node.getParent();

					// If the node is root, set a new root
					if (parent == null) {
						this.root = right;
						right.setParent(null);
						// Otherwise, the parent will look at the child that's not a leaf
					} else {
						if (parent.getRight().equals(node)) {
							parent.setRight(right);
							right.setParent(parent);
						} else {
							parent.setLeft(right);
							right.setParent(parent);
						}
					}
					// Otherwise, the right child is a leaf
				} else {
					BinaryNode left = node.getLeft();
					BinaryNode parent = node.getParent();

					// If the node is root, set a new root
					if (parent == null) {
						this.root = left;
						left.setParent(null);
						// Otherwise, the parent will look at the child that's not a leaf
					} else {
						if (parent.getRight().equals(node)) {
							parent.setRight(left);
							left.setParent(parent);
						} else {
							parent.setLeft(left);
							left.setParent(parent);
						}
					}
				}
			} else {
				// Find the smallest node of its subtree if the node is internal and replace it
				BinaryNode p = node.getRight();
				while (!p.isLeaf()) {
					p = p.getLeft();
				}
				p = p.getParent();

				// Replace the data with the smallest node of the subtree data
				node.setData(p.getData());
				BinaryNode smallParent = p.getParent();

				// Set parent to point to smallest node
				if (smallParent.getLeft().equals(p)) {
					smallParent.setLeft(p.getRight());
				} else {
					smallParent.setRight(p.getRight());
				}
				p.getRight().setParent(smallParent);
			}
		}
	}

	/*
	 * successor method will give the pixel with the smallest key larger than the
	 * given one.
	 * 
	 * @param r the node
	 * 
	 * @param key the key the stores the location of the pixel
	 * 
	 * @return the pixel with the smallest key compared to the one given
	 */
	@Override
	public Pixel successor(BinaryNode r, Location key) {
		if (r.isLeaf()) {
			return null;
		} else {
			// Find the node that is at least larger than the current node
			BinaryNode p = getNode(r, key);

			/*
			 * If the right child is not a leaf, find the smallest of its right subtree
			 */
			if (!p.isLeaf() && !p.getRight().isLeaf()) {
				p = p.getRight();
				while (!p.isLeaf()) {
					p = p.getLeft();
				}

				return p.getParent().getData();
				// Go through its parents
			} else {
				BinaryNode n = p.getParent();
				// Look at the closet parent that the right child is
				while (n != null && n.getRight() == p) {
					p = n;
					n = n.getParent();
				}

				if (n != null) {
					return n.getData();
				} else {
					return null;
				}
			}
		}
	}

	/*
	 * predecessor method will give the pixel with the largest key smaller than the
	 * given one.
	 * 
	 * @param r the node
	 * 
	 * @param key the key the stores the location of the pixel
	 * 
	 * @return the pixel with the largest key compared to the one given
	 */
	@Override
	public Pixel predecessor(BinaryNode r, Location key) {
		if (r.isLeaf()) {
			return null;
		} else {
			// Find the node who is at least smaller than the current node
			BinaryNode n = getNode(r, key);
			// If the right child is not a leaf, find the largest of its left sub tree
			if (!n.isLeaf() && !n.getLeft().isLeaf()) {
				// Get the most right leaf's parent
				BinaryNode p = n.getLeft();
				while (!p.isLeaf()) {
					p = p.getRight();
				}

				return p.getParent().getData();
			} else {
				// Otherwise, go through its parents
				BinaryNode node = n.getParent();
				while (node != null && node.getLeft() == n) {
					n = node;
					node = node.getParent();
				}

				if (node == null) {
					return null;
				} else {
					return node.getData();
				}
			}
		}
	}

	/*
	 * smallest method will give the pixel with the smallest key.
	 * 
	 * @param r the node
	 * 
	 * @return the pixel with the smallest key
	 */
	@Override
	public Pixel smallest(BinaryNode r) throws EmptyTreeException {
		if (r.isLeaf()) {
			throw new EmptyTreeException();
		} else {
			// Get the most left node from parent's pixel
			BinaryNode n = r;
			while (!n.isLeaf()) {
				n = n.getLeft();
			}

			return n.getParent().getData();
		}
	}

	/*
	 * largest method will give the pixel with the largest key.
	 * 
	 * @param r the node
	 * 
	 * @return the pixel with the largest key
	 */
	@Override
	public Pixel largest(BinaryNode r) throws EmptyTreeException {
		if (this.getRoot().isLeaf()) {
			throw new EmptyTreeException();
		} else {
			// Get the most right node from parent's pixel
			BinaryNode n = r;
			while (!n.isLeaf()) {
				n = n.getRight();
			}

			return n.getParent().getData();
		}
	}

	/*
	 * getRoot method will give the root of the binary search tree.
	 * 
	 * @return the root
	 */
	@Override
	public BinaryNode getRoot() {
		return root;
	}

	/*
	 * getNode will help find the right position.
	 * 
	 * @param r the node
	 * 
	 * @param key the location of the pixel
	 * 
	 * @return the pixel that stores the given key
	 */
	private BinaryNode getNode(BinaryNode r, Location key) {
		// if the node is a leaf, then return the node
		if (r.isLeaf()) {
			return r;
		}

		// Compare the location to this internal node
		int compareLoc = r.getData().getLocation().compareTo(key);

		// Location is equal to this internal node
		if (compareLoc == 0) {
			return r;
			// Location is greater than this node, go to the right subtree
		} else if (compareLoc == 1) {
			return getNode(r.getLeft(), key);
			// Otherwise, go to the left subtree
		} else {
			return getNode(r.getRight(), key);
		}
	}

}
