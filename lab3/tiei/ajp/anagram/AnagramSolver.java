package tiei.ajp.anagram;

import java.util.*;

public class AnagramSolver {
	private Map<LetterCounter, List<String>> dictionaryMap;

	public AnagramSolver(Scanner dictionary) {
		dictionaryMap = new HashMap<>();
		while (dictionary.hasNextLine()) {
			String word = dictionary.nextLine();
			LetterCounter counter = new LetterCounter(word);
			dictionaryMap.computeIfAbsent(counter, k -> new ArrayList<>()).add(word);
		}
	}

	public void print(String text, int max) {
		if (max < 0) {
			throw new IllegalArgumentException("Max cannot be less than 0");
		}
		LetterCounter phraseCounter = new LetterCounter(text);
		printHelper(phraseCounter, max, "", 0);
	}

	private void printHelper(LetterCounter remaining, int max, String current, int wordsUsed) {
//		System.out.println("wordsUsed: " + wordsUsed);
//		System.out.println("dictionarymap" + dictionaryMap);
//		System.out.println("Remaining: " + remaining);
//		System.out.println("Current: " + current);
		if (remaining.isEmpty()) {
			System.out.println(current.trim());
			return;
		}
		if (max != 0 && wordsUsed >= max) {
			return;
		}

		for (Map.Entry<LetterCounter, List<String>> entry : dictionaryMap.entrySet()) {
			LetterCounter wordCounter = entry.getKey();
			if (wordCounter.isEmpty()) {
				continue;
			}
			if (wordCounter.includedIn(remaining)) {
				LetterCounter newRemaining = remaining.subtract(wordCounter);
				wordsUsed++;
				printHelper(newRemaining, max, current + " " + entry.getValue().get(0), wordsUsed);
				wordsUsed--;
			}
		}
	}
}
