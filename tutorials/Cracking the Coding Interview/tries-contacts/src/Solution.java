import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
	
	public static class Node {
		Character value;
		HashMap<Character, Node> children;
		boolean isCompleted;
		int nodeWordCount; 
		
		public Node() {
			value = null;
			children = new HashMap<Character, Node>();
			isCompleted = false;
			nodeWordCount = 0;
		}
		
		public void addWord(Node n, String word) {
			char[]wordCharArray = word.toCharArray();
			for (int i = 0; i < word.length(); i++) {
				Character c = wordCharArray[i];
				n = addChar(n,c);
			}
			n.isCompleted = true;
			return;
		}
		
		public Node addChar(Node n, Character c) {
			Node nextNode;
			if (n.children.containsKey(c)) {
				nextNode = n.children.get(c);
				nextNode.nodeWordCount++;
			} else {			
				nextNode = new Node();
				n.children.put(c,nextNode);	
				nextNode.value = c;
				nextNode.nodeWordCount++;
			}
			return nextNode;
		}
		
		public Node findWord(Node n, String partial) {
			
			char[]partialCharArray = partial.toCharArray();
			for (int i = 0; i < partial.length() && n != null; i++) {
				Character c = partialCharArray[i];
				n = findChar(n,c);
			}
			
			int wordCount = 0;
			if (n != null) {
//				wordCount = countWords(n, wordCount);
				wordCount = n.nodeWordCount;
			}
			System.out.println(wordCount);
			
			return n;
		}
		
		public Node findChar(Node n, Character c) {
			Node nextNode = null;
			if (n.children.containsKey(c)) {
				nextNode = n.children.get(c);
			} 
			return nextNode;
		}
		
//		public Integer countWords(Node n, Integer wordCount) {
//			if (n.isCompleted) wordCount++;
//		    
//	    	Character nextCharacter;
//	    	Node nextNode;
//		    Set<Map.Entry<Character, Node>> set = n.children.entrySet();
//		    for (Map.Entry<Character, Node> nodeMapSet : set) {
//		    	nextCharacter = nodeMapSet.getKey();
//		    	nextNode = nodeMapSet.getValue();
//		    	wordCount = countWords(nextNode, wordCount);
//		    }
//		    
//		    return wordCount;
//		}
		
	}

    public static void main(String[] args) {
//    	long startTime = System.currentTimeMillis( );
//    	System.out.println(new Date());
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();            
        Node headNode = new Node();
        for(int a0 = 0; a0 < n; a0++){
            String op = in.next();
            String contact = in.next();

            if (Objects.equals(op, "add")) {
            	headNode.addWord(headNode, contact);
            }
            if (Objects.equals(op, "find")) {
            	headNode.findWord(headNode, contact);
            }
        }
        in.close();
//    	long endTime = System.currentTimeMillis( );
//    	System.out.println(new Date());
//    	System.out.println("Elapsed Milliseconds: " + (endTime - startTime));
    }
}
