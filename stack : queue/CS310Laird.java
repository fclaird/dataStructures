/*
 * This progrqam reads data in from a file about the details relating to 
 * realitors and realestate.  It checks some of the values for the correct 
 * formatting it then stores this data in two locations, an AttayList of 
 * Realtors and an arry of properties.  
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
public class CS310Laird {

    /**
     *
     */
    public static RealtorLogImpl realtorList = new RealtorLogImpl();

    /**
     *
     */
    public static PropertyLogImpl propertyList = new PropertyLogImpl();

    /**
     *
     */
    public static PrintImpl printImpl = new 
        PrintImpl(realtorList, propertyList);

    /**
     *
     */
    public static CarStackImpl carStackImpl = new CarStackImpl();

    /**
     *
     */
    public static RealtorQueueImpl topRealtorQueueImpl = new RealtorQueueImpl();

    /**
     *
     */
    public static RealtorQueueImpl standardRealtorQueueImpl = new 
        RealtorQueueImpl();

    /**
     *
     */
    public static VehicleUsageImpl vehicleUsageList = new 
        VehicleUsageImpl(realtorList);
    
    /**main
     * processes the input file and generates a report on the data 
     * 
     * @param args 
     */
    public static void main(String[] args) {
        final String OUTPUT_FILENAME_B = "output/assn3finalReport.txt";
        
        processFile();
        realtorList.traverseDisplay();
        propertyList.traverseDisplay();
        realtorList.cleanUp(propertyList);
        propertyList.cleanUp();
        createReport(OUTPUT_FILENAME_B);//(clean)
        processCarInfo();
        createCarUsageReport();
        
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
        final String INPUT_FILE = "input/assn4input2.txt";
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
    
    /**attempts to remove a car from the lot
     * 
     * @param topSeller y/n
     * @param realtor object
     * @return  success or failure
     */
    public static boolean useCar(boolean topSeller, Realtor realtor) {
        int use = -1;
        boolean success = false;
        if (topSeller) {
            if (!carStackImpl.isLuxEmpty()) {
                use = carStackImpl.popLux();
                vehicleUsageList.addLux(realtor, use);
                success = true;
            }
            else if (!carStackImpl.isBasicEmpty()) {
                use = carStackImpl.popBasic();
                vehicleUsageList.addBasic(realtor, use, topSeller);
                success = true;
            }
            else if (carStackImpl.isLuxEmpty()) {
                topRealtorQueueImpl.add(realtor);
                System.out.println("all cars are out realtor with license "
                        + realtor.getMyLicense() + " has been added to the "
                        + "top seller queue");
            }
            else {
                success = false;
            }
        }
        else {
            if (!carStackImpl.isBasicEmpty()) {
                    use = carStackImpl.popBasic();
                    vehicleUsageList.addBasic(realtor, use, topSeller);
                    success = true;
            }
            else if (carStackImpl.isBasicEmpty()) {
                standardRealtorQueueImpl.add(realtor);
                System.out.println("all cars are out of the basic lot realtor "
                        + "with license " + realtor.getMyLicense() + " has been"
                        + " added to the standard  seller queue");
            }
            else {
                success = false;
            }
        }
        return success;
    }
    
    /**returns a car to the lot
     * 
     * @param car number of the car to return
     * @return  success or failure
     */
    public static boolean returnCar(int car) {
        boolean success = false;
        if (car > -1 && car < 4 && !carStackImpl.basicSearch(car)) {//basic
            carStackImpl.pushBasic(car);
            success = true;
        }
        else if (car > 3 && car < 7) {//lux
            carStackImpl.pushLux(car);
            success = true;
        }
        else if (carStackImpl.basicSearch(car) || carStackImpl.luxSearch(car)) {
            System.out.println("The car numbered " + car + " is allready in the"
                    + " lot and cannot be returned again.");
        }
        return success;
    }
    
    /**process the input car data file
     *
     */
    public static void processCarInfo() {
        final String CAR_INFO = "input/carInfo.txt";
        
        String inputLine;
        
        String [] line;
        
        int count = 0,
            basic,
            lux;
        
        final double TOP_SELLER = 1000000;
        
        boolean topSeller = false,
                success,
                known;
        
        Realtor realtor;
   
        File dataFile = new File(CAR_INFO);
        Scanner fin = null;
        
        try {
        fin = new Scanner(dataFile);
        } 
        catch (FileNotFoundException e) {
        System.out.println("File not found exception for " + CAR_INFO 
                + "\n" + e);
        System.exit(1);
        }
        
        System.out.println("Reading data from file " + CAR_INFO);
        while (fin.hasNextLine()) {
            inputLine = fin.nextLine();
            line = inputLine.split(" ");
            if (line[0].equals("REQUEST")) {
                //compares listed numbers to the cutoff
                realtor = realtorList.get(line[1]);
                
                if (realtor != null) {
                    if (propertyList.totalPropertyValue(line[1]) > TOP_SELLER) {
                        topSeller = true;
                        success = useCar(topSeller, realtor);
                        if (!success) {
                            topRealtorQueueImpl.add(realtor);
                            System.out.println(realtor.getMyFirstName() + " " 
                                    + realtor.getMyLastName() + " is waiting "
                                    + "in the top seller queue ");
                        }
                    }
                    else {
                        topSeller = false;
                        success = useCar(topSeller, realtor);
                        if (!success) {
                            standardRealtorQueueImpl.add(realtor);
                        }
                    }
                }
                else {
                    System.out.println("unknown realtor " 
                            + line[1] + " not allowd access to "
                            + "the cars.  request ignored.");
                }
            }
            else if ((line[0].equals("RETURN"))) {
                realtor = realtorList.get(line[1]);
                if (realtor == null) {
                    System.out.println("realtor " 
                            + line[1] + " cannot return a car  they are not"
                            + "in the list.  request ignored.");
                }
                else {
                    //get the car number associated with this realtor
                    basic = vehicleUsageList.findBasic(realtor); 
                    lux = vehicleUsageList.findLux(realtor);

                    if (basic != -1) {
                        returnCar(basic);  
                    }
                    else if (lux != -1) {
                        returnCar(lux);
                    }
                    else {
                        System.out.println("realtor " 
                                + realtor.getMyLicense() + " cannot return a "
                                + "car that they do not have.  request ignored"
                                + ".");
                    }
                } 
            }
        }
        System.out.println("Done reading file. " + count + " lines read\n");
    }
   
    
    /**
     * generates a report and exports it to the desired file
     * @param file
     */
    public static void createReport(String file) {
            String clean = "\nCreating Clean Report.....";
            System.out.println(clean);
        
        printImpl.createReport(file); 
    }  
    
    /**searches for realtor 
     * 
     * @param realtor object being sought
     * @return t/f
     */
    public static boolean isRealtorKnown(Realtor realtor) {
        boolean result = true;
        Realtor search;
        search = realtorList.get(realtor.getMyLicense());
        if (search == null) {
            result = false;
        }
        return result;
    }
    
    /**
     * generates the car usage report
     */
    public static void createCarUsageReport() {
        Realtor basicUsage[] = vehicleUsageList.getBasicUsage();
        Realtor luxUsage[] = vehicleUsageList.getLuxUsage();
        int basic[] = carStackImpl.getBasicLot();
        int lux[] = carStackImpl.getLuxLot();
        final int BASIC_MAX_INDEX = 3;
        final int LUX_MAX_INDEX = 2;
        int car = -1;
        String file = "output/carUsageReport.txt";
        Realtor realtor;
        RealtorNode node;
        
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
        
        fileOut.println("\nCAR USAGE REPORT");
        for (int i = 0; i <= BASIC_MAX_INDEX; i++) {
            car = i + 1;
            if (basicUsage[i] != null) {
                fileOut.println(basicUsage[i].getMyFirstName() 
                        + " " + basicUsage[i].getMyLastName() 
                        + " is using car number " + car);
            }
        }
        for (int i = 0; i <= LUX_MAX_INDEX; i++) {
            car = i + 1;
            if (luxUsage[i] != null) {
                fileOut.println(luxUsage[i].getMyFirstName() 
                        + " " + luxUsage[i].getMyLastName() 
                        + " is using car number " + car);
            }
        }
        fileOut.println("\nAVILABLE CARS");
        fileOut.println("\n     BASIC CARS");
        if (!carStackImpl.isBasicEmpty()) {
            for (int i = 0; basic[i] != -1 && i < BASIC_MAX_INDEX; i++) {
                fileOut.println("\n          basic car number " + basic[i] 
                        + " is avilable");
            }  
        }
        else {
            fileOut.println("\n          no basic cars are avilable");
        }
        
        fileOut.println("\n     LUXURY CARS");
        if (!carStackImpl.isLuxEmpty()) {
            for (int i = 0; lux[i] != -1 && i < LUX_MAX_INDEX; i++) {
                fileOut.println("\n          luxury car number " + lux[i] 
                        + " is avilable");
            }  
        }
        else {
            fileOut.println("\n          no luxury cars are avilable");
        }
        fileOut.println("\nTOP SELLER QUEUE");
        node = topRealtorQueueImpl.getHead();
        if (node == null) {
            fileOut.println("there are no top selling realtors waiting");   
        }
        else {
            fileOut.println(node.getRealtor().getMyFirstName() + " " 
                + node.getRealtor().getMyLastName() + "is waiting");
        
            while (node != null) {
                node = node.getNextNode();
                realtor = node.getRealtor();
                fileOut.println(realtor.getMyFirstName() + " " 
                    + realtor.getMyLastName() + "is waiting");
            }
        }
        
        fileOut.println("\nSTANDARD REALTOR QUEUE");
        node = standardRealtorQueueImpl.getHead();
        if (node == null) {
            fileOut.println("there are no standatd realtors waiting");
        }
        else {
            fileOut.println(node.getRealtor().getMyFirstName() + " " 
                    + node.getRealtor().getMyLastName() + "is waiting");

            while (node.getRealtor() != null) {
                node = node.getNextNode();
                fileOut.println(node.getRealtor().getMyFirstName() + " " 
                    + node.getRealtor().getMyLastName() + "is waiting");
            } 
        }
    }
}