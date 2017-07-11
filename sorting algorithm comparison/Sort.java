/* contains the menthods to generate the random lists and then sort them with
 * Sleection Sort, ShellSort, and Merge Sort Algorithms.
 */
package cs310lairdsort;

/**
 *
 * @author christopherlaird
 * @version 1
 */
public class Sort {
    private int[] list1,
                  list2,
                  list3,
                  temp;
    
    private final int numElements = 50000;
    
    /**constructor generates 4 identical lists of random numbers
     *
     */
    public Sort() {
        java.util.Random random = new java.util.Random();
        list1 = new int[numElements];
        list2 = new int[numElements];
        list3 = new int[numElements];
        temp = new int[numElements];
        
        for (int i = 0; i < numElements; i++){
            list1[i] = list2[i] = list3[i] = temp[i] = random.nextInt(100000);
        }
    }
    
    /**Selection Sort
     *
     * @return the time required to complete
     */
    public long selectionSort(){
        long start,
             stop,
             delta;
        
        int i,
            j,
            minPos,
            temp; 

        start = System.nanoTime();
        for (i = 0; i < numElements - 1; i++){
            minPos = i;
            for (j = i + 1; j < numElements; j++){
                if (list1[j] < list1[minPos]) minPos = j;
            }
            //swap
            temp = list1[minPos];
            list1[minPos] = list1[i];
            list1[i] = temp;  
        }
        stop = System.nanoTime();
        delta = (stop - start) / 1000000;//delta in mili secconds
        return delta;
    }
    
    /**Shell Sort
     *
     * @return the time required to complete
     */
    public long shellSort(){
        long start,
             stop,
             delta;
        
        int gap = numElements,
            index,
            insertValue,
            tryIdx;    
        
        start = System.nanoTime();
        while (gap > 1){
            gap = gap / 2;
            index = gap;
            while (index < numElements){
                insertValue = list2[index];
                tryIdx = index - gap;
                while (tryIdx >= 0 && insertValue < list2[tryIdx]){
                    list2[tryIdx + gap] = list2[tryIdx];
                    tryIdx = tryIdx - gap;
                }
                list2[tryIdx + gap] = insertValue;
                index++;
            }
        }
        stop = System.nanoTime();
        delta = (stop - start) / 1000000;//delta in mili secconds
        return delta; 
    }
    
    /**Merge Sort
     *
     * @return the time required to complete
     */
    public long mergeSort(){
        long start,
             stop,
             delta;
        
        start = System.nanoTime();
        mergeSort(list3, 0, numElements - 1);
        stop = System.nanoTime();
        delta = (stop - start) / 1000000;//delta in mili secconds
        return delta;
    }
    
    private void mergeSort(int[] array, int lowIdx, int highIdx){
        int midIdx;
        if (lowIdx < highIdx){//more than one index not base case
            midIdx = (lowIdx + highIdx) / 2;
            mergeSort(array, lowIdx, midIdx);
            mergeSort(array, midIdx + 1, highIdx);
            merge(list3, temp, lowIdx, midIdx, highIdx);
        }
    }
    
    private void merge(int[] list,int[] temp, int lowIdx, int midIdx, 
            int highIdx){
        int leftIdx = lowIdx,
            rightIdx = midIdx + 1,
            tempPos = lowIdx;
        
        temp = list;
        while (leftIdx <= midIdx && rightIdx <= highIdx){
            if (temp[leftIdx] <= temp[rightIdx]){
                list[tempPos] = temp[leftIdx];
                leftIdx++;
            }
            else{
                list[tempPos] = temp[rightIdx];
                rightIdx++;
            }
            tempPos++;
        }
        while (leftIdx <= midIdx){
            list[tempPos] = temp[leftIdx];
            tempPos++;
            leftIdx++;
        }
        while (rightIdx <= highIdx){
            list[tempPos] = temp[rightIdx];
            tempPos++;
            rightIdx++;
        }
    }
    
    /** validates all 3 lists for a 
     *
     * @return 0 = all pass
     * 1 = first sort failed
     * 2 = second sort failed
     * 3 = third sort failed
     */
    public int checkSort(){
        int check = 0;
        for (int i = 0; i < numElements - 1; i++){
            if (list1[i] > list1[i + 1]) 
                return 1;
        }
        for (int i = 0; i < numElements - 1; i++){
            if (list2[i] > list2[i + 1]) 
                return 2;
        }
        for (int i = 0; i < numElements - 1; i++){
            if (list3[i] > list3[i + 1]) 
                return 3;
        }
        return check;
    }
}
