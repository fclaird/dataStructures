
/**MyCreditCardAccount - this class this class contains a credit card account 
 * object and its associated methods  
 * @author christopherlaird
 * @version 1, assn 7
 */
public class CreditCardAccount {
    private double myCurrentBalance,
                   myAnualIntrestRate,
                   myPercentOfCurrentBalanceEachMonth;
    
    /**
     * constructor  
     * @param initialBalance
     * @param anualIntrestRate
     * @param percentOfCurrentBalanceEachMonth 
     */
    public CreditCardAccount(double initialBalance, double anualIntrestRate, 
            double percentOfCurrentBalanceEachMonth){
        
        if (initialBalance <= 0) {
            throw new IllegalArgumentException("\n  *** Invalid initial "
                    + "balance " + initialBalance + " -- data line: " 
                    + initialBalance + " " + anualIntrestRate + " " 
                    + percentOfCurrentBalanceEachMonth + " ignored");
        } else myCurrentBalance = initialBalance;
        
        int lowerBoundRate = 3;
        int upperBoundRate = 25;
        if (anualIntrestRate < lowerBoundRate || anualIntrestRate > 
                upperBoundRate){
            throw new IllegalArgumentException("\n  *** Invalid card  intrest " 
                    + anualIntrestRate + "% -- data line:  " + initialBalance + " "
                    + anualIntrestRate + " " + percentOfCurrentBalanceEachMonth
                    + " ignored");
        } else myAnualIntrestRate = anualIntrestRate;
        
        int lowerBoundPercent = 0;
        int upperBoundPercent = 33;
        if (percentOfCurrentBalanceEachMonth < lowerBoundPercent || 
                percentOfCurrentBalanceEachMonth > upperBoundPercent) {
            throw new IllegalArgumentException("\n  *** Invalid monthly payment "
                    + "percentage" + percentOfCurrentBalanceEachMonth + "% -- "
                    + "data line: " + initialBalance + " "
                    + anualIntrestRate + " " + percentOfCurrentBalanceEachMonth
                    + " ignored");
        } else myPercentOfCurrentBalanceEachMonth = 
                percentOfCurrentBalanceEachMonth;
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
    
    /**instanceMethod2 - executes one months payments and associated calculations
     * and displays the results in the terminal 
     */
    public double makePayment(){
        final double INPUT_TO_MONTHLY_INTREST_RATE_CONVERSION = 1200,
                     INPUT_PERCENT_CONVERSION_TO_DECIMAL = 100;
        double monthIntrest = myAnualIntrestRate / 
                INPUT_TO_MONTHLY_INTREST_RATE_CONVERSION * myCurrentBalance; 
        myCurrentBalance += monthIntrest;
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
        myCurrentBalance -= calculatedPayment;
    return monthIntrest;
    } 
    
    /**instanceMethod3 - displays the results and tracks the number of months 
     * required to close the balance
     * @return number of months to clear the account
     */
    
    //for (initialization; condition; increment/decrement) {
    //statement(s) //block of statements

    public double calcAccountIntrest(int numberOfMonths){
        double totalIntrest = 0;
        for (int i = 0; myCurrentBalance > 0 && i != numberOfMonths; i++){
            totalIntrest += this.makePayment();
        }
    return totalIntrest;
    }    
}
