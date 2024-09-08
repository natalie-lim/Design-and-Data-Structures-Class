public class MyHashSet {
	private ListNode[] buckets;
	private int objectCount;
	private double loadFactorLimit;

	// Constructor: creates an empty hash set with default parameters
	public MyHashSet()
	{
		this.buckets = new ListNode[10];
		this.objectCount = 0;
		this.loadFactorLimit = 0.75;
	}

	// Constructor: creates a hash set with the given initial bucket size and load factor limit
	public MyHashSet(int bucketCount, double loadFactorLimit)
	{
		this.buckets = new ListNode[bucketCount];
		this.objectCount = 0;
		this.loadFactorLimit = loadFactorLimit;
	}

	// Return a pointer to the bucket array
	public ListNode[] getBuckets() {
		return this.buckets;
	}

	// Returns true if this set is empty; otherwise returns false.
	public boolean isEmpty()
	{
		return (objectCount == 0);
	}

	// Returns the number of elements in this set.
	public int size()
	{
		return objectCount;
	}
	
	// Return the bucket index for the object
	public int whichBucket(Object obj) {
		return (0x7FFFFFFF & obj.hashCode()) % this.buckets.length;
	}

	// Returns the current load factor (objCount / buckets)
	public double currentLoadFactor() {
		return ((double) objectCount / (double) buckets.length);
	}


	// Return true if the object exists in the set, otherwise false.
	// Use the .equals method to check equality.
	public boolean contains(Object obj) {
		int bucket = whichBucket (obj);
		for (ListNode node = buckets[bucket]; node != null; node = node.getNext()) {
			if (obj.equals(node.getValue())) {
				return true;
			}
		}
		return false;
	}

	// Add an object to the set.
	// If the object already exists in the set you should *not* add another.
	// Return true if the object was added; false if the object already exists.
	// If an item should be added, add it to the beginning of the bucket.
	// After adding the element, check if the load factor is greater than the limit.
	// - If so, you must call rehash with double the current bucket size.
	public boolean add(Object obj) {
		if (contains (obj)) {
			return false;
		}
		objectCount++;
		if (currentLoadFactor() > loadFactorLimit) {
			rehash(buckets.length * 2);
		}
		int bucket = whichBucket (obj);
		ListNode add = new ListNode(obj);
		if (buckets[bucket] == null) {
			buckets[bucket] = add;
		} else {
			add.setNext(buckets[bucket]);
			buckets[bucket] = add;
		}
		return true;
	}

	// Remove the object.  Return true if successful, false if the object did not exist
	public boolean remove(Object obj) {
		if (!contains(obj)) {
			return false;
		}
		int bucket = whichBucket (obj);
		if (buckets[bucket].getValue().equals(obj)) {
			if (buckets[bucket].getNext() == null) {
				buckets[bucket] = null;
			} else {
				buckets[bucket] = buckets[bucket].getNext();
			}
			objectCount--;
			return true;
		}
		for (ListNode node = buckets[bucket]; node != null; node = node.getNext()) {
			if (node.getNext().getValue() == null) {
				return false;
			}
			if (node.getNext().getValue().equals(obj)) {
				if (node.getNext().getNext() == null) {
					node.setNext(null);
				} else {
					node.setNext(node.getNext().getNext());
				}
				objectCount--;
				return true;
			}
		}
		return false;
	}

	// Rehash the set so that it contains the given number of buckets
	// Loop through all existing buckets, from 0 to length
	// rehash each object into the new bucket array in the order they appear on the original chain.
	public void rehash(int newBucketCount) {
		ListNode[] newBuckets = new ListNode[newBucketCount];
		for (int i = 0; i < buckets.length; i++) {
			if (buckets[i] != null) {
				for (ListNode node = buckets[i]; node != null; node = node.getNext()) {
					Object obj = node.getValue();
					ListNode next = new ListNode(obj);
					int newSpot = (0x7FFFFFFF & obj.hashCode()) % newBucketCount;
					if (newBuckets[newSpot] == null) {
						newBuckets[newSpot] = next;
					} else {
						next.setNext(newBuckets[newSpot]);
						newBuckets[newSpot] = next;
					}
				}
			}
		}
		this.buckets = newBuckets;
	}

	// The output should be in the following format:
	// [ #1, #2 | { b#: v1 v2 v3 } { b#: v1 v2 } ]
	// #1 is the objCount
	// #2 is the number of buckets
	// For each bucket that contains objects, create a substring that indicates the bucket index
	// And list all of the items in the bucket (in the order they appear)
	public String toString() 
	{
		StringBuilder sb = new StringBuilder ();
		sb.append("[ " + objectCount + ", " + buckets.length + " | ");
		for (int i = 0; i < buckets.length; i++) {
			if (buckets[i] != null) {
				sb.append("{ b" + i + ": ");
				for (ListNode node = buckets[i]; node != null; node = node.getNext()) {
					sb.append(node.getValue() + " ");
				}
				sb.append("} ");
			}
		}
		sb.append("]");
		return sb.substring(0);
	}

}
