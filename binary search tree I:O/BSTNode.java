/*
 * builds a binary search tree node, alowing for the creation oa a binary 
 * search tree 
 */
package cs310laird;

/**
 *
 * @author christopherlaird
 * @version 1. assn 6
 */
public class BSTNode {
    private Realtor realtor;
    private BSTNode left;
    private BSTNode right;
    private String key;
    
    /**constructor: constructs a new node from a given object 
     *
     * @param obj refrence of the new node
     */
    public BSTNode(Realtor obj){
        this.realtor = obj;
        this.key = obj.getMyLicense();
        left = null;
        right = null;
        
    }

    /**getter
     *
     * @return the realtor stored in the node
     */
    public Realtor getRealtor() {
        return realtor;
    }
    
    /**getter
     *
     * @return the key refrence for the given node
     */
    public String getKey() {
        return key;
    }

    /**setter sets a node in the left child position
     *
     * @param leftChild value to set
     */
    public void setLeft(BSTNode leftChild) {
        this.left = leftChild;
    }

    /**setter sets a node in the right child position
     *
     * @param rightChild value to set
     */
    public void setRight(BSTNode rightChild) {
        this.right = rightChild;
    }

    /**getter
     *
     * @return the value stored in the left position
     */
    public BSTNode left() {
        return left;
    }

    /**getter 
     *
     * @return the value stored in the right position
     */
    public BSTNode right() {
        return right;
    }
    
    
    
    
}
