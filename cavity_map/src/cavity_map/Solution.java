package cavity_map;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
	

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String[] grid = new String[n];
        for(int grid_i=0; grid_i < n; grid_i++){
            grid[grid_i] = in.next();
        }
    	boolean[][] cavities = new boolean[n][n];
        
        for (int row = 1; row < n - 1; row++) {
        	for (int col = 1; col < n - 1; col++) {
        		int cellDepth = Integer.parseInt(grid[row].substring(col, col + 1));
        		if ((Integer.parseInt(grid[row - 1].substring(col, col + 1)) < cellDepth)
        				&& (Integer.parseInt(grid[row + 1].substring(col, col + 1)) < cellDepth)
        				&& (Integer.parseInt(grid[row].substring(col - 1, col)) < cellDepth)
        				&& (Integer.parseInt(grid[row].substring(col + 1, col + 2)) < cellDepth)
        				) {
        			cavities[row][col] = true;
        		}
        	}
        }
        
        for (int row = 0; row < n; row++) {
        	for (int col = 0; col < n; col++) {
        		char character = grid[row].charAt(col);
        		if (cavities[row][col]) character = 'X';
            	System.out.print(character);
        	}
        	System.out.println();
        }
    }
}


