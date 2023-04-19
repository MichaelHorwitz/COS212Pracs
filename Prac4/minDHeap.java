@SuppressWarnings("unchecked")
public class minDHeap<T extends Comparable<T>> {
    private int d;
    private T[] nodes;

    @Override
    public String toString() {
        if (nodes.length == 0) {
            return "";
        }

        return "[" + nodes[0] + "]\n" + toStringRec(0, "");
    }

    public String toStringRec(int i, String pre) {
        if (i >= nodes.length) {
            return "";
        }
        String res = "";
        for (int k = 0; k < d; k++) {
            int c = d * i + k + 1;
            if (c < nodes.length) {
                if (k == d - 1 || c + 1 >= nodes.length) {
                    res += pre + "└── " + "[" + nodes[c] + "]\n" + toStringRec(c, pre + "    ");
                } else {
                    res += pre + "├── " + "[" + nodes[c] + "]\n" + toStringRec(c, pre + "│   ");
                }
            }
        }
        return res;
    }

    public T[] getNodes() {
        return nodes;
    }

    /*
     * Don't change anything above this line
     */

    public minDHeap(int d) {
        this.d = d;
        this.nodes = new Comparable[0];
    }

    public void insert(T val) {
        T [] temp = new T[nodes.length + 1];
        for (int i = 0; i < nodes.length; i++) {
            temp[i] = nodes[i];
        }
        temp[nodes.length] = val;
        int i = nodes.length;
        while (nodes[i].compareTo(nodes[(i-1)/d]) < 0 && i > 0) {
            T tempNode = nodes[i];
            nodes[i] = nodes[(i-1)/d];
            nodes[(i-1)/d] = tempNode;
        }
        nodes = temp;
    }

    public void remove(T val) {
        int i = 0;
        boolean found = false;
        while ( i < nodes.length && !found) {
            if (nodes[i].compareTo(val) == 0) {
                found = true;
            } else {
                i++;
            }
        }
        if (!found) {
            return;
        }
        T[] tempNodes = new T[nodes.length-1];
        for (int j = 0; j < tempNodes.length; j++) {
            tempNodes[j] = nodes[j];
        }
        tempNodes[i] = nodes[nodes.length - 1];
        boolean biggest = true;
        while (biggest) {
            int largestChild = i;
            for (int j = 1; j <= d; j++) {
                if (tempNodes[d*i + j].compareTo(tempNodes[largestChild]) < 0) {
                    largestChild = d * i + j;
                    biggest = false;
                }
            }
            if(!biggest){
                T tempNode = tempNodes[i];
                tempNodes[i] = tempNodes[largestChild];
                tempNodes[largestChild] = tempNode;
                i = largestChild;
                biggest = true;
            }
        }
        nodes = tempNodes;
    }

    public void changeD(int newD) {
        d = newD;
        /*
        i = index of the last nonleaf
        while i >= 0 
            p = i; 
            while p is not a leaf and data[p] < any of its children
                swap p with the larger child;
            i = i - 1; // index of the previous non-leaf 
         */
        int i = (nodes.length - 1) / d;
        while (i >= 0 ) {
            int p = i;
            while (p > (nodes.length - 1) / d && isSmallest(p) >= 0) {
                int j = isSmallest(p);
                T tempNode = nodes[j];
                nodes[j] = nodes[p];
                nodes[p] = tempNode;
            }
            i--;
        }
    }
    private int isSmallest(int p){
        int currChild = p * d + 1;
        for (int i = 0; i < d/2; i++) {
            if (nodes[currChild].compareTo(nodes[p]) < 0){
                return i;
            }
            currChild++;
        }
        return -1;
    }
    public T min(int i) {
        if (i < 0 || i >= nodes.length) {
            return null;
        }
        if (nodes.length == 0) {
            return null;
        }
        return nodes[i];
    }

    public T max(int i) {
        int currChild = i * d + 1;
        for (int j = 0; j < d/2; j++) {
            if (nodes[currChild].compareTo(nodes[p]) < 0){
                return max(j);
            }
            currChild++;
        }
        return nodes[currChild];
    }

    public String pathToRoot(T val) {
        boolean found = false;
        for (int i = 0; i < nodes.length && !found; i++) {
            if (nodes[i].equals(val)) {
                found = true;
            }
        }
        
        return "";
    }

}
