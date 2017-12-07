package flipping_bits;

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
        	long number = in.nextLong();
            System.out.println(~number & 0xffffffffl);
        	
        }


    }
}
