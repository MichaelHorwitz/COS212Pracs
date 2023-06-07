public class myDS<T extends Comparable<T>>{
    Comparable<T>[] mainArr;
    int size;
    int numElements;
    public myDS(){
        mainArr = new Comparable[0];
        size = 0;
        numElements = 0;
    }
    public void insert(Comparable<T> data){
        if (numElements + 1 >= size) {
            resize(2.0);
        }
        mainArr[numElements] = data;
        numElements++;

    }
    public boolean remove(Comparable<T> data){
        int index = -1;
        for (int i = 0; i < numElements && index == -1; i++) {
            if (data.equals(mainArr[i])) {
                index = i;
            }
        }
        if (index == -1) {
            return false;
        }
        for (int i = index + 1; i < numElements; i++) {
            mainArr[i-1] = mainArr[i];
        }
        mainArr[numElements] = null;
        numElements--;
        if (numElements < size/2) {
            resize(0.5);
        }
        return true;
    }
    private void resize(Double resizeFactor){
        int newSize = (int)(size * resizeFactor);
        if (size == 0 && resizeFactor > 1) {
            newSize = 1;
            mainArr = new Comparable[1];
            size = newSize;
            return;
        }
        Comparable<T>[] newArr = new Comparable[newSize];
        for (int i = 0; i < numElements; i++) {
            newArr[i] = mainArr[i];
        }
        mainArr = newArr;
        size = newSize;
    }
    public Comparable<T>[] toArray(){
        Comparable<T>[] retArr = new Comparable[numElements];
        for (int i = 0; i < numElements; i++) {
            retArr[i] = mainArr[i];
        }
        return retArr;
    }
    public String toString(){
        String ret = "";
        for (int i = 0; i < numElements; i++) {
            ret += mainArr[i] + ", ";
        }
        return ret;
    }
}
