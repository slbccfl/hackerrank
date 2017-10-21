---
layout: solution
title: How Many Substrings?
description: Find all the unique substrings in a given string of characters.
exercise_link: https://www.hackerrank.com/challenges/how-many-substrings
---
## Solution:
Incomplete - Solution is giving correct answers for cases #0 through #3, but timing out on test cases #4 through #10. So the logic is correct, but is just not fast enough yet with the methods tried so far.
### Strategy:
To solve this problem, we need a means for collecting all the substrings contained within a given string, eliminating all duplicates among these substrings.

My first attempt was an implementation of a Trie data structure. This does eliminate duplicates and allows me to generate a correct count of all unique substrings. I tried to optimize this solution by excluding cases where the same character repeated from the Trie. And instead calculated the number of substrings in those cases. This was limited to substrings of a single character repeated. I suspected there are further optimizations possible for repeating substrings beyond a single character. I.e. "abc" repeated 50 times. But after running speed tests I found that collecting and counting single character repeats, separate from the Trie, had inferior performance to just relying on the Trie.

I've also tried to think of this as either a recursion problem, where the overall string is broken in half and the respective substrings broken in half and so on to substrings of single characters. And then returning and building up a count from the constituent substrings. But I haven't derived a clear vision of how this would work. The constituent substrings do not stand alone but are part of surrounding substrings too. Take for example, the string "abcabcabc". While the string "abc" would be found to contain six substrings ("a", "b", "c", "ab", "bc", "abc"), the string "abcabcabc" does not contain 3 x 6 substrings, as there the substrings "ca", "bca" and "cab" too.

I've also tried to look at this as a dynamic programming problem. Doing a recursive evaluation of the impact of the first character in relation to the balance of the string, until the entire string is processed. I suspect that an argument could be made that the trie data structure is effectively doing something like this. The potential first characters of all substrings are the letters connected to the root node. The potential second characters are the subsequent set of nodes connected to the first layer of nodes. And so on. But otherwise I am not yet seeing a solution for this problem using dynamic programming.

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
In this data structure I define an array to hold all possible next letters that would come from the current node. Since the problem is limited to lower case letters only, there are only 26 possible letters for each character in the string. So within each Trie node, the letterArray can hold a reference to a node for each possible next letter. To reference the array cell that applies for a character I use an index based on the character's Unicode binary value, converting each character to a char and calculating the index with "int index = c - 'a';", where c is the char variable. In some cases a node would use only a couple of array cells. Or even none of the array cells in the case of leaf nodes. I suspected that a simple HashMap might be more efficient. So I redefined the Trie as
~~~

class TrieNode {
  boolean isEnd;
  HashMap<Character, TrieNode> nextLetters;

  public TrieNode() {
    this.nextLetters = new HashMap<Character, TrieNode>();
  }
}

~~~
and made the appropriate changes in the code using the Trie. Turns out this was significantly slower, so I reset the code back and have kept the letterArray within nodes in the subsequent versions.

The process of slicing the substrings out of the designated string for each query, subS, has also gone through a number of iterations. Within the loop for the query, I started with a pair of nested loops to slice out all the possible substrings for analysis. This was very brute force and not surprisingly, not optimal. There was one observation that was very informative. Initially these loops traversed the string subS front to back, changing the length of the successive substrings from 1 to the full length of subS. I turned this loop around, to process the longest string, all of subS, down to substrings of length 1. This ran about twice as fast. I suspect that the queries to the Trie finding the chain of characters already in place was faster than building the chain one call to insert into the Trie at a time. This led to a bit of an epiphany. The subsequent inserts into the Trie were only tagging the individual characters along the string as substrings themselves. This was leading to a bunch of unnecessary looping for my purposes. I needed to create the predictable chain of characters in the trie, with every character marked as an ending character. Realizing this, I was able to eliminated the inter loop in main and called a new method to generate the series of chained trie Nodes instead of using the trie insert method for all the substrings with in the prefix. The new method creates the series of Trie nodes to represent the substring, setting isEnd true for each letter an ending character of a string. The first letter is excluded in the next call and the chain of Trie nodes for that substring is created, and so on down to the last character of subS. This removed an entire loop from the generation of the Trie. Not surprisingly, the performance was nearly an order of magnitude better. What was surprising, and aggravating, was that the submission of this dramatically improved code got the same results on HackerRank. Timing out for testcases #4 through #10. I expected this code to pass at least some additional test cases.

On to the next big idea. I consulted with someone much smarter than me, and he suggested that I look at a compact prefix tree, as an alternative to the Trie (or prefix trie) that I am using. This would store strings at each node, splitting out a portion of the string to a subsidiary node only when there is a difference between two substrings. I was afraid that this approach would result in more or less the same tree as the current Trie as I suspected every node would get broken down to a single character. Nevertheless, I did proceed to retrofit my solution to implement this approach. This was about five times faster than a simple trie. Which, when submitted to HackerRank, stubbornly give the same result in timeouts from test case #4, on. My implementation of this used the following data structure for the node:
~~~

class TrieNode {
	boolean isEnd;
	TrieNode parentNode;
	String string;
	TrieNode[] letterArray;

  public TrieNode(int start, int length) {
		this.string = "";
		this.letterArray = new TrieNode[26];
	}
~~~
From what I read, what I did was move from a prefix trie to a prefix tree. I'm not sure this qualifies as a compact prefix tree. Some of my reading seemed to indicate that a compact prefix tree should use indexes into the original string instead of copying out the relative segments for storage in tree nodes or into variables for comparison within the code. This made a lot of sense to me and I embarked on a further retrofit of my logic to use this data structure:
~~~
class TrieNode {
	boolean isEnd;
	TrieNode parentNode;
	int stringStart;
	int stringEnd;
	TrieNode[] letterArray;

	public TrieNode(int start, int length) {
		this.stringStart = start;
		this.stringEnd = start + length;
		this.letterArray = new TrieNode[26];
	}
~~~
Getting this code right, using indexes and comparison statements such as the following, was a real pain.
~~~
s.substring(prefixStart, prefixStart + prefixLength).equals(s.substring(currentNode.stringStart, currentNode.stringStart + prefixLength))
~~~
But I got it working and found that is was slightly *slower* than just storing the substrings in the nodes and dealing with variable references to them. (*Dammit!* that was a waste of time... )

What's left? A lot actually. I have found some relatively recent developments in substring processing. Interestingly this work seems to be driven to some extent by genomic research, where extremely long strings of data (DNA sequences) need to be compared to other long strings. But before I attempt that, I had an idea for something that had been nagging at me about this problem.

This problem is a analysis of many cuts of substrings from the same string "s" for a series of queries into a portion of that string. I kept wondering if there is some way of using information across all of these runs. My initial solutions where implemented with each run predominantly separate from the others. A new Trie was built for each query, each subS string, with no information saved from query to query. I finally realized that a prefix tree built from the entire sting "s" contains the tree for each query's substring subS, including all the branches that represent the substrings within subS. The trick was to constrain the evaluation of each prefix, to the portion of the branches that apply to the current query and to ensure that once the characters in a node were counted, they not be counted again. To do this I built a separate tree of nodes visited with a count of the characters counted, that parallels the parts of the prefix tree included when processing a query. The visited nodes are tracked in a separate tree, rather than extending the existing tree. This tree of visited nodes is built during the processing of a query, as the count of possible substrings is collected. I used a separate tree so that I could quickly throw the prior tree of visited nodes away and reinitialize it for each query. For the moment, I kept the TrieNodes that indexes to substrings since I think this may be useful in an upcoming solution. As a result, once again the debugging was more painful than just dealing with substrings stored within the prefix tree. So what did this change get me? Well, it runs a little faster overall but the results from HackerRank still remain the same. Still timing out on test cases #4 and above. 

So now on to my recent findings.

During my research I also found references to an algorithm (Ukkonen) that was supposed to be able to find all unique substrings in O(n) time. This required the construction of a suffix tree, instead of a prefix tree. Not that different from what I'm doing. But also includes suffix links to be created during the tree's constuction. I'm still working on understanding how these links are used. Here are links to some of the best (clearest) of what I've referred to:
- <https://www.cs.helsinki.fi/u/ukkonen/SuffixT1withFigs.pdf>{:target="\_blank"}
- <https://stackoverflow.com/questions/9452701/ukkonens-suffix-tree-algorithm-in-plain-english/9513423#9513423>{:target="\_blank"}
- <https://www.cs.cmu.edu/~ckingsf/bioinfo-lectures/suffixtrees.pdf>{:target="\_blank"}
- <https://en.wikipedia.org/wiki/Suffix_tree#Implementation>{:target="\_blank"}
- <http://homepage.usask.ca/~ctl271/857/suffix_tree.shtml>{:target="\_blank"}

There is also this definition of a suffix array, that indicates that there are advantages over suffix trees:
- <https://en.wikipedia.org/wiki/Suffix_array>{:target="\_blank"}

### Methods:

#### main
The main method uses BufferReader to read the inputs, with a for loop to take in the left and right indexes that identify the portion of the string "s" to be evaluated for each query, which is assigned to subS.


### Data Structures:
