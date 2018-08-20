package missing_numbers;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

import flipping_bits.Solution;

public class TestSolution {

	@Test
	public void testLcs() {
		Solution.arrayA = new LinkedList<Integer>(Arrays.asList(203, 204, 205, 206, 207, 208, 203, 204, 206));
		Solution.arrayB = new LinkedList<Integer>(Arrays.asList(203, 204, 204, 205, 206, 207, 205, 208, 203, 206, 205, 206, 204));
		List<Integer> newArray = Solution.findMissingNumbers();
		assertEquals(Arrays.asList(205, 205, 206, 204), newArray);
		assertEquals("204 205 206", Solution.outputArray(newArray));
	}

}