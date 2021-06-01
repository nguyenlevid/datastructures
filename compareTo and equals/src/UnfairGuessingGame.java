/*
 * Name: Viet Nguyen
 * Date: 24th Mar 2021
 * CSC 202
 * Lab06--UnfairGuessingGame.java
 * 
 * An unfair guessing game which doesn't choose a target number in advance.
 * Instead, after the player makes a guess, the game keeps the largest
 * interval of possible targets. After each guess,the player is given a
 * clue of too low, too high or correct. The number of guesses the player 
 * has made is also tracked.
 *
 */
public class UnfairGuessingGame extends GuessingGame implements Guessable {
	// data fields
	private int lowEnd;
	private int highEnd;
	
	/**
	 * Constructs an object to play an unfair guessing game with
	 * the range of possible targets from 1 to the value received as
	 * a parameter.
	 * @param maxValue - the maximum value of the target
	 */
	public UnfairGuessingGame(int maxPossibility) {
		if (maxPossibility < 1) {
			throw new IllegalArgumentException("Must be at least 1.");
		}
		this.lowEnd = 1;
		this.highEnd = maxPossibility;
	}

	
	@Override  //for processGuess
	public String processGuess(int guess) {
		super.incrementNumGuesses();
		if (lowEnd == highEnd && guess == lowEnd) {
			return Guessable.CORRECT_CLUE;
		}
		if (guess > highEnd) {
			return Guessable.HIGH_CLUE;
		}
		if (guess < lowEnd) {
			return Guessable.LOW_CLUE;
		}
		if (guess > (highEnd+lowEnd)/2) {
			highEnd = guess-1;
			return Guessable.HIGH_CLUE;
		}
		else if (guess < (highEnd+lowEnd)/2) {
			lowEnd = guess+1;
			return Guessable.LOW_CLUE;
		}
		else {
			lowEnd = guess+1;
			return Guessable.LOW_CLUE;
		}
		
	}
	
	@Override  //for toString
	public String toString() {
		return "[lowEnd = "+lowEnd+", highEnd = "+highEnd+", numGuesses = "+super.getNumGuessesSoFar()+"]";
	}
}
