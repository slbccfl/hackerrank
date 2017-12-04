package missing_numbers;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
	
    static List<Integer> arrayA = new LinkedList<Integer>();
    static List<Integer> arrayB = new LinkedList<Integer>();
	
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        
        for (int i = 0;i < n; i++) {
        	arrayA.add(in.nextInt());
        }
        
        int m = in.nextInt();

        for (int i = 0;i < m; i++) {
        	arrayB.add(in.nextInt());
        }
        findMissingNumbers();

        System.out.println(outputArray(arrayB));

    }
    public static List<Integer> findMissingNumbers() {
    	for (Integer number : arrayA) {
    		arrayB.remove(number);
    	}
    	
    	return arrayB;
    	
    }
    
    public static String outputArray(List<Integer> array) {
//        Collections.sort(array);
        SortedSet<Integer> set = new TreeSet<Integer>(array);
        StringBuilder outputString = new StringBuilder();
    	for (Integer number : set) {
    		outputString.append(number).append(" ");
    		
    	}
    	
    	return outputString.toString().trim();
    }
}
