/*
 * 
 */

/**MyCreditCardAccount - this class this class contains a credit card account 
 * object and its associated methods  
 * @author christopherlaird
 * @version 1, assn 6
 */
public class CreditCardAccount {
    private double myCurrentBalance,
                   myAnualIntrestRate,
                   myPercentOfCurrentBalanceEachMonth;
    
    /**
     * constructor 1, assumes a minimum monthly payment
     * @param initialBalance
     * @param anualIntrestRate 
     */
    public CreditCardAccount(double initialBalance, double anualIntrestRate){
        myCurrentBalance = initialBalance;
        myAnualIntrestRate = anualIntrestRate;
        myPercentOfCurrentBalanceEachMonth = 0;
    }
    
    /**
     * constructor 2 
     * @param initialBalance
     * @param anualIntrestRate
     * @param percentOfCurrentBalanceEachMonth 
     */
    public CreditCardAccount(double initialBalance, double anualIntrestRate, 
            double percentOfCurrentBalanceEachMonth){
        myCurrentBalance = initialBalance;
        myAnualIntrestRate = anualIntrestRate;
        myPercentOfCurrentBalanceEachMonth = percentOfCurrentBalanceEachMonth;
    }
    /**
     * getter 
     * @return the percentage of the current balance that is being
     * paid each month
     */
    public double getMyPercentOfCurrentBalanceEachMonth() {
        return myPercentOfCurrentBalanceEachMonth;
    }
    
    /**instanceMethod1 - calculates the minimum payment
     * @return amount of minimum payment
     */
    public double minimumPayment(){
        final double DECIMAL_CONVERSION = 100;
        double lowBalanceMinimumPayment = 50;
        double highBalancepercentage = 5;
        double lowBalanceLimit = 1000;
        
        if (myCurrentBalance < lowBalanceLimit){
            return lowBalanceMinimumPayment;
        }
        else{
            return highBalancepercentage / DECIMAL_CONVERSION * 
                    myCurrentBalance;
        }
    }
    
    /**instanceMethod - executes one months payments and associated calculations
     * and displays the results in the terminal 
     */
    public void makePayment(){
        final double INPUT_TO_MONTHLY_INTREST_RATE_CONVERSION = 1200,
                     INPUT_PERCENT_CONVERSION_TO_DECIMAL = 100;
        
        System.out.printf("%11.2f", myCurrentBalance);
        double monthIntrest = myAnualIntrestRate / 
                INPUT_TO_MONTHLY_INTREST_RATE_CONVERSION * myCurrentBalance; 
        myCurrentBalance += monthIntrest;
        System.out.printf("%11.2f%13.2f",monthIntrest, myCurrentBalance);
        double calculatedPayment = myCurrentBalance / 
                INPUT_PERCENT_CONVERSION_TO_DECIMAL * 
                myPercentOfCurrentBalanceEachMonth;
        double minimumPayment = this.minimumPayment();
        
        if (calculatedPayment < minimumPayment){
            calculatedPayment = minimumPayment;
        }
        
        if (calculatedPayment > myCurrentBalance){
            calculatedPayment = myCurrentBalance;
        }
        
        System.out.printf("%11.2f",calculatedPayment);
        myCurrentBalance -= calculatedPayment;
        System.out.printf("%11.2f",myCurrentBalance);
    }
    
    /**instanceMethod3 - displays the results and tracks the number of months 
     * required to close the balance
     * @return number of months to clear the account
     */
    public int payOff(){
        int month = 1;
        if (myPercentOfCurrentBalanceEachMonth == 0) {
            System.out.println("\nResults when paying minimum required payment "
                    + "per month");
        }
        else { 
           System.out.println("\nResults when paying " + 
                myPercentOfCurrentBalanceEachMonth + "% of the balance per "
                + "month"); 
        }
        
        System.out.println("---------------------------------------------------"
                + "-----------");
        System.out.println("         Initial    Month's     Balance             "
                + "     End");
        System.out.println("Month    Balance    Intrest    w/Intrest    Payment  "
                + "  Balance");
        System.out.println("---------------------------------------------------"
                + "-----------");

        while (myCurrentBalance > 0){
            System.out.printf("%5d",month);
            this.makePayment();
            System.out.println();
            month++;
        }
    return month;
    }    
}
