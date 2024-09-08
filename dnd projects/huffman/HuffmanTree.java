import java.util.HashMap;
import java.util.PriorityQueue;

public class HuffmanTree {
	private PriorityQueue<HNode> pq = new PriorityQueue<HNode>();
	private HNode root;
	
	public HuffmanTree (HashMap<Character, Integer> freq) {
		//putting it into a pq
		this.pq = setPQ(freq);
		//creating the tree
		HNode smallest1 = null;
		HNode smallest2 = null;
		
		while (pq.size() > 1) {
			smallest1 = pq.remove();
			smallest2 = pq.remove();
			int parentNum = (smallest1.getFrequency() + smallest2.getFrequency());
			HNode parent = new HNode (null, parentNum);
			parent.setLeft(smallest1);
			parent.setRight(smallest2);
			pq.add(parent);
		}	
		this.root = pq.remove();
	}
	
	public PriorityQueue<HNode> setPQ (HashMap<Character, Integer> freq) {
		PriorityQueue<HNode> queue = new PriorityQueue<HNode>();
		for (HashMap.Entry<Character, Integer> mapElement : freq.entrySet()) {
			char key = mapElement.getKey();
			int value = mapElement.getValue();
			HNode node = new HNode (key, value);
			queue.add(node);
		}
		return queue;
	}
	
	public HNode getRoot () {
		return this.root;
	}
}
