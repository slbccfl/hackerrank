---
layout: solution
title: Mark and Toys
description: Count number of elements in an array that sum to a value less then k.
exercise_link: https://www.hackerrank.com/challenges/mark-and-toys/problem
---
## Solution:
I sorted the array and then subtract each element from k, in order of increasing value, until k < 0. Then break out of the loop and return the value i, the number of elements that could be subtracted from k. 
