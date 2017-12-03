package longest_common_subsequence;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
	
	static String[][] lcsMemArray;

    static List<Integer> arrayA = new ArrayList<Integer>();
    static List<Integer> arrayB = new ArrayList<Integer>();
	
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();

        for (int i = 0;i < n; i++) {
        	arrayA.add(in.nextInt());
        }
        for (int i = 0;i < m; i++) {
        	arrayB.add(in.nextInt());
        }
     
        lcsMemArray = new String[n][m];
        System.out.println(lcs(n - 1, m - 1));

    }
    
    static String lcs(int endA, int endB){
    	if (endA < 0 || endB < 0) return "";
    	if (lcsMemArray[endA][endB] != null) return lcsMemArray[endA][endB];
    	String lcsString;
    	if (arrayA.get(endA).equals(arrayB.get(endB))) {
    		lcsString = lcs(endA - 1, endB - 1) + " " + arrayA.get(endA);
    	} else {
    		String lcsStringA = lcs(endA - 1, endB);
    		String lcsStringB = lcs(endA, endB - 1);
    		int valuesA = lcsStringA.length() - lcsStringA.replace(" ", "").length() + 1;
    		int valuesB = lcsStringB.length() - lcsStringB.replace(" ", "").length() + 1;
    		if (valuesA > valuesB) {
    			lcsString = lcsStringA;
    		} else {
    			lcsString = lcsStringB;
    		}
    	}
    	
    	lcsMemArray[endA][endB] = lcsString;
//    	System.out.println(endA + " - " + endB + " >>> " + lcsString);
    	return lcsString.trim();
    }
}
