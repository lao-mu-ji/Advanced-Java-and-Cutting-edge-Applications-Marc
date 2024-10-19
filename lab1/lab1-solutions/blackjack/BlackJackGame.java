package tiei.ajp.blackjack;

import java.util.LinkedList;
import java.util.List;

public class BlackJackGame {

	public static void main(String[] args) {
		
			List<Player> players = new LinkedList<>();
			players.add(new HumanPlayer("Joe"));
			players.add(new VirtualPlayer("Zog"));
			players.add(new VirtualPlayer("Kul"));
			
			
			BlackJack bj = new BlackJack(players,3,System.out);
			Player winner = bj.play();
			if ( winner == null )
				System.out.println("Draw!");
			else
				System.out.println("And the winner is " + winner);
	}

}
