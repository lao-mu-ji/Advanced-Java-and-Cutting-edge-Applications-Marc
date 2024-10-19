//
// Advanced Java Programming
// TIEI - Fall 2024
//
package tiei.ajp.blackjack;

import java.util.*;

public class Dice {
	
	private int faces;
	private Random random;
	
	public Dice(int faces) {
		this.faces = faces;
		this.random = new Random();
	}
	
	public Dice() {
		this(6);
	}

	public int roll(int n) {
		int total = 0;
		for ( int i = 0; i < n; i++ )
			total += random.nextInt(faces) + 1;
		return total;
	}	
}
