/**
 * Name: Viet Nguyen
 * Date: 10th Mar 2021
 * CSC 202
 * Lab04-BankAccount.java
 * 
 * A class to represent a single bank account.
 */
public class BankAccount {

    private int accountNumber;
    private double balance;

    /**
     * Constructs a bank account with the account number and initial balance received
     * 
     * @param accountNumber-The account number to associate with this 
     *                      BankAccount. Must be 1...9999999
     * @param initialBalance-the initial balance of the BankAccount. Must be non-negative.
     */
    public BankAccount(int accountNumber, double initialBalance) {

        if (initialBalance < 0) {
            throw new IllegalArgumentException(
                    "Accounts with a negative balance cannot be created!");
        }
        //TODO TASK 2: add code here to throw an exception if the account
        // number isn't 1...9999999
        if (accountNumber < 1 || accountNumber > 9999999) {
        	throw new IllegalArgumentException(
                    "Account numbers must be 1…9999999.");
        }
        
        balance = initialBalance;
        this.accountNumber = accountNumber;
        
    }
    /**
     * Deposits money into the BankAccount
     *
     * @param amount-the amount to deposit, must be positive
     */
    // TODO TASK 2: add code to throw an exception if the deposit amount is not positive
    public void deposit(double amount) {
    	if (amount <= 0) {
    		throw new IllegalArgumentException("Positive amount required.");
    	}
        balance = balance + amount;
    }

    //************
    //*  TASK 3  *
    //************
    /**
     * Withdraws money from the BankAccount
     *
     * @param amount-the amount to withdraw, must be positive
     */
    //TODO TASK 2: add code to throw an exception if the withdrawal amount is not positive
    //TODO TASK 3: add code to throw our new exception if an overdraw is attempted
    public void withdraw(double amount) {
    	if (amount <= 0) {
    		throw new IllegalArgumentException("Positive amount required.");
    	}
    	if (amount > balance) {
    		throw new InsufficientFundsException("You can't withdraw more than your balance!");
    	}
        balance = balance - amount;
    }

    /**
     * Gets the current balance of the BankAccount
     *
     * @return the current balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Gets the account number of the BankAccount
     * @return the account number
     */
    public int getAccountNumber() {
        return accountNumber;
    }

    /**
     * Returns a string representation this BankAccount in
     * the following format, e.g. 0012345 $100.52 where 0012345
     * is the account number, and 100.52 is the balance.
     */
    public String toString () {
    	return String.format("%07d %.2f", accountNumber, balance);
    }
}
