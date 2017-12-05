package jim_and_the_orders;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
	
    public static HashMap<Integer, Integer> orderArray = new HashMap<Integer, Integer>();
	
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        int n= in.nextInt();  
        for (int i = 0; i < n; i++) {
	        orderArray.put(i, in.nextInt() + in.nextInt());
        }
        orderArray = sortByValues(orderArray);
        
        for (Map.Entry<Integer, Integer> order : orderArray.entrySet()) {
        	System.out.print((order.getKey() + 1) + " ");
        }

    }
    private static HashMap sortByValues(HashMap map) { 
        List list = new LinkedList(map.entrySet());
        Collections.sort(list, new Comparator() {
             public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o1)).getValue())
                   .compareTo(((Map.Entry) (o2)).getValue());
             }
        });

        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
               Map.Entry entry = (Map.Entry) it.next();
               sortedHashMap.put(entry.getKey(), entry.getValue());
        } 
        return sortedHashMap;
   }
    
}
