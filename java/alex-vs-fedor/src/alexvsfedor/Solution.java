package alexvsfedor;
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
	
	static HashMap<Integer, Boolean>visited;
	static HashMap<Integer, Node> graph;
	
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        Node nodex, nodey;
        LinkedList<Edge> edges;
        int[][] laplacianMatrix = new int[n][n];
        visited = new HashMap<Integer, Boolean>();
        graph = new HashMap<Integer, Node>();
        for(int i = 0; i < m; i++){
            int x = in.nextInt();
            int y = in.nextInt();
            long z = in.nextInt();
            visited.put(x, false);
            visited.put(y, false);
            Edge edge = new Edge(x,y,z);
            addToGraph(x, edge);
            addToGraph(y, edge);
            laplacianMatrix = addEdgeToLaplacianMatrix(laplacianMatrix, x-1,y-1);
        }
        
        System.out.println(countOfMSTs(n) + "/" + countOfSpanningTrees(laplacianMatrix, n));
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
    
    private static int[][] addEdgeToLaplacianMatrix(int[][] laplacianMatrix, int x, int y) {
    	// add to degree cells
    	laplacianMatrix[x][x]++;
    	laplacianMatrix[y][y]++;
    	// set adjacency cells to -1
    	laplacianMatrix[x][y] = -1;
    	laplacianMatrix[y][x] = -1;
    	
    	return laplacianMatrix;
    }
    private static TreeMap<Long, LinkedList<Edge>> queueNodeEdges(TreeMap<Long, LinkedList<Edge>>edgesQueue, Node node) {
    	Integer newNode, connectedNode;
    	LinkedList<Edge> edges = node.adjacencyList;
    	for (Edge edge : edges) {
    		newNode = null;
    		connectedNode = null;   
    		int adjacentNode = 0;
    		if (edge.startNode == node.nodeID) adjacentNode = edge.endNode;
    		if (edge.endNode == node.nodeID) adjacentNode = edge.startNode;
    		if (visited.containsKey(adjacentNode)) continue; 
    		edges = edgesQueue.get(adjacentNode);
    		if (edges == null) edges = new LinkedList<Edge>();
    		edges.add(edge);
    		edgesQueue.put(edge.weight, edges);
    	}
    	return edgesQueue;
    }
    
    private static int countOfMSTs(int n) {
    	int count = 1;
    	// using Prim's algorithm to assemble a MST ...

    	LinkedList<Node> MSTgraph = new LinkedList<Node>();
    	Map.Entry<Integer, Node> entry = graph.entrySet().iterator().next();  // get a single random node form the graph
    	int nodeID = entry.getKey();
    	Node node = entry.getValue();
    	TreeMap<Long, LinkedList<Edge>> edgesQueue = new TreeMap<Long, LinkedList<Edge>>();
    	MSTgraph.add(node);
    	visited.put(nodeID, true);
    	queueNodeEdges(edgesQueue, node);
    	
    	while (!edgesQueue.isEmpty()) { 
        	Edge lowestEdge = null;
        	LinkedList<Edge> lowestEdges;
        	while (lowestEdge == null) {
            	Integer newNode = null;
            	Map.Entry<Long,LinkedList<Edge>> lowestEdgeEntry = edgesQueue.pollFirstEntry();
            	if (lowestEdgeEntry == null) continue; 
        		lowestEdges = lowestEdgeEntry.getValue();
        		for (Edge edge : lowestEdges) {
            		if (!visited.get(lowestEdge.startNode)) {
            			lowestEdge = edge;
            			newNode = lowestEdge.startNode;
            		} else {
            			if (!visited.get(lowestEdge.endNode)) {
	            			lowestEdge = edge;
	            			newNode = lowestEdge.startNode;
            			}
            		}
        		}
        		node = graph.get(newNode);
            	MSTgraph.add(node);
            	visited.put(newNode, true);
            	queueNodeEdges(edgesQueue, node);
        	}
    	}

    	return count;
    }
    
    private static Integer countOfSpanningTrees(int[][] matrix, int n) {
    	if (n == 0) return null;
    	if (n == 1) return 0;
    	if (n == 2) return 1;
		int[][] minorMatrix = new int[n-1][n-1]; 
    	
		minorMatrix = new int[n-1][n-1];
		for (int x = 1; x < n; x++) {
    		for (int y = 1;y < n; y++) {
    			minorMatrix[x-1][y-1] = matrix[x][y];
    		}
		}
		
    	return minorDeterminant(minorMatrix);
    }
    
    static Integer minorDeterminant(int[][] matrix) {
    	int n = matrix.length;
		int[][] minorMatrix; 
		int element = 1;
		int sign = 1;
		int minorDeterminant = 0;
		int determinant = 0;
    	if (n == 2) {
    		int a = matrix[0][0];
    		int b = matrix[0][1];
    		int c = matrix[1][0];
    		int d = matrix[1][1];
        	return quadDeterminant(a,b,c,d);
    	} else {
    		for (int j = 0; j < n; j++) {
    			element = matrix[0][j];
				if (element != 0) {
	    			minorMatrix = new int[n-1][n-1];
	    			if (j % 2 == 0) {
	    				sign = 1;
	    			} else {
	    				sign = -1;
	    			}
	    			for (int x = 1; x < n; x++) {
	    				int z = 0;
	            		for (int y = 0;y+z < n-1; y++) {
	            			if (y == j) {
	            				z = -1;
	            			} else {
	                			minorMatrix[x-1][y+z] = matrix[x][y];
	            			}
	            		}
	    			}
					minorDeterminant = minorDeterminant(minorMatrix);
	        		determinant += sign * element * minorDeterminant;
				}
	    	}
    		return determinant;
	    }
    }
    
    static int quadDeterminant(int a, int b, int c, int d) {
    	return (a * d) - (b * c);
    }
}