/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs310laird;

/**
 *
 * @author christopherlaird
 */
public class CarStackImpl {
    private final int LUX_CAR_MAX = 3;
    private final int BASIC_CAR_MAX = 4;
    private int[] basicLot; 
    private int[] luxLot; 
    private int basicTop;
    private int luxTop;
    private final int  BASIC_MIN= 1;
    private final int  BASIC_MAX= 4;
    private final int LUX_MIN = 5;
    private final int LUX_MAX = 7;
     
    
    /**
     *constructor
     */
    public CarStackImpl() {
        basicLot = new int[BASIC_CAR_MAX];
        
        basicLot[0] = BASIC_MIN;
        basicLot[1] = BASIC_MIN + 1;
        basicLot[2] = BASIC_MIN + 2;
        basicLot[3] = BASIC_MAX;
        basicTop = BASIC_CAR_MAX - 1;
        
        luxLot = new int[LUX_CAR_MAX];
        luxLot[0] = LUX_MIN;
        luxLot[1] = LUX_MIN + 1;
        luxLot[2] = LUX_MAX;
        luxTop = LUX_CAR_MAX - 1;
    }
    
    /**push a car to the basic stack
     * 
     * @param basic car to push
     */
    public void pushBasic(int basic){//add to stack
        if (!this.isBasicFull()) {
            basicTop++;
            basicLot[basicTop] = basic; 
        }
        else {
            System.out.println("The basic car lot is full");
        }
    }

    /**getter
     * 
     * @return  basic lot array
     */
    public int[] getBasicLot() {
        return basicLot;
    }

    /**getter
     * 
     * @return  luxury lot array
     */
    public int[] getLuxLot() {
        return luxLot;
    }
    
    
    
   /**pop a car from basic stack
    * 
    * @return car popped from the stack
    */ 
    public int popBasic() { //remove from stack
        int basicCarNum = -1;
        basicCarNum = basicLot[basicTop];
        basicLot[basicTop] = -1;
        basicTop--;
        return basicCarNum;
    }
    
   /** empty check
    * 
    * @return  t/f
    */ 
    public boolean isBasicEmpty() {
        boolean result = false;
        if (basicTop < 0) {
            result = true;
        }
        return (result);
    }
    /**full check 
     * 
     * @return  t/f
     */
    public boolean isBasicFull() {
        boolean result = false;
        if (basicTop >= BASIC_CAR_MAX - 1) {
            result = true;
        }
        return (result);
    }
    
    /**push a luxury car from the stack
     * 
     * @param lux number car to push 
     */
    public void pushLux(int lux){
        if (luxTop < luxLot.length) {
            luxTop++;
            luxLot[luxTop] = lux; 
        }
        else {
            System.out.println("The luxury car lot is full");
        }
    }
    /**pop luxury car from stack
     * 
     * @return  number of popped car
     */
    public int popLux() {
        int luxCarNum = -1;
        luxCarNum = luxLot[luxTop];
        luxLot[luxTop] = -1;
        luxTop--;
        return luxCarNum;
    }
    /**empty check 
     * 
     * @return  t/f
     */
    public boolean isLuxEmpty() {
        boolean result = false;
        if (luxTop < 0) {
            result = true;
        }
        return (result);
    }
    
    /**full check 
     * 
     * @return  t/f
     */
    public boolean isLuxFull() {
        boolean result = false;
        if (luxTop >= LUX_CAR_MAX - 1) {
            result = true;
        }
        return (result);
    }
    
    /**search for a basic car in the stack
     * 
     * @param car search car
     * @return  car index       - 1 if not found
     */
    public boolean basicSearch(int car) {
        boolean result = false;
        for (int i = basicTop; i > -1; i--) {
            if (basicLot[i] == car) {
                result = true;
            }
        }
        return result;
    }
    
    /**search for a luxury car in stack
     * 
     * @param car search car
     * @return  index of car       -1 if not found
     */
    public boolean luxSearch(int car) {
        boolean result = false;
        for (int i = luxTop; i > -1; i--) {
            if (luxLot[i] == car) {
                result = true;
            }
        }
        return result;
    }
}
