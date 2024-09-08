
public class HNode implements Comparable<HNode> {
	private Integer frequency;
	private Character key;
	private HNode left;
	private HNode right;
	
	public HNode (Character key, Integer frequency) {
		this.frequency = frequency;
		this.key = key;
		this.left = null;
		this.right = null;
	}
	
	public HNode getRight () {
		return this.right;
	}
	
	public HNode getLeft () {
		return this.left;
	}
	
	public boolean hasLeft () {
		return this.left != null;
	}
	
	public boolean hasRight () {
		return this.right != null;
	}
	
	public void setLeft (HNode other) {
		this.left = other;
	}
	
	public void setRight (HNode other) {
		this.right = other;
	}
	
	public int getFrequency () {
		return this.frequency;
	}
	
	public Character getKey() {
		return key;
	}
	
	public int compareTo(HNode other) {
		return this.frequency - other.getFrequency();
	}

}
