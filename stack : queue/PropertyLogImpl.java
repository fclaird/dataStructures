/*
 * creates and manage an unordered array of property objects
 */
package cs310laird;
import java.util.Iterator;
import java.util.LinkedList;
/**
 *
 * @author christopherlaird
 * @version 1, assn2
 */
public class PropertyLogImpl {
    private final LinkedList<Property> propertyLog;
    
    
    /**
     * constructor
     */
    public PropertyLogImpl() {
        propertyLog = new LinkedList<>();
    }
    
    /**
     * getter
     * @return the number of properties in the list
     */
    public int getNumProperties() {
        int size  = propertyLog.size();
        return size;
    }

    /**
     *
     * @return
     */
    public LinkedList<Property> getPropertyLog() {
        return propertyLog;
    }
    
    
    
    /**ADD's a Property object to the log
     * 
     * @param obj - to be added
     * @return T/F success\fail
     */
    public boolean add(Property obj) {  
        propertyLog.add(obj);
        return true;
    }
    
    /**remove a property object from the log associates with a given license 
     * 
     * @param removeLicense - the search query
     * @return T/F success/failure
     */
    public boolean remove(String removeLicense) { 
        String inspectLicense;                               
        boolean result = false;
        Property property;
        Iterator it;
        
        it = propertyLog.iterator();
        while (it.hasNext()) {
            property = (Property) it.next();
            inspectLicense = property.getMyRealitorLicense();
            if (removeLicense.equals(inspectLicense)) {
                
                it.remove();
                System.out.println("   Removing property " 
                        + property.getMyMLS() + " listed by realtor " 
                        + property.getMyRealitorLicense());
                result = true;
            } 
        }
        return result;
    }
    
    /**remove a property object from the log associates with a given MLS
     * 
     * @param searchMls - search query
     * @return T/F Pass/Fail
     */
    public boolean remove(int searchMls) {
        int inspectMls;
        boolean result = false;                                           
        int i = 0;
        
        Iterator it = propertyLog.iterator();
        while (it.hasNext()) {
            
            inspectMls = propertyLog.get(i).getMyMLS();
            if (inspectMls == searchMls) {
                it.next();
                it.remove();
                result = true;
            }
            i++; 
        }
        return result;
    }
        
    
    /**searches the log for the provided MLS
     * 
     * @param searchMls - search query
     * @return T/F  if searchMls is found in the log returns false
     *          of not found return true
     */
    public boolean isMlsUnique(int searchMls) {//search
        int inspectMls;
        boolean result = true;                                                
        //int i = 0;
        Property property;
        
        Iterator it = propertyLog.iterator();
        while (it.hasNext()) {
            property = (Property) it.next();
            inspectMls = property.getMyMLS();
            if (inspectMls == searchMls) {
                return false;  
            } 
        }
        return result;
    }
    
    /** provides the number of properties in the log with a given license
     * 
     * @param searchLicense - search query
     * @return the number found
     */
    public int numberOfProperties(String searchLicense) {
        int count = 0;
        Property property;
        String inspectLicense;
        
        Iterator it = propertyLog.iterator();
        while (it.hasNext()) {
            property = (Property) it.next();
            inspectLicense = property.getMyRealitorLicense();
            if (searchLicense.equals(inspectLicense)) {
                count++;
            }
        }
        return count;
    }

    
    /**calculates the total value of all properties in the log
     * 
     * @return total value
     */
    public double totalPropertyValue() {//return a sum of all asking prices
        double sum = 0,
               price;
        Property property;
        
        
        Iterator it = propertyLog.iterator();
        while (it.hasNext()) {
            property = (Property) it.next();
            price = property.getMyAskingPrice();
            sum += price; 
        }
        return sum;
    }
    
    /**calculates the total value associated with a give license
     * 
     * @param searchLicense search query
     * @return total value
     */
    public double totalPropertyValue(String searchLicense) {
        double sum = 0;
        String inspectLicense;
        Property property;
        
        Iterator it = propertyLog.iterator();
        while (it.hasNext()) {
            property = (Property) it.next();
            inspectLicense = property.getMyRealitorLicense();
            if (searchLicense.equals(inspectLicense)) {
                sum += property.getMyAskingPrice();
            }
        }
        return sum;
    }
       
    /**
     *
     */
    public void traverseDisplay() {
        System.out.println("\nProperty Log:");
        Iterator it = propertyLog.iterator();
        while (it.hasNext()) {
            System.out.println(it.next().toString());
        }
        
    }
    
    /**
     *
     */
    public void cleanUp() {
        Property property;
        
        boolean checkMls;
        
        Iterator it = propertyLog.iterator();
        while (it.hasNext()) {
            property = (Property) it.next();
            checkMls = property.checkMLS(property.getMyMLS());

            
            if (!checkMls) {
                this.remove(property.getMyRealitorLicense());
                System.out.println("invalid MLS number for property " 
                        + property.getMyMLS() + "-- Deleting from log");
                break;
            }
            
        }
    }
    
    
    
}
