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
		
//		index = 'c' - 'a';
//		oldNode = replacementNode.letterArray[index];
//		assertEquals(startingNode, oldNode);
//		assertEquals("c", oldNode.string);
//		assertEquals(oldNode.parentNode, replacementNode);
//		
//		index = 'x' - 'a';
//		newNode = replacementNode.letterArray[index];
//		assertEquals("x", newNode.string);
//		assertEquals(newNode.parentNode, replacementNode);
	}
//	
//	@Test
//	public void testInsertCompactNode() {
//		Solution.trieTreeRoot = new TrieNode();
//		String subS = "abcd";
//		for (int i = 0; i < 4; i++) {
//			Solution.insertPrefix(Solution.trieTreeRoot, subS.substring(i,subS.length()));
//		}
//		
//		assertEquals("", Solution.trieTreeRoot.string);
//		TrieNode[] childNodes = new TrieNode[4];
//		for (int i = 0; i < 4; i++) {
//			childNodes[i] = Solution.trieTreeRoot.letterArray[i];
//			assertEquals(subS.subSequence(i, subS.length()), childNodes[i].string);
//			assertEquals(Solution.trieTreeRoot, childNodes[i].parentNode);
//		}
//		
//		Solution.trieTreeRoot = new TrieNode();
//		subS = "abcabc";
//		for (int i = 0; i < 6; i++) {
//			Solution.insertPrefix(Solution.trieTreeRoot, subS.substring(i,subS.length()));
//		}
//		
//		assertEquals("", Solution.trieTreeRoot.string);
//		childNodes = new TrieNode[6];
//		for (int i = 0; i < 6; i++) {
//			if (i > 2) {
//				assertEquals(null,Solution.trieTreeRoot.letterArray[i]);
//			} else {
//				childNodes[i] = Solution.trieTreeRoot.letterArray[i];
//				assertEquals(subS.subSequence(i, subS.length()), childNodes[i].string);
//				assertEquals(Solution.trieTreeRoot, childNodes[i].parentNode);
//			}
//		}
//		
//		
//	}

}
