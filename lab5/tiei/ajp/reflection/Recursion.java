package tiei.ajp.reflection;

import java.util.Arrays;

/**
 * A class for practicing recursion
 */
public class Recursion {
	
	///// printing binary words

    /**
     * Print out all binary words of length k
     */
    public static void binary(int k) {
        binary("",k);
        System.out.println();
    }

    /**
     * Print out all binary words of length d
     * prefixed by s
     */
    private static void binary(String s, int d) {
        if (d == 0 ) {
            System.out.print(s + " ");
        }
        else {
            binary(s + "0",d-1);
            binary(s + "1",d-1);
        }
    }
    
    ///// printing A-B words
    
    /**
     * Print out all words made of
     * x letters 'A' and y letters 'B'
     */
    public static void words(int x, int y) {
        words(x,y,"");
        System.out.println();
    }
 
    /**
     * Print out all words made of
     * x letters 'A' and y letters 'B'
     * prefixed by s
     */
    private static void words(int x, int y, String s) {
        if ( x == 0 && y == 0 ) {
        	System.out.print(s + " ");
        }
        if ( x > 0 ){
        	words(x-1,y,s + 'A');
        }
        if ( y > 0 ){
            words(x,y-1,s + 'B');
        }
    }

    ///// printing all permutations of { 0, 1, 2, ..., n - 1 }
    
    /**
     * Print out all permutations of
     * (1, 2, 3, ..., n)
     */
    public static void permutations(int n) {
        int[] p = new int[n];
        for( int i = 0; i < p.length; i++ )
            p[i] = i+1;
        permutations(p,0);
        System.out.println();
    }

    /**
     * Print out the array p for all
     * permutations of (i, ..., n).
     * This method must NOT change the array p
     * (i.e. after the call, p is the same as 
     * before the call).
     */
    private static void permutations(int[] p, int i) {
        if ( i == p.length-1 )
        	System.out.print(Arrays.toString(p) + " ");
        else {
            for( int k = i; k < p.length; k++ ) {
                int x = p[i]; p[i] = p[k]; p[k] = x;
                permutations(p,i+1);
            }
            int x = p[i];
            for( int k = i+1; k < p.length; k++ )
                p[k-1] = p[k];
            p[p.length-1] = x;
        }
    }
    
    ///// checks if a given value can be found by summing up items in an array
    
    /**
     * Check if N can be found by adding
     * some values taken inside A
     */
    public static boolean sum(int[] A, int N) {
        if ( N < 0 || N > Arrays.stream(A).sum() )
            return false;
        return sum(A,N,A.length);
    }

    /**
     * Check if N can be found by adding
     * some values taken inside A[0..k-1]
     */
    private static boolean sum(int[] A, int N, int k) {
        if ( N == 0 )
            return true;
        if ( k == 0 )
            return false;
        if ( A[k-1] <= N )
            return sum(A,N,k - 1) || sum(A,N - A[k-1],k - 1);
        return sum(A,N,k - 1);
    }
}
