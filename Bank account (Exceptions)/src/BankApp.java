/**
 * Name: Viet Nguyen
 * Date: 10th Mar 2021
 * CSC 202
 * Lab04-BankApp.java
 * 
 * A class to test the BankAccount class.  Uses a menu-driven system to
 * allow the user to interactively test the BankAccount class.
 * 
 *  
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class BankApp {

    public static void main(String[] args) {
    	Scanner console = new Scanner(System.in);

        Bank bank = new Bank();
        
	    readData(console, bank);

        //************
        //*  TASK 1  *
        //************
        //TODO TASK 1: note that the account is initially null
        BankAccount account = null;
        int choice;
        double amount;
        int accountNumber;
        
        do {
            choice = getUserChoice(console);
            switch (choice) {
            case 1:
                amount = getAmount(console);
                try {
                	accountNumber = getAccountNumber(console);
                	if (bank.find(accountNumber) != null) {
                		throw new Exception("That account already exits!\n");
                	}
                } catch (Exception e) {
                	System.out.println(e.getMessage());
                	break;
                }
                try {
                    account = new BankAccount(accountNumber, amount);
                    bank.add(account);
                    System.out.println("Account info: " + account + "\n");
                } catch (IllegalArgumentException e) {
                    System.out.println("\n*****ERROR*****: " + e.getMessage() + "\n");
                }
                break;

            //*****************
            //*  TASKS 1 & 2  *
            //*****************
            //TODO TASK 1: Note the first catch clause
            //TODO TASK 2: Note the second catch clause
            case 2:
                amount = getAmount(console);
                try {
                    account.deposit(amount);
                    System.out.println("Account info: " + account + "\n");
                } catch (NullPointerException e) {
                    System.out.println("\n*****ERROR*****: " + "No account! First find account"
                            + " or create a new account\n");    
                } catch (IllegalArgumentException e) {
                    System.out.println("\n*****ERROR*****: " + e.getMessage()  + "\n");
                }
                break;

            //*****************
            //*  TASKS 1 & 3  *
            //*****************
            //TODO TASK 1: add a similar NullPointerException catch clause below
            //TODO TASK 3: add a InsufficientFunds catch clause below
            case 3:
                amount = getAmount(console);
                try {
                    account.withdraw(amount);
                    System.out.println("Account info: " + account + "\n");
                } catch (IllegalArgumentException e) {
                    System.out.println("\n*****ERROR*****: " + e.getMessage()  + "\n");
                } catch (NullPointerException e) {
                    System.out.println("\n*****ERROR*****: " + "No account! First find account"
                            + " or create a new account\n");    
                } catch (InsufficientFundsException e) {
                	System.out.println("\n*****ERROR*****: " + e.getMessage()  + "\n");
                }
                

                break;
                
            case 4:
                accountNumber = getAccountNumber(console);
                BankAccount found = bank.find(accountNumber);
                if (found != null) {
                    account = found;
                    System.out.println("Account info: " + account + "\n");
                } else {
                    System.out.println("\n*****ERROR*****: Bank account " + accountNumber
                            + " not found!\n");
                }
                break;
                
            case 5:
                System.out.print("\n\nThe accounts: \n" + bank + "\n\n");
                break;
            }
         } while (choice != 0);
        System.out.println("\n\nGoodbye!");
        
        writeData(console, bank);
    }

    // Gives the user a menu of choices and requires a valid choice to
    // continue. Returns the valid choice.
    private static int getUserChoice(Scanner console) {
        int choice;
        do {
            choice = -1;
            System.out.println("Menu Options:");
            System.out.println("0) Quit");
            System.out.println("1) Create new account");
            System.out.println("2) Deposit to current account");
            System.out.println("3) Withdraw from current account");
            System.out.println("4) Find account");
            System.out.println("5) Print all accounts\n");

            System.out.print("Enter your choice (0 - 5): ");
            try {
                choice = Integer.parseInt(console.nextLine());
            } catch (NumberFormatException exception) {
            }
            if (choice < 0 || choice > 5)
                System.out.println("Invalid choice");
        } while (choice < 0 || choice > 5);
        return choice;
    }

    // Prompts the user to enter an amount a double is entered
    // and returns it.
    private static double getAmount(Scanner console) {
        System.out.print("Enter the amount: $ ");
        double amount = -1;
        boolean valid = false;
        do {
            try {
                amount = Double.parseDouble(console.nextLine());
                valid = true;
            } catch (NumberFormatException exception) {
                System.out.println("Make sure you enter a valid double!");
                System.out.print("Enter the amount: $ ");
            }
        } while (!valid);
        return amount;
    }

    // Prompts the user to enter and account number until an
    // integer is entered and returns it.
    private static int getAccountNumber(Scanner console) {
        System.out.print("Enter the account number: ");
        int amount = -1;
        boolean valid = false;
        do {
            try {
                amount = Integer.parseInt(console.nextLine());
                valid = true;
            } catch (NumberFormatException exception) {
                System.out.println("Make sure you enter a valid integer!");
                System.out.print("Enter the account number: ");
            }
        } while (!valid);
        return amount;
    }
    
    // Enter filename and read bank data from file
    private static void readData(Scanner console, Bank bank) {
    	System.out.print("Enter the filename of bank accounts: ");
		String filename = console.nextLine();

		try {
			BankIO.readFile(filename, bank);

		} catch (FileNotFoundException e) {
			System.out.println("*****" + e.getMessage());
			System.exit(0);
		}
    }
    
    // Enter filename and write bank data to file
    private static void writeData(Scanner console, Bank bank) {
		System.out.print("Enter the filename for output: ");
		String filename = console.nextLine();

		try {
			BankIO.writeFile(filename, bank);
		} catch (IOException e) {
			System.out.println("*****" + e.getMessage());
			System.exit(0);
		}
    }
}
