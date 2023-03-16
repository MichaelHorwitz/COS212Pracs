
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
        if (root == null) {
            return null;
        }
        if (root.data.compareTo(data) == 0) {
            System.out.println(root);
            return null;
        }
        return recursiveFindParent(null, root, data);
    }
    
    private Leaf<T> recursiveFindParent(Leaf<T> parent, Leaf<T> leaf, T data){
        if (leaf == null) {
            return null;
        }
        
        if (leaf.data.compareTo(data) == 0) {
            return parent;
        }
        
        System.out.println(leaf);


        if (leaf.data.compareTo(data) > 0) {
            return recursiveFindParent(leaf, leaf.left, data);
        }
        if (leaf.data.compareTo(data) < 0) {
            return recursiveFindParent(leaf, leaf.right, data);
        }

        return null;
        /*
        if (parent == null) {
            return null;
        }
        //System.out.println("IN LOOP");
        System.out.println(parent);
        if (parent.left != null && parent.left.data.compareTo(data) == 0) {
            return parent;
        }
        if (parent.right != null && parent.right.data.compareTo(data) == 0) {
            return parent;
        }
        
        if (parent.right != null && parent.right.data.compareTo(data) < 0) {
            //System.out.println("MOVE TO RIGHT");
            return recursiveFindParent(parent.right, data);
        } else if (parent.left != null && parent.left.data.compareTo(data) > 0) {
            //System.out.println("MOVE TO LEFT");
            return recursiveFindParent(parent.left, data);
        }
        return null;
        */
        
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
        System.out.println(leaf);
        if (leaf.data.compareTo(data) == 0) {
            return leaf;
        }
        if (leaf.data.compareTo(data) > 0) {
            return recursiveFind(leaf.left, data);
        }
        if (leaf.data.compareTo(data) < 0) {
            return recursiveFind(leaf.right, data);
        }
        return null;

    }   
    
    @Override
    public boolean perfectlyBalanced() {
        if (root == null) {
            return false;
        }
        return recursivePerfectlyBalanced(root);
    }
    
    private boolean recursivePerfectlyBalanced(Leaf<T> leaf){
        if (leaf == null) {
            //System.out.println("IF0");
            return false;
        }
        if (leaf.left != null && leaf.right != null) {
            //System.out.println("IF1");
            return recursivePerfectlyBalanced(leaf.left) && recursivePerfectlyBalanced(leaf.right);
        }
        if (leaf.left == null && leaf.right == null) {
            //System.out.println("IF2");
            return true;
        }
        //System.out.println("RET");
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
        System.out.println(leaf);
        if (leaf.data.compareTo(data) == 0) {
            //System.out.println("found");
            return true;
        }
        if (leaf.left != null && leaf.data.compareTo(data) > 0) {
            return recursiveContains(leaf.left, data);
        }
        if (leaf.right != null && leaf.data.compareTo(data) < 0){
            return recursiveContains(leaf.right, data);
        }
        return false;
    }

    BinaryTree<T> mt = new MirroredBinaryTree<T>();
    @Override
    public BinaryTree<T> convertTree() {
        return null;
    }

    private BinaryTree<T> recursiveConvert(){
        return null;
    }
}