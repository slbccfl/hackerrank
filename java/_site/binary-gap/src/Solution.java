import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
	
    public static void main(String[] args) {
		System.out.println(maxGap(1041));
		System.out.println(maxGap(9));
		System.out.println(maxGap(99));
		System.out.println(maxGap(9999));
		System.out.println(maxGap(999999));
		System.out.println(maxGap(99999999));
		System.out.println(maxGap(999999999));
		System.out.println(maxGap(Integer.MAX_VALUE));
		
	}
	
	public static int maxGap(int n) {
		String binaryRep = Integer.toBinaryString(n);
		System.out.println(binaryRep);
		int gap = 0;
		String[] gaps = binaryRep.split("1");
		for (int i = 0; i < gaps.length; i++) {
			gap = Math.max(gap, gaps[i].length());
		}
		return gap;
	}
}
