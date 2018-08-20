---
layout: solution
title: Sherlock and Array
description: Find an element in an array where the sum of the elements to the left is equal to the elements to the right.
exercise_link: https://www.hackerrank.com/challenges/sherlock-and-array/problem
---
## Solution:
I traversed the array left to right creating an array that accumulates the sum of the current and all prior values. Here is an example of a given array "a" and array I created "cumA"
---------------------
|  1 |  2 |  3 |  3 |
---------------------
|  1 |  3 |  6 |  9 |
---------------------
I then traverse back down the array a accumulating a "rightValue" the next cumA cell (a[i - 1]) is >= to the rightValue.
A final check for whether the left and right values are equal to deterine if "YES" or "NO" will be returned.
