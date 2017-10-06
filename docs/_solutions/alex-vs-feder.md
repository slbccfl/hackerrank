---
layout: solution
title: Alex vs Fedor
description: Given an arbitrary weighted graph, find all the potential spanning trees and all the potential minimal spanning trees so that a fraction can be outputted that will reflect the probably of randomly selected spanning tree, being a minimal spanning tree.
exercise_link: https://www.hackerrank.com/challenges/alex-vs-fedor
---
## Solution:
Incomplete - ...
### Strategy:
This problem takes some interpretation. While the problem is presented as a game, I think it was critical to note that the participant picking a spanning tree to be a minimal spanning tree "... became too lazy to look for minimal spanning trees and now picks some arbitrary spanning tree...". This effectively makes the problem into a probability of an arbitrarily selected spanning tree, being a minimal spanning tree. Being a simple random probability, we simply need to output the faction of
~~~
Number of all minimal spanning trees in the graph / Number of all spanning trees in the graph
~~~
Given that all spanning trees in a graph could be identified. Each spanning tree could be summed to a total weight for each spanning tree. The lowest of these values would identify the total weight of a minimal spanning tree. And the number of trees that are of that value could be identified. We would then be in a position to output the required values.
### Methods:
For identifying the number of all spanning trees in a graph I found references to a [Laplacian Matrix](https://en.wikipedia.org/wiki/Laplacian_matrix){:target="\_blank"} which, along with [Kirchhoff's theorem](https://en.wikipedia.org/wiki/Kirchhoff%27s_theorem){:target="\_blank"} could be used to calculate the number of spanning trees. While this would provide the denominator that we need. It would not tell us how many of the spanning trees are minimal and does not give us information about the weights of these trees.
I attempted to use [Kruskal's algorithm](https://en.wikipedia.org/wiki/Kruskal%27s_algorithm){:target="\_blank"} to find a minimal spanning tree, so that we at least know what the weight of such a tree would be.
But this does not tell us how many minimal spanning trees are in the graph.
#### main
The main method is almost exclusively dedicated to loading the STDIN data...

### Data Structures:
