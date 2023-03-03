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
                //System.out.println("Level: " + level);
            }
        }
        return level;
    }

    public void insert(T key) {
        int nodeLevel = this.chooseLevel();
        //System.out.println("NodeLevel: " + nodeLevel);
        SkipListNode<T> newNode = new SkipListNode<T>(key, nodeLevel + 1);
        SkipListNode<T> currNode = new SkipListNode<T>(null, maxLevel + 1);
        currNode.next = root;
        SkipListNode<T>[] pred = new SkipListNode[nodeLevel + 1];
        
        // CODE FOR NO PRED
        for (int currLevel = nodeLevel; currLevel >= 0; currLevel--) {
            while (currNode.next[currLevel] != null && pred[currLevel] == null) {
                if (currNode.next[currLevel].key.compareTo(key) <= 0) {
                    currNode = currNode.next[currLevel];
                } else {
                    pred[currLevel] = currNode;
                }
            }
            if (currNode.next[currLevel] == null) {
                pred[currLevel] = currNode;
            }
            
        }
        for (int i = 0; i <= nodeLevel; i++) {
            //System.out.println("HERE");
            if (pred[i] != null) {
                newNode.next[i] = pred[i].next[i];
                pred[i].next[i] = newNode;
            }
        }
        /*
        while (pred == null) {
            if (currNode.next[currLevel] == null) {
                if(currLevel == 0){
                    pred = currNode;
                } else {
                    currLevel--;
                }
            }else{
                while (currNode.next[currLevel] != null && currNode.next[currLevel].key.compareTo(key) <= 0) {
                    currNode = currNode.next[currLevel];
                }
                if (currLevel == 0) {
                    pred = currNode;
                } else {
                    currLevel--;
                }

            }
        }
        for (int i = nodeLevel; i >= 0; i--) {
            System.out.println(newNode.next[i]);
            System.out.println(pred.next[i]);
            newNode.next[i] = pred.next[i];

            pred.next[i] = newNode;
        }
        */
    }

    public boolean isEmpty() {
        boolean empty = true;
        for (int i = 0; i < maxLevel; i++) {
            if (root[i] != null) {
                empty = false;
            }
        }
        return empty;
    }

    public SkipListNode<T> search(T key) {
        SkipListNode<T> currNode = new SkipListNode<T>(null, maxLevel);
        SkipListNode<T> ret = null;
        currNode.next = root;
        int currLevel = maxLevel-1;
        boolean found = false;

        while (currLevel >= 0 && !found) {
            // System.out.println("currLevel: " + currLevel);
            while (currNode.next[currLevel] != null && currNode.next[currLevel].key.compareTo(key) < 0) {
                //System.out.println("While");
                currNode = currNode.next[currLevel];
                //System.out.println("currNode: " + currNode.toString());
                //System.out.println("currLevel: " + currLevel);
            }
            if (currLevel < 0) {

            } else {
                if (currNode.next[currLevel] == null) {
                    //System.out.println("Minus");
                    currLevel--;
                } else{

                    if (currNode.next[currLevel].key.compareTo(key) == 0) {
                        //System.out.println("IF2");
                        ret = currNode.next[currLevel];
                        found = true;
                        //System.out.println("Found");
                    } else {
                        //System.out.println("Minus");
                        currLevel--;
                    }
                }
            }

        }
        //System.out.println("OUT");
        if (found) {
            return ret;
        }
        return null;
    }

    @Override
    public String toString() {
        String[] levelsStr = new String[maxLevel];
        for (int i = 0; i < maxLevel; i++) {
            levelsStr[i] = "[Lvl " + i + "]";
        }
        SkipListNode<T> currNode = root[0];
        if (currNode == null) {
            String ret = "";
            for (int i = maxLevel - 1; i >= 0; i--) {
                ret += levelsStr[i] + "\n";
            }
            //System.out.println("Root[0]: " + root[0]);
            return ret;
        }
        
        while (currNode != null) {
            int currLevel = currNode.next.length;
            for (int i = 0; i < maxLevel; i++) {
                if (i <= currLevel - 1) {
                    levelsStr[i] += "->";
                    levelsStr[i] += currNode.toString();
                    //System.out.println("Here once");

                } else {
                    levelsStr[i] += "--";
                    levelsStr[i] += currNode.emptyString();
                }
            }
            //System.out.println("CurrNode: " + currNode.next.length);
            currNode = currNode.next[0];
        }
        String ret = "";
        for (int i = maxLevel - 1; i >= 0; i--) {
            ret += levelsStr[i] + "\n";
        }
        return ret;
    }

    public boolean delete(T key) {
        return false;
    }

    public void printSearchPath(T key) {
    }
}
