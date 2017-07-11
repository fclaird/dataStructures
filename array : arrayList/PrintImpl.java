/*
 * 
 */
package cs310laird;


import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * CS310 assn 3 version 1
 * @author christopherlaird
 */
public class PrintImpl {
    private final String OUTPUT_FILENAME_A = "output/assn3initialReport.txt";
    private final String OUTPUT_FILENAME_B = "output/assn3finalReport.txt";
    //private RealtorNode realtorLog;
    private LinkedList<Property> propertyLog;
    private RealtorLogImpl realtorLogImpl;
    private PropertyLogImpl propertyLogImpl;
    String realtorLicense,
           propertyLicense;

    /**constructor: constructs a PrintImpl object 
     * 
     * @param realtorLog
     * @param propertyLog
     */
    public PrintImpl(RealtorLogImpl realtorLog, PropertyLogImpl 
            propertyLog) {
        realtorLogImpl = realtorLog;
        propertyLogImpl = propertyLog;
    }
        
        
     
    /**
     * generates report and exports it to the report file
     * @param number to separate the first and second reports 
     */
    public void createReport(int number) {    
        Realtor realtor;
        Property property;
        String realtorLicense;
        String propertyLicense;
        int numProperties = 0,//count of properties for a given realtor
            netNumProperties = 0,
            i = 0;
        RealtorNode realtorNode;
        
        double totalValue = 0,
               netTotalValue = 0;
        
        File outputFile = null; 
        PrintWriter fileOut = null;
        
        if (number == 1) {
            outputFile = new File(OUTPUT_FILENAME_A);
        }
        else if (number == 2) {
            outputFile = new File(OUTPUT_FILENAME_B);
        }

        try {
        fileOut = new PrintWriter(outputFile);
        }
    
        catch (IOException e) {
        System.err.println("Input/Output exception for " + outputFile 
                + "\n" + e);
        System.exit(1);
        }
        
        realtorNode = realtorLogImpl.getTop();
        while (realtorNode.getNextNode() != null) {
            realtor = realtorNode.getRealtor();
            realtorLicense = realtor.getMyLicense();
            fileOut.println(realtor.getMyLicense() + " " 
                + realtor.getMyLastName() + ", " 
                + realtor.getMyFirstName() + "\n");

            propertyLog = propertyLogImpl.getPropertyLog();
            Iterator it = propertyLog.iterator();
            while (it.hasNext()) {
                property = (Property) it.next();
                propertyLicense = property.getMyRealitorLicense();
                if (propertyLicense.equals(realtorLicense)) {
                    fileOut.print("        " + property.getMyMLS() 
                            + "          "
                            + property.getMyAdress() + "  " 
                            + property.getMyNumBed() + "/" 
                            + property.getMyNumBath() + "  $  " 
                            + property.getMyAskingPrice() + "  ");    
                    if (property.isSold()) {
                        fileOut.print("SOLD");
                    }
                    fileOut.println("\n                    " 
                            + property.getMyCity() + ",  " 
                            + property.getMyState() + " " 
                            + property.getMyZip() + "\n");
                }
            }
            realtorNode = realtorNode.getNextNode();
            fileOut.println("    Number of property listings for realtor: " 
                    + propertyLogImpl.numberOfProperties(realtorLicense));
            fileOut.println("    Total sales value of property listings for "
                    + "realtor:  $ " + propertyLogImpl.totalPropertyValue(
                            realtorLicense) +"\n");
        }
        fileOut.println("Total number of property listings for all realtors = "
                + propertyLogImpl.getNumProperties());
        fileOut.println("Total sales value of property listings for realtors = "
                + "$ " + propertyLogImpl.totalPropertyValue());
        if (number == 1) {
            System.out.println("Report is complete --- located in file:" + 
                    OUTPUT_FILENAME_A);
        }
        else if (number == 2) {
            System.out.println("Report is complete --- located in file:" + 
                    OUTPUT_FILENAME_B);
        }
    fileOut.close(); 
    }   
}

