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
        int randNum = Math.abs(randomGenerator.nextInt()) % powers[maxLevel - 1] + 1;
        int i = 1;
        while (i < maxLevel) {

            if (randNum < powers[i]) {
                return i - 1;
            }
            i++;
        }
        return i - 1;
    }

    public void insert(T key) {
        int nodeLevel = this.chooseLevel();
        //System.out.println("nodeLevel: " + nodeLevel);
        SkipListNode<T> newNode = new SkipListNode<T>(key, nodeLevel + 1);
        SkipListNode<T>[] currNode = new SkipListNode[maxLevel];
        SkipListNode<T>[] prevNode = new SkipListNode[maxLevel];
        int currLevel;
        currNode[maxLevel-1] = root[maxLevel-1];
        System.out.println("currNode: " + currNode[maxLevel-1]);
        System.out.println("root: " + root[maxLevel - 1]);
        prevNode[maxLevel-1] = null;
        
        for (currLevel = maxLevel - 1; currLevel >= 0; currLevel--) {
            while (currNode[currLevel] != null && currNode[currLevel].key.compareTo(key) < 0) {
                prevNode[currLevel] = currNode[currLevel];
                currNode[currLevel] = currNode[currLevel].next[currLevel];
                
            }
            
            if (currLevel > 0) {
                if (prevNode[currLevel] == null) {
                    currNode[currLevel-1] = root[currLevel-1];
                    prevNode[currLevel-1] = null;
                } else {
                    currNode[currLevel-1] = prevNode[currLevel].next[currLevel - 1];
                    prevNode[currLevel-1] = prevNode[currLevel];
                }
            }
            for (int i = 0; i <= nodeLevel; i++) {
                newNode.next[i] = currNode[i];
                if (prevNode[i] == null) {
                    System.out.println("HERE");
                    root[i] = newNode;
                } else {
                    prevNode[i].next[i] = newNode;
                }
            }
        }
        /*
        SkipListNode<T>[] pred = new SkipListNode[nodeLevel];
        currNode.next = root;
        
        for (int currLevel = nodeLevel-1; currLevel >= 0; currLevel--) {
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
            if (pred[i] != null) {
                newNode.next[i] = pred[i].next[i];
                pred[i].next[i] = newNode;
            }
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
        int currLevel = maxLevel-1;
        for (currLevel = maxLevel-1; currLevel >= 0&& root[currLevel] == null; currLevel--);
        SkipListNode<T> prevNode = root[currLevel];
        SkipListNode<T> currNode = root[currLevel];
        
        
        while (true) {
            if (currNode.key.compareTo(key) == 0) {
                return currNode;
            }
            if (currNode.key.compareTo(key) > 0) {
                if (currLevel == 0) {
                    return null;
                }
                else if (currNode == root[currLevel]){
                    currLevel--;
                } else {
                    currLevel--;
                    currNode = prevNode.next[currLevel];
                }
            } else {
                prevNode = currNode;
                if (currNode.next[currLevel] != null) {
                    currNode = currNode.next[currLevel];
                    //System.out.println("currNode: " + currNode);
                } else {
                    for(currLevel--; currLevel >= 0 && currNode.next[currLevel] == null; currLevel--);
                    if (currLevel >= 0) {
                        currNode = currNode.next[currLevel];
                    } else {
                        return null;
                    }
                }
            }
        }
        
        /*
        boolean found = false;
        
        
        while (currLevel >= 0 && !found) {
            while (currNode.next[currLevel] != null && currNode.next[currLevel].key.compareTo(key) < 0) {
                currNode = currNode.next[currLevel];
            }
            if (currLevel >= 0) {
                if (currNode.next[currLevel] == null) {
                    currLevel--;
                } else{
                    if (currNode.next[currLevel].key.compareTo(key) == 0) {
                        ret = currNode.next[currLevel];
                        found = true;
                    } else {
                        currLevel--;
                    }
                }
            }
        }
        if (found) {
            return ret;
        }
        
        return null;
        */
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
            for (int i = maxLevel - 1; i > 0; i--) {
                ret += levelsStr[i] + "\n";
            }
            ret += levelsStr[0];
            return ret;
        }
        
        while (currNode != null) {
            //System.out.println("currNode: " + currNode);
            int currLevel = currNode.next.length;
            for (int i = 0; i < maxLevel; i++) {
                if (i <= currLevel - 1) {
                    levelsStr[i] += "->";
                    levelsStr[i] += currNode.toString();
                } else {
                    levelsStr[i] += "--";
                    levelsStr[i] += currNode.emptyString();
                }
            }
            currNode = currNode.next[0];
        }
        String ret = "";
        for (int i = maxLevel - 1; i > 0; i--) {
            ret += levelsStr[i].substring(0, levelsStr[i].lastIndexOf("]") + 1) + "\n";
        }
        ret += levelsStr[0].substring(0, levelsStr[0].lastIndexOf("]") + 1);
        return ret;
    }

    public boolean delete(T key) {
        SkipListNode<T> delNode = search(key);
        SkipListNode<T>[] prev = new SkipListNode[delNode.next.length];
        SkipListNode<T> curr = root[delNode.next.length - 1];
        if (curr == delNode) {
            for (int i = delNode.next.length-1; i >= 0; i--) {
                root[i] = curr.next[i];
            }
            return true;
        }
        for (int i = delNode.next.length - 1; i >= 0; i--) {
            /*
            System.out.println(curr);
            System.out.println(curr.next[i]);
            System.out.println(delNode);
            */
            while (curr.next[i] != delNode) {
                curr = curr.next[i];
            }
            prev[i] = curr;
        }
        for (int i = 0; i < delNode.next.length; i++) {
            prev[i].next[i] = delNode.next[i];
        }
        return true;

        /*
        SkipListNode<T> currNode = new SkipListNode<T>(null, maxLevel);
        SkipListNode<T> delNode = null;
        currNode.next = root;
        int currLevel = maxLevel-1;
        boolean found = false;
        
        while (currLevel >= 0 && !found) {
            while (currNode.next[currLevel] != null && currNode.next[currLevel].key.compareTo(key) < 0) {
                currNode = currNode.next[currLevel];
            }
            if (currLevel >= 0) {
                if (currNode.next[currLevel] == null) {
                    if (currNode.key.compareTo(key) == 0) {
                        delNode = currNode;
                        
                        found = true;
                    } else {
                        currLevel--;
                    }
                    currLevel--;
                } else{
                    if (currNode.next[currLevel].key.compareTo(key) == 0) {
                        delNode = currNode;
                        
                        found = true;
                    } else {
                        currLevel--;
                    }
                }
            }

        }
        if (found) {
            for (int i = 0; i < delNode.next.length; i++) {
                if (delNode.next[i] != null) {
                    delNode.next[i] = delNode.next[i].next[i];
                } else {
                    //delNode.next
                }
                
            }
            return true;
        }
        return false;
        */
        
    }
    
    public void printSearchPath(T key) {
        if (search(key) == null) {
            return;
        }
        int currLevel = maxLevel-1;
        for (currLevel = maxLevel-1; currLevel >= 0&& root[currLevel] == null; currLevel--);
        SkipListNode<T> prevNode = root[currLevel];
        SkipListNode<T> currNode = root[currLevel];
        
        
        while (true) {
            System.out.print(currNode);
            if (currNode.key.compareTo(key) == 0) {
                System.out.println();
                return;
            }
            if (currNode.key.compareTo(key) > 0) {
                if (currLevel == 0) {
                    return;
                }
                else if (currNode == root[currLevel]){
                    currLevel--;
                } else {
                    currLevel--;
                    currNode = prevNode.next[currLevel];
                }
            } else {
                prevNode = currNode;
                if (currNode.next[currLevel] != null) {
                    currNode = currNode.next[currLevel];
                    //System.out.println("currNode: " + currNode);
                } else {
                    for(currLevel--; currLevel >= 0 && currNode.next[currLevel] == null; currLevel--);
                    if (currLevel >= 0) {
                        System.out.print(currNode);
                        currNode = currNode.next[currLevel];
                    } else {
                        return;
                    }
                }
            }
        }
        /*
        
        SkipListNode<T> currNode = new SkipListNode<T>(null, maxLevel);
        SkipListNode<T> ret = null;
        currNode.next = root;
        int currLevel = maxLevel-1;
        boolean found = false;
        
        while (currLevel >= 0 && !found) {
            if (currNode.key != null) {
                
                System.out.print(currNode);
            }
            while (currNode.next[currLevel] != null && currNode.next[currLevel].key.compareTo(key) < 0) {
                
                currNode = currNode.next[currLevel];
                System.out.print(currNode);
            }
            if (currLevel < 0) {

            } else {
                if (currNode.next[currLevel] == null) {
                    //System.out.println("Minus");
                    currLevel--;
                } else{
                    
                    if (currNode.next[currLevel].key.compareTo(key) == 0) {
                        //System.out.println(currNode.toString());
                        ret = currNode.next[currLevel];
                        System.out.print(ret.toString());
                        found = true;
                        //System.out.println("Found");
                    } else {
                        //System.out.println("Minus");
                        currLevel--;
                    }
                }
            }

        }
        System.out.println("");
        if (found) {
            return;
        }
        */
    }
}
