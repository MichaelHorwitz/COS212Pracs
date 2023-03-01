import java.util.Random;

// This is used to remove the warnings which occur when using generics
@SuppressWarnings("unchecked")
public class SkipList<T extends Comparable<T>> {
    private int maxLevel;
    private SkipListNode<T>[] root;
    private int[] powers;
    // Do not change the seed. This is used to generate the same values every run
    private Random randomGenerator = new Random(123456);

    public SkipList(int maxLevel) {
        this.maxLevel = maxLevel;
        root = new SkipListNode[maxLevel];
        for (int i = 0; i < maxLevel; i++) {
            root[i] = null;
        }
        int n = (int) Math.pow(2, maxLevel) - 1;
        powers = new int[maxLevel];
        powers[0] = 1;
        for (int i = 1; i < maxLevel; i++) {
            powers[i] = powers[i-1] + (int)(n/(Math.pow(2, i)) + 1);
        }
        
    }

    public int chooseLevel() {
        int maxNum = (int)Math.pow(2, maxLevel);
        int num = randomGenerator.nextInt(maxNum - 1) + 1;
        int level = -1;
        for (int i = 0; i < maxLevel; i++) {
            if (num >= powers[i]) {
                level = i;
            }
        }
        return 0;
    }

    public void insert(T key) {
    }

    public boolean isEmpty() {
        return false;
    }

    public SkipListNode<T> search(T key) {
        return null;
    }

    @Override
    public String toString() {
        return "";
    }

    public boolean delete(T key) {
        return false;
    }

    public void printSearchPath(T key) {
    }
}
