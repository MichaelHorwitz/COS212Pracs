public class NaturalOrderList<T extends Comparable<T>> extends SelfOrderingList<T> {
    @Override
    public SelfOrderingList<T> getBlankList() {
        NaturalOrderList<T> ret = new NaturalOrderList<T>();
        return ret;
    }
    
    @Override
    public void access(T data) {
        return;
    }
    
    @Override
    public void insert(T data) {
        Node<T> newNode = new Node<T>(data);
        if (head == null) {
            head = newNode;
            return;
        }
        Node<T> prevNode = null;
        Node<T> currNode = head;
        while (currNode != null && data.compareTo(currNode.data) <= 0) {
            prevNode = currNode;
            currNode = currNode.next;
        }
        if (prevNode == null) {
            head = newNode;
            newNode.next = currNode;
            currNode.prev = newNode;
            return;
        } else if (currNode == null) {
            prevNode.next = newNode;
            newNode.prev = prevNode;
            return;
        }
        prevNode.next = newNode;
        currNode.prev = newNode;
        newNode.prev = prevNode;
        newNode.next = currNode;
    }
}