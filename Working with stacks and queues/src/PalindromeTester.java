/*
 * Name(s): Viet Nguyen
 * CSC 202
 * Lab 7--PalindromeTester
 * 
 * PalindromeTester allows the user to enter a word or phrase from the
 * console window and tests whether the input is a palindrome. A palidrome has
 * the same letters forwards and backwards ignoring case and punctuation marks.
 */

import java.util.*;

public class PalindromeTester {

	public static void main(String[] args) {
		System.out.println("Palindrome Tester\n");
		Scanner console = new Scanner(System.in);
		
		do {
			System.out.print("Enter a word or sentence: ");
			String phraseToTest = console.nextLine();	
			phraseToTest = phraseToTest.toLowerCase();
			ArrayStack<Character> s = new ArrayStack<Character>();
			ArrayQueue<Character> q = new ArrayQueue<Character>();
			for (int i = 0; i < phraseToTest.length(); i++) {
				char token = phraseToTest.charAt(i);
				if (token < 'a' || token > 'z') {
					continue;
				}
				s.push(token);
				q.add(token);
			}
			while (!s.isEmpty()) {
				if (s.pop() != q.remove()) {
					System.out.println("Nope, that's not a palindrome.");
					break;
				}
			}
			if (s.isEmpty()) {
				System.out.println("You entered a palindrome.");
			}	
			
			
		}while (yesTo("\nDo you want to enter another word or sentence?", console));
		
		System.out.println("Thanks for using Palindrome Tester!");
		console.close();

	}
	
	/**
	 * asks the user a question, forcing an answer of "y" or "n";
	 * @param prompt String for the question prompt
	 * @param console Scanner to facilitate input
	 * @return  true if the answer was y, returns false otherwise
	 */
    public static boolean yesTo(String prompt, Scanner console) {
        System.out.print(prompt + " (y/n)? ");
        String response = console.nextLine().trim().toLowerCase();
        while (!response.equals("y") && !response.equals("n")) {
            System.out.println("Please answer y or n.");
            System.out.print(prompt + " (y/n)? ");
            response = console.nextLine().trim().toLowerCase();
        }
        return response.equals("y");
    }

}
