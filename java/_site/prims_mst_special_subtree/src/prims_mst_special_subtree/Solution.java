package prims_mst_special_subtree;

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
	LinkedList<Edge> adjacencyList; 
	
	public Node(int r, Edge edge) {
		nodeID = r;
		adjacencyList = new LinkedList<Edge>();
		if (edge != null) adjacencyList.add(edge);
	}
}
public class Solution {

    static HashMap<Integer, Node> graph = new HashMap<Integer, Node>();
	static HashMap<Integer, Boolean>visited;
	static LinkedList<Edge> MSTgraph;
	static int numberOfNodes, numberOfEdges;

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        numberOfNodes = in.nextInt();
        numberOfEdges = in.nextInt();
        Node nodex, nodey;
        LinkedList<Edge> edges;
        visited = new HashMap<Integer, Boolean>();
        for (int i = 0; i < numberOfNodes; i++) {
            visited.put(i + 1, false);
        }
        for(int i = 0; i < numberOfEdges; i++){
            int x = in.nextInt();
            int y = in.nextInt();
            long z = in.nextInt();
            Edge edge = new Edge(x,y,z);
            addToGraph(x, edge);
            addToGraph(y, edge);
        }
        int startNodeID = in.nextInt();
        primsMST(startNodeID);
        int MSTweight = 0;
        for (Edge edge : MSTgraph) {
        	MSTweight += edge.weight;
        }

        System.out.println(MSTweight);
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
	
    static LinkedList<Edge> primsMST(int startNodeID) {
    	// using Prim's algorithm to assemble a MST ...

    	MSTgraph = new LinkedList<Edge>();
    	TreeMap<Long, LinkedList<Edge>> edgesQueue = new TreeMap<Long, LinkedList<Edge>>();
    	Node node = graph.get(startNodeID);
    	edgesQueue = queueNodeEdges(edgesQueue, node);
    	LinkedList<Edge> lowestEdges;
    	
    	while (!edgesQueue.isEmpty() || MSTgraph.size() < numberOfNodes - 1) { 
        	Edge lowestEdge = null;
        	Node newNode = null;
        	Map.Entry<Long,LinkedList<Edge>> lowestEdgeEntry = edgesQueue.pollFirstEntry();
        	if (lowestEdgeEntry == null) continue; 
    		lowestEdges = lowestEdgeEntry.getValue();
    		Edge edge = lowestEdges.removeFirst();
    		if (!lowestEdges.isEmpty()) edgesQueue.put(lowestEdgeEntry.getKey(), lowestEdges);
    		if (!visited.get(edge.startNode)) {
    			lowestEdge = edge;
    			newNode = graph.get(lowestEdge.startNode);
    		} else {
    			if (!visited.get(edge.endNode)) {
        			lowestEdge = edge;
        			newNode = graph.get(lowestEdge.endNode);
    			}
    		}
    		if (newNode != null) edgesQueue = queueNodeEdges(edgesQueue, newNode);
    		if (lowestEdge != null) MSTgraph.add(lowestEdge);
    	}
    	return MSTgraph;
    }
    static TreeMap<Long, LinkedList<Edge>> queueNodeEdges(TreeMap<Long, LinkedList<Edge>>edgesQueue, Node node) {
    	Integer newNode, connectedNode;
    	visited.put(node.nodeID, true);
    	LinkedList<Edge> edges = node.adjacencyList;
    	LinkedList<Edge> weightEdges;
    	for (Edge edge : edges) {
    		newNode = null;
    		connectedNode = null;   
    		Integer adjacentNodeID = null;
    		if (edge.startNode == node.nodeID) adjacentNodeID = edge.endNode;
    		if (edge.endNode == node.nodeID) adjacentNodeID = edge.startNode;
    		if (visited.get(adjacentNodeID)) continue; 
    		weightEdges = edgesQueue.get(edge.weight);
    		if (weightEdges == null) weightEdges = new LinkedList<Edge>();
    		weightEdges.add(edge);
    		edgesQueue.put(edge.weight, weightEdges);
    	}
    	return edgesQueue;
    }
}