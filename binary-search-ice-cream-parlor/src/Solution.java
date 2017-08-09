import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
	
	static HashMap<Integer, Stack<Integer>> pricesDirectory = new HashMap<Integer, Stack<Integer>>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++){
            int m = in.nextInt();
            int n = in.nextInt();
            int a[] = new int[n];
            pricesDirectory.clear();
            Stack<Integer> idStack;
            for(int a_i=0; a_i < n; a_i++){
                a[a_i] = in.nextInt();
                idStack = pricesDirectory.get(a[a_i]);
                idStack.push(a_i);
                pricesDirectory.put(a[a_i], idStack);
                
            }
        	Integer firstFlavorID = null;
        	int firstFlavorCost;
        	int secondFlavorCost;
            Integer secondFlavorID = null;
            for(int a_i=0; a_i < n && secondFlavorID == null; a_i++){
            	firstFlavorID = a_i;
            	firstFlavorCost = a[a_i];
            	
                idStack = pricesDirectory.get(a[a_i]);
                idStack.pop();
                pricesDirectory.put(a[a_i], idStack);
            	
            	secondFlavorCost = m - firstFlavorCost;
            	idStack = pricesDirectory.get(secondFlavorCost);
            	secondFlavorID = idStack.pop()
            	
            }
            System.out.println((firstFlavorID + 1) + " " + (secondFlavorID + 1));
            
            
        }
    }
 
}
