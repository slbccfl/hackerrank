import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class Node {
	int row;
	int col;
	boolean settled;
	LinkedList<Node> adjacencyList; 
	
	public Node(int row, int col) {
		settled = false;
		adjacencyList = new LinkedList<Node>();
	}
}

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int grid[][] = new int[n][m];
        for(int grid_i=0; grid_i < n; grid_i++){
            for(int grid_j=0; grid_j < m; grid_j++){
                grid[grid_i][grid_j] = in.nextInt();
            }
        }
        System.out.println(evaluateGrid(grid));
        in.close();
    }
    
    public static int evaluateGrid(int[][] grid) {

    	return 5;
    }
    
    public static int evaluateNode(int row, int col, Node node) {
    		
    	return 5;
    }
}

