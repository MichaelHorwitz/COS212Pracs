public class TransposeList<T extends Comparable<T>> extends SelfOrderingList<T> {
    @Override
    public SelfOrderingList<T> getBlankList() {
        TransposeList<T> ret = new TransposeList<T>();
        return ret;
    }
    
    @Override
    public void access(T data) {
        if (head == null) {
            return;
        }
    
        Node<T> currNode = head;
        Node<T> prevNode = null;
        boolean found = false;
    
        while (currNode != null && !found) {
            if (currNode.data == data) {
                found = true;
            } else {
                prevNode = currNode;
                currNode = currNode.next;
            }
        }
        
        if (!found || prevNode == null) {
            return;
        }

        prevNode.prev.next = currNode;
        currNode.next.prev = prevNode;
        currNode.prev = prevNode.prev;
        prevNode.next = currNode.next;
        currNode.next = prevNode;
        prevNode.prev = currNode;

    }
}
