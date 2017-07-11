/*
 * This program reads data from a file and uses it to calculate the profits for 
 * a credit card company.  It also takes input from the user on the number of 
 * months to calculate arroud and weather or not to repeat the calculations
 * once completed.
 */
import java.util.Scanner;
import java.io.*;
/**
 * @author christopherlaird
 * @version 1, assn 7
 */
public class ProfitCalculator {
static Scanner in = new Scanner(System.in);

/**
 * main method executes the backbone of the program calling all required methods 
 * @param args 
 */
public static void main(String[] args) {    
    final String INPUT_DATA = "accounts.txt";
    System.out.print("This program calculates how much intrest is earned by a "
            + "credit card\ncompany, from all accounts, over a specific number "
            + "of months.\n\n\n\n");
    boolean test = false;
    do {
        double totalIntrest = 0;
        int numberOfMonths = numberOfMonths();
        try {
            File inFile = new File(INPUT_DATA);
            Scanner fin = new Scanner(inFile);
            
            int count = 0;
            while (fin.hasNextLine()) { 
                try {
                CreditCardAccount alpha = new CreditCardAccount(fin.nextDouble(), 
                fin.nextDouble(), fin.nextInt());
                totalIntrest += alpha.calcAccountIntrest(numberOfMonths);
                count++;
                } catch (IllegalArgumentException e) {
                System.err.println(    e.getMessage()  );
                }
    
            }
        fin.close();    
        System.out.printf("%nOver the next " + numberOfMonths + " months,\n"
                + "     the total intrest earned from " + count + " accounts "
                + "will be $ " + "%.2f",totalIntrest);
            
        } catch (FileNotFoundException fnfe) {
            System.out.println("  *** ERROR " + INPUT_DATA + " not found");
            System.exit(0);
        }
        System.out.print("\n\nRun again with a different number of months? "
                + "(y/n)");
        String answer = in.next();
        
        if (answer.equals("y") || answer.equals("Y")) {
            test = true;
        }
    } while (test);
            
            
      
       
        

}
/**
 * reads in the number of months from the user then validated that the input 
 * received is greater than 0.
 * @return number of months used in calculations
 */    
public static int numberOfMonths(){
    int numberOfMonths = 0;
    do{
        System.out.print("Enter the number of months (greater than 0) to "
                + "calculate intrest for: ");
        numberOfMonths = in.nextInt();
        if (numberOfMonths < 0){
       System.out.println("   *** ERROR number of months must be "
               + "greater-than zero");
        } 
    } while (numberOfMonths < 0);
return numberOfMonths;
} 



}