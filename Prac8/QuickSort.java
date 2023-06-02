public class QuickSort<T extends Comparable<T>> extends Sort<T> {
    boolean first;
    @Override
    @SuppressWarnings("unchecked")
    public Comparable<T>[] sort(Comparable<T>[] arr) {
        printArr(arr);
        // Add code below this line
        first = true;
        arr = recSort(arr);
        return arr;

    }
    
    
    @SuppressWarnings("unchecked")
    private Comparable<T>[] recSort(Comparable<T>[] arr) {// }, Comparable<T>[] resultingArr){
        if (!first) {
            printArr(arr);
        } else {
            first = false;
        }
        // Add code below this line
        // if data.length > 1
        if (arr.length > 1) {
            // choose pivot;
            int pivotIndex = getPivotPoint(arr);
            // Partition
            pivotIndex = partition(arr, pivotIndex);
            Comparable<T>[] arr1 = new Comparable[pivotIndex];
            Comparable<T>[] arr2 = new Comparable[arr.length - pivotIndex - 1];
            for (int i = 0; i < arr1.length; i++) {
                arr1[i] = arr[i];
            }
            for (int i = 0; i < arr2.length; i++) {
                arr2[i] = arr[i + pivotIndex + 1];
            }
            // quicksort(data1[]); // recursive call
            recSort(arr1);
            // quicksort(data2[]);
            //huh
            //System.out.println("he");
            recSort(arr2);
            for (int i = 0; i < arr1.length; i++) {
                arr[i] = arr1[i];
            }
            for (int i = pivotIndex + 1; i < arr.length; i++) {
                arr[i] = arr2[i - pivotIndex - 1];
            }
        }
        return arr;
    }

    private int partition(Comparable<T>[] arr, int pivotIndex) {
        // swap(data[first],data[(first + last)/2]);
        swap(arr, 0, pivotIndex);
        // lower = first + 1, upper = last;
        int lower = 1;
        int upper = arr.length - 1;
        Comparable<T> largest = arr[upper];
        int largestIndex = upper;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].compareTo((T)largest) > 0) {
                largest = arr[i];
                largestIndex = i;
            }
        }
        swap(arr, largestIndex, upper);
        // T pivot = data[first];
        Comparable<T> pivot = arr[0];
        // while (lower <= upper) {
        while (lower <= upper) {
            // while (data[lower] < pivot) lower++;
            while (lower < arr.length && arr[lower].compareTo((T)pivot) < 0) {
                lower++;
            }
            // while (data[upper] > pivot) upper--;
            while (upper < arr.length && arr[upper].compareTo((T)pivot) > 0) {
                upper--;
            }
            // if (lower < upper)
            if (lower < upper) {
                // swap(data[lower++],data[upper--]);
                swap(arr, lower++, upper--);
            } else {
                lower++;
            }
        }
        // swap(data[upper],data[first]);
        swap(arr, upper, 0);
        return upper;
    }

    private void swap(Comparable<T>[] arr, int firstIndex, int secondIndex) {
        Comparable<T> temp = arr[firstIndex];
        arr[firstIndex] = arr[secondIndex];
        arr[secondIndex] = temp;
    }

    private int getPivotPoint(Comparable<T>[] arr) {
        if (arr == null || arr.length == 0)
            return 0;

        if (arr.length % 2 == 0)
            return (int) Math.floor(arr.length / 2) - 1;
        else
            return (int) Math.floor(arr.length / 2);
    }
}
