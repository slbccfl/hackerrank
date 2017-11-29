package how_many_substrings;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class TestSolution {

	@Test
	public void testAddNewCompactNode() {
		System.out.println("\n *** testAddNewCompactNode *** ");
		Solution.aP = new activePoint();
//		Solution.aP.currentPosition = 1;
		Solution.s = "abca";
		Solution.nodeCount = 0;
//		Solution.nodeTable = new ArrayList<TreeNode>();
		Solution.suffixTreeRoot = new TreeNode(0, 0);
//		Solution.nodeTable.add(Solution.suffixTreeRoot.nodeNumber, Solution.suffixTreeRoot);
		assertEquals(null, Solution.suffixTreeRoot.parentNode);
//		assertEquals("", Solution.nodeString(Solution.trieTreeRoot));
		boolean letterArrayIsEmpty = true;
		for (int i = 0; i < Solution.suffixTreeRoot.letterArray.length; i++) {
			if (Solution.suffixTreeRoot.letterArray[i] != null) {
				letterArrayIsEmpty = false;
				break;
			}
		}
		assertEquals(true, letterArrayIsEmpty);

		TreeNode nodeA = Solution.addNewCompactNode(Solution.suffixTreeRoot, 0, null);
		TreeNode nodeB = Solution.addNewCompactNode(Solution.suffixTreeRoot, 1, null);
		TreeNode nodeC = Solution.addNewCompactNode(Solution.suffixTreeRoot, 2, null);
		assertEquals(Solution.suffixTreeRoot, nodeA.parentNode);
		assertEquals(Solution.suffixTreeRoot, nodeB.parentNode);
		assertEquals(Solution.suffixTreeRoot, nodeC.parentNode);
		assertEquals(nodeA, Solution.suffixTreeRoot.letterArray[0]);
		assertEquals(nodeB, Solution.suffixTreeRoot.letterArray[1]);
		assertEquals(nodeC, Solution.suffixTreeRoot.letterArray[2]);
		
		Solution.aP.currentPosition = Solution.s.length();
		assertEquals("abca", Solution.nodeString(nodeA));
		assertEquals("bca", Solution.nodeString(nodeB));
		assertEquals("ca", Solution.nodeString(nodeC));
		
//		Solution.outputTree();
	}

	@Test
	public void testCreateBranch() {
		System.out.println("\n *** testCreateBranch *** ");
		Solution.s = "abcabxabcd";
//		Solution.s = "abcdabce";
		Solution.nodeCount = 0;
		Solution.suffixTreeRoot = new TreeNode(0, 0);
		Solution.aP = new activePoint();
		Solution.aP.node = Solution.suffixTreeRoot;
		Solution.aP.edgeIndex = 0;
		Solution.aP.length = 0;
		Solution.aP.remainder = 0;
		Solution.aP.currentPosition = 3;
		Solution.aP.firstNodeUpdated = true;
		Solution.aP.priorNodeUpdated = null;
		TreeNode nodeA = Solution.addNewCompactNode(Solution.suffixTreeRoot, 0, null);
		TreeNode nodeB = Solution.addNewCompactNode(Solution.suffixTreeRoot, 1, null);
		TreeNode nodeC = Solution.addNewCompactNode(Solution.suffixTreeRoot, 2, null);
		

		Solution.aP.node = Solution.suffixTreeRoot;
		Solution.aP.edgeIndex = 0;
		Solution.aP.length = 2;
		Solution.aP.remainder = 2;
		Solution.aP.currentPosition = 6;
		Solution.aP.firstNodeUpdated = true;
		Solution.aP.priorNodeUpdated = null;
		while (Solution.aP.remainder > 0) {
//			Solution.outputActivePoint("testCreateBranch first branches");
			Solution.createBranch();
		}
		char c = Solution.s.charAt(Solution.aP.currentPosition - 1);
		int currentPositionIndex = c - 'a';
		if (Solution.aP.node.letterArray[currentPositionIndex] == null) {
			Solution.addNewCompactNode(Solution.aP.node, Solution.aP.currentPosition  - 1, null);
//			Solution.outputTree();
		}
		if (Solution.aP.node == Solution.suffixTreeRoot) Solution.aP.edgeIndex = null;
		
//		Solution.aP.currentPosition++;
//		Solution.outputActivePoint("testCreateBranch before first asserts ");
//		Solution.outputTree();
		TreeNode node4 = Solution.suffixTreeRoot.letterArray['a' - 'a'];
		assertEquals("ab", Solution.nodeString(node4));
		assertEquals("cabx", Solution.nodeString(node4.letterArray['c' - 'a']));
		assertEquals("x", Solution.nodeString(node4.letterArray['x' - 'a']));
		TreeNode node6 = Solution.suffixTreeRoot.letterArray['b' - 'a'];
		assertEquals(node4.suffixLink, node6);
		assertEquals("b", Solution.nodeString(node6));
		assertEquals("cabx", Solution.nodeString(node6.letterArray['c' - 'a']));
		assertEquals("x", Solution.nodeString(node6.letterArray['x' - 'a']));
		TreeNode node3 = Solution.suffixTreeRoot.letterArray['c' - 'a'];
		assertEquals("cabx", Solution.nodeString(node3));
		TreeNode node8 = Solution.suffixTreeRoot.letterArray['x' - 'a'];
		assertEquals("x", Solution.nodeString(node8));

		Solution.aP.node = Solution.suffixTreeRoot.letterArray[0];
		Solution.aP.edgeIndex = 2;
		Solution.aP.length = 1;
		Solution.aP.remainder = 3;
		Solution.aP.currentPosition = 10;
		Solution.aP.firstNodeUpdated = true;
		Solution.aP.priorNodeUpdated = null;
		
		while (Solution.aP.remainder > 0) {
//			Solution.outputActivePoint("testCreateBranch second branches");
			Solution.createBranch();
		}
		c = Solution.s.charAt(Solution.aP.currentPosition - 1);
		currentPositionIndex = c - 'a';
		if (Solution.aP.node.letterArray[currentPositionIndex] == null) {
			Solution.addNewCompactNode(Solution.aP.node, Solution.aP.currentPosition  - 1, null);
//			Solution.outputTree();
		}
		if (Solution.aP.node == Solution.suffixTreeRoot) Solution.aP.edgeIndex = null;
		
//		Solution.aP.currentPosition++;
//		Solution.outputActivePoint("testCreateBranch before second asserts ");
//		Solution.outputTree();
		TreeNode node9 = node4.letterArray['c' - 'a'];
		assertEquals("abxabcd", Solution.nodeString(node9.letterArray['a' - 'a']));
		assertEquals("d", Solution.nodeString(node9.letterArray['d' - 'a']));
		TreeNode node11 = node6.letterArray['c' - 'a'];
		assertEquals(node9.suffixLink, node11);
		assertEquals("abxabcd", Solution.nodeString(node11.letterArray['a' - 'a']));
		assertEquals("d", Solution.nodeString(node11.letterArray['d' - 'a']));
		TreeNode node13 = Solution.suffixTreeRoot.letterArray['c' - 'a'];
		assertEquals(node11.suffixLink, node13);
		assertEquals("abxabcd", Solution.nodeString(node13.letterArray['a' - 'a']));
		assertEquals("d", Solution.nodeString(node13.letterArray['d' - 'a']));
		TreeNode node15 = Solution.suffixTreeRoot.letterArray['d' - 'a'];
		assertEquals("d", Solution.nodeString(node15));
		assertEquals("xabcd", Solution.nodeString(node8));
	}

	@Test
	public void testBuildTree() {
		Solution.prefixesCountString = new StringBuilder();
		System.out.println("\n *** testBuildTree *** ");
		int start;
		int end;	
		TreeNode[] childNodes;
		
		Solution.s = "abcd";
		start = 1;
		end = Solution.s.length();	
		
		Solution.buildTree(start, end);

		Solution.aP.currentPosition = Solution.s.length();
		assertEquals("", Solution.nodeString(Solution.suffixTreeRoot));
		TreeNode childNode;
		for (int i = 0; i < end; i++) {
			childNode = Solution.suffixTreeRoot.letterArray[i];
			assertEquals(Solution.s.substring(i, end),Solution.nodeString(childNode));
			assertEquals(Solution.suffixTreeRoot, childNode.parentNode);
		}

		Solution.s = "abcabc";
		start = 1;
		end = Solution.s.length();
		
		Solution.buildTree(start, end);

		Solution.aP.currentPosition = Solution.s.length();
		assertEquals("", Solution.nodeString(Solution.suffixTreeRoot));
		childNodes = new TreeNode[end + 1];
		for (int i = 0; i < 3; i++) {
			childNodes[i] = Solution.suffixTreeRoot.letterArray[i];
			assertEquals(Solution.s.substring(i, end),Solution.nodeString(childNodes[i]));
			assertEquals(Solution.suffixTreeRoot, childNodes[i].parentNode);
		}
		
		Solution.s = "abcabxabcd";
		start = 1;
		end = Solution.s.length();
		Solution.nodeCount = 0;
		
		Solution.buildTree(start, end);

		Solution.aP.currentPosition = end;
//		Solution.outputTree();
		assertEquals("", Solution.nodeString(Solution.suffixTreeRoot));
		TreeNode node4 = Solution.suffixTreeRoot.letterArray['a' - 'a'];
		assertEquals("ab", Solution.nodeString(node4));
		assertEquals("c", Solution.nodeString(node4.letterArray['c' - 'a']));
		assertEquals("xabcd", Solution.nodeString(node4.letterArray['x' - 'a']));
		TreeNode node6 = Solution.suffixTreeRoot.letterArray['b' - 'a'];
		assertEquals(node4.suffixLink, node6);
		assertEquals("b", Solution.nodeString(node6));
		assertEquals("c", Solution.nodeString(node6.letterArray['c' - 'a']));
		assertEquals("xabcd", Solution.nodeString(node6.letterArray['x' - 'a']));
		TreeNode node9 = node4.letterArray['c' - 'a'];
		assertEquals("c", Solution.nodeString(node9));
		assertEquals("abxabcd", Solution.nodeString(node9.letterArray['a' - 'a']));
		assertEquals("d", Solution.nodeString(node9.letterArray['d' - 'a']));
		TreeNode node11 = node6.letterArray['c' - 'a'];
		assertEquals("c", Solution.nodeString(node11));
		assertEquals(node9.suffixLink, node11);
		assertEquals("abxabcd", Solution.nodeString(node11.letterArray['a' - 'a']));
		assertEquals("d", Solution.nodeString(node11.letterArray['d' - 'a']));
		TreeNode node13 = Solution.suffixTreeRoot.letterArray['c' - 'a'];
		assertEquals("c", Solution.nodeString(node13));
		assertEquals(node11.suffixLink, node13);
		assertEquals("abxabcd", Solution.nodeString(node13.letterArray['a' - 'a']));
		assertEquals("d", Solution.nodeString(node13.letterArray['d' - 'a']));
		TreeNode node15 = Solution.suffixTreeRoot.letterArray['d' - 'a'];
		assertEquals("d", Solution.nodeString(node15));
		TreeNode node8 = Solution.suffixTreeRoot.letterArray['x' - 'a'];
		assertEquals("xabcd", Solution.nodeString(node8));

	}

	@Test
	public void testBuildTreeX() {
		System.out.println("\n *** testBuildTreeX *** ");
		Solution.s = "bnbnnbnnnz";
		int start = 1;
		int end = Solution.s.length();
		Solution.nodeCount = 0;
		Solution.prefixesCountString = new StringBuilder();
		
//		Solution.buildTree(start, end);

//		Solution.outputTree();
		int subSStart[] = {0};
		int subSEnd[] = {9};
    	long subStringCounts[] = {40};
    	for (int i = 0; i < subStringCounts.length; i++) {
        	int subSLength = subSEnd[i] - subSStart[i] + 1;
    		Solution.buildTree(subSStart[i] + 1, subSEnd[i] + 1);
    		Solution.aP.currentPosition = subSEnd[i] + 1;
//        	long prefixesCount = Solution.countTreePrefixes(subSStart[i], subSLength);
//        	long prefixesCount = Solution.countTreePrefixes();
//	    	assertEquals(subStringCounts[i], prefixesCount);
//	    	Solution.outputTree();
    	}
//		Solution.outputTree();
	}

	@Test
	public void testBuildTreeObs3() {
		System.out.println("\n *** testBuildTreeObs3 *** ");
		Solution.s = "cdddcdc";
		int start = 1;
		int end = Solution.s.length();
		Solution.nodeCount = 0;
		Solution.prefixesCountString = new StringBuilder();
		
//		Solution.buildTree(start, end);

//		Solution.outputTree();
		int subSStart[] = {0};
		int subSEnd[] = {6};
    	long subStringCounts[] = {20};
    	for (int i = 0; i < subStringCounts.length; i++) {
        	int subSLength = subSEnd[i] - subSStart[i] + 1;
    		Solution.buildTree(subSStart[i] + 1, subSEnd[i] + 1);
    		Solution.aP.currentPosition = subSEnd[i] + 1;
//        	long prefixesCount = Solution.countTreePrefixes(subSStart[i], subSLength);
//        	long prefixesCount = Solution.countTreePrefixes();
//	    	assertEquals(subStringCounts[i], prefixesCount);
//	    	Solution.outputTree();
    	}
//		Solution.outputTree();
	}
	

	@Test
	public void testBuildTree00() {
		System.out.println("\n *** testBuildTree00 *** ");
		// the following is the same five queries that are in test case #0
		int start;
		int end;	
//		TrieNode[] childNodes;

		Solution.s = "aabaa";
		start = 1;
		end = Solution.s.length();	
		Solution.nodeCount = 0;
		Solution.prefixesCountString = new StringBuilder();

		int subSStart[] = {1, 1, 1, 1, 0, 0};
		int subSEnd[] = {1, 4, 1, 4, 2, 4};
    	long subStringCounts[] = {1, 8, 1, 8, 5, 11};
    	for (int i = 0; i < subStringCounts.length; i++) {
        	int subSLength = subSEnd[i] - subSStart[i] + 1;
    		Solution.buildTree(subSStart[i] + 1, subSEnd[i] + 1);
    		Solution.aP.currentPosition = subSEnd[i] + 1;
//        	long prefixesCount = Solution.countTreePrefixes(subSStart[i], subSLength);
//        	long prefixesCount = Solution.countTreePrefixes();
//	    	assertEquals(subStringCounts[i], prefixesCount);
//	    	Solution.outputTree();
    	}
//    	Solution.outputTree();
	}

	@Test
	public void testBuildTree01D() {
		System.out.println("\n *** testBuildTree01D *** ");
		// the following is the same as query #0 from test case #01
		int start;
		int end;	
//		TrieNode[] childNodes;

		Solution.s = "qqqqqqqqqqzrzrrzrzrrzrrzrzrrzrzrrzttttttttttttttttttttttttttttttttttttttttttttttttttttttqncpqzcxpbwa";
		start = 1;
		end = Solution.s.length();	
		Solution.nodeCount = 0;
		
//		Solution.buildTree(start, end);

		int subSStart[] = {61};
		int subSEnd[] = {97};
		long subStringCounts[] = {349};
    	for (int i = 0; i < subStringCounts.length; i++) {
        	int subSLength = subSEnd[i] - subSStart[i] + 1;
    		Solution.buildTree(subSStart[i] + 1, subSEnd[i] + 1);
    		Solution.aP.currentPosition = subSEnd[i] + 1;
//        	long prefixesCount = Solution.countTreePrefixes(subSStart[i], subSLength);
//        	long prefixesCount = Solution.countTreePrefixes();
//	    	assertEquals(subStringCounts[i], prefixesCount);
    	}
//    	Solution.outputTree();
	}

	@Test
	public void testBuildTree01() {
		System.out.println("\n *** testBuildTree01 *** ");
		// the following is the same as query #'s 97, 94 87 and 0 from test case #01
		int start;
		int end;	
//		TrieNode[] childNodes;

		Solution.s = "qqqqqqqqqqzrzrrzrzrrzrrzrzrrzrzrrzttttttttttttttttttttttttttttttttttttttttttttttttttttttqncpqzcxpbwa";
		start = 1;
		end = Solution.s.length();	
		Solution.nodeCount = 0;
		
//		Solution.buildTree(start, end);
//		Solution.aP.currentPosition = end;
//		Solution.outputTree();

		int subSStart[] = {0, 0, 2, 61};
		int subSEnd[] = {17, 22, 32, 97};
		long subStringCounts[] = {114, 193, 347, 349};
    	for (int i = 0; i < subStringCounts.length; i++) {
        	int subSLength = subSEnd[i] - subSStart[i] + 1;
    		Solution.buildTree(subSStart[i] + 1, subSEnd[i] + 1);
    		Solution.aP.currentPosition = subSEnd[i] + 1;
//        	long prefixesCount = Solution.countTreePrefixes(subSStart[i], subSLength);
//        	long prefixesCount = Solution.countTreePrefixes();
//	    	assertEquals(subStringCounts[i], prefixesCount);
    	}
//    	Solution.outputTree();
	}
}
