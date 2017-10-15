package how_many_substrings;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestSolution {
	
	@Test
	public void testAddNewCompactNode() {
		String string = "abc";
		TrieNode newNode = Solution.addNewCompactNode(null, string);
		assertEquals(newNode, Solution.trieTreeRoot);
		assertEquals(null, newNode.parentNode);
		assertEquals(string, newNode.string);
		boolean letterArrayIsEmpty = true;
		for (int i = 0; i < newNode.letterArray.length; i++) {
			if (newNode.letterArray[i] != null) {
				letterArrayIsEmpty = false;
				break;
			}
		}
		assertEquals(true, letterArrayIsEmpty);

		TrieNode nodeD = Solution.addNewCompactNode(newNode, "d");
		TrieNode nodeX = Solution.addNewCompactNode(newNode, "x");
		assertEquals(newNode, nodeD.parentNode);
		assertEquals(newNode, nodeX.parentNode);
		assertEquals(nodeD, newNode.letterArray[3]);
		assertEquals(nodeX, newNode.letterArray[23]);
		assertEquals("d", nodeD.string);
		assertEquals("x", nodeX.string);
		
		
	}

	@Test
	public void testCreateBranch() {
		TrieNode startingNode = Solution.addNewCompactNode(null, "abcd");
		String subS = "abce";
		Solution.createBranch(subS, startingNode, 3);
		
		TrieNode replacementNode = Solution.trieTreeRoot;
		assertEquals("abc", replacementNode.string);
		
		int index = 'd' - 'a';
		TrieNode oldNode = replacementNode.letterArray[index];
		assertEquals(startingNode, oldNode);
		assertEquals("d", oldNode.string);
		assertEquals(oldNode.parentNode, replacementNode);
		
		index = 'e' - 'a';
		TrieNode newNode = replacementNode.letterArray[index];
		assertEquals("e", newNode.string);
		assertEquals(newNode.parentNode, replacementNode);
		
		startingNode = Solution.trieTreeRoot;
		Solution.createBranch("abx", startingNode, 2);
		
		replacementNode = Solution.trieTreeRoot;
		assertEquals("ab", replacementNode.string);
		
		index = 'c' - 'a';
		oldNode = replacementNode.letterArray[index];
		assertEquals(startingNode, oldNode);
		assertEquals("c", oldNode.string);
		assertEquals(oldNode.parentNode, replacementNode);
		
		index = 'x' - 'a';
		newNode = replacementNode.letterArray[index];
		assertEquals("x", newNode.string);
		assertEquals(newNode.parentNode, replacementNode);
	}
	
	@Test
	public void testInsertCompactNode() {
		Solution.trieTreeRoot = new TrieNode();
		String subS = "abcd";
		for (int i = 0; i < 4; i++) {
			Solution.insertPrefix(Solution.trieTreeRoot, subS.substring(i,subS.length()));
		}
		
		assertEquals("", Solution.trieTreeRoot.string);
		TrieNode[] childNodes = new TrieNode[4];
		for (int i = 0; i < 4; i++) {
			childNodes[i] = Solution.trieTreeRoot.letterArray[i];
			assertEquals(subS.subSequence(i, subS.length()), childNodes[i].string);
			assertEquals(Solution.trieTreeRoot, childNodes[i].parentNode);
		}
		
		Solution.trieTreeRoot = new TrieNode();
		subS = "abcabc";
		for (int i = 0; i < 6; i++) {
			Solution.insertPrefix(Solution.trieTreeRoot, subS.substring(i,subS.length()));
		}
		
		assertEquals("", Solution.trieTreeRoot.string);
		childNodes = new TrieNode[6];
		for (int i = 0; i < 6; i++) {
			if (i > 2) {
				assertEquals(null,Solution.trieTreeRoot.letterArray[i]);
			} else {
				childNodes[i] = Solution.trieTreeRoot.letterArray[i];
				assertEquals(subS.subSequence(i, subS.length()), childNodes[i].string);
				assertEquals(Solution.trieTreeRoot, childNodes[i].parentNode);
			}
		}
		
		
	}

}
