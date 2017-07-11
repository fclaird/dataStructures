

/**
 * speeding ticked object for storing data on each ticket
 * java assn 8 version 1
 * @author christopherlaird
 */
public class SpeedingTicket {
    private String myLicensePlate;
    private double mySpeedLimit,
                   myClockedSpeed;
    
    /**
     * constructor
     * @param licensePlate data read from file
     * @param speedLimit data read from file
     * @param clockedSpeed data read from file
     */
    public SpeedingTicket (String licensePlate, double speedLimit, 
            double clockedSpeed) {
        myLicensePlate = licensePlate;
        mySpeedLimit = speedLimit;
        myClockedSpeed = clockedSpeed;
    }
    
    /**
     * provides access to specific data for calculations and distribution
     * @return stored license plate data
     */
    public String getMyLicensePlate() {
        return myLicensePlate;    
    }
    
    /**
     * provides access to specific data for calculations and distribution
     * @return stored speed limit data
     */
    public double getmySpeedLimit() {
        return mySpeedLimit;
    }
    
    /**
     * provides access to specific data for calculations and distribution
     * @return stored actual speed data
     */
    public double getMyClockedSpeed() {
        return myClockedSpeed;
    }
}
