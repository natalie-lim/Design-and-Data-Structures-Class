import java.util.Objects;

public class DoublyLinkedList {
	// Implements a circular doubly-linked list.

	private ListNode2<Nucleotide> head;
	private ListNode2<Nucleotide> tail;
	private int nodeCount;

	// Constructor: creates an empty list
	public DoublyLinkedList() {
		head = null;
		tail = null;
		nodeCount = 0;
	}

	// Constructor: creates a list that contains
	// all elements from the array values, in the same order
	public DoublyLinkedList(Nucleotide[] values) {
		if (values == null) {
			this.head = null;
			this.tail = null;
			this.nodeCount = 0;
		} else {
			for (int i = 0; i < values.length; i++) {
				add ((Nucleotide)values[i]);
			}
		}
	}
	
	public ListNode2<Nucleotide> getHead() {
		return head;
	}
	
	public ListNode2<Nucleotide> getTail() {
		return tail;
	}


	// Returns true if this list is empty; otherwise returns false.
	public boolean isEmpty() {
		return (nodeCount == 0);
	}

	// Returns the number of elements in this list.
	public int size() {
		return nodeCount;
	}

	// Returns true if this list contains an element equal to obj;
	// otherwise returns false.
	public boolean contains(Nucleotide obj) {
		if (nodeCount == 0) {
			return false;
		}
		for (ListNode2<Nucleotide> node = head; node != null; node = node.getNext()) {
			if (Objects.equals(node.getValue(), obj)) {
				return true;
			}
		}
		return false;
	}

	// Returns the index of the first element in equal to obj;
	// if not found, returns -1.
	public int indexOf(Nucleotide obj) {
		int idx = 0;
		for (ListNode2<Nucleotide> node = head; node != null; node = node.getNext()) {
			if (Objects.equals(node.getValue(), obj)) {
				return idx;
			}
			idx++;
		}
		return -1;
	}

	// Adds obj to this collection.  Returns true if successful;
	// otherwise returns false.
	public boolean add(Nucleotide obj) {
		ListNode2<Nucleotide> added = new ListNode2<Nucleotide>(obj);
		if (nodeCount == 0) {
			head = added;
			tail = added;
		} else {
			tail.setNext(added);
			added.setPrevious((tail));
			tail = added;
		}
		nodeCount++;
		return (Objects.equals(tail.getValue(), obj));
	}

	// Removes the first element that is equal to obj, if any.
	// Returns true if successful; otherwise returns false.
	public boolean remove(Nucleotide obj) {
		if (!contains(obj)) {
			return false;
		}
		if (nodeCount == 0) {
			return false;
		} else if (Objects.equals(head.getValue(), obj)) {
			if (nodeCount==1) {
				head = null;
				tail = null;
				nodeCount = 0;
				return true;
			}
			head.getNext().setPrevious(null);
			head = head.getNext();
			nodeCount--;
			return true;
		} else {
			for (ListNode2<Nucleotide> node = head; node != null; node = node.getNext()) {
				if (node != null && Objects.equals(node.getValue(), obj)) {
					if (node == tail) {
						tail.getPrevious().setNext(null);
						tail = tail.getPrevious();
					} else {
						node.getPrevious().setNext(node.getNext());
						node.getNext().setPrevious(node.getPrevious());
					}
					nodeCount--;
					return true;
				}
			}
		}
		return false;
	}

	// Returns the i-th element.               
	public Nucleotide get(int i) {
		if (i>= nodeCount) {
			throw new IndexOutOfBoundsException();
		}
		return ((Nucleotide) getNode(i).getValue());
	}

	// Replaces the i-th element with obj and returns the old value.
	public Nucleotide set(int i, Nucleotide obj) {
		Nucleotide returnValue = get(i);
		getNode(i).setValue(obj);
		return returnValue;
	}

	// Inserts obj to become the i-th element. Increments the size
	// of the list by one.
	public void add(int i, Nucleotide obj) {
		if (i > nodeCount) { 
			throw new IndexOutOfBoundsException();
		}
		nodeCount++;
		if (i == 0) {
			ListNode2 newNode = new ListNode2 (obj);
			head.setPrevious (newNode);
			newNode.setNext(head);
			head = newNode;
		} else if (i == nodeCount-1) {
			this.add(obj);
		} else {
			ListNode2 newNode = new ListNode2 (obj, getNode (i-1), getNode(i));
			getNode(i-1).setNext(newNode);
			getNode(i+1).setPrevious(newNode);
		}
	}

	// Removes the i-th element and returns its value.
	// Decrements the size of the list by one.
	public Nucleotide remove(int i) {
		if (i < 0 || i >= nodeCount) {
			throw new IndexOutOfBoundsException(); 
		}
		Nucleotide returnValue = (Nucleotide) (getNode(i).getValue());
		if (i == 0) {
			head.getNext().setPrevious(null);
			head = head.getNext();
		} else if (i == (nodeCount-1)) {
			tail.getPrevious().setNext(null);
			tail = tail.getPrevious();
		} else{
			ListNode2<Nucleotide> previous = getNode(i-1);
			ListNode2<Nucleotide> next = getNode(i+1);;
			previous.setNext(next);
			next.setPrevious(previous);
		}
		nodeCount--;
		return returnValue;
	}

	// Returns a string representation of this list exactly like that for MyArrayList.
	public String toString() {
		if (isEmpty()) {
			return "[]";
		}
		StringBuilder sb = new StringBuilder ("[");
		for (ListNode2<Nucleotide> node = head; node != null; node = node.getNext()) {
			sb.append(node.getValue() + ", ");
		}
		return sb.substring(0, sb.length() - 2) + "]";
	}
	
	public String detailedToString() {
		if (isEmpty()) {
			return "[]";
		}
		StringBuilder sb = new StringBuilder ("[");
		StringBuilder sbackwards = new StringBuilder ("[");
		for (ListNode2<Nucleotide> node = head; node != null; node = node.getNext()) {
			sb.append(node.getValue() + ", ");
		}
		for (ListNode2<Nucleotide> node = tail; node != null; node = node.getPrevious()) {
			sbackwards.append(node.getValue() + ", ");
		}
		return (sb.substring(0, sb.length() - 2) + "]" + "\nhead = " + getHead().getValue() + "\ntail = " 
		+ getTail().getValue() + "\nnodeCount = " + nodeCount + "\nbackwards: " + sbackwards + "]\n");
	}
	
	public ListNode2 getNode(int i) {
		int idx = 0;
		for (ListNode2<Nucleotide> node = head; node != null; node = node.getNext()) {
			if (idx == i) {
				return node;
			}
			idx++;
		}
		return null;
	}
	// Like question 7 on the SinglyLinkedList test:
	// Add a "segment" (another list) onto the end of this list
	public void addSegmentToEnd(DoublyLinkedList seg) {
		if (seg != null) {
			ListNode2<Nucleotide> newTail = seg.getTail();
			this.tail.setNext(seg.getHead());
			seg.getHead().setPrevious(this.tail);
			tail = newTail;
			nodeCount += seg.size();
		}
	}
	
	// Like question 8 on the SinglyLinkedList test:
	// You are to remove the next 16 nodes from the list, after the node nodeBefore.
	// (on the test these nodes were assumed to contain CCCCCCCCGGGGGGGG, but here
	// you do not need to assume or check for that)
	public void removeCCCCCCCCGGGGGGGG(ListNode2<Nucleotide> nodeBefore) {
		int idx = 0;
		for (ListNode2<Nucleotide> node = head; node != null; node = node.getNext()) {
			if (node == nodeBefore) {
				if ((idx + 16) >= nodeCount) {
					throw new IndexOutOfBoundsException();
				}
				for (int i = 0; i < 16; i++) {
					remove(idx+1);
				}
			}
			idx++;
		}
	}
	
	// Like question 9 on the SinglyLinkedList test:
	// You are to find and delete the first instance of seg in the list.
	// If seg is not in the list, return false, otherwise return true.
	public boolean deleteSegment(DoublyLinkedList seg) {
		int size = seg.size();
		int idx = 0;
		for (ListNode2<Nucleotide> node = head; node != null; node = node.getNext()) {
			if (Objects.equals(node.getValue(), seg.getNode(0).getValue())) {
				int index = idx;
				boolean containsSeg = true;
				for (int i = 0; i < size; i++) {
					if (!Objects.equals(getNode(index).getValue(), seg.getNode(i).getValue())) {
						containsSeg = false;
					}
					index++;
				}
				if (containsSeg) {
					for (int i = 0; i < size; i++) {
						remove(idx);
					}
					return true;
				} else {
					idx++;
				}
			}
		}
		return false;
	}
	
	// Like question 10 on the SinglyLinkedList test:
	// Delete the last three nodes in the list
	// If there are not enough nodes, return false
	public boolean deleteLastThree() {
		if (size() < 3) {
			return false;
		}
		for (int i = 0; i < 3; i++) {
			remove (size()-1);
		}
		return true;
	}

	// Like question 11 on the SinglyLinkedList test:
	// Replaces every node containing "A" with three nodes containing "T" "A" "C"
	public void replaceEveryAWithTAC() {
		int idx = 0;
		for (ListNode2<Nucleotide> node = head; node != null; node = node.getNext()) {
			if (Objects.equals(node.getValue(), Nucleotide.A)) {
				add(idx, Nucleotide.T);
				add(idx+2, Nucleotide.C);
				if (getNode(idx+2) != tail) { 
					idx++;
				}
			}
			idx++;
		}
	}

}
