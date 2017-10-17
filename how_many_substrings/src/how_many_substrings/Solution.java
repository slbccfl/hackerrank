package how_many_substrings;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


class TrieNode {
	boolean isEnd;
	TrieNode parentNode;
//	String string;
	int stringStart;
	int stringEnd;
	TrieNode[] letterArray;
	
	public TrieNode(int start, int length) {
		this.stringStart = start;
		this.stringEnd = start + length;
//		this.string = "";
		this.letterArray = new TrieNode[26];
	}
	
	public String string() {
		return Solution.s.substring(this.stringStart, this.stringEnd);
	}
}

public class Solution {

	static TrieNode trieTreeRoot;
	static long startTime = System.currentTimeMillis();
	static String s = null;

    public static void main(String[] args) {
    	
//    	timeStamp("Constuct a Compact Prefix Tree \"impolitely\"");
    	
    	BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
    	String line = null;
    	try {
			line = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	int n = Integer.parseInt(line.split("\\s")[0]);
    	int q = Integer.parseInt(line.split("\\s")[1]);
//    	String s = null;
		try {
			s = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
        for(int a0 = 0; a0 < q; a0++){ 
        	try {
				line = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	int subSStart = Integer.parseInt(line.split("\\s")[0]);
	    	int subSEnd = Integer.parseInt(line.split("\\s")[1]);
	    	int subSLength = subSStart - subSEnd + 1;
            
            trieTreeRoot = new TrieNode(subSStart,subSEnd);
//            String subS = s.substring(left,right+1);
            
//        	long queryStartTime = System.currentTimeMillis();
//	    	timeStamp("Test case " + a0 + " -- Left:  " + left + " Right: " + right + "   subS.length(): " + subS.length());
//	    	System.out.println(subS);

//	    	for (int ssLength = subS.length();ssLength >= 0; ssLength--) {
//	    		int beginIndex = subS.length() - ssLength;
//	    		insertPrefix(trieTreeRoot, subS.substring(beginIndex));
//            }
	        
//			long endTime = System.currentTimeMillis( );
//			float queryElapsedTime = endTime - queryStartTime;
//			System.out.println("Query Elapsed Milliseconds: " + queryElapsedTime + " -- Characters/millisecond: " + (subS.length() / queryElapsedTime));
//        	long ouputStartTime = System.currentTimeMillis();
        	
//          System.out.println(countNodesInTrie(trieTreeRoot));	        
          
//			endTime = System.currentTimeMillis( );
//			System.out.println("Output Elapsed Milliseconds: " + (endTime - ouputStartTime));
          
        }
    }

//	private static void timeStamp(String title) {
//		long endTime = System.currentTimeMillis( );
//		System.out.println(new Date());
//		System.out.println("Cumulative Elapsed Milliseconds: " + (endTime - startTime));
//		System.out.println(title);
//	}
    
//    static void insertPrefix(TrieNode currentNode, String subS) {
//    	// Check of subS is a prefix of the current node's prefix, is so no action needed, return
//    	if (subS.length() < currentNode.string.length() && subS.equals(currentNode.string.substring(0, subS.length()))) return;
//    	// Check of current node's prefix is a prefix of subS, 
//    	if (subS.length() > currentNode.string.length() && currentNode.string.equals(subS.substring(0, currentNode.string.length()))) {
//    		char nextChar = subS.charAt(currentNode.string.length());
//    		int index = nextChar - 'a';
//    		String subSSuffix = subS.substring(currentNode.string.length(), subS.length());
//    		if (currentNode.letterArray[index] != null) {
//    			insertPrefix(currentNode.letterArray[index], subSSuffix);
//    		} else {
//    			boolean letterArrayIsEmpty = true;
//    			for (int i = 0; i < currentNode.letterArray.length; i++) {
//    				if (currentNode.letterArray[i] != null) {
//    					letterArrayIsEmpty = false;
//    					break;
//    				}
//    			}
//    			if (letterArrayIsEmpty) {
//    				currentNode.string = subS;
//    			} else {
//    				addNewCompactNode(currentNode, subSSuffix);
//    			}
//    		}
//    		return;
//    	}
//    	int maxLCP = Math.min(subS.length(), currentNode.string.length());
//    	for (int i = 0; i < maxLCP; i++) {
//    		if (!subS.substring(i, i + 1).equals(currentNode.string.substring(i, i + 1))) {
//    			createBranch(subS, currentNode, i);
//    			return;
//    		}
//    	}
//    }

	static void createBranch(int start, int length, TrieNode oldNode, int lcpLength) {
		TrieNode replacementNode = addNewCompactNode(oldNode.parentNode, oldNode.stringStart, lcpLength);
		addNewCompactNode(replacementNode, start + lcpLength, length - lcpLength);
//		oldNode.string = oldNode.string.substring(lcpLength, oldNode.string.length());
		oldNode.stringStart = oldNode.stringStart + lcpLength;
		char c = s.charAt(oldNode.stringStart);
		int oNodeIndex = c - 'a';
		replacementNode.letterArray[oNodeIndex] = oldNode;
		oldNode.parentNode = replacementNode;
	}
	
	static TrieNode addNewCompactNode(TrieNode parentNode, int start, int length) {
		TrieNode newNode = new TrieNode(start, length);
		if (parentNode == null) {
			trieTreeRoot = newNode;
		} else {
			newNode.parentNode = parentNode;
			char c = s.charAt(start);
			int index = c - 'a';
			parentNode.letterArray[index] = newNode;
		}
//		newNode.string = string;
		return newNode;
		
	}
    
//    static int countNodesInTrie(TrieNode node) {
//    	int stringCount = node.string.length();
//
//    	for (int i = 0; i < 26; i++) {
//    		if (node.letterArray[i] != null) {
//    			stringCount += countNodesInTrie(node.letterArray[i]);
//    		}
//    	}
//    	return stringCount;
//    }
    
}

