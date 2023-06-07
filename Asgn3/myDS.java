public class myDS<T extends Comparable<T>>{
    Comparable<T>[] mainArr;
    int size;
    int numElements;
    public myDS(){
        mainArr = new Comparable[0];
        size = 0;
        numElements = 0;
    }
    public insert(Comparable<T> data){
        if (numElements + 1 >= size) {
            resize(2);
        }
        mainArr[numElements] = data;
        numElements++;

    }
    public remove(Comparable<T> data){

    }
    private resize(int resizeFactor){
        int newSize = size * resizeFactor;
        Comparable<T>[] newArr = new Comparable[newSize];
        for (int i = 0; i < size; i++) {
            newArr[i] = mainArr[i];
        }
        mainArr = newArr;
        size = newSize;
    }
}
