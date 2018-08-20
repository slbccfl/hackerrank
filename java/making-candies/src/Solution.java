import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static long minimumPasses(long m, long w, long p, long n) {
    	long totalN = 0;
    	do  {
    		long purchases = (totalN / p) / 2;
    		totalN += m * w;
    		
    		
    	} while (totalN < n);
    	
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long m = in.nextLong();
        long w = in.nextLong();
        long p = in.nextLong();
        long n = in.nextLong();
        long result = minimumPasses(m, w, p, n);
        System.out.println(result);
        in.close();
    }
}
