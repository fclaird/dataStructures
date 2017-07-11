package cs310laird;
/**
 *
 * @author christopherlaird
 * @version 1, assn5
 */
public class PropertyLogImpl {
    private int MAX_SIZE;
    private int currSize;
    private int numIdx;
    private  MapEntry[] propertyHashMap;
    private MapEntry insp;
    
    /**
     * constructor
     */
    public PropertyLogImpl() {
        MAX_SIZE = 17;
        currSize = 0;
        numIdx = 0;
        propertyHashMap = new MapEntry[MAX_SIZE];
        for (int i = 0; i < propertyHashMap.length; i++){
            propertyHashMap[i] = null;
        }
    }
    
    /**ADD's a Property object to the log.  in doing this a new property node 
     * is created, the hash code is then calculated, if the hash code is empty 
     * the property node is stored, if there is already one or more property 
     * nodes stored at in the location the new node will be added to the top 
     * of the list 
     * 
     * @param obj - PropertyNode to be added to the hash map
     */
    public void add(Property obj) {  
        MapEntry newEntry = new MapEntry(obj);
        int hashCode = newEntry.hashCode();
        hashCode = hashCode % MAX_SIZE;
        MapEntry currEntry;
        if (propertyHashMap[hashCode] == null){
            currSize++;
            numIdx++;
            propertyHashMap[hashCode] = newEntry;
        }
        else{
            currEntry = propertyHashMap[hashCode];
            newEntry.getNode().setNextNode(currEntry);
            propertyHashMap[hashCode] = newEntry; 
            currSize++;
            numIdx++;
        }
    }
    
    /**searches for a property object in the hash map.  if found it returns a 
     * refrence to the location otherwise it returns null.
     * 
     * @param mls discriminator
     * @return the property  will be null if not found in the map
     */
    public Property find(int mls){
        boolean found = false,
                end = false;
        MapEntry location = null;
        int hashCode = mls;
        hashCode = hashCode % MAX_SIZE;
        int currMls;
        MapEntry inspect = propertyHashMap[hashCode];
        Property property = null;
        
        if (inspect == null){
            location = inspect;
        }
        else{
            while (!found && !end){
                if (inspect.getNode().getNextNode() == null) {
                    currMls = inspect.getNode().getProperty().getMyMLS();
                    if (currMls == mls){
                        found = true;
                        property = inspect.getNode().getProperty();
                    }
                    else{
                        end = true;
                    }
                }
                else{
                    currMls = inspect.getNode().getProperty().getMyMLS();
                    if (currMls == mls){
                        found = true;
                        property = inspect.getNode().getProperty();
                    }
                    else{
                        inspect = inspect.getNode().getNextNode();
                    }
                }
            }
        }
        return property;
    }
     
  /**print the entire current hashMap in the terminal
   * 
   */    
    public void displayHash(){
        int key = 0;
        boolean end;
        
        System.out.println("\nProperty Map: ");
        for (int i = 0; i < propertyHashMap.length; i++){
            end = false;
            insp = propertyHashMap[i];
            if (insp == null){
                System.out.println("      Index " + i + " is empty");
                }
            else{
                key = insp.getkey();
                System.out.print("      Index " + i + " contains properties: ");
                while (!end){
                    if (insp.getNode().getNextNode() == null){
                    System.out.print(key + "\n");
                    end = true;
                    }
                    else{
                        System.out.print(key + " ");
                        insp = insp.getNode().getNextNode();
                        key = insp.getkey();
                    }
                } 
            }    
        }
    }   
}
