public class Node<T extends Comparable<T>> {

	private Comparable<T>[] keys;
	private Node<T>[] children;
	public int keyTally = 0;
	public boolean leaf = true;
	/**
	 * 
	 * @param m
	 */
	@SuppressWarnings("unchecked")
	public Node(int m) {
		keys = new Comparable<T>[m-1];
		children = new Node<T>[m];
		if (m <= 0) {
			throw new UnsupportedOperationException();
		}
	}

	public void insertNode(Comparable<T> value){
		int indexIns = -1, i=0;
		while (i < m-1 && indexIns == -1) {
			if (value < key[indexIns]) {
				indexIns = i;
			}
			i++;
		}
	}

	@Override
	public String toString() {
		String res = "[";
		for (int i = 0; i < keys.length; i++) {
			if (keys[i] != null)
				res += keys[i];
			else
				res += "null";
			res += ",";
		}
		if (res.length() > 1) {
			res = res.substring(0, res.length() - 1);
		}
		return res + "]";
	}

}