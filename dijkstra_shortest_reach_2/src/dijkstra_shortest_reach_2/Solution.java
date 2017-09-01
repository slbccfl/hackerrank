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
	HashMap<Integer, Edge> adjacencyList; 
	
	public Node(int r, Edge edge) {
		nodeID = r;
		distance = Long.MAX_VALUE;
		adjacencyList = new HashMap<Integer, Edge>();
		Integer adjacentNode = null;
		if (edge.startNode == nodeID) adjacentNode = edge.endNode;
		if (edge.endNode == nodeID) adjacentNode = edge.startNode;
		if (edge != null) adjacencyList.put(adjacentNode, edge);
	}
	
}
public class Solution {

    static HashMap<Integer, Node> graph;
	static HashMap<Integer, Boolean>settled;
//	static LinkedList<Edge> SPTgraph;
	static int numberOfNodes, numberOfEdges;
	static long startTime = System.currentTimeMillis();


//	public static <Long, LinkedList<Integer>> extends Comparable<Node>> Map<Long, Integer> sortByDistance(final Map<Long, Integer> map) {
//		Comparator<Long> distanceComparator = new Comparator<Long>() {
//		  public int compare(Long d1, Long d2) {
//		    int compare = map.get(d1).compareTo(map.get(d2));
//		    if (compare == 0) 
//		      return 1;
//		    else 
//		      return compare;
//		  }
//		};
//		
//		Map<Long, LinkedList<Integer>> sortedByDistance = new TreeMap<Long, LinkedList<Integer>>(distanceComparator);
//		sortedByDistance.putAll(map);
//		return sortedByDistance;
//	}

    public static void main(String[] args) {
//    	timeStamp("");

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
	        
//	    	timeStamp("Test case " + a0 + " Loaded  --  Nodes: " + numberOfNodes + "   Edges: " + numberOfEdges);
	    	
	        dijkstraSPT(startNodeID);
	        
//	    	timeStamp("Output");
	        
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
        
//        timeStamp("");
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
			Integer adjacentNode = null;
			if (edge.startNode == x) adjacentNode = edge.endNode;
			if (edge.endNode == x) adjacentNode = edge.startNode;
			Edge priorEdge = nodex.adjacencyList.get(adjacentNode);
			if (priorEdge == null || edge.weight < priorEdge.weight) {
				nodex.adjacencyList.put(adjacentNode, edge);
			}
		} else {
			nodex = new Node(x, edge);
		}
		graph.put(x, nodex);
	}
	
    static void dijkstraSPT(int startNodeID) {
    	// using Dijkstra's algorithm to assemble a Shortest Path Tree for all nodes ...

//    	SPTgraph = new LinkedList<Edge>();
    	TreeMap<Long, LinkedList<Integer>>nodesQueue = new TreeMap<Long, LinkedList<Integer>>();
    	Node node = graph.get(startNodeID);
    	node.distance = 0;
    	nodesQueue = queueNodeEdges(nodesQueue, startNodeID);
//    	Edge lowestEdge;
    	Integer lowestNodeID;
    	
    	while (settled.containsValue(false) && !nodesQueue.isEmpty()) { 
    		lowestNodeID = findLowestEdge(nodesQueue);
        	if (lowestNodeID != null) {
        		nodesQueue = queueNodeEdges(nodesQueue, lowestNodeID);
        	}
//        	timeStamp("Queue size: " + nodesQueue.size());
    	}
    	return;
    }
    static TreeMap<Long, LinkedList<Integer>> queueNodeEdges(TreeMap<Long, LinkedList<Integer>> nodesQueue, Integer nodeID) {
//    	timeStamp("queueNodeEdges in");
    	settled.put(nodeID, true);
    	Node node, adjacentNode;
    	node = graph.get(nodeID);
//    	if (nodesQueue.containsValue(node)) nodesQueue.remove(node);
    	HashMap<Integer, Edge> edges = node.adjacencyList;
    	Iterator<Map.Entry<Integer, Edge>> edgeIterator = edges.entrySet().iterator();
    	while (edgeIterator.hasNext()) {
    		Map.Entry<Integer, Edge> edgeEntry = (Map.Entry<Integer, Edge>)edgeIterator.next();
    		Edge edge = edgeEntry.getValue();
    		Integer adjacentNodeID = null;
    		if (edge.startNode == nodeID) adjacentNodeID = edge.endNode;
    		if (edge.endNode == nodeID) adjacentNodeID = edge.startNode;
    		if (!settled.get(adjacentNodeID)) {
    			adjacentNode = graph.get(adjacentNodeID);
    			long distance = node.distance + edge.weight;
    			if (adjacentNode.distance > distance) {
        			adjacentNode.distance = distance;
        			LinkedList<Integer> nodeList  = nodesQueue.get(distance);
        			if (nodeList == null) nodeList = new LinkedList<Integer>();
        			nodeList.add(adjacentNodeID);
//        			if (!nodesQueue.containsValue(adjacentNode)) {
//        				nodesQueue.remove(adjacentNode);
        				nodesQueue.put(distance, nodeList);
//        			}
    			}
    			
    		}
    	}
//    	timeStamp("queueNodeEdges out - queue size: " + nodesQueue.size());
    	return nodesQueue;
    }
    static Integer findLowestEdge(TreeMap<Long, LinkedList<Integer>> nodesQueue) {
//    	timeStamp("findLowestEdge in");
    	long lowestDistance = Long.MAX_VALUE;
    	Edge lowestEdge = null;
    	LinkedList<Integer> lowestNodeList = null;
    	Integer lowestNodeID = null;
    	boolean lowestNodeSettled = true;
    	while (lowestNodeSettled) {
        	Map.Entry<Long, LinkedList<Integer>> nodeQueueEntry = nodesQueue.pollFirstEntry();
        	if (nodeQueueEntry == null) {
        		lowestNodeSettled = false;
        	} else {
	        	lowestDistance = nodeQueueEntry.getKey();
        		lowestNodeList = nodeQueueEntry.getValue();
        		lowestNodeID = lowestNodeList.poll();
        		if (lowestNodeList.size() != 0) {
        			nodesQueue.put(lowestDistance, lowestNodeList);
        			if (lowestNodeID != null) lowestNodeSettled = settled.get(lowestNodeID);
        		} else {
            		lowestNodeSettled = false;
        		}
        	}
    	}
    	
//    	for (int nodeID : nodesQueue) { 
//    		node = graph.get(nodeID);
//        	HashMap<Integer, Edge> edges = node.adjacencyList;
//        	Iterator<Map.Entry<Integer, Edge>> edgeIterator = edges.entrySet().iterator();
//        	while (edgeIterator.hasNext()) {
//        		Map.Entry<Integer, Edge> edgeEntry = (Map.Entry<Integer, Edge>)edgeIterator.next();
//        		Edge edge = edgeEntry.getValue();
//        		Integer adjacentNodeID = null;
//        		if (edge.startNode == nodeID) adjacentNodeID = edge.endNode;
//        		if (edge.endNode == nodeID) adjacentNodeID = edge.startNode;
//        		if (settled.get(adjacentNodeID)) {
//            		long nodeDistance = graph.get(adjacentNodeID).distance + edge.weight;
//            		if (nodeDistance < lowestDistance) {
//            			edge.startNode = adjacentNodeID;
//            			edge.endNode = nodeID;
//            			lowestEdge = edge;
//            			lowestDistance = nodeDistance;
//            		}
//        		}
//    		}
//    	}
//    	if (lowestEdge != null) {
//	    	SPTgraph.add(lowestEdge);
//	    	node = graph.get(lowestEdge.endNode);
//	    	node.distance = lowestDistance;
////    	}
//    	if (lowestNode != null) {
//	    	node = graph.get(lowestNode);
//	    	node.distance = lowestDistance;
//    	}
//    	timeStamp("findLowestEdge out --" + lowestEdge.startNode + " to " + lowestEdge.endNode + " w:" + lowestEdge.weight);
    	return lowestNodeID;
    }
    
}
