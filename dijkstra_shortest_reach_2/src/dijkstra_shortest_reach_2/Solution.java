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
	static long startTime = System.currentTimeMillis();;

    public static void main(String[] args) {
    	timeStamp("");

    	BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
    	Integer testCases = null;
		try {
			testCases = Integer.parseInt(br.readLine());
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	String line = null;
        for(int a0 = 0; a0 < testCases; a0++) {
        	graph = new HashMap<Integer, Node>();
        	try {
				line = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
	        numberOfNodes = Integer.parseInt(line.split("\\s")[0]);
	        numberOfEdges = Integer.parseInt(line.split("\\s")[1]);
	        settled = new HashMap<Integer, Boolean>();
	        for(int i = 0; i < numberOfEdges; i++){
	        	try {
					line = br.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
		        int x = Integer.parseInt(line.split("\\s")[0]);
		        int y = Integer.parseInt(line.split("\\s")[1]);
	            settled.put(x, false);
	            settled.put(y, false);
		        long z = Long.parseLong(line.split("\\s")[2]);
	            Edge edge = new Edge(x,y,z);
	            addToGraph(x, edge);
	            addToGraph(y, edge);
	        }

	    	Integer startNodeID = null;
			try {
				startNodeID = Integer.parseInt(br.readLine());
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	        
	    	timeStamp("Test case " + a0 + " Loaded  --  Nodes: " + numberOfNodes + "   Edges: " + numberOfEdges);
	    	
	        dijkstraSPT(startNodeID);
	        
	    	timeStamp("Output");
	        
	        Iterator<Map.Entry<Integer, Node>> it = graph.entrySet().iterator();
	        StringBuilder sb = new StringBuilder();
	        while (it.hasNext()) {
	        	Map.Entry<Integer, Node> graphEntry = (Map.Entry)it.next();
	        	Node node = graphEntry.getValue();
	        	if (node.distance != 0) {
	        		if (node.distance == Long.MAX_VALUE) node.distance = -1;
	        		sb.append(node.distance);
	        		sb.append(" ");
	        	}
	        }
	        System.out.println(sb);
	        
//	    	timeStamp("");
        }
        try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        timeStamp("");
    }

	private static void timeStamp(String title) {
		long endTime = System.currentTimeMillis( );
		System.out.println(new Date());
		System.out.println("Elapsed Milliseconds: " + (endTime - startTime));
		System.out.println(title);
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
    	List<Integer> nodesQueue = new ArrayList<Integer>();
    	Node node = graph.get(startNodeID);
    	node.distance = 0;
    	nodesQueue = queueNodeEdges(nodesQueue, startNodeID);
    	Edge lowestEdge;
    	
    	while (settled.containsValue(false) && !nodesQueue.isEmpty()) { 
    		lowestEdge = findLowestEdge(nodesQueue);
        	if (lowestEdge != null) {
        		nodesQueue = queueNodeEdges(nodesQueue, lowestEdge.endNode);
        	}
        	timeStamp("SPTgraph size:" + SPTgraph.size() + " - queue size: " + nodesQueue.size());
    	}
    	return;
    }
    static List<Integer> queueNodeEdges(List<Integer> nodesQueue, Integer nodeID) {
//    	timeStamp("queueNodeEdges in");
    	settled.put(nodeID, true);
    	if (nodesQueue.contains(nodeID)) nodesQueue.remove(nodeID);
    	Node node, adjacentNode;
    	node = graph.get(nodeID);
    	LinkedList<Edge> edges = node.adjacencyList;
    	for (Edge edge : edges) {
    		Integer adjacentNodeID = null;
    		if (edge.startNode == nodeID) adjacentNodeID = edge.endNode;
    		if (edge.endNode == nodeID) adjacentNodeID = edge.startNode;
    		if (!settled.get(adjacentNodeID)) {
    			if (!nodesQueue.contains(adjacentNodeID)) nodesQueue.add(adjacentNodeID);
    			adjacentNode = graph.get(adjacentNodeID);
    			adjacentNode.distance = node.distance + edge.weight;
    			
    		}
    	}
//    	timeStamp("queueNodeEdges out - queue size: " + nodesQueue.size());
    	return nodesQueue;
    }
    static Edge findLowestEdge(List<Integer> nodesQueue) {
//    	timeStamp("findLowestEdge in");
    	long lowestDistance = Long.MAX_VALUE;
    	Edge lowestEdge = null;
    	Node node;
    	for (int nodeID : nodesQueue) { 
    		node = graph.get(nodeID);
    		for (Edge edge : node.adjacencyList) {
        		Integer adjacentNodeID = null;
        		if (edge.startNode == nodeID) adjacentNodeID = edge.endNode;
        		if (edge.endNode == nodeID) adjacentNodeID = edge.startNode;
        		if (settled.get(adjacentNodeID)) {
            		long nodeDistance = graph.get(adjacentNodeID).distance + edge.weight;
            		if (nodeDistance < lowestDistance) {
            			edge.startNode = adjacentNodeID;
            			edge.endNode = nodeID;
            			lowestEdge = edge;
            			lowestDistance = nodeDistance;
            		}
        		}
    		}
    	}
    	if (lowestEdge != null) {
	    	SPTgraph.add(lowestEdge);
	    	node = graph.get(lowestEdge.endNode);
	    	node.distance = lowestDistance;
    	}
//    	timeStamp("findLowestEdge out --" + lowestEdge.startNode + " to " + lowestEdge.endNode + " w:" + lowestEdge.weight);
    	return lowestEdge;
    }
    
}
