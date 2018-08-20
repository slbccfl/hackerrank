package how_many_substrings;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


class TrieNode {
	boolean isEnd;
	TrieNode[] letterArray;
	
	public TrieNode() {
		this.letterArray = new TrieNode[26];
	}
}

public class Solution {

	static TrieNode trieTreeRoot;
//	static boolean[] alphabet;
	static int[] repeatingCharCounts;

    public static void main(String[] args) {
    	BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
    	String line = null;
    	try {
			line = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	int n = Integer.parseInt(line.split("\\s")[0]);
    	int q = Integer.parseInt(line.split("\\s")[1]);
//        int n = in.nextInt();
//        int q = in.nextInt();
    	Integer testCases = null;
    	String s = null;
		try {
			s = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
//        String s = in.next();
        for(int a0 = 0; a0 < q; a0++){ 
        	repeatingCharCounts = new int[26];
//        	alphabet = new boolean[26];
        	try {
				line = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	int left = Integer.parseInt(line.split("\\s")[0]);
	    	int right = Integer.parseInt(line.split("\\s")[1]);
//            int left = in.nextInt();
//            int right = in.nextInt();
            
//            Set<String> subSSet = new HashSet<String>();
            trieTreeRoot = new TrieNode();
            String subS = s.substring(left,right+1);
            for (int ssLength = 1;ssLength <= subS.length(); ssLength++) {
//            	char c = subS.charAt(ssLength - 1);
//        		int index = c - 'a';
//        		alphabet[index] = true;
                for (int i = 0; i < subS.length() - ssLength + 1; i++) {
                	int endIndex = ssLength + i - 1;
                	char firstChar = subS.charAt(i);
                	int j;
                	for (j = i + 1; j <= endIndex; j++) {
                		if (subS.charAt(j) != firstChar) break;
                	}
                	if (j > endIndex) {
                		int index = firstChar - 'a';
                		repeatingCharCounts[index] = Math.max(repeatingCharCounts[index], ssLength);
            		} else {
//                		String sSubS = subS.substring(i, endIndex + 1);
                    	insertTrieTree(subS.substring(i, endIndex + 1));
                	}
                		
                }
            }
//            double lettersUsed = 0;
//            for (int i = 0; i < 26; i++) if (alphabet[i]) lettersUsed++;
//            double letterPermutations = Math.pow(lettersUsed, (double) right - left + 1);
            
            int repeatingCharCount = 0;
            for (int i = 0;i < 26; i++) repeatingCharCount += repeatingCharCounts[i];
//            System.out.println(a0 + ":  " + (repeatingCharCount + countNodesInTrie(trieTreeRoot)) + " -- " + subS.length() + ":  " + subS);
            System.out.println(repeatingCharCount + countNodesInTrie(trieTreeRoot));
        }
    }
    
    static void insertTrieTree(String subS) {
    	TrieNode p = trieTreeRoot;
    	for (int i = 0; i < subS.length(); i++) {
    		char c = subS.charAt(i);
    		int index = c - 'a';
    		if (p.letterArray[index] == null) {
    			TrieNode temp = new TrieNode();
    			p.letterArray[index] = temp;
    			p = temp;
    		} else {
    			p = p.letterArray[index];
    		}
    		
    	}
    	p.isEnd = true;
    }
    
    static int countNodesInTrie(TrieNode node) {
    	int stringCount = 0;
		if (node.isEnd) stringCount++;
    	for (int i = 0; i < 26; i++) {
    		if (node.letterArray[i] != null) {
    			stringCount += countNodesInTrie(node.letterArray[i]);
    		}
    	}
    	return stringCount;
    }
    
}

