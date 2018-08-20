package kruskal_MST_really_special_subtree;

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
	static List<Set<Integer>> visited;
	static LinkedList<Edge> MSTgraph;
	static TreeMap<Long, LinkedList<Edge>> edgesQueue = new TreeMap<Long, LinkedList<Edge>>() ;
	static int numberOfNodes, numberOfEdges;

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        numberOfNodes = in.nextInt();
        numberOfEdges = in.nextInt();
        visited = new ArrayList<Set<Integer>>();
        for (int i = 0; i < numberOfNodes+2; i++) {
    		Set<Integer> s = new HashSet<Integer>();
        	s.add(i);
            Solution.visited.add(i,s);
        }
        long currentEdgeWeight = -1;
        LinkedList<Edge> currentEdges;
        for(int i = 0; i < numberOfEdges; i++){
            int x = in.nextInt();
            int y = in.nextInt();
            long z = in.nextInt();
            Edge edge = new Edge(x,y,z);
            if (edgesQueue.containsKey(z)) {
            	currentEdges = edgesQueue.get(z);
            } else {
            	currentEdges = new LinkedList<Edge>();
            }
            currentEdges.add(edge);
            edgesQueue.put(z, currentEdges);
        }
        kruskalsMST();
        int MSTweight = 0;
        for (Edge edge : MSTgraph) {
        	MSTweight += edge.weight;
        }

        System.out.println(MSTweight);
        in.close();
    }
	
    static void kruskalsMST() {
    	// using Kruskal's algorithm to assemble a MST ...

    	MSTgraph = new LinkedList<Edge>();
    	LinkedList<Edge> lowestEdges;
    	
    	Map.Entry<Long,LinkedList<Edge>> lowestEdgeEntry = edgesQueue.pollFirstEntry();
    	while (!edgesQueue.isEmpty() || MSTgraph.size() < numberOfNodes - 1) { 
        	Edge lowestEdge = null;
        	if (lowestEdgeEntry == null) continue; 
    		lowestEdges = lowestEdgeEntry.getValue();
    		Set<Integer> treeSet;
    		
    		for (Edge edge : lowestEdges) {
    			int lowerNode = Math.min(edge.startNode,edge.endNode);
    			int higherNode = Math.max(edge.startNode,edge.endNode);
    			if (!visited.get(lowerNode).contains(higherNode)) {
    				MSTgraph.add(edge);
    				treeSet = visited.get(lowerNode);
    				treeSet.addAll(visited.get(higherNode));
    				for (int node : treeSet) {
    					visited.set(node, treeSet);
    				}
    			}
    		}
        	lowestEdgeEntry = edgesQueue.pollFirstEntry();
    	}
    	return;
    }
}