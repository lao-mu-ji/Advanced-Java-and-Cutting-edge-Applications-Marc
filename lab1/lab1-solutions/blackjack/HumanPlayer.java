//
// Advanced Java Programming
// TIEI - Fall 2024
//
package tiei.ajp.blackjack;

import java.util.*;

public class HumanPlayer extends Player {
	
	private static Scanner input = new Scanner(System.in);
	
	public HumanPlayer(String name) {
		super(name);
	}

	@Override
	public int secondRoll() {
		System.out.print("how many dices to roll? ");
		return input.nextInt();
	}

}
