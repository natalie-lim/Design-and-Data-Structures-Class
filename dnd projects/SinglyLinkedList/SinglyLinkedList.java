import java.util.Objects;

// Implements a singly-linked list.


public class SinglyLinkedList<E> {
	private ListNode<E> head;
	private ListNode<E> tail;
	private int nodeCount;

	// Constructor: creates an empty list
	public SinglyLinkedList() {
		this.head = null;
		this.tail = null;
		this.nodeCount = 0;
	}

	// Constructor: creates a list that contains
	// all elements from the array values, in the same order
	public SinglyLinkedList(Object[] values) {
		if (values == null) {
			this.head = null;
			this.tail = null;
			this.nodeCount = 0;
		} else {
			for (int i = 0; i < values.length; i++) {
				add ((E)values[i]);
			}
		}
	}
	
	public ListNode<E> getHead() {
		return head;
	}
	
	public ListNode<E> getTail() {
		return tail;
	}

	// Returns true if this list is empty; otherwise returns false.
	public boolean isEmpty() {
		return (nodeCount <= 0);
	}

	// Returns the number of elements in this list.
	public int size() {
		return nodeCount;
	}

	// Returns true if this list contains an element equal to obj;
	// otherwise returns false.
	public boolean contains(E obj) {
		for (ListNode<E> node = head; node != null; node = node.getNext()) {
			if (Objects.equals(node.getValue(), obj)) {
				return true;
			}
		}
		return false;
	}

	// Returns the index of the first element in equal to obj;
	// if not found, returns -1.
	public int indexOf(E obj) {
		int index = 0;
		for (ListNode<E> node = head; node != null; node = node.getNext()) {
			if (Objects.equals(node.getValue(), obj)) {
				return index;
			}
			index++;
		}
		return -1;
	}

	// Adds obj to this collection.  Returns true if successful;
	// otherwise returns false.
	public boolean add(E obj) {
		ListNode<E> added = new ListNode<E>(obj);
		if (nodeCount == 0) {
			this.head = added;
			this.tail = added;
		} else {
			tail.setNext(added);
			this.tail = added;
		}
		nodeCount++;
		return (Objects.equals(tail, added));
	}

	// Removes the first element that is equal to obj, if any.
	// Returns true if successful; otherwise returns false.
	public boolean remove(E obj) {
		if (Objects.equals(head.getValue(), obj)) {
			head = head.getNext();
			nodeCount--;
			return true;
		} else {
			for (ListNode<E> node = head; node != null; node = node.getNext()) {
				if (Objects.equals(node.getNext(), null)) {
					return false;
				}
				if (Objects.equals(node.getNext().getValue(), obj)) {
					if (Objects.equals(node.getNext(), tail)) {
						tail = node;
						node.getNext().setNext(null);
					}
					node.setNext(node.getNext().getNext());
					nodeCount--;
					return true;
				}
			}
		}
		return false;
	}

	// Returns the i-th element.               
	public E get(int i) {
		if (i >= nodeCount) {
			throw new IndexOutOfBoundsException();
		}
		return getNode(i).getValue();
	}

	// Replaces the i-th element with obj and returns the old value.
	public E set(int i, Object obj) {
		if (i >= nodeCount) {
			throw new IndexOutOfBoundsException();
		}
		E returnValue = getNode(i).getValue();
		getNode(i).setValue((E) obj);
		return returnValue;
	}

	// Inserts obj to become the i-th element. Increments the size
	// of the list by one.
	public void add(int i, Object obj) {
		if (i > nodeCount) {
			throw new IndexOutOfBoundsException();
		}
		if (i == 0) {
			head = new ListNode<E> ((E) obj, head);
		} else if (i == nodeCount) {
			this.add((E) obj);
		} else {
			ListNode<E> added = new ListNode<E>((E) obj, getNode(i));
			getNode(i-1).setNext(added);
		}
		nodeCount++;
	}

	// Removes the i-th element and returns its value.
	// Decrements the size of the list by one.
	public E remove(int i) {
		if (i >= nodeCount) {
			throw new IndexOutOfBoundsException();
		}
		E returnValue = null;
		nodeCount--;
		if (i == 0) {
			returnValue = head.getValue();
			head = head.getNext();
		} else if (i == nodeCount - 1) {
			returnValue = tail.getValue();
			getNode(i-1).setNext(null);
			tail = getNode(i-1);
		} else {
			returnValue = (getNode(i).getValue());
			getNode(i-1).setNext(getNode(i).getNext());
		}
		return returnValue;
	}

	// Returns a string representation of this list exactly like that for MyArrayList.
	public String toString() {
		if (isEmpty()) {
			return "[]";
		}
		StringBuilder sb = new StringBuilder ("[");
		for (ListNode<E> node = head; node != null; node = node.getNext()) {
			sb.append(node.getValue() + ", ");
		}
		return sb.substring(0, sb.length() - 2) + "]";
	}
	
	private ListNode<E> getNode(int i) {
		if (i >= nodeCount) {
			throw new IndexOutOfBoundsException();
		}
		int index = 0;
		for (ListNode<E> node = head; node != null; node = node.getNext()) {
			if (index == i) {
				return node;
			}
			index++;
		}
		return null;
	}

}
