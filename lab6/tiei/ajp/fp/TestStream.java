package tiei.ajp.fp;

import java.io.BufferedWriter;
import java.util.function.IntPredicate;
import java.util.stream.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


/**
 * A convenient class for exercise 8 of lab 6
 */
class IntList implements Comparable<IntList> {
	
	private final int value;
	private final IntList next;

	private static IntPredicate isPrime;
	
	public IntList(int value) {
		this.value = value;
		this.next = null;
	}
	
	public IntList(int value, IntList next) {
		this.value = value;
		this.next = next;
	}

	@Override
	public int compareTo(IntList il) {
		if ( il == null )
			return 1;
		IntList tmp1 = this;
		IntList tmp2 = il;
		while ( tmp1 != null && tmp2 != null && tmp1.value == tmp2.value ) {
			tmp1 = tmp1.next;
			tmp2 = tmp2.next;
		}
		if ( tmp1 == null ) {
			if ( tmp2 == null )
				return 0;
			return -1;
		}
		if ( tmp2 == null )
			return 1;
		return tmp1.value - tmp2.value;
	}
	
	@Override
	public String toString() {
		String s = "(" + value;
		IntList tmp = next;
		while ( tmp != null ) {
			s += "," + tmp.value;
			tmp = tmp.next;
		}
		return s + ")";
	}

	public Stream<IntList> perms(BitSet bs) {
		if (bs.isEmpty()) return Stream.of(this);
		return IntStream.range(0, bs.length())
				.filter(bs::get)
				.mapToObj(i -> new IntList(i, this).perms(TestStream.subtract(bs, i)))
				.flatMap(s -> s);
	}
}


public class TestStream {
	
	///////////// A main for quick testing
	
	public static void main(String[] args) throws IOException {
		
		///////////// exercise 1
		/*List <Integer> nums = Arrays.asList(1, 3, 6, 8, 10, 18, 36);
		double average = average(nums);
		System.out.println(average); // expected output: 11.714285714285714
		///////////// exercise 2
		System.out.println(fibo(10)); // expected output: 55
		System.out.println(fibo(20)); // expected output: 6765
		///////////// exercise 3
		System.out.println("13 is prime: " + isPrime(13)); // expected output: 13 is prime: true
		System.out.println("29 is prime: " + isPrime(29)); // expected output: 29 is prime: true
		System.out.println("53 is prime: " + isPrime(53)); // expected output: 29 is prime: true
		System.out.println("1537 is prime: " + isPrime(1537)); // expected output: 1537 is prime: false
		///////////// exercise 4
		perfect(4); // expected output: 6 28 496 8128
		///////////// exercise 5
		System.out.println(sieve(30)); // expected output: [2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113]
		///////////// exercise 6
		fibo(20,"output.txt");
		///////////// exercise 7
		System.out.println(sieve2(30)); // expected output: [2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113]
		///////////// exercise 8
		perms(3);
		System.out.println();*/
//		perms(4);
		queens(3); // no solution
//		System.out.println();
		queens(4); // 2 solutions
//		System.out.println();
		queens(5); // 10 solutions
	}

	
	///////////// exercise 1

	public static double average(List<Integer> list) {
		if (list == null || list.isEmpty()) {
			return 0;
		}
		return list.stream()
				.mapToDouble(Integer::doubleValue)
				.average()
				.orElse(0.0);
	}
	
	///////////// exercise 2
	
	private record Pair (Integer first, Integer second){};

	public static int fibo(int n) {
		if (n == 0) return 0;
		if (n == 1) return 1;

		return Stream.iterate(new Pair(0, 1), p -> new Pair(p.second, p.first + p.second))
				.limit(n+1)
				.reduce((firstPair, secondPair) -> secondPair)
				.map(Pair::first)
				.orElse(0);
	}
	
	///////////// exercise 3

	public static boolean isPrime(int n) {
		if (n <= 1) {
			return false; // 0 and 1 are not prime numbers
		}
		int limit = (int) Math.sqrt(n) + 1;
		return IntStream.range(2, limit)
				.mapToObj(i -> n % i == 0)
				.reduce(true, (isPrime, divides) -> isPrime && !divides);
	}
	
	///////////// exercise 4

	public static void perfect(int k) {
		IntStream.iterate(2, n -> n + 1)
				.filter(TestStream::isPerfect)
				.limit(k)
				.forEach(System.out::println);
	}

	private static boolean isPerfect(int number) {
		return IntStream.range(1, number)
				.filter(divisor -> number % divisor == 0)
				.sum() == number;
	}
	
	///////////// exercise 5

	public static List<Integer> sieve(int k) {
		if (k <= 0) {
			return Collections.emptyList();
		}

		int limit = k * (int) (Math.log(k) + Math.log(Math.log(k)));
		BitSet sieve = new BitSet(limit + 1);
		sieve.set(2, limit + 1);

		return IntStream.iterate(2, i -> i <= limit, i -> i + 1)
				.filter(sieve::get)
				.peek(i -> IntStream.iterate(i * i, j -> j <= limit, j -> j + i)
						.forEach(sieve::clear))
				.limit(k)
				.boxed()
				.collect(Collectors.toList());
	}



	///////////// exercise 6

	public static void fibo(int k, String filepath) throws IOException {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))) {
			Stream.iterate(new Pair(0, 1), p -> new Pair(p.second, p.first + p.second))
					.map(p -> p.first)
					.limit(k)
					.forEach(fibNumber -> {
						try {
							writer.write(fibNumber + System.lineSeparator());
						} catch (IOException e) {
							throw new RuntimeException(e);
						}
					});
		}
	}
	
	///////////// exercise 7
	
	private static IntPredicate isPrime = x -> true;

	public static List<Integer> sieve2(int k) {
		if (k <= 0) {
			return Collections.emptyList();
		}

		isPrime = n -> n == 2 || n > 2 && IntStream.rangeClosed(2, (int) Math.sqrt(n))
				.noneMatch(divisor -> n % divisor == 0);

		return IntStream.iterate(2, n -> n + 1)
				.filter(n -> isPrime.test(n))
				.limit(k)
				.boxed()
				.collect(Collectors.toList());
	}
	
	///////////// exercise 8
	
	static BitSet subtract(BitSet bs, int n) {
		BitSet newBitSet = (BitSet) bs.clone();
		newBitSet.flip(n);
		return newBitSet;
	}

	public static void perms(int n) {
		BitSet bs = new BitSet(n);
		bs.set(0, n);
		IntStream.range(0, n)
				.mapToObj(i -> new IntList(i).perms(subtract(bs, i)))
				.flatMap(s -> s)
				.sorted()
				.forEach(System.out::println);
	}

	public static void queens(int n) {
		int[] columns = new int[n];
		placeQueens(columns, 0);
	}

	private static void placeQueens(int[] columns, int row) {
		int n = columns.length;
		if (row == n) {
			printSolution(columns);
		} else {
			for (int col = 0; col < n; col++) {
				if (isSafe(columns, row, col)) {
					columns[row] = col;
					placeQueens(columns, row + 1);
					columns[row] = 0;
				}
			}
		}
	}

	private static boolean isSafe(int[] columns, int row, int col) {
		for (int i = 0; i < row; i++) {
			if (columns[i] == col || Math.abs(columns[i] - col) == Math.abs(i - row)) {
				return false;
			}
		}
		return true;
	}

	private static void printSolution(int[] columns) {
		for (int col : columns) {
			System.out.print("(" + col + ")");
		}
		System.out.println();
	}
}
