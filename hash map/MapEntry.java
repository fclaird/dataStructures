/*provide a map entry method
 * 
 */
package cs310laird;

/**
 *@version 1, assn 5
 * @author christopherlaird
 */
public class MapEntry {
    private int key;
    private PropertyNode newNode;
    
    /**constructs a Map entry object with a key and a node
     *
     * @param obj property to build the map entry
     */
    public MapEntry(Property obj) {
    newNode = new PropertyNode(obj);
    key = obj.getMyMLS();    
    }
    
    /**getter
     *
     * @return the hash code / key (they are the same in this case)
     */
    public int hashCode() {
        return key;
    }
    
    
    /**getter
     *
     * @return key
     */
    public int getkey() {
        return key;
    }
    
    /**getter 
     *
     * @return the node requested
     */
    public PropertyNode getNode() {
        return newNode;
    }
}

