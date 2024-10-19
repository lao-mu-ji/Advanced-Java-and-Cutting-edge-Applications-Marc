//
// Advanced Java Programming
// TIEI - Fall 2024
//
package tiei.ajp.anagram;

import java.util.*;

/**
 * A class for MultiSet
 */
public class MultiSet<T> implements Iterable<MultiSet.Pair<T>> {

	private Map<T, Integer> map;

	/**
	 * Build an empty MultiSet
	 */
	public MultiSet() {
		map = new HashMap<>();
	}

	/**
	 * Check if the MultiSet is empty
	 */
	public boolean isEmpty() {
		if(map.isEmpty()){
			return true;
		}
		else{
			for(int count :  map.values()){
				if(count != 0){
					return false;
				}
			}
			return true;
		}
	}

	/**
	 * Check if the MultiSet contains
	 * at least one occurrence of 'item'
	 */
	public boolean contains(T item) {
		if(!map.containsKey(item)){
			return false;
		}
		else{
			return map.getOrDefault(item, 0) > 0;
		}
	}

	/**
	 * Return the number of occurrences
	 * of 'item' in the MultiSet
	 */
	public int countOf(T item) {
		if(!contains(item)){
			return 0;
		}
		return map.getOrDefault(item, 0);
	}

	/**
	 * Add 'item' in the MultiSet
	 */
	public void add(T item) {
		add(item, 1);
	}

	/**
	 * Add 'n' occurrences of 'item'
	 * in the MultiSet
	 */
	public void add(T item, int n) {
		map.put(item, map.getOrDefault(item, 0) + n);
	}

	/**
	 * Remove one occurrence of 'item'
	 * in the MultiSet
	 */
	public void remove(T item) {
		remove(item, 1);
	}

	/**
	 * Remove 'n' occurrence of 'item'
	 * in the MultiSet
	 */
	public void remove(T item, int n) {
		int count = map.getOrDefault(item, 0);
		if (count < n) {
			throw new NoSuchElementException("Not enough elements to remove");
		}
		if (count == n) {
			map.remove(item);
		} else {
			map.put(item, count - n);
		}
	}

	/**
	 * Return an iterator on the MultiSet
	 */
	public Iterator<Pair<T>> iterator() {
		return new MultiSetIterator();
	}

	/**
	 * A Pair type used by the iterator
	 */
	public record Pair<T> (T item, Integer count) {};

	private class MultiSetIterator implements Iterator<MultiSet.Pair<T>> {
		private final Iterator<Map.Entry<T, Integer>> iterator;

		public MultiSetIterator() {
			iterator = map.entrySet().iterator();
		}

		@Override
		public boolean hasNext() {
			return iterator.hasNext();
		}

		@Override
		public Pair<T> next() {
			Map.Entry<T, Integer> entry = iterator.next();
			return new Pair<>(entry.getKey(), entry.getValue());
		}
	}

}