public class MoveToFrontList<T extends Comparable<T>> extends SelfOrderingList<T> {
    @Override
    public SelfOrderingList<T> getBlankList() {
        MoveToFrontList<T> ret = new MoveToFrontList<>();
        return ret;
    }
    
    @Override
    public void access(T data) {
        if (head == null) {
            return;
        }
        Node<T> currNode = head;
        boolean found = false;
        while (currNode != null && !found) {
            if (currNode.data.compareTo(data) == 0) {
                found = true;
            } else {
                currNode = currNode.next;
            }
        }
        if (!found) {
            return;
        }
        if (currNode.prev == null && currNode.next == null) {
            return;
        } else if (currNode.prev == null){
            currNode.next.prev = currNode.prev;
            head = currNode;
        } else if (currNode.next == null){
            currNode.prev.next = currNode.next;
        } else {
            currNode.prev.next = currNode.next;
            currNode.next.prev = currNode.prev;
        }
        Node<T> newNode = currNode;
        newNode.next = null;
        newNode.prev = null;
        currNode = head;

        head = newNode;
        newNode.next = currNode;
        currNode.prev = newNode;
    }
}
