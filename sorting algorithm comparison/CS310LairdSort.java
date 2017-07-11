/* this program generates lists of random numbers 50,000 in length containing 
 * values from 1 - 100,000.  It then sorts identical lists them using 3 
 * different algorithms, and compares the time required for each algorithm to 
 * complete the task.  This is then repeated 3 times with diferent lists.  
 * The sorts are verified and the three times are veraged and output in a report. 
 */
package cs310lairdsort;


/**
 *
 * @author christopherlaird
 * @version 1, assn 7
 */
public class CS310LairdSort {
    public static Sort alpha = new Sort();
    public static Sort bravo = new Sort();
    public static Sort charlie = new Sort();
    public static int[][] results = new int[3][3];
    public static PrintImpl output = new PrintImpl();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
        System.out.println("Starting sort #1...");
        results[0][0] = (int) alpha.selectionSort();
        System.out.println("        Selection Sort time " + results[0][0] 
                + " mSec" );
        results[1][0] = (int) alpha.shellSort();
        System.out.println("        Shell Sort time " + results[1][0] 
                + " mSec" );
        results[2][0] = (int) alpha.mergeSort();
        System.out.println("        Merge Sort time " + results[2][0] 
                + " mSec" );
        checkSort(alpha);
            
        System.out.println("Starting sort #2...");
        results[0][1] = (int) bravo.selectionSort();
        System.out.println("        Selection Sort time " + results[0][1] 
                + " mSec" );
        results[1][1] = (int) bravo.shellSort();
        System.out.println("        Shell Sort time " + results[1][1] 
                + " mSec" );
        results[2][1] = (int) bravo.mergeSort();
        System.out.println("        Merge Sort time " + results[2][1] 
                + " mSec" );
        checkSort(bravo);
        
        System.out.println("Starting sort #3...");
        results[0][2] = (int) charlie.selectionSort();
        System.out.println("        Selection Sort time " + results[0][2] 
                + " mSec" );
        results[1][2] = (int) charlie.shellSort();
        System.out.println("        Shell Sort time " + results[1][2] 
                + " mSec" );
        results[2][2] = (int) charlie.mergeSort();
        System.out.println("        Merge Sort time " + results[2][2] 
                + " mSec" );
        checkSort(charlie); 
        
        output.generateReport(results);
    }
    
    /**Validates the object's arrays are correctly sorted
     *
     * @param obj object containing arrays to be checked
     */
    public static void checkSort(Sort obj){
        switch (obj.checkSort()) {
            case 0:
                System.out.println("           Sorts validated");
                break;
            case 1:
                System.out.println("*** Sorting error *** in Selection Sort");
                System.exit(0);
            case 2:
                System.out.println("*** Sorting error *** in Shell Sort");
                System.exit(0);
            case 3:
                System.out.println("*** Sorting error *** in Merge Sort");
                System.exit(0);
            default:
                break;
        }
    }  
}
