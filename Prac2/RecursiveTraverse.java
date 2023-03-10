public class RecursiveTraverse<T extends Comparable<T>> extends Traverser<T>{
    
    public RecursiveTraverse(){
        list = null;
    }
    
    public RecursiveTraverse(SelfOrderingList<T> list){
        Node<T> currNode = list.head;
        this.list = list.getBlankList();
        recursiveConstructor(currNode);
    }
    private void recursiveConstructor(Node<T> currNode){
        if (currNode == null) {
            return;
        } else {
            list.insert(currNode.data);
            recursiveConstructor(currNode.next);
        }
    }
    
    @Override
    public SelfOrderingList<T> reverseList() {
        if (list == null) {
            return null;
        }
        Node<T> currNode = list.head;
        SelfOrderingList<T> newList = list.getBlankList();
            
        return recursiveReverseList(currNode, newList);
    }
    private SelfOrderingList<T> recursiveReverseList(Node<T> currNode, SelfOrderingList<T> newList){
        if (currNode == null) {
            return newList;
        }
        recursiveReverseList(currNode.next, newList);
        newList.insert(currNode.data);
        return newList;
    }
    
    @Override
    public boolean contains(T data) {
        Node<T> currNode = list.head;
        return containsRecursive(currNode, data);
    }
    
    private boolean containsRecursive(Node<T> currNode, T data){
        if (currNode == null) {
            return false;
        }
        if (currNode.data.compareTo(data) == 0) {
            return true;
        }
        return containsRecursive(currNode.next, data);
    }

    @Override
    public String toString() {
        if (list.head == null) {
            return "";
        }
        Node<T> currNode = list.head;
        return recursiveToString(currNode);
    }
    private String recursiveToString(Node<T> currNode){
        if (currNode == null) {
            return "";
        } else {
            return "->" + currNode + recursiveToString(currNode.next);
        }
    }
    @Override
    public Node<T> get(int pos) {
        if (list == null) {
            return null;
        }
        Node<T> currNode = list.head;
        return recursiveGet(currNode, pos);
    }
    private Node<T> recursiveGet(Node<T> currNode, int pos){
        if (currNode == null) {
            return null;
        }
        if (pos == 0) {
            return currNode;
        }
        return recursiveGet(currNode.next, pos - 1);
    }

    @Override
    public Node<T> find(T data) {
        if (list == null) {
            return null;
        }
        Node<T> currNode = list.head;
        return recursiveFind(currNode, data);
    }
    private Node<T> recursiveFind(Node<T> currNode, T data){
        if (currNode == null) {
            return null;
        }
        if (currNode.data.compareTo(data) == 0) {
            return currNode;
        }
        return recursiveFind(currNode.next, data);
    }
    @Override
    public int size() {
        Node<T> currNode = list.head;
        return recursiveSize(currNode);
    }
    
    private int recursiveSize(Node<T> currNode){
        if (currNode == null) {
            return 0;
        }
        return recursiveSize(currNode.next) + 1;
    }

    @Override
    public SelfOrderingList<T> clone(SelfOrderingList<T> otherList) {
        if (otherList == null) {
            return null;
        }
        Node<T> currNode = list.head;
        SelfOrderingList<T> newList = list.getBlankList();
        return recursiveClone(newList, otherList, currNode);
    }
    private SelfOrderingList<T> recursiveClone(SelfOrderingList<T> newList, SelfOrderingList<T> otherList, Node<T> currNode){
        if (currNode == null) {
            return newList;
        }
        newList.insert(currNode.data);
        return recursiveClone(newList, otherList, currNode.next);
    }
}
