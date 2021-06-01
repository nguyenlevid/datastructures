/**
 * This class represents the superclass for a variety 
 * of guessing games keeping track of the number of guesses.
 */
public abstract class GuessingGame {
	// data field
	private int numGuesses;
	
	/**
	 * Constructs this guessing game with 0 for the initial number of guesses
	 */
	public GuessingGame() {
		numGuesses = 0;
	}
	
	/** 
	 * Increases the number of guesses by 1
	 */
	public void incrementNumGuesses() {
		numGuesses++;
	}
	
	/**
	 * Allows access to the number of guesses made in the game so far
	 * @return the number of guesses made in the game so far
	 */
	public int getNumGuessesSoFar() {
		return numGuesses;
	}
	
	/**
	 * Method to process the guess made by the person playing the game
	 * @param guess - the person's guess
	 * @return a clue indicating if the guess was too high, too low, or correct.
	 */
	public abstract String processGuess(int guess);
}
