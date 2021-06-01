/*
 * Name: Viet Nguyen
 * Date: 24th Mar 2021
 * CSC 202
 * Lab06--Guessable.java
 *
 */
public interface Guessable {
	public static final String CORRECT_CLUE = "CORRECT";
	public static final String LOW_CLUE = "TOO LOW";
	public static final String HIGH_CLUE = "TOO HIGH";
	
	/**
	 * Allows access to the number of guesses made in the game so far
	 * @return the number of guesses made in the game so far
	 */
	public abstract int getNumGuessesSoFar();
	
	/**
	 * Process the guess made by the user returning a clue about the guess
	 * @param guess - the user's guess for the target number
	 * @return a clue about the guess compared to the target (too high, too low)
	 * or correct if the user's guess was the target.
	 */
	public abstract String processGuess(int guess);
}
