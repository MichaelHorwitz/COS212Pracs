public class MergeSort <T extends Comparable<T>> extends Sort<T> {
    @Override
    @SuppressWarnings("unchecked")
    public Comparable<T>[] sort(Comparable<T>[] arr) {
        //Hint This function can be implemented as a one liner consisting of a return and a function call
        return mergeSort(arr);
    }
    
    @SuppressWarnings("unchecked")
    private Comparable<T>[] mergeSort(Comparable<T>[] arr){
        //Do not change the position of this function.
        printArr(arr);
        //Add any code below
        //  if first < last
        if (arr.length > 1) {
            // 	    mid = (first + last) / 2;
            int mid = getMidPoint(0, arr.length);
            int size = (int)Math.ceil(arr.length/2.0);
            size--;
            Comparable<T>[] arr1 = new Comparable[size];
            Comparable<T>[] arr2 = new Comparable[size];
            for (int i = 0; i <= mid; i++) {
                arr1[i] = arr[i];
            }
            for (int i = mid + 1; i < arr.length; i++) {
                arr2[i - mid - 1] = arr[i];
            }
            // 	    mergeSort(array, first, mid);
            arr1 = mergeSort(arr1);
            // 	    mergeSort(array, mid + 1, last);
            arr2 = mergeSort(arr2);
            // 	    merge(data, first, last);
            arr = merge(arr1, arr2);
        }
        return arr;
    }
    
    @SuppressWarnings("unchecked")
    private Comparable<T>[] merge(Comparable<T>[] lh, Comparable<T>[] rh){
        int size = lh.length + rh.length;
        Comparable<T>[] arr = new Comparable[size];
        for (int i = 0; i < lh.length; i++) {
            arr[i] = lh[i];
        }
        for (int i = 0; i < rh.length; i++) {
            arr[i + lh.length] = rh[i];
        }
        return arr;
        
    }

    private int getMidPoint(int first, int last){
        return (int)Math.floor((first+last)/2);
    }
    
}
