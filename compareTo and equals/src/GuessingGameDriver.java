/**
 * Driver program for the user to play a guessing game to guess a target number.
 * If the guess is incorrect, the user is given a clue. The number of guesses
 * is also tracked.
 */

import java.io.IOException;
import java.util.*;

public class GuessingGameDriver {

	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		System.out.println("Let's play a guessing game!\n");
		int maxValue = inputPositiveInt(console, "Enter the max value for the game: ");
		
		Guessable myGame = new UnfairGuessingGame(maxValue);
		String clue = "";
		do {
			int guess = inputPositiveInt(console, "\nEnter your guess: ");
			clue = myGame.processGuess(guess);
			System.out.println(myGame);				// to help in debugging
			System.out.println(clue);
		} while (!clue.equals(Guessable.CORRECT_CLUE));
		
		String plural = "";
		if (myGame.getNumGuessesSoFar() > 1) {
			plural = "es";
		}
		System.out.println("\nYou got it in " + myGame.getNumGuessesSoFar() + " guess" + plural + ".");
	}
	
	/**
	 * Prompts the user and requires the user to enter a positive integer.
	 * @param console - Scanner to facilitate keyboard input
	 * @param prompt - the prompt given to the user
	 * @return the positive integer entered by the user
	 */
	public static int inputPositiveInt(Scanner console, String prompt) {
		boolean valid = false;
		int response = 0;
		do {
			try {
				System.out.print(prompt);
				String line = console.nextLine();
				response = Integer.parseInt(line);
				if (response <= 0) {
					throw new IOException("Only positives allowed!");
				}
				valid = true;
			} catch (NumberFormatException e) {
				System.out.println("Must enter a positive integer. Try again.\n");
			} catch (IOException e) {
				System.out.println(e.getMessage() + " Try again.\n");
			}
		}while (!valid);
			
		return response;
	}

}
