/*
 * Name: Viet Nguyen
 * Date: 24th Mar 2021
 * CSC 202
 * Lab06--FairGuessingGame.java
 * 
 * A fair guessing game randomly chooses a target for the player to guess. After each
 * guess, the player is given a clue of too low, too high or correct. The number of 
 * guesses the player has made is also tracked.
 *
 */

import java.util.*;

public class FairGuessingGame extends GuessingGame implements Guessable {
	private static Random randGen = new Random();
	private int target;
		
	/**
	 * Constructs an object to play an fair guessing game with
	 * the range of possible targets from 1 to the value received as
	 * a parameter.
	 * @param maxValue - the maximum value of the target
	 */
	public FairGuessingGame(int maxPossibility) {
		if (maxPossibility < 1) {
			throw new IllegalArgumentException("Must be at least 1.");
		}
		int randNum = randGen.nextInt(maxPossibility) + 1;
		this.target = randNum;
	}
	
	@Override   // for processGuess
	public String processGuess(int guess) {
		super.incrementNumGuesses();
		if (guess > target) {
			return Guessable.HIGH_CLUE;
		}
		else if (guess < target) {
			return Guessable.LOW_CLUE;
		}
		else {
			return Guessable.CORRECT_CLUE;
		}
	}
	
	@Override   // for toString
	public String toString() {
		return "[target = " + target + ", numGuesses = " + super.getNumGuessesSoFar() + "]";
	}
}
