package how_many_substrings;

import static org.junit.Assert.assertEquals;

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
class VisitedNode {
	int counted;
	VisitedNode[] letterArray;
	
	public VisitedNode() {
		counted = 0;
		this.letterArray = new VisitedNode[26];
	}
}

public class Solution {

	static TrieNode trieTreeRoot;
	static VisitedNode vTreeRoot;
	static long startTime = System.currentTimeMillis();
	static String s = null;

    public static void main(String[] args) {
    	
//    	timeStamp("Prefix Counts from a single Prefix Tree");
    	
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
		int sLength = s.length();
		trieTreeRoot = addNewCompactNode(null, 0, sLength);
    	for (int ssLength = sLength - 1; ssLength >= 0; ssLength--) {
    		int ssStart = sLength - ssLength;
    		insertPrefix(trieTreeRoot, ssStart, ssLength);
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
            
//	    	addNewCompactNode(null, subSStart, subSLength);
//            String subS = s.substring(left,right+1);
            
//        	long queryStartTime = System.currentTimeMillis();
//	    	timeStamp("Test case " + a0 + " -- Left:  " + subSStart + " Right: " + subSEnd + "   subSLength: " + subSLength);
//	    	System.out.println(s.substring(subSStart, subSEnd));

//	    	for (int ssLength = subSLength - 1; ssLength >= 0; ssLength--) {
//	    		int ssStart = subSStart + subSLength - ssLength;
//	    		insertPrefix(trieTreeRoot, ssStart, ssLength);
//            }
	        
//			long endTime = System.currentTimeMillis( );
//			float queryElapsedTime = endTime - queryStartTime;
//			System.out.println("Query Elapsed Milliseconds: " + queryElapsedTime + " -- Characters/millisecond: " + (subSLength / queryElapsedTime));
//        	long ouputStartTime = System.currentTimeMillis();
	    	
        	Solution.vTreeRoot = new VisitedNode();
    		int prefixesCount = 0;
        	for (int ssLength = subSLength; ssLength > 0; ssLength--) {
        		int ssStart = subSStart + subSLength - ssLength;
        		prefixesCount += Solution.countPrefixes(Solution.trieTreeRoot, ssStart, ssLength, Solution.vTreeRoot);
        		
            }
	    	System.out.println(prefixesCount);	        
          
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
    	if (currentNode.stringLength() == 0 || (prefixLength > currentNode.stringLength() && s.substring(currentNode.stringStart, currentNode.stringEnd).equals(s.substring(prefixStart, prefixStart + currentNode.stringLength())))) {
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
    	for (int i = 0; i <= maxLCP; i++) {
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
    
    static int countPrefixes(TrieNode currentNode, int prefixStart, int prefixLength, VisitedNode currentVNode) {
    	
    	// Check of subS is a prefix of the current node's prefix, is so no action needed, return
    	int returnValue = 0;
    	if (prefixLength <= currentNode.stringLength() && s.substring(prefixStart, prefixStart + prefixLength).equals(s.substring(currentNode.stringStart, currentNode.stringStart + prefixLength))) {
    		if (prefixLength > currentVNode.counted) {
	    		returnValue = prefixLength - currentVNode.counted;
	    		currentVNode.counted = returnValue;
    		}
    		
    	} else {
	    	// Check of current node's prefix is a prefix of subS, 
	    	if (currentNode.stringLength() ==0 || (prefixLength > currentNode.stringLength() && s.substring(currentNode.stringStart, currentNode.stringEnd).equals(s.substring(prefixStart, prefixStart + currentNode.stringLength())))) {
	    		char nextChar = s.substring(prefixStart, prefixStart + prefixLength).charAt(currentNode.stringLength());
	    		int index = nextChar - 'a';
	    		if (currentVNode.letterArray[index] == null) currentVNode.letterArray[index] = new VisitedNode();
	//    		String subSSuffix = s.substring(start, start + length).substring(currentNode.stringLength(), length);
	    		int subSSuffixStart = prefixStart + currentNode.stringLength();
	    		int subSSuffixLength = prefixLength - currentNode.stringLength();
	    		if (currentVNode.counted < currentNode.stringLength()) returnValue = currentNode.stringLength() - currentVNode.counted;
	    		returnValue += countPrefixes(currentNode.letterArray[index], subSSuffixStart, subSSuffixLength, currentVNode.letterArray[index]);
	    		currentVNode.counted = currentNode.stringLength();
	    	} else {
		    	int maxLCP = Math.min(prefixLength, currentNode.stringLength());
		    	for (int lcp = 0; lcp <= maxLCP; lcp++) {
		    		if (!s.substring(prefixStart + lcp, prefixStart + lcp + 1).equals(s.substring(currentNode.stringStart + lcp, currentNode.stringStart + lcp + 1))) {
		        		int subSSuffixStart = prefixStart + lcp;
		        		int subSSuffixLength = prefixLength - lcp;
		    			char c = s.charAt(subSSuffixStart);
		    			int index = c - 'a';
			    		if (currentVNode.letterArray[index] == null) currentVNode.letterArray[index] = new VisitedNode();
			    		if (lcp > currentVNode.counted) returnValue = lcp - currentVNode.counted;
		    			returnValue += countPrefixes(currentNode.letterArray[index], subSSuffixStart, subSSuffixLength, currentVNode.letterArray[index]);
		    			break;
		    		}
		    	}
	    	}
    	}
    	return returnValue;
    }
    
}

