---
layout: solution
title: Permuting Two Arrays
description: Find a permutation of two arrays so that adding pairs of elements from each array is >= a given value (k) for each set of arrays.
exercise_link: https://www.hackerrank.com/challenges/two-arrays/problem
---
## Solution:
My solution was to sort both arrays, the first array in ascending order and the second array in descending order. Then traverse the arrays, adding pairs of values in sequence. As soon as a pair sums to less then the value k, a "NO" can be returned. If the traversal of the arrays completes, a "YES" is returned, indicating that there is a valid permutation has been found. 
