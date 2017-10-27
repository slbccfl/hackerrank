package how_many_substrings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;


class TrieNode {
	boolean isEnd;
	TrieNode parentNode;
//	String string;
	int stringStart;
	Integer stringEnd;
	TrieNode[] letterArray;
	TrieNode suffixLink;
	
	public TrieNode(int start, Integer end) {
		this.stringStart = start;
//		this.stringEnd = start + length;
		this.stringEnd = end;
//		this.string = "";
		this.letterArray = new TrieNode[26];
	}
	
//	public String string() {
//		return Solution.s.substring(this.stringStart, this.stringEnd);
//	}
//	
//	public int stringLength() {
//		return this.stringEnd - this.stringStart;
//	}
}

class VisitedNode {
	int counted;
	VisitedNode[] letterArray;

	public VisitedNode() {
		counted = 0;
		this.letterArray = new VisitedNode[26];
	}
}	

class activePoint {
	TrieNode node;
	Integer edgeIndex;
	int length;
	int remainder;
	Integer currentPosition;
	boolean firstNodeCreated;
	TrieNode priorNodeCreated;
}

public class Solution {

	static TrieNode trieTreeRoot;
	static VisitedNode vTreeRoot;
	static long startTime = System.nanoTime();
	static String s = null;
	static activePoint aP;

    public static void main(String[] args) {

    	timeStamp("Prefix Counts from a single Prefix Tree");

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
//    		insertPrefix(trieTreeRoot, ssStart, ssLength);
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

        	long queryStartTime = System.nanoTime();
	    	timeStamp("Test case " + a0 + " -- Left:  " + subSStart + " Right: " + subSEnd + "   subSLength: " + subSLength);
	    	System.out.println(s.substring(subSStart, subSEnd));

//	    	for (int ssLength = subSLength - 1; ssLength >= 0; ssLength--) {
//	    		int ssStart = subSStart + subSLength - ssLength;
//	    		insertPrefix(trieTreeRoot, ssStart, ssLength);
//            }
//	    	System.out.println(prefixesCount);	        
//          
////			endTime = System.currentTimeMillis( );
////			System.out.println("Output Elapsed Milliseconds: " + (endTime - ouputStartTime));
//          
//        }
//    }

			long endTime = System.nanoTime( );
			float queryElapsedTime = endTime - queryStartTime;
			System.out.println("Query Elapsed Nanoseconds: " + queryElapsedTime + " -- nanoseconds/character: " + (queryElapsedTime / subSLength));
        	long outputStartTime = System.nanoTime();

        	Solution.vTreeRoot = new VisitedNode();
    		int prefixesCount = 0;
        	for (int ssLength = subSLength; ssLength > 0; ssLength--) {
        		int ssStart = subSStart + subSLength - ssLength;
//        		prefixesCount += Solution.countPrefixes(Solution.trieTreeRoot, ssStart, ssLength, Solution.vTreeRoot);

            }
	    	System.out.println(prefixesCount);

			endTime = System.nanoTime( );
			float outputElapsedTime = endTime - outputStartTime;
			System.out.println("Output Elapsed Nanoseconds: " + outputElapsedTime + " -- nanoseconds/character: " + (outputElapsedTime / subSLength));
//			outputTree(trieTreeRoot, 0);

        }
    }

	private static void timeStamp(String title) {
		long endTime = System.nanoTime( );
		System.out.println(new Date());
		System.out.println("Cumulative Elapsed Nanoseconds: " + (endTime - startTime));
		System.out.println(title);
	}

//    static void insertPrefix(TrieNode currentNode, int prefixStart, int prefixLength) {
//    	// Check of subS is a prefix of the current node's prefix, is so no action needed, return
//    	if (prefixLength <= currentNode.stringLength() && s.substring(prefixStart, prefixStart + prefixLength).equals(s.substring(currentNode.stringStart, currentNode.stringStart + prefixLength))) return;
//    	// Check of current node's prefix is a prefix of subS,
//    	if (currentNode.stringLength() == 0 || (prefixLength > currentNode.stringLength() && s.substring(currentNode.stringStart, currentNode.stringEnd).equals(s.substring(prefixStart, prefixStart + currentNode.stringLength())))) {
//    		char nextChar = s.substring(prefixStart, prefixStart + prefixLength).charAt(currentNode.stringLength());
//    		int index = nextChar - 'a';
////    		String subSSuffix = s.substring(start, start + length).substring(currentNode.stringLength(), length);
//    		int subSSuffixStart = prefixStart + currentNode.stringLength();
//    		int subSSuffixLength = prefixLength - currentNode.stringLength();
//    		if (currentNode.letterArray[index] != null) {
//    			insertPrefix(currentNode.letterArray[index], subSSuffixStart, subSSuffixLength);
//    		} else {
//    			boolean letterArrayIsEmpty = true;
//    			for (int i = 0; i < currentNode.letterArray.length; i++) {
//    				if (currentNode.letterArray[i] != null) {
//    					letterArrayIsEmpty = false;
//    					break;
//    				}
//    			}
//    			if (letterArrayIsEmpty) {
////    				currentNode.string = s.substring(start, start + length);
//					currentNode.stringStart = prefixStart;
//					currentNode.stringEnd = prefixStart + prefixLength;
//    			} else {
//    				addNewCompactNode(currentNode, subSSuffixStart, subSSuffixLength);
//    			}
//    		}
//    		return;
//    	}
//    	int maxLCP = Math.min(prefixLength, currentNode.stringLength());
//    	for (int i = 0; i <= maxLCP; i++) {
//    		if (!s.substring(prefixStart + i, prefixStart + i + 1).equals(s.substring(currentNode.stringStart + i, currentNode.stringStart + i + 1))) {
//    			createBranch(prefixStart, prefixLength, currentNode, i);
//    			return;
//    		}
//    	}
//    }

	static void createBranch(int start, int end, TrieNode oldNode, int lcpLength) {
 		TrieNode replacementNode = addNewCompactNode(oldNode.parentNode, oldNode.stringStart, lcpLength);
		replacementNode.stringEnd = start + lcpLength;
		addNewCompactNode(replacementNode, Solution.aP.currentPosition - 1, null);
//		oldNode.string = oldNode.string.substring(lcpLength, oldNode.string.length());
		oldNode.stringStart = oldNode.stringStart + lcpLength;
		char c = s.charAt(oldNode.stringStart);
		int oNodeIndex = c - 'a';
		replacementNode.letterArray[oNodeIndex] = oldNode;
		oldNode.parentNode = replacementNode;
		if (!aP.firstNodeCreated) {
			aP.priorNodeCreated.suffixLink = replacementNode;
		}
		aP.firstNodeCreated = false;
 		aP.priorNodeCreated = replacementNode;
		
		Solution.outputTree(Solution.trieTreeRoot, 0, Solution.vTreeRoot);
		
		Solution.aP.remainder--;
		Solution.aP.edgeIndex = activeChar() - 'a';
//		Solution.aP.length--;
		if (Solution.aP.node == Solution.trieTreeRoot && Solution.aP.length == 0) {
			Solution.aP.edgeIndex = null;
			addNewCompactNode(Solution.aP.node, Solution.aP.currentPosition, null);
//			Solution.aP.length--;
		} else {
			if (oldNode.suffixLink == null) {
				Solution.aP.node = trieTreeRoot;
			} else {
				Solution.aP.node = oldNode.suffixLink;
			}
//			Solution.createBranch(
//					Solution.aP.node.letterArray[Solution.aP.edgeIndex].stringStart, 
//					Solution.aP.currentPosition, 
//					Solution.aP.node.letterArray[Solution.aP.edgeIndex], 
//					Solution.aP.length);
		}		
//		Solution.outputTree(Solution.trieTreeRoot, 0, Solution.vTreeRoot);
	}
	static char activeChar() {
		return s.charAt(aP.node.letterArray[aP.edgeIndex].stringStart + aP.length - 1);
	}
	
	static TrieNode addNewCompactNode(TrieNode parentNode, int start, Integer end) {
		TrieNode newNode;
		newNode = new TrieNode(start, end);
		if (parentNode == null) {
			trieTreeRoot = newNode;
		} else {
			char c = s.charAt(start);
			int index = c - 'a';
			newNode.parentNode = parentNode;
			parentNode.letterArray[index] = newNode;
		}
//		newNode.string = string;
		return newNode;
		
	}
	
	static String nodeString(TrieNode node) {
		Integer end = node.stringEnd;
		if (node.stringEnd == null) end = aP.currentPosition;
		return s.substring(node.stringStart, end);
	}
	
	static String nodeStringLength(TrieNode node) {
		Integer end = node.stringEnd;
		if (node.stringEnd == null) end = aP.currentPosition;
		return s.substring(node.stringStart, end);
	}
    
//    static int countNodesInTrie(TrieNode node) {
//    	int stringCount = node.stringLength();
//
//    	for (int i = 0; i < 26; i++) {
//    		if (node.letterArray[i] != null) {
//    			stringCount += countNodesInTrie(node.letterArray[i]);
//    		}
//    	}
//    	return stringCount;
//    }
    
//    static int countPrefixes(TrieNode currentNode, int prefixStart, int prefixLength, VisitedNode currentVNode) {
//    	
//    	// Check of subS is a prefix of the current node's prefix, is so no action needed, return
//    	int returnValue = 0;
//    	if (prefixLength <= currentNode.stringLength() && s.substring(prefixStart, prefixStart + prefixLength).equals(s.substring(currentNode.stringStart, currentNode.stringStart + prefixLength))) {
//    		if (prefixLength > currentVNode.counted) {
//	    		returnValue = prefixLength - currentVNode.counted;
//	    		currentVNode.counted = returnValue;
//    		}
//    		
//    	} else {
//	    	// Check of current node's prefix is a prefix of subS, 
//	    	if (currentNode.stringLength() ==0 || (prefixLength > currentNode.stringLength() && s.substring(currentNode.stringStart, currentNode.stringEnd).equals(s.substring(prefixStart, prefixStart + currentNode.stringLength())))) {
//	    		char nextChar = s.substring(prefixStart, prefixStart + prefixLength).charAt(currentNode.stringLength());
//	    		int index = nextChar - 'a';
//	    		if (currentVNode.letterArray[index] == null) currentVNode.letterArray[index] = new VisitedNode();
//	//    		String subSSuffix = s.substring(start, start + length).substring(currentNode.stringLength(), length);
//	    		int subSSuffixStart = prefixStart + currentNode.stringLength();
//	    		int subSSuffixLength = prefixLength - currentNode.stringLength();
//	    		if (currentVNode.counted < currentNode.stringLength()) returnValue = currentNode.stringLength() - currentVNode.counted;
//	    		returnValue += countPrefixes(currentNode.letterArray[index], subSSuffixStart, subSSuffixLength, currentVNode.letterArray[index]);
//	    		currentVNode.counted = currentNode.stringLength();
//	    	} else {
//		    	int maxLCP = Math.min(prefixLength, currentNode.stringLength());
//		    	for (int lcp = 0; lcp <= maxLCP; lcp++) {
//		    		if (!s.substring(prefixStart + lcp, prefixStart + lcp + 1).equals(s.substring(currentNode.stringStart + lcp, currentNode.stringStart + lcp + 1))) {
//		        		int subSSuffixStart = prefixStart + lcp;
//		        		int subSSuffixLength = prefixLength - lcp;
//		    			char c = s.charAt(subSSuffixStart);
//		    			int index = c - 'a';
//			    		if (currentVNode.letterArray[index] == null) currentVNode.letterArray[index] = new VisitedNode();
//			    		if (lcp > currentVNode.counted) returnValue = lcp - currentVNode.counted;
//		    			returnValue += countPrefixes(currentNode.letterArray[index], subSSuffixStart, subSSuffixLength, currentVNode.letterArray[index]);
//		    			break;
//		    		}
//		    	}
//	    	}
//    	}
//    	return returnValue;
//    }
    static void outputTree(TrieNode currentNode, int treeDepth, VisitedNode currentVNode) {
    	if (currentNode == null) return;
    	
    	if (treeDepth == 0) System.out.println("=========");

    	String indent = "";
    	for (int i = 0; i < treeDepth; i++) {
    		if (i % 2 == 0) {
    			indent += "\\";
    		} else {
    			indent += "|";
    		}
    	}
    	VisitedNode[] visitedArray = new VisitedNode[26];
    	if (currentVNode != null) {
        	System.out.format("%d/%d ", currentVNode.counted,  nodeStringLength(currentNode));
        	visitedArray = currentVNode.letterArray.clone();
    	} else {
    		System.out.print("---");
    	}
    	System.out.println(indent + "\\" + nodeString(currentNode) + "(" + currentNode.stringStart + "," + currentNode.stringEnd + ")");
    	treeDepth++;
    	for (int c = 0; c < 26; c++) outputTree(currentNode.letterArray[c], treeDepth, visitedArray[c]);
    }
    
}

