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
        
    //add method generate carUsage.txt file    
     
    /**
     * generates report and exports it to the report file 
     * @param file
     */
    public void createReport(String file) {    
        Realtor realtor;
        Property property;
        String realtorLicense;
        String propertyLicense;
        int i = 0;
        RealtorNode realtorNode;
        
        
        File outputFile = null; 
        PrintWriter fileOut = null;
        
        
        outputFile = new File(file);
        

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
        
        System.out.println("Report is complete --- located in file:" + 
                    file);
    fileOut.close(); 
    }   
}

