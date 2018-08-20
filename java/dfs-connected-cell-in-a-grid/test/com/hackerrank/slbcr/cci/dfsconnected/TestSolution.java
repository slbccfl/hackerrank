package com.hackerrank.slbcr.cci.dfsconnected;
import junit.framework.*;


public class TestSolution extends TestCase {
	
	public void testEvaluateGrid() {
		int[][] grid = {{1,1,0,0},{0,1,1,0},{0,0,1,0},{1,0,0,0}};
		int returnInt = Solution.evaluateGrid(grid);
		assertEquals(5, returnInt);
		return;
	}

	public void testEvaluateNode() {
		int grid[][] = {{1,1,0,0},{0,1,1,0},{0,0,1,0},{1,0,0,0}};
		int rowIndex = 0;
		int colIndex = 0;
		Node node = new Node(rowIndex, colIndex);
		Solution.evaluateNode(node);
		assertEquals(grid[rowIndex][colIndex], "X");
		assertEquals(node.row, rowIndex);
		assertEquals(node.col, colIndex);
		assertEquals(grid[node.row][node.col], -1);
		System.out.println(node.adjacencyList);
		return;
		
	}

	public void testValidCell() {
        Solution.maxRow = 4;
        Solution.maxCol = 4;
		assertTrue(Solution.validCell(0,0));
		assertTrue(Solution.validCell(2,2));
		assertTrue(Solution.validCell(4,4));
		assertFalse(Solution.validCell(-1,0));
		assertFalse(Solution.validCell(5,0));
		assertFalse(Solution.validCell(0,-1));
		assertFalse(Solution.validCell(0,5));
		return;
	}

	public void testFindRegion() {
		int grid[][] = {{1,1,0,0},{0,1,1,0},{0,0,1,0},{1,0,0,0}};
		Node node = Solution.findRegion(grid);
		assertTrue(node.row == 0);
		assertTrue(node.col == 0);
		return;
	}
		
	
}
