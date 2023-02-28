// This is used to remove the warnings which occur when using generics
@SuppressWarnings("unchecked")
public class SkipListNode<T extends Comparable<T>> {
    public T key;
    public SkipListNode<T>[] next;

    public SkipListNode(T key, int levels) {
        this.key = key;
        Object [] temp = new Object[levels];
        for (int i = 0; i < levels; i++) {
            temp[i] = null;
        }
        next = (SkipListNode<T>[])temp;
    }

    @Override
    public String toString() {
        return "";
    }

    public String emptyString() {
        return "";
    }
}