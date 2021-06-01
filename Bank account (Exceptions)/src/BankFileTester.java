import java.util.Scanner;
import java.io.*;

public class BankFileTester {

	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);

		Bank bank = new Bank();

		System.out.print("Enter the filename of bank accounts: ");
		String filename = console.nextLine();

		try {
			BankIO.readFile(filename, bank);

		} catch (FileNotFoundException e) {
			System.out.println("*****" + e.getMessage());
			System.exit(1);
		}

		System.out.println(bank);

		System.out.print("Enter the filename for output: ");
		String outFilename = console.nextLine();

		try {
			BankIO.writeFile(outFilename, bank);
		} catch (IOException e) {
			System.out.println("*****" + e.getMessage());
			System.exit(0);
		}

		console.close();

	}

}
