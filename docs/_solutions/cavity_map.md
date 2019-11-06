---
layout: solution
title: Cavity Map
description: Display a grid with "X" for each cell where each adjacent cell has a lower value (depth).
exercise_link: https://www.hackerrank.com/challenges/mark-and-toys/problem
---
## Solution:
Traverse each cell and check the adjacent cells values in comparison to the current cell. Set a boolean array to indicate those that are "cavities".
Since cells not on the borders of the grid cannot be "cavities" I traversed only those inside the borders.
A boolean array is used to track cavities because altering the original grid would interfere with subsequent cell evaluations. 
After the relevant cells have been evaluated, output the original grid, character by character. But check if the cell position is "true" on the "cavities" boolean array. If so, output an "X" instead.
