
package cs310laird;
import java.io.*;

/**
 * CS310 assn 5 version 1
 * @author christopherlaird
 */
public class PrintImpl {
    private Realtor temp;

    /**constructor: constructs a PrintImpl object
     *  
     */
    public PrintImpl() {

    }
        
     
    /**Prints a line of test to the output file for a  realtor
     * 
     * @param realtor in for the realtor
     * @param fileOut location for the output path
     */
    public void printRealtor(Realtor realtor,PrintWriter fileOut) {
        temp = realtor;
        fileOut.println("Realtor " + realtor.getMyLicense() + ", " 
            + realtor.getMyFirstName() + " " + realtor.getMyLastName()); 
    }    
        
    /**prints a line of text to the output file for a false realtor
     * 
     * @param fileOut output path 
     * @param search the license that could not be found
     */    
    public void printVoidRealtor(PrintWriter fileOut, String search) {   
        fileOut.println("Realtor " + search + " does not exist");
    }   
        
   
    /**prints a lint of test for a property to the file
     * 
     * @param property  data to be displayed
     * @param fileOut output path
     */
    public void printproperty(Property property, PrintWriter fileOut) {  
        if (temp == null){
            //do nothing
        }
        else {
            if (property.isSold()){
            fileOut.println("      Property " + property.getMyMLS() + " is "
                    + "SOLD");
            }
            else{
                 fileOut.println("      Property " + property.getMyMLS() + " is "
                         + "available for $" + property.getMyAskingPrice());
            }
        }   
    } 
}

