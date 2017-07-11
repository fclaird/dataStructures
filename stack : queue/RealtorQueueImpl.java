package cs310laird;
/**
 *
 * @author christopherlaird
 */
public class RealtorQueueImpl {
    private RealtorNode head;
    private RealtorNode tail;
    
    /**
     *constructor
     */
    public RealtorQueueImpl() {
        head = null;
        tail = null;
    }

    /**getter
     *
     * @return head of the queue
     */
    public RealtorNode getHead() {
        return head;
    }
    
     /**getter
     *
     * @return tail of the queue
     */
    public RealtorNode getTail() {
        return tail;
    }
    

    /**adds realtor objects to the realtor log linkedList  in order based on 
     * realtor license value
     * 
     * @param obj the realtor object to be added
     */
    public void add(Realtor obj) {//modify to add to the tail
        RealtorNode newNode,
                    prev; 
        
        newNode = new RealtorNode(obj);
        
        if (head == null) {            //list is empty
            head = newNode;
            tail = newNode;
        }
        
        else {
            prev = tail;
            prev.setNextNode(newNode);
            tail = newNode;
            tail.setNextNode(null);
        }
    }                      
 
    /**empty check
     * 
     * @param queue
     * @return 
     */
    private boolean empty(RealtorQueueImpl queue) {
        boolean result = false;
        
        if (head == null) {
            result = true;
            System.out.println("the Queue is empty");
        }
        return result;
    }
    
    /**remove a realtor from the queue
     *
     * @return
     */
    public Realtor remove() {//modify to remove from the head
        Realtor realtor = null;
        boolean empty;
        
        empty = this.empty(this);
        
            while (!empty) {
                realtor = head.getRealtor();
                head = head.getNextNode(); 
            }
        return realtor;    
    }
    

    
    
    
    /**display a list of data stored in the nodes
     *
     */
    public void displayList() {
        System.out.println("\nList contains the following units:");
        RealtorNode here = head;
        
        // traverse the list, displaying each node's data
        while (here != null) {
            System.out.println("     " + here.getRealtor().toString() );
            here = here.getNextNode();
        }
        System.out.println();
    } 
}
