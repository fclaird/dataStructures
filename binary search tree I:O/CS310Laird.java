/*
 * This progrqam reads data in from 2 files about the details relating to 
 * realitors and real estate.  The first is general information on realtors and 
 * properties and their details.  The seccond is a list of Realtor license 
 * numbers and their associated property mls numbers.  A report is generated 
 * as a result of the data in the 2 files containing information from both.  In 
 * this version the realtor data is stored in a binary search tree and the 
 * property data i stored in a TreeMap from the Java collection.
 */
package cs310laird;
import java.io.*;
import java.util.*;


/**Main Class - contains the main method and various static methods that act
 * on the instance methods
 *
 * @author christopherlaird
 * @version 1, Assn 6
 */
public class CS310Laird {
    public static RealtorLogImpl realtorList = new RealtorLogImpl();
    public static PropertyLogImpl propertyLog = new PropertyLogImpl();
    public static PrintImpl printImpl = new PrintImpl();
    
    /**main
     * processes the input files and generates a report on the status of 
     * requested realtors and properties  
     * 
     * @param args main arguments
     */
    public static void main(String[] args) {
        final String OUTPUT = "output/assn6salesReport.txt";
        final String INPUT = "input/realtorRequests1.txt";
        final String INPUT_FILE = "input/assn6input2.txt";
        
        processFile(INPUT_FILE);
        realtorList.traverseDisplay();
        propertyLog.traverseDisplay();
        processFile(INPUT, OUTPUT);
    }
        
    /**adds a realtor object to the binary search tree
     * 
     * @param line a string array of data read in from the input file
     */
    public static void addRealtor(String[] line) {

        Realtor realtor = new Realtor(line);
        realtorList.insert(realtor);
        System.out.println("  ADDED: Realtor with license " 
            + realtor.getMyLicense()); 
    }
    
    /**add a property object to the TreeMap
     * 
     * @param line a string array of data from the input file
     */
    public static void addProperty(String[] line) {
        Property property = new Property(line);
        propertyLog.getPropertyTree().put(property.getMyMLS(), property);      
        System.out.println("  ADDED: Property with MLS number " 
                + property.getMyMLS() + " listed by realtor "  
                + property.getMyRealitorLicense());    
    }
    
 
    /**processes an input file
     * 
     * @param input location and name of file to process
     */
    private static void processFile(String input){
        String inputLine;
        String [] line;
        int count = 0;
   
        File dataFile = new File(input);
        Scanner fin = null;
        
        try {
        fin = new Scanner(dataFile);
        } 
        catch (FileNotFoundException e) {
        System.out.println("File not found exception for " + input 
                + "\n" + e);
        System.exit(1);
        }
        
        System.out.println("Reading data from file " + input);
        while (fin.hasNextLine()) {
            count++;
            inputLine = fin.nextLine();
            line = inputLine.split(",");
            if (line[0].equals("REALTOR")) {
                addRealtor(line); 
            }
            else if (line[0].equals("PROPERTY")) {
                addProperty(line);
            }
        }
        System.out.println("Done reading file. " + count + " lines read\n");
    }
      
    /**process a second input file and generates a refrence output file
     * 
     * @param input name and location of the input file
     * @param output name and location of the output file
     */
    public static void processFile(String input,String output){
        String inputLine;
        String [] line;
        
        File dataFile = new File(input);
        Scanner fin = null;
        
        try {
        fin = new Scanner(dataFile);
        } 
        catch (FileNotFoundException e) {
        System.out.println("File not found exception for " + input 
                + "\n" + e);
        System.exit(1);
        }
        
        
        File outputFile = null; 
        PrintWriter fileOut = null;
        outputFile = new File(output);
        
        try {
        fileOut = new PrintWriter(outputFile);
        }
        catch (IOException e) {
        System.err.println("Input/Output exception for " + outputFile 
                + "\n" + e);
        System.exit(1);
        }
        
        while (fin.hasNextLine()) {
            inputLine = fin.nextLine();
            line = inputLine.split(" ");
            String license = line[0];
            Realtor realtor = realtorList.find(license);
            if ( realtor == null){
                printImpl.printVoidRealtor(fileOut, license);
            }
            else{
                printImpl.printRealtor(realtor, fileOut);
                for (int i = 1; i < line.length; i++){
                    String mlsString = line[i];
                    int mls = Integer.parseInt(mlsString);
                    Property property = (Property) propertyLog.getPropertyTree()
                            .get(mls);
                    printImpl.printproperty(property, fileOut);
                }
            }   
        } 
        fileOut.close();
    } 
}