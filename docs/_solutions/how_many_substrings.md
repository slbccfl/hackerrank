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

I implemented the Trie data structure to attempt to solve this problem. Additionally I tried to optimize this solution by excluding cases where the same character repeated from the Trie. And instead calculated the number of substrings in those cases. At the moment this attempt to optimize is limited to substrings of a single character repeated. I suspect there are further optimizations possible for repeating substrings beyond a single character. I.e. "abc" repeated 50 times.

I've also tried to think of this as either a recursion problem, where the overall string is broken in half and the respective substrings broken in half and so on to substrings of single characters. And then returning and building up a count from the constituent substrings. But I don't have a vision of how this would work.

I've also tried to look at this as a dynamic programming problem. Evaluating that the impact of the first character in relation to the balance of the string. And then doing a recursive evaluate of each character relative to the balance of the remaining substring. I suspect that an argument could be made that the trie data structure is effectively doing something like this. The potential first characters of all substrings are the letters connected to the root node. The potential second characters are the subsequent set of nodes connected to the first layer of nodes. And so on. But otherwise I am not seeing a solution for this problem using dynamic programming. At least not yet.

For the cases where a single character repeats, it has occurred to me that this should be tracked as a key value pair. With a key for each letter and a count for the value, where the count is the longest length of a repeating series found in the overall string. This would allow counting the occurrences in only the longest such string, recognizing that all other substrings within shorter occurrences are non-unique strings and can be excluded from the final count. If there is an opportunity for further optimization for longer substrings that repeat, I suspect this tracking data structure count be expanded to support those too.
### Methods:

#### main
The main method is almost exclusively dedicated to loading the STDIN data...

### Data Structures:
