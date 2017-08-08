package com.hackerrank.slbcr.cci.dfsconnected;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class Node {
	int row;
	int col;
	LinkedList<Node> adjacencyList; 
	
	public Node(int r, int c) {
		row = r;
		col = c;
		adjacencyList = new LinkedList<Node>();
	}
}

public class Solution {
	
	static int maxRow;
	static int maxCol;
	static Queue<Node> queue = new LinkedList<Node>();
	static Boolean[][] visited;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        maxRow = n;
        maxCol = m;
        visited = new Boolean[n][m];
        int grid[][] = new int[n][m];
        for(int grid_i=0; grid_i < n; grid_i++){
            for(int grid_j=0; grid_j < m; grid_j++){
                grid[grid_i][grid_j] = in.nextInt();
                visited[grid_i][grid_j] = false;
            }
        }
        System.out.println(evaluateGrid(grid));
        in.close();
    }
    
    public static int evaluateGrid(int[][] grid) {
    	Node startNode = findRegion(grid);
    	int maxNodes = 0;
		while (startNode != null) {
        	queue.add(startNode);
    		maxNodes = Math.max(evaluateRegion(grid),maxNodes);
    		startNode = findRegion(grid);
    	}

    	return maxNodes;
    }
    
    public static Node findRegion(int[][] grid) {
    	for (int row = 0; row < maxRow; row++) {
        	for (int col = 0; col < maxCol; col++) {
        		if (grid[row][col] == 1) {
        			return new Node(row, col);
        		}
        	}
    	}
		return null;
    }
    
    public static int evaluateRegion(int[][] grid) {
    	int regionCount = 0;
		while (!queue.isEmpty()) {
			Node currentNode = queue.poll();
			evaluateNode(currentNode);
			for (Node node :  currentNode.adjacencyList) {
				if (!visited[node.row][node.col] && grid[node.row][node.col] == 1) {
					queue.add(node);
					visited[node.row][node.col] = true;
				}
			}

			regionCount++;
			grid[currentNode.row][currentNode.col] = -1; 
		}
    		
    	return regionCount;
    }
    
    public static void evaluateNode(Node node) {
    	int row, col;
    	
    	row = node.row + 1;
    	col = node.col;
		if (validCell(row,col)) {
			Node adjacentNode = new Node(row, col);
			node.adjacencyList.add(adjacentNode);
		}
    	row = node.row - 1;
    	col = node.col;
		if (validCell(row,col)) {
			Node adjacentNode = new Node(row, col);
			node.adjacencyList.add(adjacentNode);
		}    	
		row = node.row + 1;
    	col = node.col + 1;
		if (validCell(row,col)) {
			Node adjacentNode = new Node(row, col);
			node.adjacencyList.add(adjacentNode);
		}
    	row = node.row - 1;
    	col = node.col + 1;
		if (validCell(row,col)) {
			Node adjacentNode = new Node(row, col);
			node.adjacencyList.add(adjacentNode);
		}		
		row = node.row + 1;
    	col = node.col - 1;
		if (validCell(row,col)) {
			Node adjacentNode = new Node(row, col);
			node.adjacencyList.add(adjacentNode);
		}
    	row = node.row - 1;
    	col = node.col - 1;
		if (validCell(row,col)) {
			Node adjacentNode = new Node(row, col);
			node.adjacencyList.add(adjacentNode);
		}	
		row = node.row;
    	col = node.col + 1;
		if (validCell(row,col)) {
			Node adjacentNode = new Node(row, col);
			node.adjacencyList.add(adjacentNode);
		}
    	row = node.row;
    	col = node.col - 1;
		if (validCell(row,col)) {
			Node adjacentNode = new Node(row, col);
			node.adjacencyList.add(adjacentNode);
		}

    	return;
    }
    
    public static boolean validCell(int row, int col) {
    	boolean returnValidation = true;
    	if (row < 0 || row >= maxRow || col < 0 || col >= maxCol) returnValidation = false;
    	return returnValidation;
    }
}

