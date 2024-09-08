import java.util.Objects;

// Implements a BST with BinaryNode nodes

public class MyBST<E extends Comparable<E>> {
	
	private BinaryNode<E> root;
	private BinaryNode<E> parentBye;
	private BinaryNode<E> parentMax;

	// Constructor: creates an empty BST.
	public MyBST() {
		this.root = null;
	}

	public BinaryNode<E> getRoot() {
		return root;
	}
	public BinaryNode<E> getParentBye() {
		return parentBye;
	}
	public BinaryNode<E> getParentMax() {
		return parentMax;
	}

	// Returns true if this BST contains value; otherwise returns false.
	public boolean contains(E value) {
		return helpContains (value, root);
	}
	
	public boolean helpContains (E value, BinaryNode<E> current) {
		if (current == null) {
			return false;
		}
		if (Objects.equals(value, current.getValue())) {
			return true;
		} if (current.getValue().compareTo(value) < 0) {
			if (current.hasRight()) {
				return helpContains(value, current.getRight());
			} else {
				return false;
			}
		} else {
			if (current.hasLeft()) {
				return helpContains(value, current.getLeft());
			} else {
				return false;
			}
		}
	}

	// Adds value to this BST, unless this tree already holds value.
	// Returns true if value has been added; otherwise returns false.
	public boolean add(E value) {
		if (contains(value)) {
			return false;
		} else if (root == null) {
			this.root = new BinaryNode<E>(value);
			return true;
		} else {
			helpAdd(value, root);
			return true;
		}
	}
	
	public boolean helpAdd(E value, BinaryNode<E> current) {
		if (current.getValue().compareTo(value) < 0) {
			if (current.hasRight()) {
				return helpAdd(value, (current.getRight()));
			} else {
				current.setRight(new BinaryNode<E>(value));
				return true;
			}
		} else {
			if (current.hasLeft()) {
				return helpAdd(value, current.getLeft());
			} else {
				current.setLeft(new BinaryNode<E>(value));
				return true;
			}
		}
	}

	// Removes value from this BST.  Returns true if value has been
	// found and removed; otherwise returns false.
	public boolean remove(E value) {
		if (!contains(value)) {
			return false;
		} else {
			BinaryNode<E> bye = byeNode(getRoot(), value);
			BinaryNode<E> rightMax = rightMax (bye.getLeft());
			
			if (bye.hasLeft() && bye.getLeft().equals(rightMax)) {
				parentMax = bye;
			}

			if (bye.equals(root) && !root.hasChildren()) {
				root = null;
				return true;
			}
			
			//first case: remove the bottom
			if (!bye.hasRight() && !bye.hasLeft()) {
				if (parentBye.hasRight() && parentBye.getRight().equals(bye)) {
					parentBye.setRight(null);
				} else {
					parentBye.setLeft(null);
				}
				return true;
			}
			
			//second case: removing the root with only one leg
			if (bye.equals(root) && !(bye.hasLeft() && bye.hasRight())) {
				if (bye.hasLeft()) {
					root = bye.getLeft();
				} else if (bye.hasRight()) {
					root = bye.getRight();
				}
				return true;
			}
			
			//second case: remove with one child
			if (bye.hasLeft() && !bye.hasRight()) {
				if (bye.getLeft().getValue().compareTo(parentBye.getValue()) > 0) {
					parentBye.setRight(bye.getLeft());
				} else {
					parentBye.setLeft(bye.getLeft());
				}
				return true;
			}
			if (bye.hasRight() && !bye.hasLeft()) {
				if (bye.getRight().getValue().compareTo(parentBye.getValue()) > 0) {
					parentBye.setRight(bye.getRight());
				} else {
					parentBye.setLeft(bye.getRight());
				}
				return true;
			}
			
			//third case: remove with two children
			if (bye.hasLeft() && bye.hasRight()) {
				bye.setValue(rightMax.getValue());
				if (rightMax.hasLeft()) {
					if (rightMax.getLeft().getValue().compareTo(parentMax.getValue()) > 0) {
						parentMax.setRight(rightMax.getLeft());
					} else {
						parentMax.setLeft(rightMax.getLeft());
					}
				} else {
					if (parentMax.getValue().compareTo(rightMax.getValue()) < 0) {
						parentMax.setRight(null);
					} else {
						parentMax.setLeft(null);
					}
				}
			}
			return true;
		}
	}
	
	//gets the node that is going to be removed- works
	public BinaryNode<E> byeNode (BinaryNode<E> current, E value) {
		if (current == null) {
			return null;
		}
		if (current.getValue().equals(value)) {
			parentBye = null;
			return current;
		}
		if (current.hasLeft() && current.getLeft().getValue().equals(value)) {
			this.parentBye = current;
			return current.getLeft();
		} if (current.hasRight() && current.getRight().getValue().equals(value)) {
			this.parentBye = current;
			return current.getRight();
		} else {
			if (current.getValue().compareTo(value) > 0) {
				return byeNode (current.getLeft(), value);
			} else {
				return byeNode (current.getRight(),value);
			}
		}
	}
	//gets right maximum- works
	public BinaryNode<E> rightMax (BinaryNode<E> left) {
		if (left == null) {
			return null;
		}
		if (left.hasRight()) {
			if (!left.getRight().hasRight()) {
				parentMax = left;
			}
			return rightMax (left.getRight());
		} else {
			return left;
		}
	}


	// Returns a bracket-surrounded, comma separated list of the contents of the nodes, in order
	// e.g. ["Apple", "Cranberry", "Durian", "Mango"]
	public String toString() {
		return "[" + toStringHelper (root) + "]";
	}
	public String toStringHelper (BinaryNode<E> current) {
		if (current == null) {
			return "";
		}
		if (!current.hasChildren()) {
			return "" + current.getValue();
		} else {
			if (!current.hasLeft()) {
				return current.getValue() + ", " + toStringHelper(current.getRight());
			} else if (!current.hasRight()) {
				return toStringHelper (current.getLeft()) + ", " + current.getValue();
			} else {
				return toStringHelper (current.getLeft()) + ", " + current.getValue() + ", " + toStringHelper (current.getRight());
			}
		}
	}
	
	public String detailedToString() {
		return toString() + "\nroot: " + getRoot();
	}
}