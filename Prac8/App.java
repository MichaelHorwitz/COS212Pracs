public class App {
    public static void main(String[] args) throws Exception {
        studentExample();
        //quickSortTest();
        //mergeSortTest();
        //countSortTest();
    }
    public static void countSortTest(){
        //Integer[] arr1 = {5, 4, 6, 3, 7, 2, 8};// = {6, 39, 24, 38, 7};
        Integer[] arr1 = {38, 6, 39, 24, 24, 38, 7};
        CountSort<Integer> cs = new CountSort<Integer>();
        Comparable<Integer>[] arr2 = cs.sort(arr1);
        CountSort.printArr(arr2);
    }
    public static void mergeSortTest(){
        //Integer[] arr1 = {5, 4, 6, 3, 7, 2, 8};// = {6, 39, 24, 38, 7};
        Integer[] arr1 = {6, 39, 24, 38, 7};
        MergeSort<Integer> ms = new MergeSort<Integer>();
        Comparable<Integer>[] arr2 = ms.sort(arr1);
        MergeSort.printArr(arr2);
    }
    public static void quickSortTest(){
        Integer[] arr1 = {5, 4, 6, 3, 7, 7, 2, 8, 1};// = {6, 39, 24, 38, 7};
        QuickSort<Integer> qs = new QuickSort<Integer>();
        qs.sort(arr1);
        QuickSort.printArr(arr1);
    }
    public static Integer[] getArray(){
        Integer[] arr = new Integer[8];
        arr[0] = 5;
        arr[1] = 4;
        arr[2] = 6;
        arr[3] = 3;
        arr[4] = 7;
        arr[5] = 2;
        arr[6] = 8;
        arr[7] = 1;
        return arr;
    }

    public static void studentExample(){
        
        System.out.println("Quicksort example:");
        QuickSort<Integer> quickSort = new QuickSort<>();
        Comparable<Integer>[] result = quickSort.sort(getArray());
        System.out.println("Final result: ");
        Sort.printArr(result);
        System.out.println("Mergesort example:");
        MergeSort<Integer> mergeSort = new MergeSort<>();
        result = mergeSort.sort(getArray());
        System.out.println("Final result: ");
        Sort.printArr(result);
        System.out.println("Countsort example:");
        CountSort<Integer> countSort = new CountSort<>();
        result =  countSort.sort(getArray());
        System.out.println("Final result: ");
        Sort.printArr(result);
    }
}

/*
Quicksort example:
[5;4;6;3;7;2;8;1]
[2;1]
[1]
[]
[5;7;6;4;8]
[5;4]
[4]
[]
[7;8]
[]
[8]
Final result:
[1;2;3;4;5;6;7;8]
Mergesort example:
[5;4;6;3;7;2;8;1]
[5;4;6;3]
[5;4]
[5]
[4]
[6;3]
[6]
[3]
[7;2;8;1]
[7;2]
[7]
[2]
[8;1]
[8]
[1]
Final result:
[1;2;3;4;5;6;7;8]
Countsort example:
[5{1},4{1},6{1},3{1},7{1},2{1},8{1},1{1}]
[5{5},4{4},6{6},3{3},7{7},2{2},8{8},1{1}]
[1{0},2{1},3{2},4{3},5{4},6{5},7{6},8{7}]
Final result:
[1;2;3;4;5;6;7;8]
 */
