import java.util.ArrayList;
/* See ArrayList documentation here:
 * http://docs.oracle.com/javase/7/docs/api/java/util/ArrayList.html
 */

 //if (________)
		//throw new
			//IndexOutOfBoundsException;

/*
 * Your indexed functions should throw IndexOutOfBoundsException if index is invalid!
 */

public class MyArrayList<E> {

	/* Internal Object counter */
	protected int objectCount;

	/* Internal Object array */
	protected E [] internalArray;

	/* Constructor: Create it with whatever capacity you want? */
	@SuppressWarnings("unchecked")
	public MyArrayList() {
		this.internalArray = (E[])new Object[0];//E is a type
	}

	/* Constructor with initial capacity */
	@SuppressWarnings("unchecked")
	public MyArrayList(int initialCapacity){
		this.internalArray = (E[])new Object[initialCapacity];//generic
	}

	/* Return the number of active slots in the array list */
	public int size() {
		return objectCount;
	}

	/* Are there zero objects in the array list? */
	public boolean isEmpty() {
		return (objectCount == 0);
	}

	/* Get the index-th object in the list. */
	public E get(int index) {
		if (index > (objectCount - 1)) {
			throw new IndexOutOfBoundsException();
		} else {
			return internalArray[index];
		}
	}

	/* Replace the object at index with obj.  returns object that was replaced. */
	public E set(int index, E obj) {
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
	public boolean contains(E obj) {
		for (int i = 0; i < objectCount; i++) {
			if (internalArray[i] == obj) {
				return true;
			}
		}
		return false;
	}

	/* Insert an object at index */
	@SuppressWarnings("unchecked")
	public void add(int index, E obj) {
		if (index > objectCount) {
			throw new IndexOutOfBoundsException();
		}
		E [] movingValues = shiftingValues(index);
		createNewSize(objectCount+1);
		internalArray[index] = obj;
		int idx = 0;
		for (int i = index + 1; i < objectCount; i++) {
			internalArray[i] = movingValues[idx];
			idx++;
		}
	}

	/* Add an object to the end of the list; returns true */
	@SuppressWarnings("unchecked")
	public boolean add(E obj) {
		createNewSize(objectCount+1);
		internalArray[objectCount-1] = obj;
		return true;
	}

	/* Remove the object at index and shift.  Returns removed object. */
	public E remove(int index) {
		if (index >= objectCount) {
			throw new IndexOutOfBoundsException();
		}
		E [] movingValues = shiftingValues(index+1);
		E returnValue = internalArray[index];
		int idx = 0;
		createNewSize(objectCount-1);
		for (int i = index; i < objectCount; i++) {
			internalArray[i] = movingValues[idx];
			idx++;
		}
		return returnValue;
		
	}

	/* Removes the first occurrence of the specified element from this list, 
	 * if it is present. If the list does not contain the element, it is unchanged. 
	 * More formally, removes the element with the lowest index i such that
	 * (o==null ? get(i)==null : o.equals(get(i))) (if such an element exists). 
	 * Returns true if this list contained the specified element (or equivalently, 
	 * if this list changed as a result of the call). */
	public boolean remove(E obj) {
		for (int i = 0; i < objectCount; i++) {
			if (internalArray[i] == obj) {
				remove(i);
				return true;
			}
		}
		return false;
	}

	// This method will search list for all occurrences of obj and move them to the end
	// of the list without disrupting the order of the other elements.
	public void moveToBack(E obj)
	{
		int limit = objectCount;
		for (int i = 0; i < limit; i++) {
			if (internalArray[i] == obj) {
				remove(i);
				add(obj);
				limit--;
				i--;
			}
		}
	}

	/* For testing; your string should output as "[X, X, X, X, ...]" where X, X, X, X, ... are the elements in the ArrayList.
	 * If the array is empty, it should return "[]".  If there is one element, "[X]", etc.
	 * Elements are separated by a comma and a space. */
	public String toString() {
		if (internalArray.length == 0) {
			return "[]";
		}
		String toString = "[";
		for (int i = 0; i < internalArray.length; i++) {
			toString += (internalArray[i] + ", ");
		}
		return toString.substring (0, toString.length()-2) + "]";
	}
	
	//
	public E[] shiftingValues(int index) {
		E [] movingValues = (E[])new Object[objectCount-index];
		for (int i = index; i < objectCount; i++) {
			movingValues[i-index] = internalArray[i];
		}
		return movingValues;
	}
	
	public void createNewSize(int size) {
		E[] newArray = (E[])new Object[size];
		int min = -1;
		if (size >= objectCount) {
			min = objectCount;
		} else {
			min = size;
		}
		for (int i = 0; i < min; i++) {
			newArray[i] = internalArray[i];
		}
		internalArray = newArray;
		objectCount = size;
	}
}