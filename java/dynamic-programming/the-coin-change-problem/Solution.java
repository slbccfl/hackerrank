import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
	
	static long[][] solvedAmounts = new long[50][251];
	static boolean[][] solvedFlags = new boolean[50][251];

    static long getWays(long n, long[] c){
    	int numberOfCoins = c.length;
		if (numberOfCoins == 0)  return 0;
    	long lastCoin = c[numberOfCoins-1];

    	long waysWithoutLastCoin;
    	long waysWithLastCoin;
    	
		if (solvedFlags[numberOfCoins][(int) n] == false) {
	    	waysWithoutLastCoin = getWays(n, Arrays.copyOf(c,numberOfCoins-1));
	    	
	    	if (lastCoin == n) {
	    		waysWithLastCoin = 1;
	    	} else {
		    	if (lastCoin > n) {
		    		waysWithLastCoin = 0;
		    	} else {
		    		waysWithLastCoin = getWays(n-lastCoin, c);
		    	}
	    	}
	    	
			solvedAmounts[numberOfCoins][(int) n] = waysWithoutLastCoin + waysWithLastCoin;
			solvedFlags[numberOfCoins][(int) n] = true;
		}
		
    	return solvedAmounts[numberOfCoins][(int) n];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        long[] c = new long[m];
        for(int c_i=0; c_i < m; c_i++){
            c[c_i] = in.nextLong();
        }

        Arrays.sort(c);						// Sort coins array so that sub-arrays are always the same
        // Print the number of ways of making change for 'n' units using coins having the values given by 'c'
        long ways = getWays(n, c);
        System.out.println(ways);
    }
}
