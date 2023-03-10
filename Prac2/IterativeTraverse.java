public class IterativeTraverse<T extends Comparable<T>> extends Traverser<T> {
    public IterativeTraverse() {
        list = null;
    };

    public IterativeTraverse(SelfOrderingList<T> list) {
        if (list == null) {
            this.list = null;
            return;
        }
        this.list = list.getBlankList();
        if (list.head == null) {
            this.list.head = null;
            return;
        }
        Node<T> currNode = list.head;
        while (currNode != null) {
            this.list.insert(currNode.data);
            currNode = currNode.next;
        }
    }

    @Override
    public SelfOrderingList<T> reverseList() {
        return null;
    }
    
    @Override
    public boolean contains(T data) {
        return false;
    }
    
    @Override
    public String toString() {
        if (list.head == null) {
            return "";
        }
        Node<T> currNode = list.head;
        String ret = "";
        
        while (currNode != null) {
            ret += "->" + currNode;
            currNode = currNode.next;
        }
        return ret;
    }
    
    @Override
    public Node<T> get(int pos) {
        return null;
    }
    
    @Override
    public Node<T> find(T data) {
        return null;
    }
    
    @Override
    public int size() {
        return -1;
    }

    @Override
    public SelfOrderingList<T> clone(SelfOrderingList<T> otherList) {
        return null;
    }
}