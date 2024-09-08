import java.util.ArrayList;
/* See ArrayList documentation here:
 * http://docs.oracle.com/javase/7/docs/api/java/util/ArrayList.html
 * 
 * average case for add
 */

public class MyArrayList<E> {

	protected int objectCount;
	protected E [] internalArray;

	/* Constructor: Create it with whatever capacity you want? */
	@SuppressWarnings("unchecked")
	public MyArrayList() { //O(1)
		this.internalArray = (E[])new Object[100];
	}

	/* Constructor with initial capacity */
	@SuppressWarnings("unchecked") //O(1)
	public MyArrayList(int initialCapacity){
		this.internalArray = (E[])new Object[initialCapacity];//generic
	}

	/* Return the number of active slots in the array list */
	public int size() { //O(1)
		return objectCount;
	}

	/* Are there zero objects in the array list? */
	public boolean isEmpty() { //O(1)
		return (objectCount == 0);
	}

	/* Get the index-th object in the list. */
	public E get(int index) { //O(1)
		if (index >= objectCount) {
			throw new IndexOutOfBoundsException();
		} else {
			return internalArray[index];
		}
	}

	public void deleteAll() {
		while(!isEmpty())
			this.remove(this.size()-1);
	}

	public void deleteAll2() {
		while(!isEmpty())
			this.remove(this.size()-1);
	}

	/* Replace the object at index with obj.  returns object that was replaced. */
	public E set(int index, E obj) { //O(1)
		E returnValue = null;
		if (index >= objectCount) {
			throw new IndexOutOfBoundsException();
		} else {
			returnValue = internalArray[index];
			internalArray[index] = obj;
		}
		return returnValue;
	}

	/* Returns true if this list contains an element equal to obj;
	 otherwise returns false. */
	public boolean contains(E obj) { //O(n)
		for (int i = 0; i < objectCount; i++) {
			if (internalArray[i] == obj || internalArray[i].equals(obj)) {
				return true;
			}
		}
		return false;
	}

	/* Insert an object at index */
	@SuppressWarnings("unchecked")
	public void add(int index, E obj) {//O(n)
		if (index > objectCount) {
			throw new IndexOutOfBoundsException();
		}
		objectCount++;
		increaseCapacity(objectCount);
		for (int i = objectCount; i > index; i--) {
			internalArray[i] = internalArray[i-1];
		}
		internalArray[index] = obj;
	}

	/* Add an object to the end of the list; returns true */
	@SuppressWarnings("unchecked")
	public boolean add(E obj) {//O(n)
		this.add(objectCount, obj);
		return true;
	}

	/* Remove the object at index and shift.  Returns removed object. */
	public E remove(int index) { //O(n)
		//when i = 0, O(n)
		//when i = n-1, O(1)
		//worst case and best case scenario
		if (index >= objectCount) {
			throw new IndexOutOfBoundsException();
		}
		E returnValue = internalArray[index];
		increaseCapacity (objectCount);
		for (int i = index; i < objectCount; i++) {
			internalArray[i] = internalArray[i+1];
		}
		objectCount--;
		return returnValue;
	}

	/* Removes the first occurrence of the specified element from this list, 
	 * if it is present. If the list does not contain the element, it is unchanged. 
	 * More formally, removes the element with the lowest index i such that
	 * (o null ? get(i)==null : o.equals(get(i))) (if such an element exists). 
	 * Returns true if this list contained the specified element (or equivalently, 
	 * if this list changed as a result of the call). */
	public boolean remove(E obj) {//O(n)
		int shift = 0;
		boolean contains = false;
		for (int i = 0; i < objectCount; i++) {
			internalArray[i] = internalArray[i+shift];
			if (internalArray[i] == obj || internalArray[i].equals(obj)) {
				contains = true;
				shift++;
				internalArray[i] = internalArray[i+shift];
				objectCount--;
			}
		}
		return contains;
	}

//	// This method will search list for all occurrences of obj and move them to the end
//	// of the list without disrupting the order of the other elements.
	public void moveToBack (E obj) {
		E[] movingValues = (E[])new Object[objectCount];
		int mvIdx = 0;
		E[] objects = (E[])new Object[objectCount];
		int objIdx = 0;
		for (int i = 0; i < objectCount; i++) {
			if (internalArray[i] == obj || internalArray[i].equals(obj)) {
				objects[objIdx] = internalArray[i];
				objIdx++;
			} else {
				movingValues[mvIdx] = internalArray[i];
				mvIdx++;
			}
		}
		for (int i = 0; i < mvIdx; i++) {
			internalArray[i] = movingValues[i];
		}
		for (int i = mvIdx; i < (mvIdx+objIdx); i++) {
			internalArray[i] = objects[i-mvIdx];
		}
	}

	/* For testing; your string should output as "[X, X, X, X, ...]" where X, X, X, X, ... are the elements in the ArrayList.
	 * If the array is empty, it should return "[]".  If there is one element, "[X]", etc.
	 * Elements are separated by a comma and a space. */
	public String toString() {
		if (isEmpty()) {
			return "[]";
		}
		StringBuilder sb = new StringBuilder ("[");
		for (int i = 0; i < objectCount; i++) {
			sb.append (internalArray[i] + ", ");
		}
		return sb.substring (0, sb.length()-2) + "]";
	}

	private void increaseCapacity(int size) { //O(objectCount) or O(size)
		if (objectCount >= internalArray.length) {
			E[] newArray = (E[])new Object[size * 2];
			for (int i = 0; i < objectCount; i++) {
				newArray[i] = internalArray[i];
			}
			internalArray = newArray;
		}
	}
	
	public void removeRange(int startindex, int endindex) {
		int difference = endindex - startindex;
		for (int i = startindex; i < objectCount; i++) { //O(n-startindex) --> O(n)
			internalArray[i] = internalArray[i+difference];
			objectCount--;
		}
	}
}