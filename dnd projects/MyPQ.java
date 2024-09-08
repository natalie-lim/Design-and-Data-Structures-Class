import java.util.ArrayList; 

public class MyPQ <E extends Comparable<E>> implements MyPriorityQueue<E> {
	ArrayList pQueue;
	E minimum;
	int minIdx;
	public MyPQ () {
		pQueue = new ArrayList();
		minIdx = -1;
		minimum = (E) pQueue.get(0);
	}
	public void add (E obj) {
		pQueue.add(obj);
	}
	public E removeMin() {
		if (isEmpty()) {
			return null;
		} else {
			for (int i = 1; i < pQueue.size(); i++) {
				if (minimum.compareTo((E) pQueue.get(i)) < 0) {
					minimum = (E) pQueue.get(i);
					minIdx = i;
				}
			}
			pQueue.remove(minIdx);
			return minimum;
		}		
	}
	public E peek () {
		return minimum;
	}
	public boolean isEmpty() {
		return (pQueue.size() == 0);
	}
}