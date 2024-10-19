//
// Advanced Java Programming
// TIEI - Fall 2024
//
package tiei.ajp.blackjack;

import java.util.*;
import java.io.*;

public class Statistics {

	public static void main(String[] args) {
		List<Player> players = new LinkedList<>();
		players.add(new VirtualPlayer("zog"));
		players.add(new VirtualPlayer("kul"));
		players.add(new VirtualPlayer("wex"));
		
		int z = 0;
		int k = 0;
		int w = 0;
		
		BlackJack bj = new BlackJack(players,3);
		
		for ( int i = 0; i < 20000; i++ ) {
			Player winner = bj.play();
			if ( winner == null ) {
				System.out.println("Draw!");
				continue;
			}
			if ( winner.name().equals("zog") )
				z++;
			else if ( winner.name().equals("kul") )
				k++;
			else
				w++;
		}
		
		System.out.println("zog: " + z + ", kul: " + k + ", wex: " + w);
	}
}
