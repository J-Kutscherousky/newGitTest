import java.util.*;

public class ATM {

    public static Scanner kbd = new Scanner(System.in);
    // The checkID method determines if acctNum is a valid account number and pin is the correct password for the account.  If the account information is valid, the method returns the current account balance, as a string.
    // If the account information is invalid, the method returns the string "error".
    public static String checkID(String acctNum, String pwd)
    {
        String result = "error";

        // String b contains the valid account numbers and passwords.
        String b = "123456 3535 800";// the account number is listed first, followed by a space, followed by the password for the account, followed by a space, followed by the current balance.

        if (acctNum.equals(b.substring(0, b.indexOf(" "))) && 
                pwd.equals(b.substring(b.indexOf(" ")+1,b.lastIndexOf(" "))))
            return result = b.substring(b.lastIndexOf(" ") + 1); //this will run if the account number and the password are entered correctly

        return result;
    }

    public static int menu()
    {
        int menuChoice;
        do
        { 
            System.out.print("\nPlease Choose From the Following Options:" //displays options for the transaction that the user wants to make
                    + "\n 1. Check Balance \n 2. Make a Deposit"
                    + "\n 3. Make a Withdrawal\n 4. Log Out\n\n");

            menuChoice = kbd.nextInt();

            if (menuChoice < 1 || menuChoice > 4){ //if the user chooses a number less than 1 or greater than 4, it will print "error" 
                System.out.println("error");
            }

        }while (menuChoice < 1 || menuChoice > 4); //when the user chooses a number between 1 and 4, it will continue the transaction 

        return menuChoice;
    }

    public static void displayBalance(double x)
    {
        System.out.printf("\nYour Current Balance is $%.2f\n", x); //user selects 1 in order to check their balance
    }

    public static double deposit(double x, double y)
    {
        double depositAmt = y, currentBal = x; //variables for deposit and current balance, so there can be a new balance 
        double newBalance = depositAmt + currentBal; //adds deposited amount to balance

        System.out.printf("Your New Balance is $%.2f\n",  newBalance); //if the user makes a deposit from their account, a new balance will be displayed when "check balance" is chosen again 

        return newBalance; //prints new balance
    }

    public static double withdraw(double x, double y)
    {
        double withdrawAmt = y, currentBal = x, newBalance; //variables for balance, withdrawal,and new balance so the new balance can change  

        newBalance = currentBal - withdrawAmt; //subtracts the amount that was withdrawn  
        System.out.printf("Your New Balance is %.2f\n",newBalance);// displays new balance when "check balance" is chosen again 

        return newBalance;  //prints new balance
    }

    public static void main(String[] args) {

        String accNum, pass, origBal = "error";
        int count = 0, menuOption = 0;
        double depositAmt = 0, withdrawAmt = 0, currentBal=0; 
        boolean  foundNonDigit;
        //loop that will count the number of login attempt you make and will exit program if it is more than 3 as long as oriBal equals an error.  
        do{//lets user enter account number incorrectly, followed my the pin number, but will print error once both are entered, and then ask user to start over by entering their account number again followed by the pin again
            foundNonDigit = false;
            System.out.println("Please Enter Your Account Number: ");
            accNum = kbd.next();

            System.out.println("Enter Your Password: ");
            pass = kbd.next();

            origBal = checkID(accNum, pass);

            count++;

            if (count >= 3 && origBal.equals("error")){//user can enter account number and pin number 3 times max, until the account number and pin match the original string or they make too many incorrect attempts
                System.out.print("Maximum Login Attempts Reached.");
                System.exit(0);
            }
            if (!(origBal.equals("error"))){
                System.out.println("\nYour New Balance is: $ "+ origBal);
            }
            else
                System.out.println(origBal);


        }while(origBal.equals("error"));

        currentBal=Double.parseDouble(origBal);
        //this loop will keep track of the options that the user inputs in for the menu. gives the option of deposit, withdraw, or logout.

        while (menuOption != 4) //user can choose between 1 and 3 (not logging out) in order to make a transaction 
        { 
            menuOption=menu();
            switch (menuOption)
            {
            case 1: //once the user selects 1, they can check their account balance
                displayBalance(currentBal); // check balance
                break;
            case 2://once user selects 2, they can make a deposit 
                System.out.print("\nEnter Amount You Wish to Deposit: $ "); //user input - amount they want to deposit
                depositAmt = kbd.nextDouble();
                currentBal=deposit(depositAmt, currentBal); //changes balance
                break;
            case 3:
                System.out.print("\nEnter Amount You Wish to Withdral: $ "); //user input - amount they want to withdraw
                withdrawAmt = kbd.nextDouble();

                while(withdrawAmt>currentBal){
                    System.out.print("ERROR: INSUFFICIENT FUNDS!! " //if the user tries to withdraw an amount greater than the amount in their current balance, it will show an error
                            + "PLEASE ENTER A DIFFERENT AMOUNT: $");
                    withdrawAmt = kbd.nextDouble();
                }

                currentBal = withdraw(currentBal, withdrawAmt);//changes balance 
                break;
            case 4:
                System.out.print("\nThank For Using My ATM.  Have a Nice Day.  Good Bye!"); // quits and stops running loop
                System.exit(0);
                break;
            }
        }
    }
}