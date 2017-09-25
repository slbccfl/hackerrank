---
layout: solution
title: Dijkstra Shortest Reach 2
description: An implementation of the Dijkstra algorithm for finding the the shortest distance path from a starting position node to all of the other nodes in a graph.
exercise_link: https://www.hackerrank.com/challenges/dijkstrashortreach
---
## Solution:
### Strategy:
This solution implements the Dijkstra algorithm for finding the the path.<br>
See the following references for descriptions and graphics  of Dijkstra's algorithm.<br>
[Wikipedia](https://en.wikipedia.org/wiki/Dijkstra's_algorithm)<br>
[GeeksforGeeks](http://www.geeksforgeeks.org/greedy-algorithms-set-6-dijkstras-shortest-path-algorithm/)
### Classes outside the Solution class:
#### Edge
A class defined to represent an edge in the graph, includes attributes for weight, starting node and ending node.
#### Node
A class defined to represent a node in the graph. That attributes include the nodeID, as defined by the input, distance from the starting point in the graph, and an adjacencyList of the egdes that link to this node. When a node is instantiated distance is set to Long.MAX_VALUE and a new HashMap is instantiated for the adjacencyList. An edge is optionally passed to the constructor for the node. If an edge is included, the node being instantiated is identified as the starting or ending node of the edge and the other node is set as the adjacentNode and put in the adjacencyList for the node.
### Solution Class fields:
#### graph
A HashMap that is the internal representation of the graph.
#### settled
A HashMap used to track what nodes have been settled per the Dijkstra algorithm.
#### numberOfNodes and numberOfEdges
The initial inputs for the  current run.
#### startTime
This field is used for timing the algorithm and is not directly relevant to the solution.
### Methods:
#### main
The main method acts as something of a wrapper for the primary method in this solution, dijkstraSPT. Loading the STDIN data before executing that method, and then constructing and outputting the STDOUT string after. One thing to note in the solution is the use of BufferedReader to read STDIN in an attempt to gain efficiency. Similarly, StringBuilder is used to compose the STDOUT string, also in an attempt to gain efficiency there.
#### timeStamp
The timeStamp method is a method only used during development to evaluate efforts to optimize the code efficiency.
#### addToGraph
This method is used to add edges to the internal representation of the graph during the initial load of data from STDIN by the main method.
#### dijkstraSPT
This method uses a TreeMap (nodesQueue) to collect and hold the nodes to be evaluated on the frontier of analysis.
queueNodeEdges is initilized, using queueNodeEdges to load the startNode. And then a loop runs findLowestEdge and queueNodeEdges until there are no more unsettled nodes no more nodes in nodesQueue to process. At this point the Dijkstra algorithm has been completed.
#### queueNodeEdges
Broadly, this method takes in and returns the nodesQueue, also taking in a node to be added to this queue. But it's more complicated than that. The method iterates through each of the edges to link to the node being enqueued. For each adjacent node that has not been previously settled, the distance of the current node + the weight of the connecting edge is compared to the distance the adjacent node currently has and updates that distance if the new value is lower. And the adjacent node is enqueued in nodesQueue under the new distance value.
#### findLowestEdge
### Data Structures:
#### TreeMap<Long, LinkedList<Integer>>nodesQueue
This structure is used to store the nodes to be evaluated along the frontier of analysis. The Long key is used to store sets of nodes by their distance from the startNode. The LinkedList then allows for multiple nodes having the same distance from the startNode.
## TestSolution - junit testing method
### Methods:
#### testAddToGraph
#### testQueueNodeEdges
#### testDijkstraSPT
