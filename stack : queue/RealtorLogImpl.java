package cs310laird;

/**
 *
 * @author christopherlaird
 * @version 1 Assn 4
 */
public class RealtorLogImpl {    
    private RealtorNode top;
    
    
    //when the object is instantiated list top is set to null 
    //indicating a empty linkedlist

    /**
     *constructor
     */
    public RealtorLogImpl () {
        top = null;
    }
    
    //method to find a given realtor node

    /**
     *
     * @return
     */
    public RealtorNode getTop() {
        return top;
    }
    /**
     * 
     * @param search
     * @return 
     */
    public Realtor get(String search) {
        RealtorNode node = top;
        Realtor realtor = null;
        
        String inspect = "";
        
        while (node != null) {
            inspect = node.getRealtor().getMyLicense();
            if (inspect.equals(search)) {
                realtor = node.getRealtor();
                return realtor;
            }
            node = node.getNextNode();
        } 
        
    return realtor;    
    }
    /**adds realtor objects to the realtor log linkedList  in order based on 
     * realtor license value
     * 
     * @param obj the realtor object to be added
     */
    public void add(Realtor obj) {
        RealtorNode newNode,
                    curr,
                    prev; 
        
        newNode = new RealtorNode(obj);
        
        if (top == null) {
            //list is empty
        top = newNode;
        }
        
        //add to the top of the list based on license data
        else if (newNode.getRealtor().getMyLicense().compareTo
            (top.getRealtor().getMyLicense()) < 0)  {
            newNode.setNextNode(top);//new points to existing node
            top = newNode;//top points to new node
        }
        
        else {//search for location
            prev = top;
            curr = top.getNextNode();
            while (curr != null && newNode.getRealtor().getMyLicense().compareTo(
                    curr.getRealtor().getMyLicense()) >= 0 )  {
                prev = curr;
                curr = curr.getNextNode();
            }
            newNode.setNextNode(curr);
            prev.setNextNode(newNode);
        }
    }                      
 
    /**
     *
     * @param obj
     * @return
     */
    public boolean remove(Realtor obj) {
        boolean result = false;
        RealtorNode here = top,
                    prev;
        
            while (here != null) {
                prev = here;
                here = here.getNextNode();

                if (obj.getMyLicense().compareTo(
                        top.getRealtor().getMyLicense()) == 0) { 
                    top = top.getNextNode(); //remove the first
                    result = true;
                }

                else if (here != null && obj.getMyLicense().compareTo(
                        here.getRealtor().getMyLicense()) == 0) {
                    prev.setNextNode(here.getNextNode());
                    result =  true;
                }

                
                }
        return result;    
    }
    

    
    /**searches for checkLicense in the linkedList
     * 
     * @param searchLicense lookin for
     * @return true if the searchLicense is not located in the ArrayList 
     * IE: yes the license is unique
     *          false if the realtor license is already in the list
     */
    public boolean isLicenseUnique(String searchLicense) {
        String checkLicense;
        RealtorNode here = top;
        
        while (here != null) {
            checkLicense = here.getRealtor().getMyLicense();
            if  (checkLicense.compareTo(searchLicense) == 0) {
                return false;
            }
            
            here = here.getNextNode();
        }
        return true;
    }
    
    /**display the list
     *
     */
    public void displayList() {
        System.out.println("\nList contains the following students:");
        RealtorNode here = top;
        
        // traverse the list, displaying each node's data
        while (here != null) {
            System.out.println("     " + here.getRealtor().toString());
            here = here.getNextNode();
        }
        System.out.println();
    }
    
    /**displays data
     *
     */
    public void traverseDisplay() {
        RealtorNode here = top;
        System.out.println("\nRealtor Log:");
        while (here != null) {
            System.out.println(here.getRealtor().toString());
            here = here.getNextNode();
        }

    }
    
    /**cleans up the list removing errors
     *
     * @param propertyLog
     */
    public void cleanUp(PropertyLogImpl propertyLog) {
        //validate data and remove line items with errors
        RealtorNode here = top;
        Realtor realtor;
        boolean license;
       
        while (here != null) {
            realtor = here.getRealtor();
            license = realtor.licenseCheck(realtor.getMyLicense());
            
            if (!license) {
                System.out.println("Invalid realtor license number for realtor "
                        + realtor.getMyLicense());
                this.remove(realtor);
                propertyLog.remove(realtor.getMyLicense());
                System.out.println("    Removing realtor " 
                        + realtor.getMyLicense() + " and all associated "
                        + "properties");
                //System.outPrintln("    Removing ")
            }
            here = here.getNextNode();
        }
    }  
}
    

