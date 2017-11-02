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
	int nodeNumber;
	
	public TrieNode(int start, Integer end) {
		this.stringStart = start;
//		this.stringEnd = start + length;
		this.stringEnd = end;
//		this.string = "";
		this.letterArray = new TrieNode[26];
		this.nodeNumber = Solution.nodeCount++;
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
	static int nodeCount = 0;

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
	
	static void buildTree(int start, int end) {
		Solution.aP = new activePoint();
		addNewCompactNode(null, 0, 0);	
		aP.node = trieTreeRoot;
		aP.edgeIndex = null;
		aP.length = 0;
		aP.remainder = 0;
		for (aP.currentPosition = start; aP.currentPosition <= end; aP.currentPosition++) {
			outputActivePoint();
			aP.firstNodeCreated = true;
			aP.priorNodeCreated = null;
			if (aP.node.letterArray[(s.charAt(aP.currentPosition) - 'a')] == null) {
				addNewCompactNode(aP.node, aP.currentPosition, null);
			} else {
//				int index = s.charAt(aP.currentPosition) - 'a';
				if (aP.edgeIndex == null) aP.edgeIndex = s.charAt(aP.currentPosition) - 'a';
				if (s.charAt(aP.node.letterArray[aP.edgeIndex].stringStart + aP.length) != s.charAt(aP.currentPosition)) {
					createBranch(
							aP.node.letterArray[aP.edgeIndex].stringStart, 
							aP.currentPosition, 
							aP.node.letterArray[aP.edgeIndex], 
							aP.length);
				} else {
					aP.length++;
//					aP.edgeIndex = s.charAt(aP.currentPosition) - 'a';
					aP.remainder++;
					if (aP.length > nodeStringLength(aP.node.letterArray[aP.edgeIndex])) {
						aP.node = aP.node.letterArray[aP.edgeIndex];
						aP.length = 0;
					}
				}
			}
		}
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
		addNewCompactNode(replacementNode, aP.currentPosition - 1, null);
//		oldNode.string = oldNode.string.substring(lcpLength, oldNode.string.length());
		oldNode.stringStart = oldNode.stringStart + lcpLength;
		char c = s.charAt(oldNode.stringStart);
		int oNodeIndex = c - 'a';
		replacementNode.letterArray[oNodeIndex] = oldNode;
		oldNode.parentNode = replacementNode;

		Solution.outputTree(Solution.trieTreeRoot, 0, Solution.vTreeRoot);
		
		aP.remainder--;
		
		// Rule 1 
		if (aP.node == Solution.trieTreeRoot) {
			aP.edgeIndex = s.charAt(aP.node.letterArray[aP.edgeIndex].stringStart + aP.length - 1) - 'a';
			aP.length--;
		} else {		// Rule 3
			if (replacementNode.parentNode.suffixLink == null) {
				aP.node = trieTreeRoot;
			} else {
				aP.node = replacementNode.parentNode.suffixLink;
			}
		}
		
		// Rule 2
		if (!aP.firstNodeCreated) {
			aP.priorNodeCreated.suffixLink = replacementNode;
		}
		aP.firstNodeCreated = false;
 		aP.priorNodeCreated = replacementNode;
		
		if (aP.length == 0 && aP.node.letterArray[aP.edgeIndex] != null) {
			addNewCompactNode(aP.node, aP.currentPosition  - 1, null);
		}
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
	
	static int nodeStringLength(TrieNode node) {
		Integer end = node.stringEnd;
		if (node.stringEnd == null) end = aP.currentPosition;
		return end - node.stringStart + 1;
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
    
    static int countPrefixes(TrieNode currentNode, int prefixStart, int prefixLength, VisitedNode currentVNode) {
    	
    	// Check of subS is a prefix of the current node's prefix, is so no action needed, return
    	int returnValue = 0;
    	int currentNodeStringLength = nodeStringLength(currentNode);
    	if (prefixLength <= currentNodeStringLength && s.substring(prefixStart, prefixStart + prefixLength).equals(s.substring(currentNode.stringStart, currentNode.stringStart + prefixLength))) {
    		if (prefixLength > currentVNode.counted) {
	    		returnValue = prefixLength - currentVNode.counted;
	    		currentVNode.counted = returnValue;
    		}
    		
    	} else {
	    	// Check of current node's prefix is a prefix of subS, 
	    	if (currentNodeStringLength ==0 || (prefixLength > currentNodeStringLength && s.substring(currentNode.stringStart, currentNode.stringEnd).equals(s.substring(prefixStart, prefixStart + currentNodeStringLength)))) {
	    		char nextChar = s.substring(prefixStart, prefixStart + prefixLength).charAt(currentNodeStringLength);
	    		int index = nextChar - 'a';
	    		if (currentVNode.letterArray[index] == null) currentVNode.letterArray[index] = new VisitedNode();
	//    		String subSSuffix = s.substring(start, start + length).substring(currentNodeStringLength, length);
	    		int subSSuffixStart = prefixStart + currentNodeStringLength;
	    		int subSSuffixLength = prefixLength - currentNodeStringLength;
	    		if (currentVNode.counted < currentNodeStringLength) returnValue = currentNodeStringLength - currentVNode.counted;
	    		returnValue += countPrefixes(currentNode.letterArray[index], subSSuffixStart, subSSuffixLength, currentVNode.letterArray[index]);
	    		currentVNode.counted = currentNodeStringLength;
	    	} else {
		    	int maxLCP = Math.min(prefixLength, currentNodeStringLength);
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
    
    static void outputActivePoint() {
    	String edge;
    	if (aP.edgeIndex == null) {
    		edge = "none";
    	} else {
    		edge = Character.toString((char) ('a' + aP.edgeIndex));
    	}
    	System.out.format("Node: %d // Edge: %s // Length: %d // Remainder: %d // Position in s: %d(%c)\n", 
    			aP.node.nodeNumber, edge, aP.length, aP.remainder, aP.currentPosition, s.charAt(aP.currentPosition));
//    	System.out.format("Node: %d ", 
//    			aP.node.nodeNumber);
    
    }
    
}

