package matrix_layer_rotation;

import java.io.*;
import java.util.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class TestSolution {

	@Test
	public void testStripLayer() {
		int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
		Solution.rows = 4;
		Solution.columns = 4;
		int l = 0;
		int layerLength = ((Solution.rows - 1) * 2) + ((Solution.columns -1) * 2) - (l * 8);
		Solution.layersMatrix = new ArrayList<ArrayList<Integer>>();
//    	Solution.layersMatrix.add(l, new int[layerLength]);
		Solution.stripLayer(l, layerLength, matrix);
		ArrayList<Integer> resultArray = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 8, 12, 16, 15, 14, 13, 9, 5));
		assertEquals(resultArray, Solution.layersMatrix.get(0));
	}

}
