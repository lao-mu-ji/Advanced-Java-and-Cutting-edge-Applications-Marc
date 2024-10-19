//
// Advanced Java Programming
// TIEI - Fall 2024
//
package tiei.ajp.anagram;

import java.util.*;
import java.io.*;

/**
 * This is the driver class for the Anagram Solver.
 * YOU MUST NOT CHANGE THIS CLASS (except line 26)!!
 */
public class AnagramMain {

	/**
	 * The main method for the Anagram Solver
	 */
	public static void main(String[] args) throws FileNotFoundException {
		Scanner console = new Scanner(System.in);
		System.out.println("Welcome to the Anagram Solver!");
		System.out.println();
		
		// System.out.print("Dictionary file path? ");
		// Scanner input = new Scanner(new File(console.nextLine()));
		Scanner input = new Scanner(new File("eleven.txt"));

//		while (input.hasNextLine()) {
//			String line = input.nextLine();
//			System.out.println(line);
//		}

//		System.out.println(input);
		AnagramSolver as = new AnagramSolver(input);
		input.close();
		
		while ( true ) {
			System.out.print("Phrase to scramble (return to quit): ");
			String phrase = console.nextLine().trim();
			if ( phrase.length() == 0 )
				break;
			int max = readMax(console);
			as.print(phrase, max);
			System.out.println();
		}
		
		console.close();
		System.out.println();
		System.out.println("Thank you for using the Anagram Solver!");
	}
	
	/**
	 * Reads a positive integer from the keyboard
	 */
	private static int readMax(Scanner console) {
		int max = 0;
		while ( true ) {
			System.out.print("Max words to include (0 for no max)? ");
			String s = console.nextLine();
			try {
				max = Integer.parseInt(s);
			}
			catch (Exception e) {
				System.out.println(s + " is not an integer");
				continue;
			}
			if ( max < 0 )
				System.out.println(max + " is negative");
			else
				return max;
		}
	}
}
