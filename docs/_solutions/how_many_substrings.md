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
### Methods:
I implemented the Trie data structure to attempt to solve this problem. Additionally I tried to optimize this solution by excluding cases where the same character repeated from the Trie. And instead calculated the number of substrings in those cases.
#### main
The main method is almost exclusively dedicated to loading the STDIN data...

### Data Structures:
