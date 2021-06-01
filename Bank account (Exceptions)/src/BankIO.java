/**
 * Name: Viet Nguyen
 * Date: 10th Mar 2021
 * CSC 202
 * Lab04-BankIO.java
 * 
 * BankIO provides utility methods interface with the Bank class to
 * create BankAccounts from a data file and write the bank data to a data file.
 */

import java.io.*;
import java.util.*;

public class BankIO {
	
	

	/**
	 * Reads a file of bank account data. Valid account data is a 5 digit integer followed
	 * by a positive floating point number. If the data on a line of the file is valid,
	 * an account is created and added to the bank. If not, the account is not created
	 * and the bad line in the file is printed. 
	 * @param filename the name of the file with account data
	 * @param bank the bank the accounts will be added to
	 * @throws FileNotFoundException when the file with account data is not found
	 */
	public static void readFile(String filename, Bank bank) throws FileNotFoundException {
	// TODO: Task 4
		File myFile = new File(filename);
		if (!myFile.exists()) {
			System.out.println("No file found with that name!");
			System.exit(0);
		}
		Scanner input = new Scanner(myFile);

		while (input.hasNextLine()) {
			String line = input.nextLine();
			try {
			String[] arr = line.split(" ");
			if (arr.length != 2) {
				throw new IOException("Line in file has wrong number of values: "+ line);
			}
			int accountNumber = Integer.parseInt(arr[0]);
			double balance = Double.parseDouble(arr[1]);
			BankAccount account = new BankAccount(accountNumber, balance);
			bank.add(account);
			} catch (IOException e) {
				System.out.println(e.getMessage());
				System.out.println("No account created.\n");
			} catch (NumberFormatException e) {
				System.out.println("Bad type data in file: " + line);
				System.out.println("No account created.\n");
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println("No account created.\n");
			}
		}
		input.close();
	}

	/**
	 * This method writes the bank accounts to a text file
	 * @param filename the name of the file the bank account data will be written to
	 * @param bank the bank of bank accounts
	 * @throws IOException when the filename already exists to prevent overwriting data
	 */
	public static void writeFile(String filename, Bank bank) throws IOException {
	// TODO: Task 5
		File myFile = new File(filename);
		if (myFile.exists()) {
			throw new IOException("A file already exists with that name!");
		}
		PrintWriter out = new PrintWriter(myFile);
		out.println(bank);
		out.close();
	}
}
