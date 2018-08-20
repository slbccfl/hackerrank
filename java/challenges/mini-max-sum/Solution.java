import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] arr = new int[5];
        for(int arr_i=0; arr_i < 5; arr_i++){
            arr[arr_i] = in.nextInt();
        }
        
        Arrays.sort(arr);
        
        long minSum = 0;
        long maxSum = 0;
        for (int i = 1; i<5; i++) {
        	minSum += arr[i-1];
        	maxSum += arr[i];
        }
        
        System.out.println(minSum + " " + maxSum);
    }
}
