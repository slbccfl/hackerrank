package dijkstra_shortest_reach_2;

import java.io.*;
import java.util.*;

class Edge {
	long weight;
	int startNode;
	int endNode;
	
	public Edge(int sN, int eN, long w) {
		startNode = sN;
		endNode = eN;
		weight = w;
	}
}

class Node {
	int nodeID;
	long distance;
	LinkedList<Edge> adjacencyList; 
	
	public Node(int r, Edge edge) {
		nodeID = r;
		distance = Long.MAX_VALUE;
		adjacencyList = new LinkedList<Edge>();
		if (edge != null) adjacencyList.add(edge);
	}
}
public class Solution {

    static HashMap<Integer, Node> graph;
	static HashMap<Integer, Boolean>settled;
	static LinkedList<Edge> SPTgraph;
	static int numberOfNodes, numberOfEdges;
	static long startTime;

    public static void main(String[] args) {
    	startTime = System.currentTimeMillis( );
    	System.out.println(new Date());

        Scanner in = new Scanner(System.in);
        int testCases = in.nextInt();
        for(int a0 = 0; a0 < testCases; a0++) {
        	graph = new HashMap<Integer, Node>();
	        numberOfNodes = in.nextInt();
	        numberOfEdges = in.nextInt();
	        settled = new HashMap<Integer, Boolean>();
	        for(int i = 0; i < numberOfEdges; i++){
	            int x = in.nextInt();
	            int y = in.nextInt();
	            settled.put(x, false);
	            settled.put(y, false);
	            long z = in.nextInt();
	            Edge edge = new Edge(x,y,z);
	            addToGraph(x, edge);
	            addToGraph(y, edge);
	        }
	        int startNodeID = in.nextInt();
	        dijkstraSPT(startNodeID);
	        Iterator<Map.Entry<Integer, Node>> it = graph.entrySet().iterator();
	        while (it.hasNext()) {
	        	Map.Entry<Integer, Node> graphEntry = (Map.Entry)it.next();
	        	Node node = graphEntry.getValue();
	        	if (node.distance != 0) {
	        		if (node.distance == Long.MAX_VALUE) node.distance = -1;
	        		System.out.print(node.distance + " ");
	        	}
	        }
	        System.out.println();

	    	long endTime = System.currentTimeMillis( );
	    	System.out.println(new Date());
	    	System.out.println("Nodes: " + numberOfNodes + "   Edges: " + numberOfEdges);
	    	System.out.println("Elapsed Milliseconds: " + (endTime - startTime));
        }

        in.close();
    }

	static void addToGraph(int x, Edge edge) {
		Node nodex;
		if (graph.containsKey(x)) {
		    nodex = graph.get(x);
			nodex.adjacencyList.add(edge);
		} else {
			nodex = new Node(x, edge);
		}
		graph.put(x, nodex);
	}
	
    static void dijkstraSPT(int startNodeID) {
    	// using Dijkstra's algorithm to assemble a Shortest Path Tree for all nodes ...

    	SPTgraph = new LinkedList<Edge>();
    	List<Edge> edgesQueue = new ArrayList<Edge>();
    	Node node = graph.get(startNodeID);
    	node.distance = 0;
    	edgesQueue = queueNodeEdges(edgesQueue, node);
    	Edge lowestEdge;
    	
    	while (settled.containsValue(false) && !edgesQueue.isEmpty()) { 
    		lowestEdge = findLowestEdge(edgesQueue);
        	if (lowestEdge != null) {
//        		long endTime = System.currentTimeMillis( );
//        		System.out.println("Elapsed Milliseconds: " + (endTime - startTime) + "  --  " + lowestEdge.startNode + " to " + lowestEdge.endNode + " distance: " + lowestEdge.weight);
        		Node newNode = graph.get(lowestEdge.endNode);
        		edgesQueue = queueNodeEdges(edgesQueue, newNode);
        	}
    	}
    	return;
    }
    static List<Edge> queueNodeEdges(List<Edge> startingEdgesQueue, Node node) {
    	settled.put(node.nodeID, true);
    	List<Edge> edgesQueue = new ArrayList<Edge>();
    	for (Edge edge : startingEdgesQueue) {
    		if (!settled.get(edge.endNode)) {
    			edgesQueue.add(edge);
//    		} else {
//    			long endTime = System.currentTimeMillis( );
//        		System.out.println("Elapsed Milliseconds: " + (endTime - startTime) + "  --  " + edge.startNode + " to " + edge.endNode + " distance: " + edge.weight);
    		}
    	}
    	LinkedList<Edge> edges = node.adjacencyList;
    	for (Edge edge : edges) {
    		Integer adjacentNodeID = null;
    		if (edge.startNode == node.nodeID) adjacentNodeID = edge.endNode;
    		if (edge.endNode == node.nodeID) adjacentNodeID = edge.startNode;
    		if (!settled.get(adjacentNodeID)) {
        		edge.endNode = adjacentNodeID;
        		edge.startNode = node.nodeID;
    			edgesQueue.add(edge);
    		}
    	}
    	return edgesQueue;
    }
    static Edge findLowestEdge(List<Edge> edgesQueue) {
    	long lowestDistance = Long.MAX_VALUE;
    	Edge lowestEdge = null;
    	for (Edge edge : edgesQueue) { 
    		long nodeDistance = graph.get(edge.startNode).distance + edge.weight;
    		if (nodeDistance < lowestDistance && !settled.get(edge.endNode)) {
    			lowestEdge = edge;
    			lowestDistance = nodeDistance;
    		}
    	}
    	if (lowestEdge != null) {
	    	SPTgraph.add(lowestEdge);
	    	graph.get(lowestEdge.endNode).distance = lowestDistance;
    	}
    	return lowestEdge;
    }
}
