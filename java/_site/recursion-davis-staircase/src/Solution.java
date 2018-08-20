import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
	
	private static HashMap<Integer, Integer> waysToClimbMem = new HashMap<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int s = in.nextInt();
        waysToClimbMem.put(0, 0);
        waysToClimbMem.put(1, 1);
        waysToClimbMem.put(2, 2);
        waysToClimbMem.put(3, 4);
        
        for(int a0 = 0; a0 < s; a0++){
            int n = in.nextInt();
            System.out.println(waysToClimbStairs(n));
        }
        in.close();
    }
    
    private static int waysToClimbStairs(int steps) {
    	Integer waysToClimb = waysToClimbMem.get(steps);
    	if (waysToClimb != null) {
    		return waysToClimb;
    	} else {
    		waysToClimb = 0;
    	}
    	waysToClimb += waysToClimbStairs(steps-1);
    	if (steps > 1)
    		waysToClimb += waysToClimbStairs(steps-2);
    	if (steps > 2)
    		waysToClimb += waysToClimbStairs(steps-3);
    	waysToClimbMem.put(steps, waysToClimb);
    	return waysToClimb;
    		
    }
}

