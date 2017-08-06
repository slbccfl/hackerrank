//import java.io.*;
import static org.junit.Assert.*;
//import org.junit.After;
//import org.junit.Before;

import org.junit.Test;

public class SolutionTest {
	
	@Test
	public void testFindLonelyInt() {
		int[] a = {1};
		int result = Solution.findLonelyInt(a);
		assertEquals(1,result) ;
		
		a = new int []{1,1,2};
		result = Solution.findLonelyInt(a);
		assertEquals(2,result) ;
		
		a = new int []{0,0,1,2,1};
		result = Solution.findLonelyInt(a);
		assertEquals(2,result) ;
		
	}
	
	
//	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
//
//	@Before
//	public void setUpStreams() {
//	    System.setOut(new PrintStream(outContent));
//	    System.setErr(new PrintStream(errContent));
//	}
//
//	@After
//	public void cleanUpStreams() {
//	    System.setOut(null);
//	    System.setErr(null);
//	}
//	
//	@Test
//	public void testMain() {
//		try {
//			System.setIn(new FileInputStream("1\n1\n"));
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		String[] args = new String[0];
//		Solution.main(args);
//	    assertEquals("1", outContent.toString());
//	}

}
