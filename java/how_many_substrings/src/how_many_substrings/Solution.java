package how_many_substrings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.ArrayList;


class TreeNode {
	boolean isEnd;
	TreeNode parentNode;
	int stringStart;
	Integer stringEnd;
	TreeNode[] letterArray;
	TreeNode suffixLink;
	int nodeNumber;

	public TreeNode(int start, Integer end) {
		this.stringStart = start;
		this.stringEnd = end;
		this.letterArray = new TreeNode[26];
		this.nodeNumber = Solution.nodeCount++;
	}
}

//class VisitedNode {
//	long counted;
//	VisitedNode[] letterArray;
//
//	public VisitedNode() {
//		counted = 0;
//		this.letterArray = new VisitedNode[26];
//	}
//}

class activePoint {
	TreeNode node;
	Integer edgeIndex;
	int length;
	int remainder;
	Integer currentPosition;
	boolean firstNodeUpdated;
	TreeNode priorNodeUpdated;
}

public class Solution {

	static TreeNode suffixTreeRoot;
//	static VisitedNode vTreeRoot;
	static int visitedChars[];
	static long startTime = System.nanoTime();
	static String s = null;
	static activePoint aP;
	static int nodeCount = 0;
	static StringBuilder prefixesCountString;
//	static ArrayList<TreeNode> nodeTable;

    public static void main(String[] args) {

    	timeStamp("Ukkonen Algorithm - A single Prefix Tree \n	"
    			+ "prefix counts for each query using VisitedChars array");

    	BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
    	String line = null;
    	try {
			line = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	int n = Integer.parseInt(line.split("\\s")[0]);
    	int q = Integer.parseInt(line.split("\\s")[1]);
		prefixesCountString = new StringBuilder(q * 8);
		
		try {
			s = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int sLength = s.length();
		Solution.nodeCount = 0;

    	long buildStartTime = System.nanoTime();
    	timeStamp("Single Tree Build --  sLength: " + sLength);
    	
		Solution.buildTree(1, sLength);

		long endTime = System.nanoTime( );
		float buildElapsedTime = endTime - buildStartTime;
		System.out.printf("Build Elapsed Nanoseconds: %,.0f -- nanoseconds/character: %,.4f\n", buildElapsedTime, (buildElapsedTime / sLength));

        for(int a0 = 0; a0 < q; a0++){
        	try {
				line = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	int subSStart = Integer.parseInt(line.split("\\s")[0]);
	    	int subSEnd = Integer.parseInt(line.split("\\s")[1]);
	    	int subSLength = subSEnd - subSStart + 1;
	    	
//	    	long buildStartTime = System.nanoTime();
//	    	timeStamp("Build #" + a0 + " -- Left:  " + subSStart + " Right: " + subSEnd + "   subSLength: " + subSLength);
//	    	
//			Solution.buildTree(subSStart + 1, subSEnd + 1);
//			
//			long endTime = System.nanoTime( );
//			float buildElapsedTime = endTime - buildStartTime;
//			System.out.printf("Build Elapsed Nanoseconds: %,.0f -- nanoseconds/character: %,.4f\n", buildElapsedTime, (buildElapsedTime / sLength));
	    	
        	long queryStartTime = System.nanoTime();
	    	timeStamp("Query #" + a0 + " -- Left:  " + subSStart + " Right: " + subSEnd + "   subSLength: " + subSLength);
//	    	System.out.println(s.substring(subSStart, subSEnd));
	    	
//    		System.out.println(countTreePrefixes(subSStart, subSLength));		
			visitedChars = new int[nodeCount];
    		prefixesCountString.append(countTreePrefixes(subSStart, subSLength));
//    		Solution.aP.currentPosition = subSEnd + 1;
//    		prefixesCountString.append(countTreePrefixes());
    		prefixesCountString.append("\n");

			endTime = System.nanoTime( );
			float queryElapsedTime = endTime - queryStartTime;
			System.out.printf("Query Elapsed Nanoseconds: %,.0f -- nanoseconds/character: %,.4f\n", queryElapsedTime, (queryElapsedTime / subSLength));



        }
    	long outputStartTime = System.nanoTime();

		System.out.print(prefixesCountString);
		
		endTime = System.nanoTime( );
		float outputElapsedTime = endTime - outputStartTime;
		System.out.printf("Output Elapsed Nanoseconds: %,.0f -- nanoseconds/character: %,.4f\n", outputElapsedTime, (outputElapsedTime / prefixesCountString.length()));
//		outputTree(trieTreeRoot, 0);
		timeStamp("Final TimeStamp");
    }

	private static void timeStamp(String title) {
		long endTime = System.nanoTime( );
		System.out.println(new Date());
		System.out.printf("Cumulative Elapsed Nanoseconds: %,d\n", (endTime - startTime));
		System.out.println(title);
	}

	static void buildTree(int start, int end) {
		aP = new activePoint();
		nodeCount = 0;
//		nodeTable = new ArrayList<TreeNode>();
		suffixTreeRoot = new TreeNode(0, 0);
//		nodeTable.add(suffixTreeRoot.nodeNumber, suffixTreeRoot);
		aP.node = suffixTreeRoot;
		aP.edgeIndex = null;
		aP.length = 0;
		aP.remainder = 0;
		long leafCount = 0;
		long prefixCount = 0;
		Character currentPositionChar;
		for (aP.currentPosition = start; aP.currentPosition <= end; aP.currentPosition++) {
			aP.firstNodeUpdated = true;
			aP.priorNodeUpdated = null;
			currentPositionChar = s.charAt(aP.currentPosition - 1);
			Character currentAPChar = getAPCharacter();
//			outputActivePoint("Next character ");

			boolean treeModified = true;
			while (treeModified) {
//				while (currentAPChar != null && currentAPChar != currentPositionChar) {
				if (currentAPChar != null && currentAPChar != currentPositionChar) {
//					outputActivePoint("Branch at ");
					createBranch();
					leafCount++;
					currentAPChar = getAPCharacter();
				}
				if (currentAPChar == null) {
					currentAPChar = checkForNode();
					if (currentAPChar == null) {
//						outputActivePoint("New Leaf at ");
						addNewCompactNode(aP.node, aP.currentPosition - 1, null);
						leafCount++;
						if (aP.node == suffixTreeRoot) {
							treeModified = false;
						}
						applyRules(aP.node);
						currentAPChar = getAPCharacter();
					} else {
						treeModified = false;
					}
				} else {
					if (currentAPChar == currentPositionChar) {
						// Observation 1
						aP.length++;
						aP.remainder++;
						treeModified = false;
					}
				}
			}
			prefixCount += leafCount;
		}
		prefixesCountString.append(prefixCount);
		prefixesCountString.append("\n");
	}

	private static Character checkForNode() {
		Character currentAPChar = null;
		TreeNode newNode = aP.node.letterArray[s.charAt(aP.currentPosition - 1) - 'a'];
		if (newNode != null) {
			if (aP.length + 1 == nodeStringLength(newNode)) {
				aP.node = newNode;
				aP.edgeIndex = null;
				currentAPChar = s.charAt(aP.currentPosition - 1);
			} else {
				aP.edgeIndex = s.charAt(aP.currentPosition - 1) - 'a';
				aP.length++;
				currentAPChar = s.charAt(newNode.stringStart);
			}
			aP.remainder++;
		}
		return currentAPChar;
	}

	private static Character getAPCharacter() {
		if (aP.length == 0) aP.edgeIndex = null;
		if (aP.edgeIndex == null) return null;
		TreeNode currentNode = aP.node.letterArray[aP.edgeIndex];
		if (currentNode == null) return null;
		int suffixIndex = aP.remainder - aP.length;
		// Observation 2
		while (currentNode != null && aP.length >= nodeStringLength(currentNode)) {
//			outputActivePoint();
			aP.length -= nodeStringLength(currentNode);
			suffixIndex += nodeStringLength(currentNode);
			aP.node = currentNode;
			if (aP.length == 0) {
				aP.edgeIndex = null;
				return null;
			} else {
				aP.edgeIndex = s.charAt(aP.currentPosition - aP.remainder + suffixIndex - 1) - 'a';
				currentNode = aP.node.letterArray[aP.edgeIndex];
				if (currentNode == null) return null;
			}
		}
//		outputActivePoint();

		return s.charAt(aP.node.letterArray[aP.edgeIndex].stringStart + aP.length);
	}

	static void createBranch() {
		TreeNode oldNode = aP.node.letterArray[aP.edgeIndex];
		aP.node.letterArray[aP.edgeIndex] = null;
 		TreeNode replacementNode = addNewCompactNode(oldNode.parentNode, oldNode.stringStart, aP.length);
		replacementNode.stringEnd = aP.node.letterArray[aP.edgeIndex].stringStart + aP.length;
		addNewCompactNode(replacementNode, aP.currentPosition - 1, null);
		oldNode.stringStart = oldNode.stringStart + aP.length;
		char c = s.charAt(oldNode.stringStart);
		int oNodeIndex = c - 'a';
		replacementNode.letterArray[oNodeIndex] = oldNode;
		oldNode.parentNode = replacementNode;


//		Solution.outputTree(Solution.trieTreeRoot, 0, Solution.vTreeRoot);


		applyRules(replacementNode);

	}

	static TreeNode addNewCompactNode(TreeNode parentNode, int start, Integer end) {
		if (parentNode == null) {
			throw new IllegalArgumentException(
					"in Solution.addNewCompactNode: >>> parentNode is null <<<");
		}
		TreeNode newNode = new TreeNode(start, end);
//		nodeTable.add(newNode.nodeNumber, newNode);
		newNode.parentNode = parentNode;
		parentNode.letterArray[s.charAt(start) - 'a'] = newNode;
			
		return newNode;
	}

	private static void applyRules(TreeNode nodeUpdated) {
		
		if (aP.remainder > 0) aP.remainder--;
		
		// Rule 1
		if (aP.node == Solution.suffixTreeRoot && aP.length > 0) {
			aP.edgeIndex = s.charAt(aP.currentPosition - aP.remainder - 1) - 'a';
			aP.length--;
		}
		
			// aP.node not root, so internal node was updated and rules 2 and 3 apply
			// Rule 2
		if (nodeUpdated != suffixTreeRoot) {
			if (!aP.firstNodeUpdated) {
				aP.priorNodeUpdated.suffixLink = nodeUpdated;
			}
			aP.firstNodeUpdated = false;
	 		aP.priorNodeUpdated = nodeUpdated;
		}
		// Rule 3
		if (aP.node != suffixTreeRoot) {
			if (aP.node.suffixLink == null) {
				aP.node = suffixTreeRoot;
				aP.length = aP.remainder;
				aP.edgeIndex = s.charAt(aP.currentPosition - aP.remainder - 1) - 'a';
			} else {
//				aP.node = nodeUpdated.parentNode.suffixLink;
				aP.node = aP.node.suffixLink;
			}
		}
	}

//	static long countTreePrefixes() {
//		long prefixesCount = 0;
//		for (int nodeID = 0; nodeID < nodeCount; nodeID++) {
////			prefixesCount += nodeStringLength(nodeTable.get(nodeID));
//		}
//		return prefixesCount;
//	}

	static long countTreePrefixes(int subSStart, int subSLength) {
//		Solution.vTreeRoot = new VisitedNode();
		long prefixesCount = 0;
		for (int ssLength = subSLength; ssLength > 0; ssLength--) {
			int ssStart = subSStart + subSLength - ssLength;
//			prefixesCount += countNodePrefixes(suffixTreeRoot, ssStart, ssLength, vTreeRoot);
			prefixesCount += countNodePrefixes(suffixTreeRoot, ssStart, ssLength);
			

		}
		return prefixesCount;
	}

//  static long countNodePrefixes(TreeNode currentNode, int prefixStart, int prefixLength, VisitedNode currentVNode) {
  static long countNodePrefixes(TreeNode currentNode, int prefixStart, int prefixLength) {
    	// Check of subS is a prefix of the current node's prefix, is so no action needed, return
    	int returnValue = 0;
    	int currentNodeStringLength = 0;
		char nextChar = s.charAt(prefixStart);
		int index = nextChar - 'a';
//		if (currentVNode.counted < currentNodeStringLength) returnValue = currentNodeStringLength - currentVNode.counted;
//		returnValue += countNodePrefixes(currentNode.letterArray[index], subSSuffixStart, subSSuffixLength, currentVNode.letterArray[index]);
//		currentVNode.counted = currentNodeStringLength;
    	while (prefixLength > 0) {
    		nextChar = s.charAt(prefixStart);
    		index = nextChar - 'a';
//    		if (currentVNode.letterArray[index] == null) currentVNode.letterArray[index] = new VisitedNode();
    		currentNode = currentNode.letterArray[index];
//    		currentVNode = currentVNode.letterArray[index];
        	currentNodeStringLength = nodeStringLength(currentNode);
	    	if (prefixLength > currentNodeStringLength 
	    			&& 
	    			s.substring(currentNode.stringStart, currentNode.stringStart + currentNodeStringLength)
	    					.equals(s.substring(prefixStart, prefixStart + currentNodeStringLength))
	    					) {
	    		if (visitedChars[currentNode.nodeNumber] < currentNodeStringLength) returnValue += currentNodeStringLength - visitedChars[currentNode.nodeNumber];
	    		visitedChars[currentNode.nodeNumber] = currentNodeStringLength;
	    		prefixStart += currentNodeStringLength;
	    		prefixLength -= currentNodeStringLength;
	    	} else {
		    	int maxLCP = Math.min(prefixLength, currentNodeStringLength) - 1;
		    	int lcp = 0;
		    	while (lcp <= maxLCP && s.charAt(prefixStart + lcp) == s.charAt(currentNode.stringStart + lcp)) {
		    			lcp++;
		    		}
		    	prefixStart += lcp;
    			prefixLength -= lcp;
	    		if (lcp > visitedChars[currentNode.nodeNumber]) {
	    			returnValue += lcp - visitedChars[currentNode.nodeNumber];
	    			visitedChars[currentNode.nodeNumber] = lcp;
	    		}
	    	}
    		
    	}
//    	System.out.println("(" + currentNode.nodeNumber + ") " + returnValue);
    	return returnValue;
    }

	static String nodeString(TreeNode node) {
		Integer end = node.stringEnd;
		if (end == null) end = aP.currentPosition;
		return s.substring(node.stringStart, end);
	}

	static int nodeStringLength(TreeNode node) {
		Integer end = node.stringEnd;
		if (end == null) end = aP.currentPosition;
		return end - node.stringStart;
	}
	
    static void outputTree() {
		System.out.println("=========");
		System.out.println(s);
//		outputNode(Solution.suffixTreeRoot, 0, Solution.vTreeRoot);
		outputNode(Solution.suffixTreeRoot, 0);
    }

//  static void outputNode(TreeNode currentNode, int treeDepth, VisitedNode currentVNode) {
  static void outputNode(TreeNode currentNode, int treeDepth) {
    	if (currentNode == null) return;

    	String indent = "";
    	for (int i = 0; i < treeDepth; i++) {
    		if (i % 2 == 0) {
    			indent += "\\";
    		} else {
    			indent += "|";
    		}
    	}
    	String suffixString;
    	if (currentNode.suffixLink == null) {
    		suffixString = "null";
    	} else {
    		suffixString = Integer.toString(currentNode.suffixLink.nodeNumber);
    	}
    	System.out.format("(%3d)-->(%4s)", currentNode.nodeNumber, suffixString);

//    	VisitedNode[] visitedArray = new VisitedNode[26];
//    	if (currentVNode != null) {
//        	System.out.format("%3d/%3d ", currentVNode.counted,  nodeStringLength(currentNode));
////        	visitedArray = currentVNode.letterArray.clone();
//    	} else {
    		System.out.print("-------");
//    	}
    	System.out.println(indent + "\\" + nodeString(currentNode) + "(" + currentNode.stringStart + "," + currentNode.stringEnd + ")");
    	treeDepth++;
//    	for (int c = 0; c < 26; c++) outputNode(currentNode.letterArray[c], treeDepth, visitedArray[c]);
    	for (int c = 0; c < 26; c++) outputNode(currentNode.letterArray[c], treeDepth);
    }

    static void outputActivePoint(String label) {
    	System.out.println(label);
    	String edge;
    	if (aP.edgeIndex == null) {
    		edge = "none";
    	} else {
    		edge = Character.toString((char) ('a' + aP.edgeIndex));
    	}
    	char currentCharacter;
    	if (aP.currentPosition > s.length()) {
    		currentCharacter = '$';
    	} else {
    		currentCharacter = s.charAt(aP.currentPosition - 1);
    	}
    	System.out.format("Node: %d / Edge: %s / Length: %d / Remainder: %d / Position in s: %d(%c)\n",
    			aP.node.nodeNumber, edge, aP.length, aP.remainder, aP.currentPosition, currentCharacter);
    	if (aP.remainder > 0) {
    		System.out.println("Prefix: " + s.substring(aP.currentPosition - aP.remainder - 1, aP.currentPosition - 1));
    	}
    	return;
    }

    static String cummPrefix(TreeNode node) {
    	String prefix = "";
    	if (node.parentNode != null) prefix += cummPrefix(node.parentNode);
    	prefix += nodeString(node);
    	return prefix;
    }
}
