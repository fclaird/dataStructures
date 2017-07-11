package cs310laird;

/**
 * implements singly linked Property list
 * @author christopherlaird
 */
public class PropertyNode {
    private Property property;
    private MapEntry nextNode;

    /**constructor
     * 
     * @param obj property used to construct the node
     */
    public PropertyNode(Property obj) {//constructor
        this.property = obj; //stores paramiter in the node
        nextNode = null;//nextNode to null is last place in the linkedList
    }  

    /**
     * getter
     * @return realtor object
     */
    public Property getProperty() {
        return property;
    }

    /**
     * getter
     * @return next node
     */
    public MapEntry getNextNode() {
        return nextNode;
    }

    /**
     * 
     * @param obj property to store
     */
    public void setProperty(Property obj) {
        this.property = obj;
    }

    /**
     * 
     * @param obj map entry to be store in next node
     */
    public void setNextNode(MapEntry obj) {// setting nextNode
        this.nextNode = obj;
    }
}
