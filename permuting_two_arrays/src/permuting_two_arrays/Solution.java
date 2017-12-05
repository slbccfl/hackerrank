package permuting_two_arrays;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
	
    public static List<Integer> arrayA = new LinkedList<Integer>();
    public static List<Integer> arrayB = new LinkedList<Integer>();
	
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q= in.nextInt();  
        
        for (int i = 0;i < q; i++) {
            int n = in.nextInt();
            int k = in.nextInt();
            for (int j = 0; j < n; j++) {
            	arrayA.add(in.nextInt());
            }
            for (int j = 0; j < n; j++) {
            	arrayB.add(in.nextInt());
            }

            Collections.sort(arrayA);
            Collections.sort(arrayB, Collections.reverseOrder());

            System.out.println(validatePermutations(n, k));
            arrayA.clear();
            arrayB.clear();
        }

    }
    
    public static String validatePermutations(int n, int k) {
    	for (int i = 0; i < n; i++) {
    		if (arrayA.get(i) + arrayB.get(i) < k) {
    			return "NO";
    		}
    	}
    	return "YES";
    }
}
