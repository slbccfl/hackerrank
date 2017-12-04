---
layout: solution
title: Missing Numbers
description: Reconciliation of two lists of numbers. The required output is a sorted list of numbers missing from the first list that are in the second. Numbers may occur in each list multiple times and each must be considered as a unique occurrence and considered missing if there are not enough occurrences in the first list for all the occurrences in the second list. But the final output should only have one occurrence of each missing number.
exercise_link: https://www.hackerrank.com/challenges/missing-numbers/problem
---
## Solution:
I implemented this using the functionality of Java data structures.  The lists of numbers are collected as a list of integers. Each number in the first list is used to execute a remove method against the second list. The resulting list is converted into a SortedSet to remove duplicates and sort the resulting list. The resulting set is used to build a string for displaying the integers. 
