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
			Solution.createBranch(
					Solution.aP.node.letterArray[Solution.aP.edgeIndex].stringStart, 
					Solution.aP.currentPosition, 
					Solution.aP.node.letterArray[Solution.aP.edgeIndex], 
					Solution.aP.length);
		}

////		TrieNode replacementNode = Solution.trieTreeRoot;
//		Solution.aP.node = Solution.trieTreeRoot.letterArray[0];
//		Solution.aP.edgeIndex = 2;
//		Solution.aP.length = 1;
//		Solution.aP.remainder = 3;
//		Solution.aP.currentPosition = 10;
//		while (Solution.aP.remainder > 0) {
//			Solution.createBranch(
//					Solution.aP.node.letterArray[Solution.aP.edgeIndex].stringStart, 
//					Solution.aP.currentPosition, 
//					Solution.aP.node.letterArray[Solution.aP.edgeIndex], 
//					Solution.aP.length);
//		}
//		
//		Solution.outputTree(Solution.trieTreeRoot, 0, Solution.vTreeRoot);
//		assertEquals("abc", replacementNode.string());
//
//		TrieNode oldNode = replacementNode.letterArray['d' - 'a'];
//		assertEquals("dabce", oldNode.string());
//		assertEquals(startingNode, oldNode);
//		assertEquals(oldNode.parentNode, replacementNode);
//
//		TrieNode newNode = replacementNode.letterArray['e' - 'a'];
//		assertEquals("e", newNode.string());
//		assertEquals(newNode.parentNode, replacementNode);
//
//		startingNode = Solution.trieTreeRoot;
//		Solution.s += "abx";
//		subSStart = 8;
//		subSLength = 3;
//		lcpLength = 2;
//		Solution.createBranch(subSStart, subSLength, startingNode, lcpLength);
////		Solution.createBranch("abx", startingNode, 2);
//
//		replacementNode = Solution.trieTreeRoot;
//		assertEquals("ab", replacementNode.string());
//
//		oldNode = replacementNode.letterArray['c' - 'a'];
//		assertEquals(startingNode, oldNode);
//		assertEquals("c", oldNode.string());
//		assertEquals(oldNode.parentNode, replacementNode);
//
//		newNode = replacementNode.letterArray['x' - 'a'];
//		assertEquals("x", newNode.string());
//		assertEquals(newNode.parentNode, replacementNode);
		
		
	}

//	@Test
//	public void testInsertPrefix() {
//		Solution.s = "abcd";
//		Solution.trieTreeRoot = Solution.addNewCompactNode(null, 0, 4);
////		Solution.trieTreeRoot = new TrieNode();
////		String subS = "abcd";
//		int subSStart = 0;
//		int subSLength = 4;
////		Solution.trieTreeRoot = new TrieNode(subSStart, subSLength);
//		for (int i = 0; i < subSLength; i++) {
//			Solution.insertPrefix(Solution.trieTreeRoot, subSStart + i, subSLength - i);
//		}
//
//		assertEquals("", Solution.trieTreeRoot.string());
//		TrieNode[] childNodes = new TrieNode[subSLength];
//		for (int i = 0; i < subSLength; i++) {
//			childNodes[i] = Solution.trieTreeRoot.letterArray[i];
//			assertEquals(Solution.s.substring(i, subSLength), childNodes[i].string());
//			assertEquals(Solution.trieTreeRoot, childNodes[i].parentNode);
//		}
//
//		Solution.s = "abcabc";
//		Solution.trieTreeRoot = Solution.addNewCompactNode(null, 0, 6);
////		Solution.trieTreeRoot = new TrieNode();
////		subS = "abcabc";
//		subSStart = 0;
//		subSLength = 6;
//
//		for (int i = 0; i < subSLength; i++) {
//			Solution.insertPrefix(Solution.trieTreeRoot, subSStart + i, subSLength - i);
//		}
//
//		assertEquals("", Solution.trieTreeRoot.string());
//		childNodes = new TrieNode[subSLength];
//		for (int i = 0; i < subSLength; i++) {
//			if (i > 2) {
//				assertEquals(null,Solution.trieTreeRoot.letterArray[i]);
//			} else {
//				childNodes[i] = Solution.trieTreeRoot.letterArray[i];
//				assertEquals(Solution.s.substring(i, Solution.s.length()), childNodes[i].string());
//				assertEquals(Solution.trieTreeRoot, childNodes[i].parentNode);
//			}
//		}
//
//
//	}
//
//	@Test
//	public void testBuildTree00() {
//		// the following is the same five queries that are in test case #0
//
//		Solution.s = "aabaa";
//		int sLength = Solution.s.length();
//		Solution.trieTreeRoot = Solution.addNewCompactNode(null, 0, sLength);
//    	for (int ssLength = sLength - 1; ssLength >= 0; ssLength--) {
//    		int ssStart = sLength - ssLength;
//    		Solution.insertPrefix(Solution.trieTreeRoot, ssStart, ssLength);
//        }
//		int subSStart[] = {1, 1, 1, 1, 0};
//		int subSEnd[] = {1, 4, 1, 4, 2};
//    	int subStringCounts[] = {1, 8, 1, 8, 5};
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
////	    	Solution.outputTree(Solution.trieTreeRoot, 0, Solution.vTreeRoot);
//    	}
////    	Solution.outputTree(Solution.trieTreeRoot, 0, Solution.vTreeRoot);
//	}


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
