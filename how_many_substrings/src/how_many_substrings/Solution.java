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
	
	public int stringLength() {
		return this.stringEnd - this.stringStart;
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
	    	int subSLength = subSEnd - subSStart + 1;
            
	    	addNewCompactNode(null, subSStart, subSLength);
//            String subS = s.substring(left,right+1);
            
//        	long queryStartTime = System.currentTimeMillis();
//	    	timeStamp("Test case " + a0 + " -- Left:  " + left + " Right: " + right + "   subS.length(): " + subS.length());
//	    	System.out.println(subS);

	    	for (int ssLength = subSLength - 1; ssLength >= 0; ssLength--) {
	    		int ssStart = subSStart + subSLength - ssLength;
	    		insertPrefix(trieTreeRoot, ssStart, ssLength);
            }
	        
//			long endTime = System.currentTimeMillis( );
//			float queryElapsedTime = endTime - queryStartTime;
//			System.out.println("Query Elapsed Milliseconds: " + queryElapsedTime + " -- Characters/millisecond: " + (subS.length() / queryElapsedTime));
//        	long ouputStartTime = System.currentTimeMillis();
        	
	    	System.out.println(countNodesInTrie(trieTreeRoot));	        
          
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
    
    static void insertPrefix(TrieNode currentNode, int prefixStart, int prefixLength) {
    	// Check of subS is a prefix of the current node's prefix, is so no action needed, return
    	if (prefixLength <= currentNode.stringLength() && s.substring(prefixStart, prefixStart + prefixLength).equals(s.substring(currentNode.stringStart, currentNode.stringStart + prefixLength))) return;
    	// Check of current node's prefix is a prefix of subS, 
    	if (prefixLength > currentNode.stringLength() && s.substring(currentNode.stringStart, currentNode.stringEnd).equals(s.substring(prefixStart, prefixStart + currentNode.stringLength()))) {
    		char nextChar = s.substring(prefixStart, prefixStart + prefixLength).charAt(currentNode.stringLength());
    		int index = nextChar - 'a';
//    		String subSSuffix = s.substring(start, start + length).substring(currentNode.stringLength(), length);
    		int subSSuffixStart = prefixStart + currentNode.stringLength();
    		int subSSuffixLength = prefixLength - currentNode.stringLength();
    		if (currentNode.letterArray[index] != null) {
    			insertPrefix(currentNode.letterArray[index], subSSuffixStart, subSSuffixLength);
    		} else {
    			boolean letterArrayIsEmpty = true;
    			for (int i = 0; i < currentNode.letterArray.length; i++) {
    				if (currentNode.letterArray[i] != null) {
    					letterArrayIsEmpty = false;
    					break;
    				}
    			}
    			if (letterArrayIsEmpty) {
//    				currentNode.string = s.substring(start, start + length);
					currentNode.stringStart = prefixStart;
					currentNode.stringEnd = prefixStart + prefixLength;
    			} else {
    				addNewCompactNode(currentNode, subSSuffixStart, subSSuffixLength);
    			}
    		}
    		return;
    	}
    	int maxLCP = Math.min(prefixLength, currentNode.stringLength());
    	for (int i = 0; i < maxLCP; i++) {
    		if (!s.substring(prefixStart + i, prefixStart + i + 1).equals(s.substring(currentNode.stringStart + i, currentNode.stringStart + i + 1))) {
    			createBranch(prefixStart, prefixLength, currentNode, i);
    			return;
    		}
    	}
    }

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
    
    static int countNodesInTrie(TrieNode node) {
    	int stringCount = node.stringLength();

    	for (int i = 0; i < 26; i++) {
    		if (node.letterArray[i] != null) {
    			stringCount += countNodesInTrie(node.letterArray[i]);
    		}
    	}
    	return stringCount;
    }
    
}

