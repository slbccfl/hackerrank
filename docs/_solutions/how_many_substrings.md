---
layout: solution
title: How Many Substrings?
description: Find all the unique substrings in a given string of characters.
exercise_link: https://www.hackerrank.com/challenges/how-many-substrings
---
## Solution:
Incomplete - Solution is giving correct answers for cases #0 through #3, but timing out on test cases #4 through #10. So I believe the logic is correct, just not fast enough yet.
### Strategy:
We need a means for collecting all the substrings of a string while eliminating duplicates of the same substring in this count.

I implemented the Trie data structure to attempt to solve this problem. This does eliminate duplicates and allows me to generate a correct count of all unique substrings. I tried to optimize this solution by excluding cases where the same character repeated from the Trie. And instead calculated the number of substrings in those cases. This was is limited to substrings of a single character repeated. I suspect there are further optimizations possible for repeating substrings beyond a single character. I.e. "abc" repeated 50 times. But after running speed tests I found that collecting and counting these substrings separate from the Trie had inferior performance to just relying on the Trie.

I've also tried to think of this as either a recursion problem, where the overall string is broken in half and the respective substrings broken in half and so on to substrings of single characters. And then returning and building up a count from the constituent substrings. But I don't have a vision of how this would work. The constituent substrings do not stand alone but are part of surrounding substrings too. Take for example, the string "abcabcabc". While the string "abc" would be found to contain six substrings ("a", "b", "c", "ab", "bc", "abc") the string "abcabcabc" does not contain 3 x 6 substrings as there the substrings "ca", "bca" and "cab" too.

I've also tried to look at this as a dynamic programming problem. Evaluating that the impact of the first character in relation to the balance of the string. And then doing a recursive evaluate of each character relative to the balance of the remaining substring. I suspect that an argument could be made that the trie data structure is effectively doing something like this. The potential first characters of all substrings are the letters connected to the root node. The potential second characters are the subsequent set of nodes connected to the first layer of nodes. And so on. But otherwise I am not seeing a solution for this problem using dynamic programming. At least not yet.

I was suspicious that I had been too clever in my Trie representation. My Trie is defined as with this class:
~~~

class TrieNode {
  boolean isEnd;
  TrieNode[] letterArray;

  public TrieNode() {
    this.letterArray = new TrieNode[26];
  }
}

~~~
The problem is limited to lower case letters only. Therefore, there are only 26 possible letters for each character in the string. So within each Trie node I create a simple array of Trie nodes, one for each possible letter. To designate that array cell that applies for a character I use an index based on the character's Unicode binary value, converting each character to a char and calculating the index with "int index = c - 'a';". In some cases a node would have only a couple of array cells used. I suspected that a simple HashMap might be more efficient. So I redefined the Trie as
~~~

class TrieNode {
  boolean isEnd;
  HashMap<Character, TrieNode> nextLetters;

  public TrieNode() {
    this.nextLetters = new HashMap<Character, TrieNode>();
  }
}

~~~
and made the appropriate changes in the code using the Trie. Turns out this was significantly slower. So reset the code back.  

### Methods:

#### main
The main method uses BufferReader to read the inputs, with a for loops to take in the left and right indexes that identify the portion of the string "s" to be evaluated for each query, which is assigned to subS. Within the loop for the query, are two more loops to slice out all the substrings for analysis. The first of these for loops sets a substring length, ssLength, starting with 1 up to the length of the sting subS. The second of this pair of loops progresses and index "i" to pickup all the potential substrings in subS that can be of length ssLength. So each of the individual letters will be picked up in the first cycle through the outer loop of this pair with ssLength == 1. In the second run through the outer of these loops, with ssLength == 2, will pick up all the pairs of two characters in the string subS. The next pass will collect all the substrings of length ssLength == 3 and so on up the the entire subString subS. I turned this loop around, to process the longest string, all of subS, down to substrings of length 1. This ran about twice as fast. I suspect that the queries to the Trie finding the chain of characters already in place was faster than building the chain one call to insert into the Trie at a time. This led to a bit of an epiphany. The subsequent inserts into the Trie were only tagging the individual characters along the string as substrings themselves. This was leading to a bunch of unnecessary looping for my purposes. I needed to create the predictable chains of characters in the tree with every character marked as an ending character. The Trie was note really a bunch of prefixes, it was just a collection of substrings with larger substrings. So I eliminated the inter loop in main and called a addTrieChain instead of an insertTrieTree method. That method creates the series of Trie nodes to represent the substring, setting isEnd true for each letter an ending character of a string. The first letter is excluded in the next call and the chain of Trie nodes for that substring is created, and so on down to the last character of subS. This removed an entire loop from the generation of the Trie. Not surprisingly, the performance was nearly an order of magnitude better. What was surprising, and aggravating, was that the submission of this dramatically improved code got the same results on HackerRank. Timing out for testcases #4 through #10. I expected at least some of these to pass. WTF?

Since the problem is a analysis of many cuts of substrings from the same string "s" for each query. I'm now wondering if there is some way of using information across all of these runs. At the moment they are run completely separate from each other, building a Trie for each subS string with no information saved from query to query. I also continue to suspect that there is some way of optimizing for the many repeating series of characters.

I consulted with someone much smarter than me, and he suggested that I look at a compact prefix tree, as an alternative to the Trie (or prefix tree) that I am using. This would store strings at each node, splitting out a portion of the string to a subsidiary node only when there is a difference between two substrings. I have not yet tried this. I was afraid that it would more or less the same as the current Trie as I suspected every node was get broken down to a single character. After the above experience I think a compact tree would contain all the substrings that would be passed to the addTrieChain method, above, with a null node, indicating an the end of string for each letter in the string.


### Data Structures:
