
import java.util.Scanner;

/*
 * This program reads "speeding ticket" data in from a file it then creates an
 * object for each ticket and stores each ticket object in an array.  the data 
 * is then acessed to calculate the approperate fines and the fine data is 
 * stored in a parrell array. calculations are ten performed on the fine data 
 * and it is then displayed. 
 */

/**
 * Assn 8 version 1
 * @author christopherlaird
 */
public class TicketAnalysis {
    static Scanner in = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String inputFileName;
        int ticketCount;
        boolean stop;
        double [] fine;
        
        
        System.out.println("This program will run analyses on weekly speeding "
                + "ticket data files\n");
        stop = false;
        String repeat;
        while (!stop) { //outer loop 
            TicketArrayImpl alpha = new TicketArrayImpl();
            ticketCount = 0;
            while (ticketCount == 0) { //inner loop
                System.out.print("Enter the input filename: ");
                inputFileName = in.next();
                ticketCount = alpha.storeFileDataIntoArray(inputFileName);
            }
            System.out.println("\nCalculating fines...");
            fine = new double[ticketCount];
            alpha.calculateAndStoreFines(fine);
            System.out.println("Done!\n");
            alpha.createFinesReport(fine);
            displayTicketSummary(fine, ticketCount);
            
            System.out.print("Run program again with a different file (y/n)?");
            repeat = in.next();
            if (repeat.equals("n") || repeat.equals("N")) {
                stop = true;
            }
        }
    }
    
    /**
     * identifies the smallest fine 
     * @param fine array of fine data
     * @param ticketCount total number of tickets issued
     * @return smallest fine issued
     */
    public static double minFine(double [] fine, int ticketCount) {
        double minFine = Double.POSITIVE_INFINITY;
               
        for (int i = 0; i < ticketCount; i++) {
            if (fine[i] < minFine) {
                minFine = fine[i];
            }  
        } 
    return minFine;   
    }
    
    /**
     * calculates the average fine issued
     * @param fine array of fine data
     * @param ticketCount total number of tickets issued
     * @return average fine issued
     */
    public static double avgFine(double [] fine, int ticketCount) {
        double avgFine,
               fineSum = 0; 
        
        for (int i = 0; i < ticketCount; i++) {
            fineSum += fine[i];
        }
        avgFine = fineSum / ticketCount;
        return avgFine;   
    }
    
    /**
     * locates the largest fine issued
     * @param fine array of fine data
     * @param ticketCount total number of tickets issued
     * @return largest fine issued
     */
    public static double maxFine(double [] fine, int ticketCount) {
        double maxFine = 0;
               
        for (int i = 0; i < ticketCount; i++) {
            if (fine[i] > maxFine) {
                maxFine = fine[i];
            }  
        }
    return maxFine;  
    }
    
    /**
     * display summary of ticket information
     * @param fine array of fine data
     * @param ticketCount total number of tickets issued 
     */
    public static void displayTicketSummary(double [] fine, int ticketCount) {
        System.out.println("\nWeeks Ticket Analysis for " + ticketCount 
                + " tickets issued: ");
        System.out.printf("    Lowest ticket fine %13.2f%n", minFine(fine, 
                ticketCount));
        System.out.printf("    Average ticket fine %12.2f%n", avgFine(fine, 
                ticketCount)); 
        System.out.printf("    Highest ticket fine %12.2f%n%n", maxFine(fine, 
                ticketCount));      
    }
    
}
