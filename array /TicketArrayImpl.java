import java.io.*;
import java.util.Scanner;


/**stores data from file in ticket objects which are then stored in an array
 * java assn 8 version 1 
 * @author christopherlaird
 */
public class TicketArrayImpl {
    public static int MAX_TICKETS = 200;
    private SpeedingTicket [] myTicketData;
    private int myTicketCount;
    
    static Scanner in = new Scanner(System.in);
    
    /**
     * implements ticket array
     */
    public TicketArrayImpl() {
        myTicketData = new SpeedingTicket [MAX_TICKETS];
        myTicketCount = 0;    
    }
    
    /**
     * adds a ticket to the array
     * @param ticket object containing specific data
     */
    public void addSpeedingTicket(SpeedingTicket ticket) {
        if (myTicketCount < myTicketData.length) {
            myTicketData[myTicketCount] = ticket;
            myTicketCount++;
        }
        else {
            throw new ArrayIndexOutOfBoundsException("  ***ERROR: Array is full"
                    + " -- ticket with plate " + ticket.getMyLicensePlate() 
                    + " could not be added");
        }
    }
    
    /**
     * reads in data from input file and stores it in a ticket object
     * @param filename name of file containing data
     * @return number of tickets generated
     */
    public int storeFileDataIntoArray (String filename) {
        boolean arrayFull = false;
        int speedLimit;
        String licensePlate;
        int clockedSpeed;
        
        File dataFile = new File (filename);
        Scanner fin = null;
        try {
            fin = new Scanner(dataFile);
            
        } catch (FileNotFoundException fnfe) {
            System.out.println("\n    Cannot open input file: " + filename);
            return 0;
        }
        
        System.out.println("\nReading data from file.....");
        while (fin.hasNextLine() && !arrayFull) {
            //read one line of data from the file
            licensePlate = fin.next();
            speedLimit = fin.nextInt();
            clockedSpeed = fin.nextInt();
            SpeedingTicket oneTicket = new SpeedingTicket(licensePlate, speedLimit
                    , clockedSpeed);
            // try to add the speeding ticket object to the array
            try {
                this.addSpeedingTicket(oneTicket);   
            }
            catch (ArrayIndexOutOfBoundsException iae) {
                System.err.println( iae.getMessage()   );
                System.err.println("     No more data can be read from the "
                        + "file!!! ");
                arrayFull = true;//to exit while loop
            } 
        }
        fin.close();
        System.out.print("Data stored for " + myTicketCount + " tickets");
        return myTicketCount;
    }
    
    /**
     * calculate and store fines 
     * @param fine array for storing calculated fines
     */
    public void calculateAndStoreFines(double [] fine) {
        final int COURT_COST = 45;
        final double FEE_RATE_LOW = 4.25;
        final double FEE_RATE_MED = 6.0;
        final double FEE_RATE_HIGH = 8.1;
        final int LOW_TO_MED_SPLIT = 10;
        final int MED_TO_HIGH_SPLIT = 20;
        double ticketFine;
        double clockedSpeed;
        double speedLimit;
        double speedDelta;
                
                
        for (int i = 0; i < myTicketCount; i++) {
              clockedSpeed = myTicketData[i].getMyClockedSpeed();
              speedLimit = myTicketData[i].getmySpeedLimit();
              speedDelta = clockedSpeed - speedLimit;
              ticketFine = COURT_COST;
              
              if (speedDelta <= LOW_TO_MED_SPLIT) {
                  ticketFine = speedDelta * FEE_RATE_LOW;
              }
              
              else if (speedDelta <= MED_TO_HIGH_SPLIT) {
                  ticketFine = speedDelta * FEE_RATE_MED;
              }
              
              else {
                   ticketFine = speedDelta * FEE_RATE_HIGH;
              }
              fine[i] = ticketFine;              
        }
    }
    
    /**
     * generates a fine report
     * @param fine array containing fine data
     */
    public void createFinesReport(double [] fine) {
        double totalFine = 0;
        
        System.out.print("Enter the name of the report output file: ");
        String output = in.next();
        
        File finesReport = new File (output);
        PrintWriter fout = null;
        
        try {
            fout = new PrintWriter(output);
            
        } catch (Exception e) {
            System.err.println("    Cannot open input file: " + output);
            System.err.println(", so fine report will not be created.");
        }
        
        //loop to write output fine and plate data
        for (int i = 0; i < myTicketCount; i++) {
            String plate = myTicketData[i].getMyLicensePlate();
            fout.printf(plate + "%9.2f%n", 
                    fine[i]);
            totalFine += fine[i];
        }
        fout.printf("total %9.2f%n", totalFine); 
        
        fout.close();
    }
    
    
    
    
    
    
}
