package alexvsfedor;
import static org.junit.Assert.*;

import org.junit.Test;

public class TestSolution {

	@Test
	public void testMinorDeterminant() {
		int[][] laplacianMatrix;
		int determinant;
		laplacianMatrix = new int[][] {
			{2, -1, 0},
			{-1, 3, -1},
			{0, -1, 2}
		};
		determinant = Solution.minorDeterminant(laplacianMatrix);
		assertEquals(8, determinant);
		laplacianMatrix = new int[][] {
			{2, -1, -1, 0},
			{-1, 3, -1, -1},
			{-1, -1, 3, -1},
			{0, -1, -1, 2}
		};
		determinant = Solution.minorDeterminant(laplacianMatrix);
		assertEquals(8, determinant);
		laplacianMatrix = new int[][] {
			{1, 2, 2, 1},
			{1, 2, 4, 2},
			{2, 7, 5, 2},
			{-1, 4, -6, 3}
		};
		determinant = Solution.minorDeterminant(laplacianMatrix);
		assertEquals(-42, determinant);
	}
	
	@Test
	public void testQuadDeterminant() {
		int a = 3;
		int b = -1;
		int c = -1;
		int d = 2;
		int determinant = Solution.quadDeterminant(a, b, c, d);
		assertEquals(5, determinant);
	}

}
