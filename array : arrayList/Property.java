package cs310laird;
import java.util.Objects;

/**class contains instance methods pertaining to property objects
 *
 * @author christopherlaird
 * @version 1, assn1 
 */
public class Property {
    private int mymls;
    
    private String myAdress,
                   myCity,
                   myState,
                   myRealitorLicense,
                   sell;
    
    private int myZip,
                myNumBed;
    
    private double myNumBath;
    private boolean sold;
    private double myAskingPrice;
    
    
/**constructor with parameters
 * 
 */
    
    public Property() {
    }
    
    /**constructor w/o parameters
     * 
     * @param line
     */            
    public Property(String[] line) {
        int mls,
            zip,
            numBed;
        
        double numBath,
               askingPrice;
        
        boolean sold;
        
        if (line[1].equals("ADD")) {
            mls = Integer.parseInt(line[2]);
            this.mymls = mls;
            this.myRealitorLicense = line[3];
            this.myAdress = line[4];
            this.myCity = line[5];
            this.myState = line[6];
            zip = Integer.parseInt(line[7]);
            this.myZip = zip;
            numBed = Integer.parseInt(line[8]);
            this.myNumBed = numBed;
            numBath = Double.parseDouble(line[9]);
            this.myNumBath = numBath;
            this.sell = line[10];
            askingPrice = Double.parseDouble(line[11]);
            this.myAskingPrice = askingPrice;
        }
        else {
            mls = Integer.parseInt(line[2]);
            this.mymls = mls;
        }
    }

    /**getter
     *
     * @return MLS value
     */
    public int getMyMLS() {
        return mymls;
    }

    /**getter
     *
     * @return license data
     */
    public String getMyRealitorLicense() {
        return myRealitorLicense;
    }

    /**getter
     *
     * @return address info
     */
    public String getMyAdress() {
        return myAdress;
    }

    /**getter
     *
     * @return city name
     */
    public String getMyCity() {
        return myCity;
    }

    /**getter
     *
     * @return state name
     */
    public String getMyState() {
        return myState;
    }

    /**getter
     *
     * @return zip code
     */
    public int getMyZip() {
        return myZip;
    }

    /**getter
     *
     * @return number of bedrooms
     */
    public int getMyNumBed() {
        return myNumBed;
    }

    /**getter
     *
     * @return number of bathrooms
     */
    public double getMyNumBath() {
        return myNumBath;
    }

    /**getter
     *
     * @return sold (T/F)
     */
    public boolean isSold() {
        boolean result;
        if (sell.equals("Y")) {
            sold = true;
        }
        else {
            sold = false;
        }
        return sold;
    }

    /**getter
     *
     * @return asking price
     */
    public double getMyAskingPrice() {
        return myAskingPrice;
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
        final Property other = (Property) obj;
        if (this.mymls != other.mymls) {
            return false;
        }
        if (this.myRealitorLicense != other.myRealitorLicense) {
            return false;
        }
        if (this.myZip != other.myZip) {
            return false;
        }
        if (this.myNumBed != other.myNumBed) {
            return false;
        }
        if (Double.doubleToLongBits(this.myNumBath) != 
                Double.doubleToLongBits(other.myNumBath)) {
            return false;
        }
        if (this.sold != other.sold) {
            return false;
        }
        if (Double.doubleToLongBits(this.myAskingPrice) != 
                Double.doubleToLongBits(other.myAskingPrice)) {
            return false;
        }
        if (!Objects.equals(this.myAdress, other.myAdress)) {
            return false;
        }
        if (!Objects.equals(this.myCity, other.myCity)) {
            return false;
        }
        if (!Objects.equals(this.myState, other.myState)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Property{" + "myMLS=" + mymls + ", myRealitorLicense=" 
                + myRealitorLicense + ", myAdress=" + myAdress + ", myCity=" 
                + myCity + ", myState=" + myState + ", myZip=" + myZip 
                + ", myNumBed=" + myNumBed + ", myNumBath=" + myNumBath 
                + ", sold=" + sold + ", myAskingPrice=" + myAskingPrice + '}';
    }
    
    /**verify data format
     *
     * @param myMLS
     * @return true / false
     */
    public boolean checkMLS(int myMLS) {
        final int LENGTH = 7;
        boolean result = true;
        
        if (Integer.toString(myMLS).length() != LENGTH) {
            result = false;
        } 
    return result;    
    }
    
    /**verify data format 
     *
     * @param myState
     * @return true / false
     */
    public boolean checkState(String myState) {
        boolean result = true;
        
        switch (myState) {
            case ("CO") :
                break;
            case ("WY") :
                break;
            default :
                result = false;
        }  
    return result;    
    }
    
    /** check zip code data format
     *
     * @param myZip
     * @return true false
     */
    public boolean checkZip(int myZip) {
        final int LENGTH = 5;
        boolean result = true;
        String zip = Integer.toString(myZip);
        if (zip.length() !=  LENGTH)
            return false;
        
        if (zip.charAt(0) != '8') {
            return false;
        }
        
        switch (zip.charAt(1)) {
            case ('0') :
                break;
            case ('1') :
                break;
            case ('2') :
                break;
            case ('3') :
                break;    
            default :
                result = false;
        }
    return result;
    }    
}

