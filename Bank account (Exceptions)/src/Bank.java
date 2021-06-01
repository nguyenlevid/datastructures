/**
 * A Bank can hold BankAccounts. 
 * 
 */
import java.util.*;

public class Bank {
    
    /** The list of BankAccount objects. */
    private ArrayList<BankAccount> accounts;

    /**
     * Creates a bank with any number of accounts.
     */
    public Bank() {
        accounts = new ArrayList<BankAccount>();
    }

    /**
     * Adds the given BankAccount to the bank. 
     * @param account The account to add
     */
    public void add(BankAccount account) {
    	accounts.add(account);
    }

    /**
     * Returns the bank account with the given account number.
     * If no such account exists, null is returned.
     * @param acctNumber The account number
     * @return the account with the account number or null if not found
     */
    public BankAccount find(int acctNumber) {
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getAccountNumber() == acctNumber) {
                return accounts.get(i);
            }
        }

        return null;
    }

    /**
     * Returns a string representation of the bank.
     * The format is one account per line.
     */
    public String toString() {
        if (accounts.isEmpty()) {
        	return "NONE";
        }
        
        String result = "";
        for (int i = 0; i < accounts.size(); i++) {
            result += accounts.get(i)  + "\n";
        }

        return result;
    }
}
