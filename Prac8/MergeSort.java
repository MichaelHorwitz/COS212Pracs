public class MergeSort <T extends Comparable<T>> extends Sort<T> {
    @Override
    @SuppressWarnings("unchecked")
    public Comparable<T>[] sort(Comparable<T>[] arr) {
        //Hint This function can be implemented as a one liner consisting of a return and a function call
        arr = mergeSort(arr);
        return arr;
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
            Comparable<T>[] arr1 = new Comparable[size];
            Comparable<T>[] arr2;
            if (arr.length % 2 == 0) {
                arr2 = new Comparable[size];
                for (int i = 0; i < mid; i++) {
                    arr1[i] = arr[i];
                }
                for (int i = mid; i < arr.length; i++) {
                    arr2[i - mid] = arr[i];
                }
            } else {
                arr2 = new Comparable[size - 1];
                for (int i = 0; i <= mid; i++) {
                    arr1[i] = arr[i];
                }
                for (int i = mid + 1; i < arr.length; i++) {
                    arr2[i - mid - 1] = arr[i];
                }
                
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
        int firstIndex = 0, secondIndex = 0, arrIndex = 0;
        if (lh[0].compareTo((T)rh[0]) > 0) {
            Comparable<T>[] temp = lh;
            lh = rh;
            rh = temp;
        }
        while (firstIndex < lh.length && lh[firstIndex].compareTo((T)rh[secondIndex]) < 0) {
            arr[arrIndex] = lh[firstIndex];
            arrIndex++;
            firstIndex++;
        }
        if (firstIndex != lh.length) {
            while (secondIndex < rh.length && rh[secondIndex].compareTo((T)lh[firstIndex]) < 0) {
                arr[arrIndex] = rh[secondIndex];
                arrIndex++;
                secondIndex++;
            }
        }
        while (firstIndex < lh.length) {
            arr[arrIndex] = lh[firstIndex];
            arrIndex++;
            firstIndex++;
        }
        while (secondIndex < rh.length) {
            arr[arrIndex] = rh[secondIndex];
            arrIndex++;
            secondIndex++;
        }
        
        return arr;
        
    }

    private int getMidPoint(int first, int last){
        return (int)Math.floor((first+last)/2);
    }
    
}
