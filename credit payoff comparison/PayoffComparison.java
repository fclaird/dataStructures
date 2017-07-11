/*
* This prorgram calculates how long it will take to pay off your credit card 
* ballance with minimum payments vs. larger payments.  It then displays the 
* monthly results of the payments on the balance and intrest.  It also compares 
* the total time to clear the debt between the two methods.
*/


import java.util.Scanner;
/**MainClass - 
 * @author christopherlaird
 * @version 1, Assn 7
 */
public class PayoffComparison {
    static Scanner in = new Scanner(System.in);
    
    /**main
     * executes the the various methods and reads in the 
     * required input for the program to run
     * @param args 
     */
    public static void main(String[] args) {
       final int LOWEST_ANUAL_RATE = 3,
                 HIGHEST_ANUAL_RATE = 25;
       System.out.println("This prorgram calculates how long it will take to "
               + "pay off your credit \ncard ballance with minimum payments "
               + "vs. larger payments.\n");
        double initialBalance = initialBalance();
        double anualIntrestRate = readPercent(LOWEST_ANUAL_RATE, 
            HIGHEST_ANUAL_RATE, "anual intrest rate");
        double percentOfBallencePerMonth = percentOfBalance();
        CreditCardAccount minimum = new CreditCardAccount(initialBalance, 
                anualIntrestRate); 
        CreditCardAccount manual = new CreditCardAccount(initialBalance, 
                anualIntrestRate, percentOfBallencePerMonth);
        System.out.println();
        int minimumMonths = minimum.payOff();
        int manualMonths = manual.payOff();
        System.out.println("\nPaying minimum required payment per month,\n     "
                + "it will take " + minimumMonths + " months to pay off the "
                + "credit card.\n\nPaying " 
                + manual.getMyPercentOfCurrentBalanceEachMonth() + "% of the "
                + "balance per month, \n     it will take " + manualMonths 
                + " months to pay off the credit card.");   
    }
    
    /**method 1
     * prompts for an initial ballance, verifies it is valid 
     * @return a valid initial balance
     */
    public static double initialBalance(){
        final double MIN_INITIAL_BALANCE = 500;
        double initialBalance;
        
        do {
            System.out.print("Enter the initial balance (at least $" + 
                MIN_INITIAL_BALANCE + "): ");
            initialBalance = in.nextDouble();
            if (initialBalance < MIN_INITIAL_BALANCE){
                System.out.println("ERROR that value is less than $500!!"); 
            }     
        }while(initialBalance < MIN_INITIAL_BALANCE);
    return initialBalance;
    }
    
    /**method 2
     * prompts for a percentage between specified limits and under a given 
     * description validates that the input value is inside the limits
     * @return a legal value between the defined limits
     */            
    public static double readPercent(double lowest, double highest, 
            String description){
        boolean flag = true;
        double percent;
        
        do{
        System.out.print("the " + description + " (between " + lowest + "% "
                + "and " + highest + "%): ");
        percent = in.nextDouble();
            if (percent < lowest || percent > highest){
            System.out.println("ERROR the value entered is out of bounds!!");
            flag =false;
            }
            else {
            flag = true;
            }
        }while(flag == false);
    return percent;
    }
    
    /**method 3
     * prompts a menu, takes an input from said menu, validated the input, if 
     * option 4 is selected it prompts and takes a second input which is also 
     * validated inside a given set of limits
     * @return the percent of the balance to be paid each month 
     */
    public static double percentOfBalance(){
        final double LOWEST = 6,
                     HIGHEST = 33;
        
        int menuInput = 0;
        double percentOfBalance = 0;
        boolean error = false;
        
        do{
            System.out.println("\nWhat will the monthly payment be?");
            System.out.println("    1 - 10% of remaining balance each month");
            System.out.println("    2 - 20% of remaining balance each month");
            System.out.println("    3 - 30% of remaining balance each month");
            System.out.println("    4 - Some othe percent");
            System.out.print("Enter a choice from the menu above: ");
            menuInput = in.nextInt();
            switch (menuInput){
                case 1: percentOfBalance = 10;
                break;
                case 2: percentOfBalance = 20;
                break;
                case 3: percentOfBalance = 30;
                break;
                case 4: percentOfBalance = readPercent(LOWEST, HIGHEST, 
                        "percent of balance");
                break;
                default: System.out.print("ERROR invalid input!!");
                error = true;
            }
        }while(error == true);
    return percentOfBalance;    
    }   
}