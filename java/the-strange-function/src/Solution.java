import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.util.stream.IntStream;

public class Solution {

    static long maximumValue(int[] a) {
    	long maxV = 0;
    	for (int l = 0; l < a.length - 1; l++) {
    		for (int r = l; r < a.length - 1; r++) {
    			long newMaxV = subMaxValue(Arrays.copyOfRange(a, l, r));
    			if (newMaxV > maxV) maxV = newMaxV;
    		}
    	}
    	return maxV;
    }

    static long subMaxValue(int[] a) {
    	Arrays.sort(a);
    	long maxInArray = a[a.length - 1];
    	long maxV = gcdArray(a) * (IntStream.of(a).sum() - maxInArray);
    	return maxV;
    }
    
    static long gcdArray(int[] a) {
    	long maxGcd = 0;
    	for (int i = 0; i < a.length; i++) {
    		for (int j = 0; j < a.length - 1; ) {
    			long newGcd = gcd(a[i],a[j]);
    			if (newGcd > maxGcd) maxGcd = newGcd;
    		}
    	}
    	return maxGcd;
    }
    
    static int gcd(int x, int y) {
    	if (y != 0) {
    		gcd(y, x%y);
    	}
		return x;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] a = new int[n];
        for(int a_i = 0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
        }
        long result = maximumValue(a);
        System.out.println(result);
        in.close();
    }
}
