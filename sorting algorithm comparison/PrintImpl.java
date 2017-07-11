package cs310lairdsort;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author christopherlaird
 * @version 1, assn7
 */
public class PrintImpl {
    
    /**constructor
     *
     */
    public PrintImpl() {

    }
    
    /** generates the report  
     *
     * @param results contains a 3X3 array of algorithm execution times
     */
    public void generateReport(int[][] results){
        int average1,
            average2,
            average3;    
        String output = "output/sortResults.txt";
        File outputFile = null; 
        PrintWriter fileOut = null;
        outputFile = new File(output);
        
        average1 = (results[0][0] + results[0][1] + results[0][2]) / 3;
        average2 = (results[1][0] + results[1][1] + results[1][2]) / 3;
        average3 = (results[2][0] + results[2][1] + results[2][2]) / 3;
        
        try {
        fileOut = new PrintWriter(outputFile);
        }
        catch (IOException e) {
        System.err.println("Input/Output exception for " + outputFile 
                + "\n" + e);
        System.exit(1);
        }        
        
        fileOut.println("SORTING RESULTS");
        fileOut.println("---------------");
        fileOut.println("                 Run 1     Run 2     Run 3    "
                + "Average");
        fileOut.println("Selection Sort    " + results[0][0] + "      " 
                + results[0][1] + "      " + results[0][2] + "       " 
                + average1 + "[mSec]");
        fileOut.println("Shell Sort          " + results[1][0] + "        " 
                + results[1][1] + "        " + results[1][2] + "         " 
                + average2 + "[mSec]");
        fileOut.println("Merge Sort          " + results[2][0] + "         " 
                + results[2][1] + "         " + results[2][2] + "          " 
                + average3 + "[mSec]");
        fileOut.close(); 
    }
}
