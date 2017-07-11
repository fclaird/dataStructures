package cs310laird;
/**
 *
 * @author christopherlaird
 */
public class VehicleUsageImpl {
    private final int LUX_CAR_MAX = 3;
    private final int BASIC_CAR_MAX = 4;
    private Realtor[] basicUsage; 
    private Realtor[] luxUsage; 
    private RealtorLogImpl realtorList;
    private int luxIndex = 0;
    private int basicIndex = 0;
    private final int LUX_MAX_INDEX = 2;
    private final int BASIC_MAX_INDEX = 3;
    
    
    /**
     *
     * @param list
     * @param list
     */
    public VehicleUsageImpl(RealtorLogImpl list) {
        basicUsage = new Realtor[BASIC_CAR_MAX];
        luxUsage = new Realtor[LUX_CAR_MAX];
        realtorList = list;
    }

    /**
     * getter
     * @return basic usage array
     */
    public Realtor[] getBasicUsage() {
        return basicUsage;
    }
    
    /**
     * getter
     * @return luxury usage array
     */
    public Realtor[] getLuxUsage() {
        return luxUsage;
    }
    
    
    
    /**adds a car tot he basic usage list
     * 
     * @param basic realtor
     * @param car number
     * @param topSeller  y/n
     */
    public void addBasic(Realtor basic, int car, boolean topSeller){
        boolean sat = false;
        int i = 0;
        if (i >= 0 && i <= 3) {
            basicUsage[car - 1] = basic;
            i++;
            if (topSeller) {
                System.out.println("Top seller " + basic.getMyFirstName() + " " 
                    + basic.getMyLastName() + " has been assigned basic car "
                    + "number " + car + " as there are not luxure cars "
                        + "avilable");
            }
            else {
            System.out.println("Standard realtor " + basic.getMyFirstName() + " " 
                    + basic.getMyLastName() + " has been assigned basic car "
                    + "number " + car);
            }
        }  
    }
    
    /**removes a car from the basic usage list
     * 
     * @param basic realtor
     * @return 
     */
    public boolean removeBasic(Realtor basic) {
        boolean sat = false;
        int index = -1;
        index = this.findBasic(basic);
        if (index > -1 && index < BASIC_CAR_MAX) {
            basicUsage[index] = null;
            System.out.println("Standard realtor " + basic.getMyFirstName() + " " 
                + basic.getMyLastName() + " has returned their basic car");
            sat = true;
        } 
        return sat;
    }
    
    /** checks to see if the basic usage list is full
     * 
     * @return  t/f
     */
    public boolean basicUsageFull() {
        boolean result = true;
        
        for (int i = 0; i < BASIC_CAR_MAX; i++) {
            if (basicUsage[i] == null) {
                return false;
            }
        }
        return (result);
    }
    
    /**adds a luxury car to the usage list
     * 
     * @param lux realtor
     * @param car car number
     * @return  sat / unsat
     */
    public boolean addLux(Realtor lux, int car){
        boolean sat = false;
        for (int i = 0; i < LUX_CAR_MAX && !sat; i++) {
            if (luxUsage[i] == null) {
                luxUsage[i] = lux;
                sat = true;
            } 
        }
        System.out.println("Top seller " + lux.getMyFirstName() + " " 
                    + lux.getMyLastName() + " has been assigned luxury car "
                    + "number " + car);
        return sat;
    }
        
    
    /**removes a car from the usage list
     * 
     * @param lux realtor
     * @return sat/unsat
     */
    public boolean removeLux(Realtor lux) {
        boolean sat = false;
        int index = -1;
        index = this.findLux(lux);
        if (index > -1 && index < LUX_CAR_MAX) {
            luxUsage[index] = null;
            System.out.println("Standard realtor " + lux.getMyFirstName() + " " 
                + lux.getMyLastName() + " has returned their luxury car");
            sat = true;
        }      
        return sat;
    }
    
    
    /**checks to see if the luxury usage list is full
     * 
     * @return t/f
     */
    public boolean luxUsageFull() {
        boolean result = false;
        if (luxIndex == LUX_CAR_MAX - 1) {
            return true;
        }
        
        return (result);
    }
    
    /**searches for a realtor in the basic list
     * 
     * @param realtor
     * @return  location      -1 if not found
     */
    public int findBasic(Realtor realtor) {
        int index = -1;
        String realtorLicense = "";
        boolean found = false;
        int i = BASIC_MAX_INDEX;
        
        realtorLicense = realtor.getMyLicense();
        while (i > -1 && !found) {
            if (basicUsage[i] == null) {
                i--;
            }
            else {
                if (basicUsage[i].getMyLicense().equalsIgnoreCase
                        (realtorLicense)) {
                    
                    found = true;
                    index = i;
                    i--;
                }
                else {
                    i--;
                }
            }
        }
        return index;
    }
       
    /**searches for a realtor in the the luxury list
     * 
     * @param realtor object being sought
     * @return item location      -1 if not found
     */   
    public int findLux(Realtor realtor) {   
       int index = -1;
        String realtorLicense = "";
        boolean found = false;
        int i = LUX_MAX_INDEX;
        
        realtorLicense = realtor.getMyLicense();
        while (i > -1 && !found) {
            if (luxUsage[i] == null) {
                i--;
            }
            else {
                if (luxUsage[i].getMyLicense().equalsIgnoreCase
                        (realtorLicense)) {
                    
                    found = true;
                    index = i;
                    i--;
                }
                else {
                    i--;
                }
            }
        }
        return index;
    }    
}


    
    

