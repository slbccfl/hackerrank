package how_many_substrings;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestSolution {

	@Test
	public void testAddNewCompactNode() {
		Solution.aP = new activePoint();
		Solution.aP.currentPosition = 1;
		Solution.s = "abca";
		TrieNode newNode = Solution.addNewCompactNode(null, 0, 0);
		assertEquals(newNode, Solution.trieTreeRoot);
		assertEquals(null, newNode.parentNode);
//		assertEquals("", Solution.nodeString(newNode));
		boolean letterArrayIsEmpty = true;
		for (int i = 0; i < newNode.letterArray.length; i++) {
			if (newNode.letterArray[i] != null) {
				letterArrayIsEmpty = false;
				break;
			}
		}
		assertEquals(true, letterArrayIsEmpty);

		TrieNode nodeA = Solution.addNewCompactNode(newNode, 0, null);
		TrieNode nodeB = Solution.addNewCompactNode(newNode, 1, null);
		TrieNode nodeC = Solution.addNewCompactNode(newNode, 2, null);
		assertEquals(newNode, nodeA.parentNode);
		assertEquals(newNode, nodeB.parentNode);
		assertEquals(newNode, nodeC.parentNode);
		assertEquals(nodeA, newNode.letterArray[0]);
		assertEquals(nodeB, newNode.letterArray[1]);
		assertEquals(nodeC, newNode.letterArray[2]);
		
		Solution.aP.currentPosition += 3;
		assertEquals("abca", Solution.nodeString(nodeA));
		assertEquals("bca", Solution.nodeString(nodeB));
		assertEquals("ca", Solution.nodeString(nodeC));
		
//		Solution.outputTree(Solution.trieTreeRoot, 0, Solution.vTreeRoot);
	}

	@Test
	public void testCreateBranch() {
		Solution.s = "abcabxabcd";
//		Solution.s = "abcdabce";
		Solution.nodeCount = 0;
		Solution.addNewCompactNode(null, 0, 0);
		TrieNode nodeA = Solution.addNewCompactNode(Solution.trieTreeRoot, 0, null);
		TrieNode nodeB = Solution.addNewCompactNode(Solution.trieTreeRoot, 1, null);
		TrieNode nodeC = Solution.addNewCompactNode(Solution.trieTreeRoot, 2, null);
		
		Solution.outputTree(Solution.trieTreeRoot, 0, Solution.vTreeRoot);
//		String subS = "abce";
//		int subSStart = 4;
//		int subSLength = 4;
//		int lcpLength  = 3;
		Solution.aP.node = Solution.trieTreeRoot;
		Solution.aP.edgeIndex = 0;
		Solution.aP.length = 2;
		Solution.aP.remainder = 2;
		Solution.aP.currentPosition = 6;
		Solution.aP.firstNodeCreated = true;
		Solution.aP.priorNodeCreated = null;
		while (Solution.aP.remainder > 0) {
			Solution.outputActivePoint();
			Solution.createBranch(
					Solution.aP.node.letterArray[Solution.aP.edgeIndex].stringStart, 
					Solution.aP.currentPosition, 
					Solution.aP.node.letterArray[Solution.aP.edgeIndex], 
					Solution.aP.length);
		}
		
		Solution.outputActivePoint();
		Solution.outputTree(Solution.trieTreeRoot, 0, Solution.vTreeRoot);
		TrieNode node4 = Solution.trieTreeRoot.letterArray['a' - 'a'];
		assertEquals("ab", Solution.nodeString(node4));
		assertEquals("cabx", Solution.nodeString(node4.letterArray['c' - 'a']));
		assertEquals("x", Solution.nodeString(node4.letterArray['x' - 'a']));
		TrieNode node6 = Solution.trieTreeRoot.letterArray['b' - 'a'];
		assertEquals(node4.suffixLink, node6);
		assertEquals("b", Solution.nodeString(node6));
		assertEquals("cabx", Solution.nodeString(node6.letterArray['c' - 'a']));
		assertEquals("x", Solution.nodeString(node6.letterArray['x' - 'a']));
		TrieNode node3 = Solution.trieTreeRoot.letterArray['c' - 'a'];
		assertEquals("cabx", Solution.nodeString(node3));
		TrieNode node8 = Solution.trieTreeRoot.letterArray['x' - 'a'];
		assertEquals("x", Solution.nodeString(node8));

		Solution.aP.node = Solution.trieTreeRoot.letterArray[0];
		Solution.aP.edgeIndex = 2;
		Solution.aP.length = 1;
		Solution.aP.remainder = 3;
		Solution.aP.currentPosition = 10;
		Solution.aP.firstNodeCreated = true;
		Solution.aP.priorNodeCreated = null;
		
		while (Solution.aP.remainder > 0) {
			Solution.outputActivePoint();
			Solution.createBranch(
					Solution.aP.node.letterArray[Solution.aP.edgeIndex].stringStart, 
					Solution.aP.currentPosition, 
					Solution.aP.node.letterArray[Solution.aP.edgeIndex], 
					Solution.aP.length);
		}
		
		Solution.outputActivePoint();
		Solution.outputTree(Solution.trieTreeRoot, 0, Solution.vTreeRoot);
		TrieNode node9 = node4.letterArray['c' - 'a'];
		assertEquals("abxabcd", Solution.nodeString(node9.letterArray['a' - 'a']));
		assertEquals("d", Solution.nodeString(node9.letterArray['d' - 'a']));
		TrieNode node11 = node6.letterArray['c' - 'a'];
		assertEquals(node9.suffixLink, node11);
		assertEquals("abxabcd", Solution.nodeString(node11.letterArray['a' - 'a']));
		assertEquals("d", Solution.nodeString(node11.letterArray['d' - 'a']));
		TrieNode node13 = Solution.trieTreeRoot.letterArray['c' - 'a'];
		assertEquals(node11.suffixLink, node13);
		assertEquals("abxabcd", Solution.nodeString(node13.letterArray['a' - 'a']));
		assertEquals("d", Solution.nodeString(node13.letterArray['d' - 'a']));
		TrieNode node15 = Solution.trieTreeRoot.letterArray['d' - 'a'];
		assertEquals("d", Solution.nodeString(node15));
		assertEquals("xabcd", Solution.nodeString(node8));
	}

	@Test
	public void testBuildTree() {
		int start;
		int end;	
		TrieNode[] childNodes;
		
		Solution.s = "abcd";
		start = 0;
		end = Solution.s.length() - 1;	
		
		Solution.buildTree(start, end);

		assertEquals("", Solution.nodeString(Solution.trieTreeRoot));
		childNodes = new TrieNode[end + 1];
		for (int i = 0; i <= end; i++) {
			childNodes[i] = Solution.trieTreeRoot.letterArray[i];
			assertEquals(Solution.s.substring(i, end + 1),Solution.nodeString(childNodes[i]));
			assertEquals(Solution.trieTreeRoot, childNodes[i].parentNode);
		}

		Solution.s = "abcabc";
		start = 0;
		end = Solution.s.length() - 1;
		
		Solution.buildTree(start, end);

		assertEquals("", Solution.nodeString(Solution.trieTreeRoot));
		childNodes = new TrieNode[end + 1];
		for (int i = 0; i < 3; i++) {
			childNodes[i] = Solution.trieTreeRoot.letterArray[i];
			assertEquals(Solution.s.substring(i, end + 1),Solution.nodeString(childNodes[i]));
			assertEquals(Solution.trieTreeRoot, childNodes[i].parentNode);
		}
		
		Solution.s = "abcabxabcd";
		start = 0;
		end = Solution.s.length();
		end = Solution.s.length() - 1;	
		Solution.nodeCount = 0;
		
		Solution.buildTree(start, end);

		assertEquals("", Solution.nodeString(Solution.trieTreeRoot));
		TrieNode node4 = Solution.trieTreeRoot.letterArray['a' - 'a'];
		assertEquals("ab", Solution.nodeString(node4));
		assertEquals("cabx", Solution.nodeString(node4.letterArray['c' - 'a']));
		assertEquals("x", Solution.nodeString(node4.letterArray['x' - 'a']));
		TrieNode node6 = Solution.trieTreeRoot.letterArray['b' - 'a'];
		assertEquals(node4.suffixLink, node6);
		assertEquals("b", Solution.nodeString(node6));
		assertEquals("cabx", Solution.nodeString(node6.letterArray['c' - 'a']));
		assertEquals("x", Solution.nodeString(node6.letterArray['x' - 'a']));
		TrieNode node3 = Solution.trieTreeRoot.letterArray['c' - 'a'];
		assertEquals("cabx", Solution.nodeString(node3));
		TrieNode node8 = Solution.trieTreeRoot.letterArray['x' - 'a'];
		assertEquals("x", Solution.nodeString(node8));
		TrieNode node9 = node4.letterArray['c' - 'a'];
		assertEquals("abxabcd", Solution.nodeString(node9.letterArray['a' - 'a']));
		assertEquals("d", Solution.nodeString(node9.letterArray['d' - 'a']));
		TrieNode node11 = node6.letterArray['c' - 'a'];
		assertEquals(node9.suffixLink, node11);
		assertEquals("abxabcd", Solution.nodeString(node11.letterArray['a' - 'a']));
		assertEquals("d", Solution.nodeString(node11.letterArray['d' - 'a']));
		TrieNode node13 = Solution.trieTreeRoot.letterArray['c' - 'a'];
		assertEquals(node11.suffixLink, node13);
		assertEquals("abxabcd", Solution.nodeString(node13.letterArray['a' - 'a']));
		assertEquals("d", Solution.nodeString(node13.letterArray['d' - 'a']));
		TrieNode node15 = Solution.trieTreeRoot.letterArray['d' - 'a'];
		assertEquals("d", Solution.nodeString(node15));
		assertEquals("xabcd", Solution.nodeString(node8));
	}

	@Test
	public void testBuildTree00() {
		// the following is the same five queries that are in test case #0
		int start;
		int end;	
		TrieNode[] childNodes;

		Solution.s = "aabaa";
		start = 0;
		end = Solution.s.length() - 1;	
		
		Solution.buildTree(start, end);

		int subSStart[] = {1, 1, 1, 1, 0};
		int subSEnd[] = {1, 4, 1, 4, 2};
    	int subStringCounts[] = {1, 8, 1, 8, 5};
    	for (int i = 0; i < subStringCounts.length; i++) {
        	Solution.vTreeRoot = new VisitedNode();
    		int prefixesCount = 0;
        	int subSLength = subSEnd[i] - subSStart[i] + 1;
        	for (int ssLength = subSLength; ssLength > 0; ssLength--) {
        		int ssStart = subSStart[i] + subSLength - ssLength;
        		prefixesCount += Solution.countPrefixes(Solution.trieTreeRoot, ssStart, ssLength, Solution.vTreeRoot);

            }
	    	assertEquals(subStringCounts[i], prefixesCount);
//	    	Solution.outputTree(Solution.trieTreeRoot, 0, Solution.vTreeRoot);
    	}
//    	Solution.outputTree(Solution.trieTreeRoot, 0, Solution.vTreeRoot);
	}


//	@Test
//	public void testBuildTree01() {
//		// the following is the same as query #'s 97, 94 and 87 from test case #01
//
//		Solution.s = "qqqqqqqqqqzrzrrzrzrrzrrzrzrrzrzrrzttttttttttttttttttttttttttttttttttttttttttttttttttttttqncpqzcxpbwa";
//		int sLength = Solution.s.length();
//		Solution.trieTreeRoot = Solution.addNewCompactNode(null, 0, sLength);
//    	for (int ssLength = sLength - 1; ssLength >= 0; ssLength--) {
//    		int ssStart = sLength - ssLength;
//    		Solution.insertPrefix(Solution.trieTreeRoot, ssStart, ssLength);
////        	Solution.outputTree(Solution.trieTreeRoot, 0, Solution.vTreeRoot);
//        }
//		int subSStart[] = {0, 0, 2};
//		int subSEnd[] = {17, 22, 32};
//    	int subStringCounts[] = {114, 193, 347};
//    	for (int i = 0; i < subStringCounts.length; i++) {
//        	Solution.vTreeRoot = new VisitedNode();
//    		int prefixesCount = 0;
//        	int subSLength = subSEnd[i] - subSStart[i] + 1;
//        	for (int ssLength = subSLength; ssLength > 0; ssLength--) {
//        		int ssStart = subSStart[i] + subSLength - ssLength;
//        		prefixesCount += Solution.countPrefixes(Solution.trieTreeRoot, ssStart, ssLength, Solution.vTreeRoot);
//
//            }
//	    	assertEquals(subStringCounts[i], prefixesCount);
//	    	Solution.outputTree(Solution.trieTreeRoot, 0, Solution.vTreeRoot);
//    	}
////    	Solution.outputTree(Solution.trieTreeRoot, 0, Solution.vTreeRoot);
//	}
//	
//	@Test
//	public void testBuildTreeXX() {
//
//		Solution.s = "aaaabcbcbcd";
//		int sLength = Solution.s.length();
//		Solution.trieTreeRoot = Solution.addNewCompactNode(null, 0, sLength);
////    	Solution.outputTree(Solution.trieTreeRoot, 0, Solution.vTreeRoot);
//    	for (int ssLength = sLength - 1; ssLength >= 0; ssLength--) {
//    		int ssStart = sLength - ssLength;
//    		Solution.insertPrefix(Solution.trieTreeRoot, ssStart, ssLength);
//        }
//		int subSStart[] = {0};
//		int subSEnd[] = {Solution.s.length() - 1};
//    	int subStringCounts[] = {54};
//    	for (int i = 0; i < subStringCounts.length; i++) {
//        	Solution.vTreeRoot = new VisitedNode();
//    		int prefixesCount = 0;
//        	int subSLength = subSEnd[i] - subSStart[i] + 1;
//        	for (int ssLength = subSLength; ssLength > 0; ssLength--) {
//        		int ssStart = subSStart[i] + subSLength - ssLength;
//        		prefixesCount += Solution.countPrefixes(Solution.trieTreeRoot, ssStart, ssLength, Solution.vTreeRoot);
//
//            }
////	    	assertEquals(subStringCounts[i], prefixesCount);
//    	}
////    	Solution.outputTree(Solution.trieTreeRoot, 0, Solution.vTreeRoot);
//	}
}
