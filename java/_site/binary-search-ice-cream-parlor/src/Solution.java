import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
	
	static HashMap<Integer, Queue<Integer>> pricesDirectory = new HashMap<Integer, Queue<Integer>>();

    Queue<Integer> idQueue = new LinkedList<Integer>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++){
            int m = in.nextInt();
            int n = in.nextInt();
            int a[] = new int[n];
            pricesDirectory.clear();
            Queue<Integer> idQueue;
            for(int a_i=0; a_i < n; a_i++){
                a[a_i] = in.nextInt();
                idQueue = pricesDirectory.get(a[a_i]);
                if (idQueue == null) idQueue = new LinkedList<Integer>();
                idQueue.add(a_i);
                pricesDirectory.put(a[a_i], idQueue);
                
            }
        	Integer firstFlavorID = null;
        	int firstFlavorCost;
        	int secondFlavorCost;
            Integer secondFlavorID = null;
            for(int a_i=0; a_i < n && secondFlavorID == null; a_i++){
            	firstFlavorID = a_i;
            	firstFlavorCost = a[a_i];
            	
                idQueue = pricesDirectory.get(a[a_i]);
                idQueue.poll();
                pricesDirectory.put(a[a_i], idQueue);
            	
            	secondFlavorCost = m - firstFlavorCost;
            	idQueue = pricesDirectory.get(secondFlavorCost);
            	if (idQueue != null) secondFlavorID = idQueue.poll();
            	
            }
            System.out.println((firstFlavorID + 1) + " " + (secondFlavorID + 1));
            
            
        }
    }
 
}
