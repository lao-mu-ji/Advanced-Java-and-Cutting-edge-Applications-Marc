//
// Advanced Java Programming
// TIEI - Fall 2024
//
package tiei.ajp.anagram;

import java.util.*;
import java.util.stream.Collectors;

public class LetterCounter {
	private MultiSet<Character> counter;
	/*
	 * Constructs an LetterCounter (a count) of the alphabetic
	 * letters in the given string input, ignoring the case of
	 * letters and ignoring any non-alphabetic characters.
	 */
	public LetterCounter(String input) {
		counter = new MultiSet<>();
		for (char c : input.toCharArray()) {
			if (Character.isLetter(c)) {
				c = Character.toLowerCase(c);
				counter.add(c);
			}
		}
	}

	/*
	 * Constructs an LetterCounter (a count) of the alphabetic
	 * letters in the empty string input, in other words, an
	 * empty LetterCounter.
	 */
	public LetterCounter() {
		counter = new MultiSet<>();
	}
	
	/*
	 * Returns true if this LetterCounter is empty (all counts are 0).
	 */
	public boolean isEmpty() {
		return counter.isEmpty();
	}

	/*
	 * Constructs and returns a new LetterCounter object that
	 * represents the result of subtracting the other LetterCounter
	 * from this LetterCounter (i.e., subtracting the counts in the
	 * other LetterCounter from this character’s counts). If any 
	 * resulting count would be negative, returns null. 
	 */
	public LetterCounter subtract(LetterCounter other) {
		LetterCounter result = new LetterCounter();
		for (MultiSet.Pair<Character> entry : counter) {
			char letter = entry.item();
			int count = entry.count();
			int otherCount = other.counter.countOf(letter);
			if (count < otherCount) {
				return null;
			}
			result.counter.add(letter, count - otherCount);
		}
		return result;
	}

	/**
	 * Returns true if this LetterCounter is included in 
	 * the other inventory, false otherwise.
	 */
	public boolean includedIn(LetterCounter other) {
		for (MultiSet.Pair<Character> entry : counter) {
			char letter = entry.item();
			int count = entry.count();
			int otherCount = other.counter.countOf(letter);
			if (count > otherCount) {
				return false;
			}
		}
		return true;
	}
	
	/*
	 * Returns a String representation of the LetterCounter
	 * with the letters all in lowercase and in sorted
	 * order and surrounded by square brackets.
	 */
	@Override
	public String toString() {
		List<MultiSet.Pair<Character>> pairs = new ArrayList<>();
		for (MultiSet.Pair<Character> pair : counter) {
			pairs.add(pair);
		}
		pairs.sort(Comparator.comparing(MultiSet.Pair::item));

		StringBuilder sb = new StringBuilder("[");
		for (int i = 0; i < pairs.size(); i++) {
			MultiSet.Pair<Character> pair = pairs.get(i);
			if (i > 0) {
				sb.append(", ");
			}
			sb.append(pair.item()).append(":").append(pair.count());
		}
		sb.append("]");
		return sb.toString();
	}
	
}
