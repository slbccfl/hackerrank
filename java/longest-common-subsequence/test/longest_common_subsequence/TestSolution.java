package longest_common_subsequence;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import org.junit.Test;

import longest_common_subsequence.Solution;

public class TestSolution {

	@Test
	public void testLcs() {
		Solution.arrayA = Arrays.asList(1, 2, 3, 4, 1);
		Solution.arrayB = Arrays.asList(3, 4, 1, 2, 1, 3);     
		Solution.lcsMemArray = new String[Solution.arrayA.size()][Solution.arrayB.size()];
		assertEquals("3 4 1", Solution.lcs(Solution.arrayA.size() - 1, Solution.arrayB.size() - 1));
	}
	
	@Test
	public void nullLcs() {
		Solution.arrayA = Arrays.asList(1, 2, 3, 4, 5);
		Solution.arrayB = Arrays.asList(6, 7, 8, 9, 10);
		Solution.lcsMemArray = new String[Solution.arrayA.size()][Solution.arrayB.size()];
		assertEquals("", Solution.lcs(Solution.arrayA.size() - 1, Solution.arrayB.size() - 1));
	}
}