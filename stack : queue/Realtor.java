package cs310laird;
import java.util.Objects;

/**contains a methods to act on realtor objects
 *
 * @author christopherlaird
 * @version 1, assn 1
 */
public class Realtor {
    private String myLicense,
                   myFirstName,
                   myLastName,
                   myPhoneNumber;
    private double myComission; 
    
    /**
    * 
    * @param line String array of data read in from file
    */
    public Realtor(String[] line) {
        double comission;
        
        if (line[1].equals("ADD")) {
            this.myLicense = line[2];
            this.myFirstName = line[3];
            this.myLastName = line[4];
            this.myPhoneNumber = line[5];
            comission = Double.parseDouble(line[6]);
            this.myComission = comission;
        }
        else {
            this.myLicense = line[2];
        }
    }    
    

    /**constructor with paramaters
     *
     * @param myLicense
     * @param myFirstName
     * @param myLastName
     * @param myPhoneNumber
     * @param myComission
     */
    public Realtor(String myLicense, String myFirstName, String myLastName, 
            String myPhoneNumber, double myComission) {
        this.myLicense = myLicense;
        this.myFirstName = myFirstName;
        this.myLastName = myLastName;
        this.myPhoneNumber = myPhoneNumber;
        this.myComission = myComission;
    }

    /**getter
     *
     * @return license info
     */
    public String getMyLicense() {
        return myLicense;
    }

    /**getter
     *
     * @return first name
     */
    public String getMyFirstName() {
        return myFirstName;
    }

    /**getter
     *
     * @return last name
     */
    public String getMyLastName() {
        return myLastName;
    }

    /**getter
     *
     * @return phone number
     */
    public String getMyPhoneNumber() {
        return myPhoneNumber;
    }

    /**getter
     *
     * @return commission
     */
    public double getMyCommission() {
        return myComission;
    }

    

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this.equals(obj)) {
            return true;
        }
        if (obj.equals(null)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Realtor other = (Realtor) obj;
        if (Double.doubleToLongBits(this.myComission) != 
                Double.doubleToLongBits(other.myComission)) {
            return false;
        }
        if (!Objects.equals(this.myLicense, other.myLicense)) {
            return false;
        }
        if (!Objects.equals(this.myFirstName, other.myFirstName)) {
            return false;
        }
        if (!Objects.equals(this.myLastName, other.myLastName)) {
            return false;
        }
        if (!Objects.equals(this.myPhoneNumber, other.myPhoneNumber)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Realtor{" + "myLicense=" + myLicense + ", myFirstName=" +
                myFirstName + ", myLastName=" + myLastName + ", myPhoneNumber=" 
                + myPhoneNumber + ", myComission=" + myComission + '}';
    }
    
    
    /** verifies license data is formatted correctly
     * 
     * @param myLicense - realtor license identifier 
     * @return true if the string is of the correct format and length
     * otherwise false
     */
    public boolean licenseCheck(String myLicense) {
        final int LENGTH = 9;//corect length of license
        boolean result = true;
        char inspectChar = 0;
        boolean [] licenseIndexCheck = new boolean[10];
        int length;
        
        length = myLicense.length();
        if (length == LENGTH) {
            licenseIndexCheck[9] = true;
        } 
        else return false;
            
        for (int i = 0; i <= 1; i++){
            inspectChar = myLicense.charAt(i);
            if (inspectChar >= 'a' && inspectChar <= 'z'||inspectChar >= 'A' 
                && inspectChar <= 'Z') {
                licenseIndexCheck[i] = true;
            }       
        }
        for (int i = 2; i <= 8; i++){
            inspectChar = myLicense.charAt(i);
            if (inspectChar >= '0' && inspectChar <= '9') {
            licenseIndexCheck[i] = true;
            }       
        }
        
        
        for (int i = 0; i <= LENGTH; i++) {
            if (!licenseIndexCheck[i]) {
                result = false;
            }      
        }
    return result;   
    }
    
    
    /**verifies phone number data is formatted correctly
     *
     * @param myPhoneNumber
     * @return true/false
     */
    public boolean phoneNumberCheck(String myPhoneNumber) {
        final int FIRST_DASH = 3,
                  SECCOND_DASH = 7;
        final int LENGTH = 12;
        boolean result = true;
        
        if (myPhoneNumber.length() != LENGTH) {
        return false;
        }
        
        if (myPhoneNumber.charAt(FIRST_DASH) != '-' || 
                myPhoneNumber.charAt(SECCOND_DASH) != '-') {
            result =  false;
        }
        for (int i = 0; i <= 2; i++) {
            if  (myPhoneNumber.charAt(i) < '0' && myPhoneNumber.charAt(i) > 
                    '9') {
                result =  false;
            }
        }    
            
        for (int i = 4; i <= 6; i++) {
            if  (myPhoneNumber.charAt(i) < '0' && myPhoneNumber.charAt(i) > 
                    '9') {
                result =  false;
            }
        }
        
        for (int i = 8; i <= 11; i++) {
            if  (myPhoneNumber.charAt(i) < '0' && myPhoneNumber.charAt(i) > 
                    '9') {
                result =  false;
            }
        }    
    return result;    
    }

}
