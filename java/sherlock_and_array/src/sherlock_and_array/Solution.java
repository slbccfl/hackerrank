package sherlock_and_array;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static String solve(int[] a){
    	if (a.length == 1) return "YES";
    	int cumA[] = new int[a.length];
    	cumA[0] = a[0];
    	for (int i = 1; i < a.length; i++) {
    		cumA[i] = cumA[i - 1] + a[i];
    	}
    	int rightValue = 0;
    	int leftValue = 0;

    	for (int i = a.length - 1; i > 0; i--) {
    		leftValue = cumA[i - 1];
    		if (rightValue >= leftValue) break;
    		rightValue += a[i];
    	}
    	if (leftValue == rightValue) return "YES";
    	return "NO";
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for(int a0 = 0; a0 < T; a0++){
            int n = in.nextInt();
            int[] a = new int[n];
            for(int a_i=0; a_i < n; a_i++){
                a[a_i] = in.nextInt();
            }
            String result = solve(a);
            System.out.println(result);
        }
    }
}


