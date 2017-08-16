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
			{3, -1, -1},
			{-1, 3, -1},
			{-1, -1, 2}
		};
		determinant = Solution.minorDeterminant(laplacianMatrix);
		assertEquals(8, determinant);
		laplacianMatrix = new int[][] {
			{3, 2, 0, 1},
			{4, 0, 1, 2},
			{3, 0, 2, 1},
			{9, 2, 3, 1}
		};
		determinant = Solution.minorDeterminant(laplacianMatrix);
		assertEquals(24, determinant);
		laplacianMatrix = new int[][] {
			{1, 2, 2, 1},
			{1, 2, 4, 2},
			{2, 7, 5, 2},
			{-1, 4, -6, 3}
		};
		determinant = Solution.minorDeterminant(laplacianMatrix);
		assertEquals(-42, determinant);
		laplacianMatrix = new int[][] {
			{1, 5, 4, 2},
			{-2, 3, 6, 4},
			{5, 1, 0, -1},
			{2, 3, -4, 0}
		};
		determinant = Solution.minorDeterminant(laplacianMatrix);
		assertEquals(242, determinant);
		laplacianMatrix = new int[][] {
			{1, 2, 3, 4},
			{5, 6, 7, 8},
			{2, 6, 4, 8},
			{3, 1, 1, 2}
		};
		determinant = Solution.minorDeterminant(laplacianMatrix);
		assertEquals(72, determinant);
		laplacianMatrix = new int[][] {
			{3, 2, 4, 1, 5},
			{-2, 0, 1, 3, 2},
			{1, 0, 0, 4, 0},
			{6, 0, 2, -1, 0},
			{3, 0, 5, 1, 0}
		};
		determinant = Solution.minorDeterminant(laplacianMatrix);
		assertEquals(412, determinant);
		laplacianMatrix = new int[][] {
			{5, -1, 0, 0, 0, -1, -1, 0, 0},
			{-1, 8, -1, -1, -1, 0, -1, -1, 0},
			{0, -1, 2, 0, 0, 0, 0, -1, 0},
			{0, -1, 0, 3, 0, -1, 0, 0, -1},
			{0, -1, 0, 0, 5, 0, 0, 0, 0},
			{-1, 0, 0, -1, 0, 3, 0, 0, 0},
			{-1, -1, 0, 0, 0, 0, 2, 0, 0},
			{0, -1, -1, 0, 0, 0, 0, 4, -1},
			{0, 0, 0, -1, 0, 0, 0, -1, 2}
		};
		determinant = Solution.minorDeterminant(laplacianMatrix);
		assertEquals(9247, determinant);
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
