package permuting_two_arrays;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

public class TestSolution {

	@Test
	public void testLcs() {
		Solution.arrayA = new LinkedList<Integer>(Arrays.asList(2, 1, 3));
		Solution.arrayB = new LinkedList<Integer>(Arrays.asList(7, 8, 9));
        Collections.sort(Solution.arrayA);
        Collections.sort(Solution.arrayB, Collections.reverseOrder());
		assertEquals("YES", Solution.validatePermutations(3, 10));

		Solution.arrayA = new LinkedList<Integer>(Arrays.asList(1, 2, 2, 1));
		Solution.arrayB = new LinkedList<Integer>(Arrays.asList(3, 3, 3, 4));
        Collections.sort(Solution.arrayA);
        Collections.sort(Solution.arrayB, Collections.reverseOrder());
		assertEquals("NO", Solution.validatePermutations(4, 5));
	}

}