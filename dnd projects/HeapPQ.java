
public class HeapPQ<E extends Comparable<E>> implements MyPriorityQueue<E> {

	private E[] heap;
	private int objectCount;
	
	public HeapPQ()
    {
        this.heap = (E[])new Comparable[3];
        this.objectCount = 0;
    }

	//Adds obj to the Priority Queue
	public void add(E obj)
	{
		objectCount++;
		if (objectCount > heap.length) {
			increaseCapacity();
		}
		heap[objectCount - 1] = obj;
		bubbleUp(objectCount-1);
	}
	
	//Removes and returns the MINIMUM element from the Priority Queue
	public E removeMin()
	{	
		E min = heap[0];
		swap(0, objectCount - 1);
		objectCount--;
		heap[objectCount] = null;
		bubbleDown(0);
		return min;
	}
	
	//Returns the MINIMUM element from the Priority Queue without removing it
	public E peek()
	{
		return heap[0];
	}
	
	// Returns true if the priority queue is empty
	public boolean isEmpty()
	{
		return (objectCount <= 0);
	}
	
	//Returns the number of elements in the priority queue
	public int size()
	{
		return objectCount;
	}
	
	public String toString()
	{
		StringBuffer stringbuf = new StringBuffer("[");
		for (int i = 0; i < objectCount; i++)
		{
			stringbuf.append(heap[i]);
			if (i < objectCount - 1)
				stringbuf.append(", ");
		}
		stringbuf.append("]\nor alternatively,\n");

		for(int rowLength = 1, j = 0; j < objectCount; rowLength *= 2)
		{
			for (int i = 0; i < rowLength && j < objectCount; i++, j++)
			{
				stringbuf.append(heap[j] + " ");
			}
			stringbuf.append("\n");
			if (j < objectCount)
			{
				for (int i = 0; i < Math.min(objectCount - j, rowLength*2); i++)
				{
					if (i%2 == 0)
						stringbuf.append("/");
					else
						stringbuf.append("\\ ");
				}
				stringbuf.append("\n");
			}
		}
		return stringbuf.toString();
	}
	
	//Doubles the size of the heap array
	private void increaseCapacity()
	{	
		E[] newArr = (E[])new Comparable[objectCount*2];
		for (int i = 0; i < objectCount - 1; i++) {
			newArr[i] = heap[i];
		}
		heap = newArr;
	}

	//Returns the index of the "parent" of index i
	public int parent(int i)
	{
		return ((i-1) / 2);
	}
	//Returns the index of the *smaller child* of index i
	private int smallerChild(int i)
	{
		int firstIdx = (2 * i + 1);
		int secondIdx = (2 * i + 2); 
		if (firstIdx >= objectCount || secondIdx >= objectCount) {
			return -1;
		} else {
			if (heap[firstIdx].compareTo(heap[secondIdx]) < 0) {
				return firstIdx;
			} else {
				return secondIdx;
			}
		}
	}
	//Swaps the contents of indices i and j
	private void swap(int i, int j)
	{
		E temp = heap[i];
		heap[i] = heap[j];
		heap[j] = temp;
	}

	// Bubbles the element at index i upwards until the heap properties hold again.
	private void bubbleUp(int i)
	{
		int parentIdx = parent(i);
		if (parentIdx >= 0 && heap[i].compareTo(heap[parentIdx]) < 0) {
			swap (i, parentIdx);
			bubbleUp(parentIdx);
		}
	}
	
	// Bubbles the element at index i downwards until the heap properties hold again.
	private void bubbleDown(int i)
	{
		int smallerIdx = smallerChild(i);
		if (smallerIdx >= 0 && heap[i].compareTo(heap[smallerIdx]) > 0) {
			swap(i, smallerIdx);
			bubbleDown(smallerIdx);
		}
	}

}
