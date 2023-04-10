import javax.imageio.ImageTypeSpecifier;

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

        //Insert as Binary Tree
        Node<T> currNode = root;
        Node<T> prevNode = null;
        Node<T> gpNode = null;
        while (currNode != null) {
            gpNode = prevNode;
            prevNode = currNode;
            if (data.compareTo(currNode.data) < 0) {
                currNode = prevNode.left;
            } else {
                currNode = prevNode.right;
            }
        }
        if (data.compareTo(prevNode.data) < 0) {
            prevNode.left = newNode;
            currNode = prevNode.left;
        } else {
            prevNode.right = newNode;
            currNode = prevNode.right;
        }
        root = correctPriority(null, data, root)        

    }

    private Node<T> correctPriority(Node<T> pNode, T data, Node<T> cNode){
        if (data.equals(cNode.data)) {
            return cNode;
        }
        if (data.compareTo(cNode.data) < 0) {
            cNode = correctPriority(cNode, data, cNode.left);
            if (cNode.left.priority > cNode.priority) {
                pNode.left = cNode.right;
                cNode.right = pNode;
                return cNode;    
                //rotation 
                //return cNode
            } else {
                return pNode;
            }
        }
        if (data.compareTo(cNode.data) > 0) {
            cNode = correctPriority(cNode, data, cNode.right);
            if (cNode.right.priority > cNode.priority) {
                pNode.right = cNode.left;
                cNode.left = pNode;
                return cNode;    
                //rotation 
                //return cNode
            } else {
                return pNode;
            }
        }
        return pNode;
    }

    public Node<T> remove(T data) {
        return null;
    }

    public Node<T> access(T data) {
        return null;
    }

}
