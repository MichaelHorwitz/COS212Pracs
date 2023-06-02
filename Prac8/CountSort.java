public class CountSort<T extends Comparable<T>> extends Sort<T> {
    
    @Override
    @SuppressWarnings("unchecked")
    public Comparable<T>[] sort(Comparable<T>[] arr) {
        //Do not alter given code nor add code above
        int[] count = formCountArr(arr);
        printArr(arr, count);
        int[] sumCount = sumCount(count);
        printArr(arr, sumCount);
        Comparable<T>[] res = new Comparable[arr.length]; //This array needs to be populated with the final result
        //Add code below this
        //for i = n-1 down to 0     
        for (int i = arr.length-1; i >= 0; i--) {
            //  tmp[count[data[i]] - 1] = data[i];
            res[sumCount[arr[i].hashCode()] - 1] = arr[i];     
            //  decrement count[data[i]];
            sumCount[arr[i].hashCode()]--;
        }
        //transfer numbers from tmp[] to data[];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = res[i];
        }


        //Do not change the statement below
        printArr(res, sumCount);
        //Ensure only return statement is below this
        return arr;
        
    }
    
    private int[] formCountArr(Comparable<T>[] arr){
        if (arr.length == 0) {
            return new int[0];
        }
        // Comparable<T> largestVal = arr[0];
        // for (int i = 0; i < arr.length; i++) {
        //     if (arr[i].compareTo((T)largestVal) > 0) {
        //         largestVal = arr[i];
        //     }
        // }
        //count occurrences of each number in data[]; 
        //store occurrences in count[] indexed with numbers in data[];
        int size = countArraySize(arr);
        int[] count = new int[size];
        for (int i = 0; i < count.length; i++) {
            count[i] = 0;
        }
        for (int i = 0; i < arr.length; i++) {
            count[arr[i].hashCode()]++;
        }
        return count;
        
    }
    
    private int[] sumCount(int[] countArr){
        int[] sumCount = new int[countArr.length];
        for (int i = 0; i < sumCount.length; i++) {
            sumCount[i] = countArr[i];
        }
        for (int i = 1; i < sumCount.length; i++) {
            sumCount[i] = sumCount[i-1] + sumCount[i];
        }
        return sumCount;
        
    }
    
    private int countArraySize(Comparable<T>[] arr){
        int largestVal = arr[0].hashCode();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].hashCode() > largestVal) {
                largestVal = arr[i].hashCode();
            }
        }
        return largestVal + 1;
        
    }

    private void printArr(Comparable<T>[] arr, int[] count){
        if(arr == null || count == null)
            System.out.println("NULL ARRAYS");

        String res = "[";
        for(Comparable<T> t: arr){
            res += t + "{" + count[t.hashCode()] + "},";
        }
        if(res.length() > 0){
            res = res.substring(0, res.length()-1);
        }
        res += "]";
        System.out.println(res);
    }


}
