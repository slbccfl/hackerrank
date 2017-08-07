import junit.framework.TestCase;

public class TestSolution extends TestCase {
	public void testEvaluateGrid() {
		int grid[][] = {{1,1,0,0},{0,1,1,0},{0,0,1,0},{1,0,0,0}};
		int returnInt = Solution.evaluateGrid(grid);
		assertEquals(5, returnInt);
	}

	public void testEvaluateNode() {
		int grid[][] = {{1,1,0,0},{0,1,1,0},{0,0,1,0},{1,0,0,0}};
		int rowIndex = 0;
		int colIndex = 0;
		Node node = new Node(rowIndex, colIndex);
		int returnInt = Solution.evaluateNode(rowIndex, colIndex, node);
		assertEquals(5, returnInt);
		
	}
	
}
