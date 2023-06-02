import java.util.Random;

public class App2
{
    public static void main(String[] args) throws Exception
    {
        Integer[] array = getArray();

        quickSort(array);
        mergeSort(array);
        countSort(array);
    }

    public static Integer[] getArray()
    {
        // Provided Example:
        // Integer[] arr = new Integer[8];
        // arr[0] = 5;
        // arr[1] = 4;
        // arr[2] = 6;
        // arr[3] = 3;
        // arr[4] = 7;
        // arr[5] = 2;
        // arr[6] = 8;
        // arr[7] = 1;

        Random rand = new Random();
        Integer[] arr = new Integer[rand.nextInt(1000)];
        for (int loop = 0; loop < arr.length; loop++)
        {
            arr[loop] = rand.nextInt(100);
        }

        return arr;
    }

    public static void quickSort(Integer[] arr)
    {
        System.out.println("Quicksort example:");
        QuickSort<Integer> quickSort = new QuickSort<>();
        Comparable<Integer>[] result = quickSort.sort(arr);
        System.out.println("Final result: ");
        Sort.printArr(result);
        if (arraySortedOrNot(result, result.length) != 0)
        {
            System.out.println("Array is correctly sorted");
        }
        else
        {
            System.out.println("Error: Array is not sorted");
        }
        System.out.println();
    }

    public static void mergeSort(Integer[] arr)
    {
        System.out.println("Mergesort example:");
        MergeSort<Integer> mergeSort = new MergeSort<>();
        Comparable<Integer>[] result = mergeSort.sort(arr);
        System.out.println("Final result: ");
        Sort.printArr(result);
        if (arraySortedOrNot(result, result.length) != 0)
        {
            System.out.println("Array is correctly sorted");
        }
        else
        {
            System.out.println("Error: Array is not sorted");
        }
        System.out.println();
    }

    public static void countSort(Integer[] arr)
    {
        System.out.println("Countsort example:");
        CountSort<Integer> countSort = new CountSort<>();
        Comparable<Integer>[] result =  countSort.sort(arr);
        System.out.println("Final result: ");
        Sort.printArr(result);
        if (arraySortedOrNot(result, result.length) != 0)
        {
            System.out.println("Array is correctly sorted");
        }
        else
        {
            System.out.println("Error: Array is not sorted");
        }
        System.out.println();
    }

    static int arraySortedOrNot(Comparable<Integer>[] arr, int n)
    {
        if ((n == 1) || (n == 0))
        {
            return 1;
        }

        if (arr[n - 1].compareTo((Integer) arr[n - 2]) < 0)
        {
            return 0;
        }

        return arraySortedOrNot(arr, n - 1);
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