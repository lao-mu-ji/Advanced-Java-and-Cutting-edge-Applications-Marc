//
// Advanced Java Programming
// TIEI - Fall 2024
//
package tiei.ajp.blackjack;

import java.util.*;
import java.io.*;

class NullOutputStream extends OutputStream {

	@Override
	public void write(int b) throws IOException {}

}

public class BlackJack {
	
	private PrintStream output;
	private List<Player> players;
	private int unit;
	
	public BlackJack(List<Player> players, int unit, PrintStream output) {
		this.players = players;
		this.unit = unit;
		this.output = output;
	}
	
	public BlackJack(List<Player> players, int unit) {
		this.players = players;
		this.unit = unit;
		this.output = new PrintStream(new NullOutputStream());
	}	
	
	public Player play() {
		output.println("Blackjack game starts!\n");
		List<Player> playerList = new LinkedList<>(players);
		int jackpot = 0;
		int round = 1;
		
		for ( Player player : playerList ) {
			player.reset();
			player.addUnit(unit);
		}
		
		while ( playerList.size() > 1 ) {
			output.println("Round #" + (round++));
			// to be fair!
			playerList.add(playerList.removeFirst());
			Player winner = null;
			int minimum = Integer.MAX_VALUE;
			ListIterator<Player> iterator = playerList.listIterator();
			while ( iterator.hasNext() ) {
				Player p = iterator.next();
				output.println("\n" + p + " first roll is " + p.roll());
				output.println(p + " second roll is " + p.rollAgain());
				output.println(p + " scores is " + p.score());
				if ( p.score() == 21 ) {
					winner = p;
					break;
				}
				if ( p.score() > 21 ) {
					jackpot += p.removeUnit();
					output.println(p + " looses a unit");
					output.println("\njackpot is now " + jackpot);
					if ( p.unit() == 0 ) {
						iterator.remove();
						output.println(p + " has no unit left and leaves the table\n");
					}
				}
				else if ( 21 - p.score() < minimum ) {
					minimum = 21 - p.score();
					winner = p;
				}
			}
			if ( winner != null ) {
				output.println(winner + " is the winner of this round!\n");
				winner.addUnit(jackpot);
				jackpot = 0;
				reward(winner,playerList);
			}
		}
		if ( playerList.isEmpty() ) {
			output.println("no winner");
			return null;
		}
		return playerList.getFirst();
	}
	
	private void reward(Player winner, List<Player> players) {
		ListIterator<Player> iterator = players.listIterator();
		while ( iterator.hasNext() ) {
			Player p = iterator.next();
			if ( p != winner) {
				winner.addUnit(p.removeUnit());
				if ( p.unit() == 0 ) {
					iterator.remove();
					output.println(p + " has no unit left and leaves the table\n");
				}
			}
		}
	}
}
