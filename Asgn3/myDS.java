public class myDS<T>{
    T[] mainArr;
    int size;
    int numElements;
    public myDS(){
        mainArr = (T[])new Object[0];
        size = 0;
        numElements = 0;
    }
    public void insert(T data){
        if (numElements + 1 >= size) {
            resize(2.0);
        }
        mainArr[numElements] = data;
        numElements++;

    }
    public boolean remove(T data){
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
            mainArr = (T[])new Object[1];
            size = newSize;
            return;
        }
        T[] newArr = (T[])new Object[newSize];
        for (int i = 0; i < numElements; i++) {
            newArr[i] = mainArr[i];
        }
        mainArr = newArr;
        size = newSize;
    }
    public Object[] toArray(){
        T[] retArr = (T[])new Object[numElements];
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
    public T search(T data){
        for (int i = 0; i < numElements; i++) {
            if (data.equals(mainArr[i])) {
                return mainArr[i];
            }
        }
        return null;
    }
    public boolean contains(T data){
        if (search(data) != null){
            return true;
        }
        return false;
    }
    public T[] toTArray(){
        Object[] objArr = this.toArray();
        T[] tArr = (T[])(new Object[objArr.length]);
        for (int i = 0; i < objArr.length; i++) {
            tArr[i] = (T)objArr[i];
        }
        return tArr;
    }
    
}
