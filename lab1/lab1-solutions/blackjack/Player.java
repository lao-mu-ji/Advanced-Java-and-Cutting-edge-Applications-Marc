//
// Advanced Java Programming
// TIEI - Fall 2024
//
package tiei.ajp.blackjack;

public abstract class Player {
	
	private String name;
	private int score;
	private int unit;
	
	private static final Dice dice = new Dice();
	
	public Player(String name) {
		this.name = name;
		this.unit = 0;
		this.score = 0;
	}
	
	public String name() {
		return name;
	}
	
	public int score() {
		return score;
	}

	public int unit() {
		return unit;
	}
	
	public void reset() {
		this.unit = 0;
		this.score = 0;
	}
	
	public int removeUnit() {
		unit--;
		return 1;
	}
	
	public void addUnit(int n) {
		unit += n;
	}
	
	public int roll() {
		score = dice.roll(3);
		return score;
	}
	
	public int rollAgain() {
		int n = dice.roll(secondRoll());
		score += n;
		return n;
	}
	
	public abstract int secondRoll();
	
	@Override
	public String toString() {
		return name + " (" + unit + ")";
	}
}
