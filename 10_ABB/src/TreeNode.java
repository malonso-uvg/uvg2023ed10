/**
 * 
 */

/**
 * @author MAAG
 *
 */
public class TreeNode<K, V> {

	private K key;
	private V value;
	
	private TreeNode left;
	private TreeNode right;
	private TreeNode parent;
	
	public TreeNode(K _key, V _value) {
		setKey(_key);
		setValue(_value);
		setLeft(null);
		setRight(null);
		setParent(null);
	}

	/**
	 * @return the key
	 */
	public K getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(K key) {
		this.key = key;
	}

	/**
	 * @return the value
	 */
	public V getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(V value) {
		this.value = value;
	}

	/**
	 * @return the left
	 */
	public TreeNode getLeft() {
		return left;
	}

	/**
	 * @param left the left to set
	 */
	public void setLeft(TreeNode left) {
		this.left = left;
	}

	/**
	 * @return the right
	 */
	public TreeNode getRight() {
		return right;
	}

	/**
	 * @param right the right to set
	 */
	public void setRight(TreeNode right) {
		this.right = right;
	}

	/**
	 * @return the parent
	 */
	public TreeNode getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(TreeNode parent) {
		this.parent = parent;
	}
	
	
}
