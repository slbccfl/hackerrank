---
layout: solution
title: Jim and the Orders
description: Sort hamburger orders in the order in which they will be completed.
exercise_link: https://www.hackerrank.com/challenges/jim-and-the-orders/problem
---
## Solution:
I generate a HashMap with i (the sequence in which the orders are inputed) as the key and value is the sum of time t(i) the order arrives + the time the order takes to complete d(i). I then sort the HashMap by values and then output the key for the ordered map.entrysets. 
