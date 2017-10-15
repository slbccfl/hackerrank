---
layout: solution
title: Alex vs Fedor
description: Given an arbitrary weighted graph, find all the potential spanning trees and all the potential minimal spanning trees so that a fraction can be outputted that will reflect the probably of randomly selected spanning tree, being a minimal spanning tree.
exercise_link: https://www.hackerrank.com/challenges/alex-vs-fedor
---
## Solution:
Incomplete - As of this writing, the latest submission for this problem yields the correct result for Test case #0 and #38, incorrect results for Test cases #1, #4, #9, #10, #36, #37, #39 and #40. All other Test cases, a vast majority, timeout. There appears to be a 4 second limit for the problem.
### Strategy:
This problem takes some interpretation. While the problem is presented as a game, I think it was critical to note that the participant picking a spanning tree to be a minimal spanning tree "... became too lazy to look for minimal spanning trees and now picks some arbitrary spanning tree...". This effectively makes the problem into a probability of an arbitrarily selected spanning tree, being a minimal spanning tree. Being a simple random probability, we simply need to output the fraction of
~~~
m / a

Where
m = Number of all potential minimal spanning trees in the graph
a = Number of all potential spanning trees in the graph
~~~
Given that all spanning trees in a graph could be identified. Each spanning tree could be summed to a total cost for each spanning tree. The lowest of these values would identify the total cost of a minimal spanning tree. And the number of trees that are of that value could be identified. We would then be in a position to output the required values.

For identifying the number of all spanning trees in a graph I found references to a [Laplacian Matrix](https://en.wikipedia.org/wiki/Laplacian_matrix){:target="\_blank"} which, along with [Kirchhoff's theorem](https://en.wikipedia.org/wiki/Kirchhoff%27s_theorem){:target="\_blank"} could be used to calculate the number of spanning trees. While this would provide the denominator that we need for our fraction. It would not tell us how many of the spanning trees are minimal and does not give us information about the costs of these trees.

I attempted to use [Kruskal's algorithm](https://en.wikipedia.org/wiki/Kruskal%27s_algorithm){:target="\_blank"} to find a minimal spanning tree, so that we at least know what the weight of such a tree would be. But this does not tell us how many minimal spanning trees are in the graph.

My understanding is that every node is connected to a minimal spanning tree by an edge is that has the lowest weight among all edges that connect that node. Krushal's algorithm supports this, as it seeks to identify the minimum weight edge to connect each node when constructing a minimal spanning tree. Therefore, each additional edge connecting a node that is this same minimum weight, among all the connecting edges, would represent a potential alternate spanning tree. Therefore we should be able to count all such alternate edges to identify the number of all potential minimal spanning trees. This calculation should give us the numerator for our probabilistic ratio.
### Data Structures:
#### Edge class
Edge is defined as a class to hold information about an edge in a graph. It contains only the weight of the edge and start and end nodes.
#### Node
Node is defined as a class to hold information about a node, or vertex, in a graph. It contains the nodeID (an integer) and a LinkedList of Edge objects that lead to adjacence nodes. When a node in instantiated, an edge may be passed to constructor to be included in the initial LinkedList created for the node. Usually this would be the first edge connecting the node to the tree, or the only edge connecting the node in the case of an minimal spanning tree.
#### visited
A package level HashMap, visited, to track the nodes visited while processing Kruskal's aglorithm to find an MST.
### Methods in Solution class:
#### main
The main method is almost exclusively dedicated to loading the STDIN data, including taking in the X, Y and Z parameters for each edge in the graph to be evaluated. A graph HashMap is initialized to hold a representation of the graph defined by the inputs. A loop in main sets false for each of these nodes in visited and calls the method addToGraph to add each of the nodes along with the edge connecting each. (Both calls are needed to take into account that all edges are bidirectional.) Last within this loop for each edge inputed, is a call to the method addEdgeToLaplacianMatrix to ...
The last statement of main outputs a string with this required answer for each graph. countOfMSTs is call to return the numerator and countOfSpanningTrees is called to return the denominator.
#### addToGraph
This method takes
