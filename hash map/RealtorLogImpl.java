package cs310laird;

/**implements a hash set of realtor objects
 *
 * @author christopherlaird
 * @version 1 Assn 5
 */
public class RealtorLogImpl {    
    private Realtor[] realtorHashSet;
    private final int MAX_SIZE;
    private int currSize;
    Realtor insp;
    
   
    /**constructs a 23 element hash table of realtor objects with all
     * indices instantiated to null
     * 
     */
    public RealtorLogImpl(){
        MAX_SIZE = 23;
        realtorHashSet = new Realtor[MAX_SIZE];
        currSize = 0;
        for (int i = 0; i < realtorHashSet.length; i++){
            realtorHashSet[i] = null;
        }
    }
    
    
    /**calculate the hash code, attempt to place the object in the location 
     * calculated, if that index is occupied will then iterate through the 
     * array with  a linear probe
     * 
     * @param obj realtor object to add
     * @return boolean success/ failure
     */
    public boolean add(Realtor obj){
        boolean found = false;
        boolean full = false;
        boolean add = false;
        
        Realtor realtor = (Realtor) obj;
        int hashCode = realtor.hashCode();
        hashCode  = hashCode % MAX_SIZE;
        while (!found && currSize < MAX_SIZE) {

            if (realtorHashSet[hashCode] == null){
                realtorHashSet[hashCode] = realtor;
                currSize++;
                found = true;
                add = true;
            }
            else {
                hashCode++;
                if (hashCode >= realtorHashSet.length) {
                    hashCode = 0;
                }
            }
        } 
        return add;
    } 
    
    
    /**finds a realtor in the hash table using the hash code and linear probe
     * 
     * @param license discriminator
     * @return the realtor or null if not located in the table
     */
    public Realtor find(String license){
        boolean found = false;
        boolean done = false;
        int ascii = 0;
        int hashCode;
        int count = 0;
        Realtor sought = null;
        for (int i = 0; i < license.length(); i++){
            ascii = (int) license.charAt(i);// convert to ascii int
            ascii += ascii;  
        }
        hashCode  = ascii % MAX_SIZE;
        while (!found && !done) {
            if (realtorHashSet[hashCode] == null){
                return null;
            }
            if (realtorHashSet[hashCode].getMyLicense().equalsIgnoreCase
                    (license)){
                found = true;
                sought = realtorHashSet[hashCode];
                
            }
            else {
                hashCode++;
                count++;
                if (hashCode >= realtorHashSet.length) {
                    hashCode = 0;
                }
                if (count >= realtorHashSet.length){
                    done = true;
                }
            }    
        }         
        return sought;
    } 
    
    
    /**displays the complete current context of the hash table in the console
     * 
     */
    public void displayHash(){
        System.out.println("\nRealtor Set: ");
        for (int i = 0; i < realtorHashSet.length; i++){
            insp = realtorHashSet[i];
            if (insp == null){
                System.out.println("      Index " + i + " is empty");
            }
            else{
                System.out.println("      Index " + i + " contains Realtor " 
                        + insp.getMyLicense() + ", " + insp.getMyFirstName()
                        + " " +insp.getMyLastName());
            }
        }
    }
}
    

