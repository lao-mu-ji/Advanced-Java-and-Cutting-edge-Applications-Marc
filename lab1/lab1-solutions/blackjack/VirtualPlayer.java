//
// Advanced Java Programming
// TIEI - Fall 2024
//
package tiei.ajp.blackjack;

public class VirtualPlayer extends Player {
	
	public VirtualPlayer(String name) {
		super(name);
	}

	@Override
	public int secondRoll() {
		int dices = 1;
        if ( score() < 9 )
            dices = 3;
        else if ( score() < 15 )
        	dices = 2;
		return dices;
	}
}
