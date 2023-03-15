
public class StandardBinaryTree<T extends Comparable<T>> extends BinaryTree<T> {
    @Override
    public void depthFirstTraversal() {
        recursiveDepthFirstTraversal(root);
    }
    private void recursiveDepthFirstTraversal(Leaf<T> leaf){
        if (leaf == null) {
            return;
        }
        if (leaf.left != null) {
            recursiveDepthFirstTraversal(leaf.left);
        }
        System.out.println(leaf);
        if (leaf.right != null) {
            recursiveDepthFirstTraversal(leaf.right);
        }
    }
    @Override
    public int numLeavesInTree() {
        return recursiveNumLeavesInTree(root);
    }

    private int recursiveNumLeavesInTree(Leaf<T> leaf){
        if (leaf == null) {
            return 0;
        }
        int leftNum = recursiveNumLeavesInTree(leaf.left);
        int rightNum = recursiveNumLeavesInTree(leaf.right);
        return leftNum + rightNum + 1;
    }

    
    @Override
    public int height() {
        return recursiveHeight(root);
    }
    
    private int recursiveHeight(Leaf<T> leaf){
        if (leaf == null) {
            return 0;
        }
        int leftNum = 0, rightNum = 0;
        if (leaf.left != null) {
            leftNum = recursiveHeight(leaf.left) + 1;
        }
        if (leaf.right != null) {
            rightNum = recursiveHeight(leaf.right) + 1;
        }
        if (leaf.left == null && leaf.right == null) {
            return 0;
        }
        if (leftNum >= rightNum) {
            return leftNum;
        } else {
            return rightNum;
        }
    }

    @Override
    public Leaf<T> findParent(T data) {
        return null;
    }
    
    @Override
    public void insert(T data) {
        super.insert(data, true);
        
    }

    @Override
    public Leaf<T> find(T data) {
        return recursiveFind(root, data);
    }

    private Leaf<T> recursiveFind(Leaf<T> leaf, T data){
        if (leaf == null) {
            return null;
        }
        if (leaf.data.compareTo(data) == 0) {
            return leaf;
        }
        Leaf<T> left = recursiveFind(leaf.left, data);
        Leaf<T> right = recursiveFind(leaf.right, data);

        if (left != null) {
            return left;
        }
        if (right != null) {
            return right;
        }
        return null;

    }   
    
    @Override
    public boolean perfectlyBalanced() {
        return false;
    }
    
    @Override
    public boolean contains(T data) {
        return recursiveContains(root, data);
    }
    
    private boolean recursiveContains(Leaf<T> leaf, T data){
        if (leaf == null) {
            return false;
        }
        if (leaf.data.compareTo(data) == 0) {
            return true;
        }
        return recursiveContains(leaf.left, data) || recursiveContains(leaf.right, data);
    }

    @Override
    public BinaryTree<T> convertTree() {
       return null;
    }
}