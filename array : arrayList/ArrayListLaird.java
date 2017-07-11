/*
 * This progrqam reads data in from a file about the details relating to 
 * realitors and realestate.  It checks some of the values for the correct 
 * formatting it then stores this data in two locations, an AttayList of 
 * Realtors and an array of properties.
 */
package cs310laird;
import java.io.*;
import java.util.Scanner;

/**Main Class - contains the main method and various static methods that act
 * on the instance methods
 *
 * @author christopherlaird
 * @version 1, Assn 2
 */
public class ArrayListLaird {
    static RealtorLogImpl realtorList = new RealtorLogImpl();
    static PropertyLogImpl propertyList = 
            new PropertyLogImpl();
    static PrintImpl printImpl = new PrintImpl(realtorList, 
            propertyList);
    
   
    
    /**main
     * processes the input file and generates a report on the data 
     * 
     * @param args 
     */
    public static void main(String[] args) {
        processFile();
        realtorList.traverseDisplay();
        propertyList.traverseDisplay();
        createReport(1);
        realtorList.cleanUp(propertyList);
        propertyList.cleanUp();
        createReport(2);//seccond report (clean)
    }
        
    /**adds a realtor object to the List
     * 
     * @param line a string array of data read in from the file
     */
    public static void addRealtor(String[] line) {
        boolean licenseCheck,
                unique,
                phoneCheck;
        
        Realtor realtor = new Realtor(line);
        licenseCheck = realtor.licenseCheck(realtor.getMyLicense());
        phoneCheck = realtor.phoneNumberCheck(realtor.getMyPhoneNumber());
        if (!licenseCheck || !phoneCheck) {
            System.out.print("    ERROR: Realtor with license number "  
                    + realtor.getMyLicense() + " has an invalid ");
            if (!phoneCheck) {
                System.out.println("Phone number");
            }
            else {
                System.out.println("license number");
            }
        }
        unique = realtorList.isLicenseUnique(realtor.getMyLicense());
        if (unique) {
            realtorList.add(realtor);
            if (!licenseCheck || !phoneCheck) {
                System.out.println("  ADDED: Realtor with license " 
                        + realtor.getMyLicense() + ", reguardless of data "
                        + "errors.");
            }
            else {
                System.out.println("  ADDED: Realtor with license " 
                        + realtor.getMyLicense());
            }
        }
        else {
            System.out.println("    ADD ERROR:  Realtor with license "
            + realtor.getMyLicense() + "is already in the log");
        }
        
    }
    
    /**add a property object to the array
     * 
     * @param line a string array of data from the input file
     */
    public static void addProperty(String[] line) {
        boolean mlsCheck,
                realtorUnique,
                stateCheck,
                zipCheck,
                mlsUnique;
        
        Property property = new Property(line);
        mlsCheck = property.checkMLS(property.getMyMLS());
        stateCheck = property.checkState(property.getMyState());
        zipCheck = property.checkZip(property.getMyZip());
        if (!stateCheck) {
            System.out.println("    ERROR:  Property with MLS number " 
                + property.getMyMLS() + " has an invalid State abbrevation " 
                    + property.getMyState());
        }
        if (!zipCheck) {
            System.out.println("    ERROR:  Property with MLS number " 
                + property.getMyMLS() + " has an invalid zip code "
                    + property.getMyZip());
        }
        if (!mlsCheck) {
            System.out.println("    ERROR:  Property with MLS number " 
                + property.getMyMLS() + " has an invalid MLS number");
        }

        realtorUnique = 
                realtorList.isLicenseUnique(property.getMyRealitorLicense());
        
        if (!realtorUnique) {//realtor is in list so property can be added
            mlsUnique = propertyList.isMlsUnique(property.
                    getMyMLS());
            if (mlsUnique) {
                propertyList.add(property);
                if (!mlsCheck || !stateCheck || !zipCheck) {
                    System.out.println("  ADDED: Property with MLS number " 
                            + property.getMyMLS() + " listed by realtor "  
                            + property.getMyRealitorLicense() + ", reguardless "
                            + "of data errors");
                }
                else {
                    System.out.println("  ADDED: Property with MLS number " 
                            + property.getMyMLS() + " listed by realtor "  
                            + property.getMyRealitorLicense());
                }
            }
            else {
                System.out.println("    ADD ERROR: Property with MLS number "
                        + property.getMyMLS() + " is allready in the list");
            }         
        }
        else {
            System.out.println("    ADD ERROR: Property with MLS number " 
                    + property.getMyMLS() + " had realtor with license number " 
                    + property.getMyRealitorLicense() + ",\n               "
                    + "but there is no such realtor in the log.\n              "
                    + " Therfore the property will not be added to the log.");
        }
    }
    
    /**removes a property from the array
     * 
     * @param line  string array of input data
     */
    public static void removeProperty(String[] line) {
        boolean unique,
                outcome,
                mlsUnique;
        
        Property property = new Property(line);
        mlsUnique = propertyList.isMlsUnique(property.
                getMyMLS());
        if (!mlsUnique) {
            propertyList.remove(property.getMyMLS());
            System.out.println("  DELETED: The property with " 
                    + property.getMyMLS() + " has been removed from the list");   
        }
        else {
            System.out.println("  DEL ERROR: Property with MLS number " 
                    + property.getMyMLS() + " is not in the property log and "
                    + "cannot be deleted");
        }

    }
    
    /**removes a realtor object all all associated properties from the lists
     * 
     * @param line  String array of input data
     */
    public static void removeRealtor(String[] line) {
        boolean unique,
                outcome;
        
        Realtor realtor = new Realtor(line);
        unique = realtorList.isLicenseUnique(realtor.getMyLicense());
        if (!unique) {
            //realtorLinkedList.remove(realtor.getMyLicense());
            System.out.println("  DELETED: realtor with license " 
                    + realtor.getMyLicense() + " has been successfully removed"
                    + " from the realtor log\n             all associated "
                    + "properties will also be removed from the properties "
                    + "log");
            outcome = propertyList.remove(realtor.
                    getMyLicense());
        }
        else {
            System.out.println("    ***   No realtor with " + 
                    realtor.getMyLicense() + " license was found in the log");
        }
    }
    
    /**processes the input file
     * 
     */
    private static void processFile(){
        final String INPUT_FILE = "input/assn3input2.txt";
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
                if (line[1].equals("ADD")) {
                    addRealtor(line);
                }
                else if (line[1].equals("DEL")) {
                    removeRealtor(line);
                }
            }
            else if (line[0].equals("PROPERTY")) {
                if (line[1].equals("ADD")) {
                    addProperty(line);
                }else if (line[1].equals("DEL")) {
                    removeProperty(line);
                }
            }
        }
        System.out.println("Done reading file. " + count + " lines read\n");
    }
    
   
    
    /**
     * generates a report and exports it to the desired file
     * @param number  defines which report
     */
    public static void createReport(int number) {
        String initial = "\nCreating Initial Report...",
               clean = "\nCreating Clean Report.....";
        
        if (number == 1) {
            System.out.println(initial);
            System.out.println("\nCleaning up realtor and property logs...");
        }
        else if (number == 2) {
            System.out.println(clean);
        }
        printImpl.createReport(number); 
    }
    


    
}
