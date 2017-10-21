package how_many_substrings;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestSolution {

	@Test
	public void testAddNewCompactNode() {
		Solution.s = "abc";
		TrieNode newNode = Solution.addNewCompactNode(null, 0, 3);
		assertEquals(newNode, Solution.trieTreeRoot);
		assertEquals(null, newNode.parentNode);
		assertEquals(Solution.s, newNode.string());
		boolean letterArrayIsEmpty = true;
		for (int i = 0; i < newNode.letterArray.length; i++) {
			if (newNode.letterArray[i] != null) {
				letterArrayIsEmpty = false;
				break;
			}
		}
		assertEquals(true, letterArrayIsEmpty);

		Solution.s = "abcdabcx";
		TrieNode nodeD = Solution.addNewCompactNode(newNode, 3, 5);
		TrieNode nodeX = Solution.addNewCompactNode(newNode, 7, 1);
		assertEquals(newNode, nodeD.parentNode);
		assertEquals(newNode, nodeX.parentNode);
		assertEquals(nodeD, newNode.letterArray[3]);
		assertEquals(nodeX, newNode.letterArray[23]);
		assertEquals("dabcx", Solution.s.substring(nodeD.stringStart, nodeD.stringEnd));
		assertEquals("x", Solution.s.substring(nodeX.stringStart, nodeX.stringEnd));
	}

	@Test
	public void testCreateBranch() {
		Solution.s = "abcdabce";
		TrieNode startingNode = Solution.addNewCompactNode(null, 0, 8);
//		String subS = "abce";
		int subSStart = 4;
		int subSLength = 4;
		int lcpLength  = 3;
		Solution.createBranch(subSStart, subSLength, startingNode, lcpLength);

		TrieNode replacementNode = Solution.trieTreeRoot;
		assertEquals("abc", replacementNode.string());

		TrieNode oldNode = replacementNode.letterArray['d' - 'a'];
		assertEquals("dabce", oldNode.string());
		assertEquals(startingNode, oldNode);
		assertEquals(oldNode.parentNode, replacementNode);

		TrieNode newNode = replacementNode.letterArray['e' - 'a'];
		assertEquals("e", newNode.string());
		assertEquals(newNode.parentNode, replacementNode);

		startingNode = Solution.trieTreeRoot;
		Solution.s += "abx";
		subSStart = 8;
		subSLength = 3;
		lcpLength = 2;
		Solution.createBranch(subSStart, subSLength, startingNode, lcpLength);
//		Solution.createBranch("abx", startingNode, 2);

		replacementNode = Solution.trieTreeRoot;
		assertEquals("ab", replacementNode.string());

		oldNode = replacementNode.letterArray['c' - 'a'];
		assertEquals(startingNode, oldNode);
		assertEquals("c", oldNode.string());
		assertEquals(oldNode.parentNode, replacementNode);

		newNode = replacementNode.letterArray['x' - 'a'];
		assertEquals("x", newNode.string());
		assertEquals(newNode.parentNode, replacementNode);
	}

	@Test
	public void testInsertPrefix() {
		Solution.s = "abcd";
		Solution.trieTreeRoot = Solution.addNewCompactNode(null, 0, 4);
//		Solution.trieTreeRoot = new TrieNode();
//		String subS = "abcd";
		int subSStart = 0;
		int subSLength = 4;
//		Solution.trieTreeRoot = new TrieNode(subSStart, subSLength);
		for (int i = 0; i < subSLength; i++) {
			Solution.insertPrefix(Solution.trieTreeRoot, subSStart + i, subSLength - i);
		}

		assertEquals("", Solution.trieTreeRoot.string());
		TrieNode[] childNodes = new TrieNode[subSLength];
		for (int i = 0; i < subSLength; i++) {
			childNodes[i] = Solution.trieTreeRoot.letterArray[i];
			assertEquals(Solution.s.substring(i, subSLength), childNodes[i].string());
			assertEquals(Solution.trieTreeRoot, childNodes[i].parentNode);
		}

		Solution.s = "abcabc";
		Solution.trieTreeRoot = Solution.addNewCompactNode(null, 0, 6);
//		Solution.trieTreeRoot = new TrieNode();
//		subS = "abcabc";
		subSStart = 0;
		subSLength = 6;

		for (int i = 0; i < subSLength; i++) {
			Solution.insertPrefix(Solution.trieTreeRoot, subSStart + i, subSLength - i);
		}

		assertEquals("", Solution.trieTreeRoot.string());
		childNodes = new TrieNode[subSLength];
		for (int i = 0; i < subSLength; i++) {
			if (i > 2) {
				assertEquals(null,Solution.trieTreeRoot.letterArray[i]);
			} else {
				childNodes[i] = Solution.trieTreeRoot.letterArray[i];
				assertEquals(Solution.s.substring(i, Solution.s.length()), childNodes[i].string());
				assertEquals(Solution.trieTreeRoot, childNodes[i].parentNode);
			}
		}


	}

	@Test
	public void testBuildTree00() {
		// the following is the same five queries that are in test case #0

		Solution.s = "aabaa";
		int sLength = Solution.s.length();
		Solution.trieTreeRoot = Solution.addNewCompactNode(null, 0, sLength);
    	for (int ssLength = sLength - 1; ssLength >= 0; ssLength--) {
    		int ssStart = sLength - ssLength;
    		Solution.insertPrefix(Solution.trieTreeRoot, ssStart, ssLength);
        }
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
    	}
	}


	@Test
	public void testBuildTree01() {
		// the following is the same as query #'s 97, 94 and 87 from test case #01

		Solution.s = "qqqqqqqqqqzrzrrzrzrrzrrzrzrrzrzrrzttttttttttttttttttttttttttttttttttttttttttttttttttttttqncpqzcxpbwa";
		int sLength = Solution.s.length();
		Solution.trieTreeRoot = Solution.addNewCompactNode(null, 0, sLength);
    	for (int ssLength = sLength - 1; ssLength >= 0; ssLength--) {
    		int ssStart = sLength - ssLength;
    		Solution.insertPrefix(Solution.trieTreeRoot, ssStart, ssLength);
        }
		int subSStart[] = {0, 0, 2};
		int subSEnd[] = {17, 22, 32};
    	int subStringCounts[] = {114, 193, 347};
    	for (int i = 0; i < subStringCounts.length; i++) {
        	Solution.vTreeRoot = new VisitedNode();
    		int prefixesCount = 0;
        	int subSLength = subSEnd[i] - subSStart[i] + 1;
        	for (int ssLength = subSLength; ssLength > 0; ssLength--) {
        		int ssStart = subSStart[i] + subSLength - ssLength;
        		prefixesCount += Solution.countPrefixes(Solution.trieTreeRoot, ssStart, ssLength, Solution.vTreeRoot);

            }
	    	assertEquals(subStringCounts[i], prefixesCount);
    	}
	}
}
