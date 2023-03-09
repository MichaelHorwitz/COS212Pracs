abstract class SelfOrderingList<T extends Comparable<T>> {
    public Node<T> head = null;

    public void insert(T data){
        Node<T> newNode = new Node<T>(data);
        if(head == null){
            head = newNode;
            return;
        }
        Node<T> currNode = head;
        while (currNode.next != null) {
            currNode = currNode.next;
        }
        currNode.next = newNode;
        newNode.prev = currNode;
    }

    public void remove(T data){
        if (head == null) {
            return;
        }
        Node<T> currNode = head;

        while (currNode != null && currNode.data != data) {
            currNode = currNode.next;
        }
        if (currNode == null) {
            return;
        }
        if (currNode.prev == null) {
            head.next = currNode.next;
            currNode.next.prev = null;
            return;
        }
        if (currNode.next == null) {
            currNode.prev.next = null;
            return;
        }
        Node<T> prev = currNode.prev;
        Node<T> next = currNode.next;
        prev.next = next;
        next.prev = prev;
    }

    public abstract void access(T data);

    public abstract SelfOrderingList<T> getBlankList();

    //REMOVE REMOVE REMOVE
    //PLEASE FOR THE LOVE OF GOD REMOVE
    public String toString(){
        Node<T> currNode = head;
        String ret = "";
        while (currNode.next != null) {
            //System.out.println(currNode);
            ret += currNode + ", ";
            currNode = currNode.next;
        }
        
        ret += currNode;
        ret += "\n";
        
        while (currNode.prev != null) {
            ret += currNode + ", ";
            currNode = currNode.prev;
        }

        ret += currNode;

        return ret;
    }
}
