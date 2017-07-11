/*
 * This progrqam reads data in from 2 files about the details relating to 
 * realitors and real estate.  The first is general information on realtors and 
 * properties and their details.  The seccond is a list of Realtor license 
 * numbers and their associated property mls numbers.  A report is generated 
 * as a result of the data in the 2 files containing information form both.
 */
package cs310laird;
import java.io.*;
import java.util.Scanner;

/**Main Class - contains the main method and various static methods that act
 * on the instance methods
 *
 * @author christopherlaird
 * @version 1, Assn 5
 */
public class CS310Laird {

    /**instantiates a realtor log
     *
     */
    public static RealtorLogImpl realtorList = new RealtorLogImpl();

    /**instantiates a property log
     *
     */
    public static PropertyLogImpl propertyList = new PropertyLogImpl();

    /**instantiates a print implementation object
     *
     */
    public static PrintImpl printImpl = new PrintImpl();

    /**main
     * processes the input files and generates a report on the status of 
     * requested realtors and properties  
     * 
     * @param args main arguments
     */
    public static void main(String[] args) {
        final String OUTPUT = "output/assn5salesReport.txt";
        final String INPUT = "input/realtorRequests1.txt";
        final String INPUT_FILE = "input/assn5input1.txt";
        
        processFile(INPUT_FILE);
        processFile(INPUT, OUTPUT);
        realtorList.displayHash();//wasnt told to use...
        propertyList.displayHash();//same
    }
        
    /**adds a realtor object to the hash set
     * 
     * @param line a string array of data read in from the file
     */
    public static void addRealtor(String[] line) {
        boolean add = false;
        Realtor realtor = new Realtor(line);
        add = realtorList.add(realtor);
        if (add){
            System.out.println("  ADDED: Realtor with license " 
                + realtor.getMyLicense()); 
        }
        else{
            System.out.println("ADD ERROR the realtor log is full no more "
                    + "realtors can be added");
        }   
    }
    
    /**add a property object to the hash map
     * 
     * @param line a string array of data from the input file
     */
    public static void addProperty(String[] line) {
        Property property = new Property(line);
        propertyList.add(property);      
        System.out.println("  ADDED: Property with MLS number " 
                + property.getMyMLS() + " listed by realtor "  
                + property.getMyRealitorLicense());    
    }
    
 
    /**processes an input file
     * 
     * @param INPUT_FILE location and name of file to process
     */
    private static void processFile(String INPUT_FILE){
        String inputLine;
        String [] line;
        int count = 0;
   
        File dataFile = new File(INPUT_FILE);
        Scanner fin = null;
        
        try {
        fin = new Scanner(dataFile);
        } 
        catch (FileNotFoundException e) {
        System.out.println("File not found exception for " + INPUT_FILE 
                + "\n" + e);
        System.exit(1);
        }
        
        System.out.println("Reading data from file " + INPUT_FILE);
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
   
    /**process an input file and generates an out put file
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
                    Property property = propertyList.find(mls);
                    printImpl.printproperty(property, fileOut);
                }
            }   
        } 
        fileOut.close();
    }     
}