
public class MirroredBinaryTree<T extends Comparable<T>> extends BinaryTree<T> {
    @Override
    public boolean contains(T data) {
        return false;
    }

    @Override
    public void depthFirstTraversal() {
        return;
    }
    
    @Override
    public int numLeavesInTree() {
        return -1;
    }

    @Override
    public int height() {
        return -1;
    }

    @Override
    public Leaf<T> findParent(T data) {
        return null;
    }

    @Override
    public void insert(T data) {
        super.insert(data, false);
    }
    
    @Override
    public Leaf<T> find(T data) {
        return null;
    }
    
    @Override
    public boolean perfectlyBalanced() {
        return false;
    }
    
    @Override
    public BinaryTree<T> convertTree() {
        return null;
    }
}

