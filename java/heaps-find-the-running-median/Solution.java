import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

		static int[] minHeap;
		static int[] maxHeap;
		static int minHeapSize = 0;
		static int maxHeapSize = 0;
	
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
//        int[] a = new int[n];
        int a;

    	BigDecimal runningMedian = new BigDecimal(0);
    	int heapCapacity = (n / 2) + 1;
    	minHeap = new int[heapCapacity];
    	maxHeap = new int[heapCapacity];
    	
        for(int a_i=0; a_i < n; a_i++){
            a = in.nextInt();
//        }
//        Arrays.sort(a);
//        double finalMedian;
//        if ((n+1) % 2 == 0) {
//        	finalMedian = a[(n-1) / 2];
//        } else {
//        	finalMedian = (a[n/2] + a[(n/2)-1]) / (double) 2;
//        }
        
//        for(int a_i=0; a_i < n; a_i++){
        	
            if ((new BigDecimal(a)).compareTo(runningMedian) == 1) {
            	addToMinHeap(a);
            } else {
            	addToMaxHeap(a);
            }
            
            if ((minHeapSize-maxHeapSize) > 1) {
            	addToMaxHeap(minHeap[0]);
            	minHeap[0] = minHeap[minHeapSize - 1];
            	minHeap[minHeapSize - 1] = 0;
            	minHeapSize--;
            	heapifyMinDown();
            } else {
                if ((maxHeapSize-minHeapSize) > 1) {
                	addToMinHeap(maxHeap[0]);
                	maxHeap[0] = maxHeap[maxHeapSize - 1];
                	maxHeap[maxHeapSize - 1] = 0;
                	maxHeapSize--;
                	heapifyMaxDown();
                }
            }
            	
            
            
            if (a_i % 2 == 0) {
            	if (minHeapSize > maxHeapSize) {
                	runningMedian = new BigDecimal(minHeap[0]).setScale(1);
            	} else {
                	runningMedian = new BigDecimal(maxHeap[0]).setScale(1);
            	}
            } else {
            	runningMedian = new BigDecimal((minHeap[0] + maxHeap[0]) / (double) 2).setScale(1);
            }

            System.out.println(runningMedian);
            
        }
        
    }
    
    private static void addToMinHeap(int a) {
    	minHeap[minHeapSize] = a;
    	minHeapSize++;
		int index = minHeapSize - 1;
		int parentIndex = (index - 1) / 2;
		while (parentIndex >= 0 && minHeap[parentIndex] > minHeap[index]) {
			swapHeapNodes(minHeap, parentIndex, index);
			index = parentIndex;
			parentIndex = (index - 1) / 2;
		}
    }
    
    private static void addToMaxHeap(int a) {
    	maxHeap[maxHeapSize] = a;
    	maxHeapSize++;
		int index = maxHeapSize - 1;
		int parentIndex = (index - 1) / 2;
		while (parentIndex >= 0 && maxHeap[parentIndex] < maxHeap[index]) {
			swapHeapNodes(maxHeap, parentIndex, index);
			index = parentIndex;
			parentIndex = (index - 1) / 2;
		}
    }
    
    private static void swapHeapNodes(int [] heap, int i1, int i2) {
    	int temp = heap[i1];
    	heap[i1] = heap[i2];
    	heap[i2] = temp;
    }
    
    private static void heapifyMaxDown() {
    	int index = 0;
    	while ((2 * index + 1) < maxHeapSize) {
    		int largerChildIndex = 2 * index + 1;
    		if ((2 * index + 2) < maxHeapSize && maxHeap[2 * index + 2] > maxHeap[2 * index + 1]){
    			largerChildIndex = 2 * index + 2;
    		}
    		
    		if (maxHeap[index] > maxHeap[largerChildIndex]) {
    			break;
    		} else {
    			swapHeapNodes(maxHeap, index, largerChildIndex);
    		}
    		index = largerChildIndex;
    	}
    }
    
    private static void heapifyMinDown() {
    	int index = 0;
    	while ((2 * index + 1) < minHeapSize) {
    		int smallerChildIndex = 2 * index + 1;
    		if ((2 * index + 2) < minHeapSize && minHeap[2 * index + 2] < minHeap[2 * index + 1]){
    			smallerChildIndex =2 * index + 2;
    		}
    		
    		if (minHeap[index] < minHeap[smallerChildIndex]) {
    			break;
    		} else {
    			swapHeapNodes(minHeap, index, smallerChildIndex);
    		}
    		index = smallerChildIndex;
    	}
    }

}
