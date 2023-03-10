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
        if (list == null) {
            return null;
        }

        SelfOrderingList<T> newList = list.getBlankList();
        if (list.head == null) {
            return newList;
        }
        Node<T> currNode = list.head;

        while (currNode.next != null) {
            currNode = currNode.next;
        }
        while (currNode != null) {
            newList.insert(currNode.data);
            currNode = currNode.prev;
        }
        return newList;
    }
    
    @Override
    public boolean contains(T data) {
        Node<T> currNode = list.head;
        while (currNode!= null) {
            if (currNode.data.compareTo(data) == 0) {
                return true;
            }
            currNode = currNode.next;            
        }
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
        if (list == null) {
            return null;
        }
        if (list.head == null) {
            return null;
        }
        int i = 0;
        Node<T> currNode = list.head;
        while (i < pos && currNode != null) {
            currNode = currNode.next;
            i++;
        }
        if (pos == i) {
            return currNode;
        }
        return null;
    }
    
    @Override
    public Node<T> find(T data) {
        Node<T> currNode = list.head;
        while (currNode!= null) {
            if (currNode.data.compareTo(data) == 0) {
                return currNode;
            }
            currNode = currNode.next;            
        }
        return null;
    }
    
    @Override
    public int size() {
        if(list.head == null){
            return 0;
        }
        Node<T> currNode = list.head;
        int size = 0;
        while (currNode != null) {
            size++;
            currNode = currNode.next;
        }
        return size;
    }

    @Override
    public SelfOrderingList<T> clone(SelfOrderingList<T> otherList) {
        SelfOrderingList<T> newList = otherList.getBlankList();
        if (otherList.head == null) {
            return newList;
        }
        Node<T> currNode = otherList.head;
        while (currNode != null) {
            newList.insert(currNode.data);
            currNode = currNode.next;
        }
        return newList;
    }
}