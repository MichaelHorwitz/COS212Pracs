public class CountList<T extends Comparable<T>> extends SelfOrderingList<T>{
    @Override
    public SelfOrderingList<T> getBlankList() {
        CountList<T> ret = new CountList<T>();
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
        currNode.accessCount++;
        if (currNode.prev == null && currNode.next == null) {
            return;
        } else if (currNode.prev == null){
            return;
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
        Node<T> prevNode = null;
        while (currNode != null && newNode.accessCount <= currNode.accessCount) {
            prevNode = currNode;
            currNode = currNode.next;
        }
        if (prevNode == null) {
            head = newNode;
            newNode.next = currNode;
            currNode.prev = newNode;
            return;
        }
        if (currNode == null) {
            prevNode.next = newNode;
            newNode.prev = prevNode;
            return;
        }
        prevNode.next = newNode;
        currNode.prev = newNode;
        newNode.prev = prevNode;
        newNode.next = currNode;

        /*
        while (currNode != null && currNode.accessCount >= newNode.accessCount) {
            currNode = currNode.next;
        }
        if (currNode == head) {
            head = newNode;
            newNode.next = currNode;
            currNode.prev = newNode;
            return;
        }
        if (currNode.next == null) {
            currNode.next = newNode;
            newNode.prev = currNode;
            return;
        }
        newNode.prev = currNode;
        newNode.next = currNode.next;
        currNode.next = newNode;
        newNode.next.prev = newNode;
        */
    }
}
