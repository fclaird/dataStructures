package cs310laird;
import java.util.*;

/**
 *
 * @author christopherlaird
 * @version 1, assn6
 */
public class PropertyLogImpl {
    private TreeMap propertyTree;
    
    /**implements the property log by:
     * construction the TreeeMap
     */
    public PropertyLogImpl() {
         propertyTree = new TreeMap();
        
    }

    /**getter:
     *
     * @return the tree map
     */
    public TreeMap getPropertyTree() {
        return propertyTree;
    }
    
    /**displays the entire tree map in the console VIA the property object's 
     * toString method
     *
     */
    public void traverseDisplay(){
        Set set = propertyTree.entrySet();
        Iterator i = set.iterator();
        
        System.out.println("Property List:");
        while (i.hasNext()){
            Map.Entry me = (Map.Entry)i.next();
            System.out.println(me.getValue().toString());
        }  
    }  
}
