package cs310laird;

/**
 * singly linked Realtor list
 * @author christopherlaird
 */
public class RealtorNode {
    private Realtor realtor;//data 
    private RealtorNode nextNode;//RealtorNode object called nextNode [link to next node]

    /**
     * constructor
     * @param realtor stored in the head
     */
    public RealtorNode(Realtor realtor) {//constructor
        this.realtor = realtor; //stores paramiter in the node
        nextNode = null;//nextNode to null is last place in the linkedList
    }  

    /**
     * getter
     * @return realtor object
     */
    public Realtor getRealtor() {//for acessing data
        return realtor;
    }

    /**
     * getter
     * @return next node
     */
    public RealtorNode getNextNode() {//for acessing next node
        return nextNode;
    }

    /**
     * setter 
     * @param realtor value to store
     */
    public void setRealtor(Realtor realtor) {//setting realtor object in the node
        this.realtor = realtor;
    }

    /**
     * setter
     * @param nextNode value to store
     */
    public void setNextNode(RealtorNode nextNode) {// setting nextNode
        this.nextNode = nextNode;
    }
    
    
}
