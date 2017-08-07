package com.hackerrank.slbcr.cci.dfsconnected;
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
	
	public Node(int r, int c) {
		row = r;
		col = c;
		settled = false;
		adjacencyList = new LinkedList<Node>();
	}
}

public class Solution {
	
	static int maxRow;
	static int maxCol;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        maxRow = n;
        maxCol = m;
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
    	Node startNode = findRegion(grid);
    	int maxNodes = 0;
    	while (startNode != null) {
    		maxNodes = Math.max(evaluateRegion(grid, startNode),maxNodes);
    		startNode = findRegion(grid);
    	}

    	return maxNodes;
    }
    
    public static Node findRegion(int[][] grid) {
    	for (int row = 0; row <= maxRow; row++) {
        	for (int col = 0; col <= maxCol; row++) {
        		if (grid[row][col] == 1) {
        			return new Node(row, col);
        		}
        	}
    	}
		return null;
    }
    
    public static int evaluateRegion(int[][] grid, Node startNode) {
    	int regionCount = 0;
		Node nextNode = startNode;
		while (nextNode != null) {
			Node currentNode = nextNode;
			evaluateNode(currentNode);
			for (Node node :  currentNode.adjacencyList) {
				if (grid[node.row][node.col] == 1) {
					nextNode = node;
				}
			}

			regionCount++;
			grid[currentNode.row][currentNode.col] = -1; 
			currentNode.settled = true;
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
    	if (row < 0 || row > maxRow || col < 0 || col > maxCol) returnValidation = false;
    	return returnValidation;
    }
}

