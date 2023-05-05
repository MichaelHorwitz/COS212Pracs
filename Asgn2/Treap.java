public class Treap<T extends Comparable<T>> {
    public Node<T> root = null;

    @Override
    public String toString() {
        if (root == null) {
            return "";
        }

        return root.toString() + "\n" + toString(root, "");
    }

    private String toString(Node<T> curr, String pre) {
        if (curr == null)
            return "";
        String res = "";

        if (curr.left != null) {
            if (curr.right != null) {
                res += pre + "├(L)─ " + curr.left.toString() + "\n" + toString(curr.left, pre + "|    ");
            } else {
                res += pre + "└(L)─ " + curr.left.toString() + "\n" + toString(curr.left, pre + "     ");
            }
        }

        if (curr.right != null) {
            res += pre + "└(R)─ " + curr.right.toString() + "\n" + toString(curr.right, pre + "   ");
        }
        return res;
    }

    /*
     * Don't change anything above this line
     */

    public void insert(T data) throws DatabaseException {
        
        Node<T> newNode = new Node<T>(data);
        if (root == null) {
            root = newNode;
            return;
        }
        if (data.equals(root.data)) {
            throw DatabaseException.duplicateInsert(data);
        }
        //Insert as Binary Tree
        binIns(newNode);
        if (data.compareTo(root.data) > 0) {
            root = doRotations(root, root.right, data);
        }
        if (data.compareTo(root.data) < 0) {
            root = doRotations(root, root.left, data);
        }
    }
    private Node<T> doRotations(Node<T> parentNode, Node<T> childNode, T data){
        if (parentNode == null) {
            return parentNode;
        }
        //System.out.println("PARENT\t" + parentNode.toString());
        //System.out.println("CHILD\t" + childNode.toString());
        //System.out.println("NO NULLS");
        if (data.compareTo(childNode.data) > 0) {
            childNode = doRotations(childNode, childNode.right, data);
        }
        if (data.compareTo(childNode.data) < 0) {
            childNode = doRotations(childNode, childNode.left, data);
        }
        if (childNode.data.compareTo(parentNode.data) < 0) {
            parentNode.left = childNode;
        } else if (childNode.data.compareTo(parentNode.data) > 0){
            parentNode.right = childNode;
        }
        if (childNode.data.compareTo(parentNode.data) < 0) {
            if (childNode.priority >= parentNode.priority) {
                parentNode.left = childNode.right;
                childNode.right = parentNode;
                return childNode;
            }
        }
        if (childNode.data.compareTo(parentNode.data) > 0) {
            if (childNode.priority >= parentNode.priority) {
                parentNode.right = childNode.left;
                childNode.left = parentNode;
                return childNode;
            }
        }
        
        

        return parentNode;
    }
    private Node<T> binIns(Node<T> newNode) throws DatabaseException{
        Node<T> prevNode = null, currNode = root;
        while (currNode != null) {
            prevNode = currNode;
            if (newNode.data.compareTo(currNode.data) > 0) {
                currNode = currNode.right;
            } else if (newNode.data.compareTo(currNode.data) < 0){
                currNode = currNode.left;
            } else if(newNode.data.equals(currNode.data)){
                //System.out.println("THROW EXCEPTION HERE");
                throw DatabaseException.duplicateInsert(newNode.data);
            }
        }
        if (newNode.data.compareTo(prevNode.data) > 0) {
            prevNode.right = newNode;
        } else if (newNode.data.compareTo(prevNode.data) < 0){
            prevNode.left = newNode;
        }
        return newNode;
    }
    public Node<T> remove(T data) {
        if (root == null) {
            return null;
        }
        Node<T> retNode = recFind(root, data);
        if (retNode == null) {
            return null;
        }
        
        root = recRemove(root, data);
        return retNode;
    }
    private Node<T> recFind(Node<T> currNode, T data){
        if (currNode == null) {
            return null;
        }
        if (data.compareTo(currNode.data) == 0) {
            return currNode;
        }
        if (data.compareTo(currNode.data) < 0) {
            return recFind(currNode.left, data);
        }
        if (data.compareTo(currNode.data) > 0) {
            return recFind(currNode.right, data);
        }
        return null;
    }
    private Node<T> recRemove(Node<T> currNode, T data){
        if (currNode == null) {
            return null;
        }
        if (currNode.data.equals(data)) {
            if (currNode.left == null && currNode.right == null) {
                return null;
            }
        }
        if (currNode.data.equals(data)) {
            if ((currNode.right != null && currNode.left == null) || currNode.right.priority >= currNode.left.priority) {
                Node<T> parent = currNode;
                Node<T> child = currNode.right;
                
                currNode = recRemove(currNode, data);
                return currNode.right;
            } else {
                Node<T> currNodeLR = currNode.left.right;
                currNode.left.right = currNode;
                currNode.left = currNodeLR;
                currNode = recRemove(currNode, data);
                return currNode.left;
            }
        } else {
            if (data.compareTo(currNode.data) < 0) {
                return recRemove(currNode.left, data);
            }
            if (data.compareTo(currNode.data) > 0) {
                return recRemove(currNode.right, data);
            }
        }
        return null;
    }
    public Node<T> access(T data) {
        return null;
    }

}
