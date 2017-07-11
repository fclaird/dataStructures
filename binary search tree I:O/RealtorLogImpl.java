package cs310laird;

/**implements a Binary Search Tree of realtor objects using the realtor license 
 * as a key 
 *
 * @author christopherlaird
 * @version 1 Assn 6
 */
public class RealtorLogImpl {    
    private BSTNode root;

    /**constructor: implements the realtor log
     *
     */
    public  RealtorLogImpl(){
        root = null; 
    }
    
    /**inserts a new realtor into the log using a recursive helper method
     *
     * @param obj to add
     */
    public void insert(Realtor obj){
        root = insertHelp(root, obj);   
    }
    
    private BSTNode insertHelp(BSTNode rt, Realtor obj){
        
        if (rt == null) return new BSTNode(obj);
        
        if (rt.getKey().compareTo(obj.getMyLicense()) >= 0)
            rt.setLeft(insertHelp(rt.left(), obj));
        else
            rt.setRight((insertHelp(rt.right(), obj)));
        return rt;    
    }
    
    /** finds a given obj in the tree with a recursive helper method
     *
     * @param key search key
     * @return the sought object or null if it is not found in the tree
     */
    public Realtor find(String key){
        return findHelp(root, key);  
    }
    
    private Realtor findHelp(BSTNode rt, String key){
        if (rt == null) return null;
        if (rt.getKey().compareTo(key) > 0)
            return findHelp(rt.left(),key);
        else if (rt.getKey().compareTo(key) == 0)
            return rt.getRealtor();
        else return findHelp(rt.right(), key);
    }
    
    /** prints the tree into the console "in order" by recursively calling the 
     * realtor toString method and using a helper method
     * 
     */
    public void traverseDisplay(){//in order recursive traversal
        System.out.println("Realtor List:");
        printInOrder(root);
        System.out.println("");
    }
    
    private void printInOrder(BSTNode rt){
        if (rt == null) return;
        printInOrder(rt.left());
        System.out.println(rt.getRealtor().toString());
        printInOrder(rt.right());
    }
   
}
    

