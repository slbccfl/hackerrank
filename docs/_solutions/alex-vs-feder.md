---
layout: solution
title: Alex vs Fedor
description: Given an arbitrary weighted graph, find all the potential spanning trees and all the potential minimal spanning trees so that a fraction can be outputted that will reflect the probably of randomly selected spanning tree, being a minimal spanning tree.
exercise_link: https://www.hackerrank.com/challenges/alex-vs-fedor
---
## Solution:
Incomplete - As of this writing, the latest submittion for this problem yeilds the correct result for Test case #0 and #38, Test cases #1, #4, #9, #10, #36, #37, #39 and #40 get incorrect results. All other Test cases timeout. There appears to be a 4 second limit for the problem.
### Strategy:
This problem takes some interpretation. While the problem is presented as a game, I think it was critical to note that the participant picking a spanning tree to be a minimal spanning tree "... became too lazy to look for minimal spanning trees and now picks some arbitrary spanning tree...". This effectively makes the problem into a probability of an arbitrarily selected spanning tree, being a minimal spanning tree. Being a simple random probability, we simply need to output the fraction of
~~~
Number of all potential minimal spanning trees in the graph / Number of all potential spanning trees in the graph
~~~
Given that all spanning trees in a graph could be identified. Each spanning tree could be summed to a total cost for each spanning tree. The lowest of these values would identify the total cost of a minimal spanning tree. And the number of trees that are of that value could be identified. We would then be in a position to output the required values.

For identifying the number of all spanning trees in a graph I found references to a [Laplacian Matrix](https://en.wikipedia.org/wiki/Laplacian_matrix){:target="\_blank"} which, along with [Kirchhoff's theorem](https://en.wikipedia.org/wiki/Kirchhoff%27s_theorem){:target="\_blank"} could be used to calculate the number of spanning trees. While this would provide the denominator that we need for out fraction. It would not tell us how many of the spanning trees are minimal and does not give us information about the costs of these trees.

I attempted to use [Kruskal's algorithm](https://en.wikipedia.org/wiki/Kruskal%27s_algorithm){:target="\_blank"} to find a minimal spanning tree, so that we at least know what the weight of such a tree would be. But this does not tell us how many minimal spanning trees are in the graph.

My understanding is that every node is connected to a minimal spanning tree by an edge is that has the lowest weight among all edges that connect that node. Krushal's algorithm supports this, as it seeks to identify the minimum weight edge to connect each node when constructing a minimal spanning tree. Therefore, each additional edge connecting a node that is this same minimum weight, among all the connecting edges, would represent a potential alternate spanning tree. Therefore we should be able to count all such alternate edges to identify the number of all potential minimal spanning trees. This calculation should give us the numerator for our probabilistic ratio.
### Methods:
#### main
The main method is almost exclusively dedicated to loading the STDIN data...

### Data Structures:
