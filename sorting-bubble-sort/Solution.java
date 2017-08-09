import java.io.*;
import java.util.*;

public class Solution {

    static long countInversions(int[] arr) {
        return mergeSort(arr, new int[arr.length], 0, arr.length - 1);
    }
    
    static long mergeSort (int arr[], int temp[], int left, int right) {
    	int middle;
    	long invCount = 0;
    	if (right > left) {
        	middle = (right + left) / 2;
        	invCount = mergeSort(arr, temp, left, middle);
        	invCount += mergeSort(arr, temp, middle+1, right);
    		invCount += merge(arr, temp, left, middle+1, right);
    	}
    	return invCount;
    }
    
    static long merge (int arr[], int temp[], int left, int middle, int right) {
    	int leftIndex, mergeIndex, rightIndex;
    	long invCount = 0;
    	
    	leftIndex = left;
    	rightIndex = middle;
    	mergeIndex = left;
    	while ((leftIndex <= middle - 1) && rightIndex <= right) {
    		if (arr[leftIndex] <= arr[rightIndex]) {
    			temp[mergeIndex] = arr[leftIndex];
    			leftIndex++;
    		} else {
    			temp[mergeIndex] = arr[rightIndex];
    			invCount = invCount + (rightIndex - mergeIndex);
    			rightIndex++;
    		}
    		mergeIndex++;
    	}
    	System.arraycopy(arr, leftIndex, temp, mergeIndex, middle - leftIndex);
    	System.arraycopy(arr, rightIndex, temp, mergeIndex, right - rightIndex + 1);
    	System.arraycopy(temp, left, arr, left, right - left + 1);
    	
    	return invCount;
    	
    }

    public static void main(String[] args) {
        
            Scanner in = new Scanner(System.in);
            int n = in.nextInt();
            int[] arr = new int[n];
            for(int arr_i = 0; arr_i < n; arr_i++){
                arr[arr_i] = in.nextInt();
            }
            long result = countInversions(arr);
            System.out.println("Array is sorted in " + result + " swaps.");
            System.out.println("First Element: " + arr[0]);
            System.out.println("Last Element: " + arr[arr.length - 1]);
            in.close();
   }
}