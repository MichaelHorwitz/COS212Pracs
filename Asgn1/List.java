public class List<T extends Comparable<T>> {
    public int length;
    public Node<T> head;

    public List() {
        length = 0;
        head = null;
    }

    public String toString() {
        String ret = "";
        if (head == null) {
            return "";
        }
        Node<T> currNode = head;
        while(currNode.next != null){
            ret += currNode.toString();
            ret += ",";
            currNode = currNode.next;
        }
        ret += currNode.toString();
        return ret;
    }

    public void append(T val) {
        Node<T> newNode = new Node<T>(val);
        if (head == null) {
            head = newNode;
            return;
        }
        Node<T> currNode = head;
        while (currNode.next != null) {
            currNode = currNode.next;
        }
        currNode.next = newNode;
    }

    public boolean remove(T val) {
        if (head == null) {
            return false;
        }
        Node<T> currNode = head, prevNode = null;
        while (currNode != null && currNode.data.compareTo(val) != 0) {
            prevNode = currNode;
            currNode = currNode.next;
        }
        if (currNode == null) {
            return false;
        }
        if (prevNode == null) {
            head = head.next;
            return true;
        }
        prevNode.next = currNode.next;
        return true;
    }

    public boolean remove(List<T> val) {
        if (head == null) {
            return false;
        }
        boolean anyRemoved = false;
        Node<T> currNode = head;
        while (currNode != null) {
            if(this.remove(currNode.data)){
                anyRemoved = true;
            }
            currNode = currNode.next;
        }
        return anyRemoved;
    }

    public boolean contains(T search) {
        return false;
    }

    public boolean equals(List<T> other) {
        return false;
    }
}
