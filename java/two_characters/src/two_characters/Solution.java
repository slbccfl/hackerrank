package two_characters;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
	
	static int[] letters = new int[26];
	static HashMap<Character, Integer> letterMap = new HashMap<Character, Integer>();
	static boolean[] adjacentFlag = new boolean[26];

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int len = in.nextInt();
        String s = in.next();
        
        for (int i = 0; i < s.length(); i++) {
        	char c = s.charAt(i);
        	int index = 'a' - c;
        	letters[index]++;
        	if (i != 0 && c == s.charAt(i - 1)) adjacentFlag[index] = true;
        }
        for (int i = 0; i < 26; i++) {
        	char c = ('a' + i).toChar();
        	if (letters[i] > 0) letterMap.put('a' + i).toChar, letters[i]);
        }
        int maxTwoChars = 0;
        Map<Character, Integer> countMap = new TreeMap<Character, Integer>(letterMap); 
        Iterator countIterator = countMap.entrySet().iterator();
        Map.Entry countMapEntry = (Map.Entry) countIterator.next();
        int lastCount = (int) countMapEntry.getKey();
        char lastChar = (char) countMapEntry.getValue();
        while (countIterator.hasNext()) {
        	countMapEntry = (Map.Entry) countIterator.next();
        	int count = (int) countMapEntry.getKey();
        	char countChar = (char) countMapEntry.getValue();
        	
        	if (Math.abs(count - lastCount) < 2) {
        		maxTwoChars = Math.max(maxTwoChars, tryPair(s, lastChar, countChar));
        	}
        }
        System.out.println(maxTwoChars);
        
    }
    private static int tryPair(String s, char char1, char char2) {
    	
    	for (char letter : letterMap.keySet()) {
    		if (letter != char1 && letter != char2) s = s.replace(Character.toString(letter), "");
    	}
    	return s.length();
    }
}
