import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int a[] = new int[n];
        for(int a_i=0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
        }
        System.out.println(findLonelyInt(a));;
        
    }
    
    public static int findLonelyInt(int[] intArray) {
    	Arrays.sort(intArray);
    	for (int i = 0; i<intArray.length;i+=2) {
    		if (i == intArray.length - 1) {
    			return intArray[i];
    		} else {
    			if (intArray[i] != intArray[i+1]) {
        			return intArray[i];
    			}
    		}
    	}
    	return intArray[intArray.length];
    }
}
