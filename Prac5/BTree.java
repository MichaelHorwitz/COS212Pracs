import org.w3c.dom.Node;

public class BTree<T extends Comparable<T>> {

	private int m;
	private Node<T> root;

	/**
	 * 
	 * @param m
	 */
	public BTree(int m) {
		this.m = m;
		
		if (m <= 0) {
			throw new UnsupportedOperationException();
		}
	}

	/**
	 * 
	 * @param data
	 */
	public Node<T> insert(T data) {
		if (root.keyTally == 0) {
			root.insertNode(data);
		}
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param data
	 */
	public Node<T> find(T data) {
		// TODO - implement BTree.find
		throw new UnsupportedOperationException();
	}

	public Node<T>[] nodes() {
		// TODO - implement BTree.leaves
		throw new UnsupportedOperationException();
	}

	public int numKeys() {
		// TODO - implement BTree.numKeys
		throw new UnsupportedOperationException();
	}

	public int countNumNodes() {
		// TODO - implement BTree.countNumLeaves
		throw new UnsupportedOperationException();
	}

}