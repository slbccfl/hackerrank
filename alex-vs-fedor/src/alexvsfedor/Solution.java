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
	
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        LinkedList<Edge> edges;
        int[][] laplacianMatrix = new int[n][n];
        HashMap<Integer, Boolean>visited = new HashMap<Integer, Boolean>();
        TreeMap<Long, LinkedList<Edge>> graph = new TreeMap<Long, LinkedList<Edge>>();
        for(int i = 0; i < m; i++){
            int x = in.nextInt() - 1;
            int y = in.nextInt() - 1;
            long z = in.nextInt();
            visited.put(x, false);
            visited.put(y, false);
            edges = graph.get(z);
            if (edges == null) edges = new LinkedList<Edge>();
            edges.add(new Edge(x,y,z));
            graph.put(z, edges);
            addEdgeToLaplacianMatrix(laplacianMatrix, x,y);
        }
        
        System.out.println(countOfMSTs(graph, visited) + "/" + countOfSpanningTrees(laplacianMatrix));
    }
    
    private static void addEdgeToLaplacianMatrix(int[][] laplacianMatrix, int x, int y) {
    	// add to degree cells
    	laplacianMatrix[x][x]++;
    	laplacianMatrix[y][y]++;
    	// set adjacency cells to -1
    	laplacianMatrix[x][y] = -1;
    	laplacianMatrix[y][x] = -1;
    	
    }
    
    private static int countOfMSTs(TreeMap<Long, LinkedList<Edge>> graph, HashMap<Integer, Boolean>visited) {
    	// using Kruskal's algorithm to assemble a MST and then collect a count of possible MST's
    	int count = 1; // initialize to 1 on the assumption that there is at least one MST
    	LinkedList<Edge> edges, nodeEdges;
    	LinkedList<Node> MSTgraph = new LinkedList<Node>();
    	long weight;
    	long currentWeight = 0;
    	Node node; 
    	Integer newNode, otherNode;
    	boolean firstEdge = true;
        for (Map.Entry<Long, LinkedList<Edge>> entry : graph.entrySet()) {
        	if (!visited.containsValue(false)) break;
        	weight = entry.getKey();
        	edges = entry.getValue();
        	for (Edge edge : edges) {
        		newNode = null;
        		if (firstEdge) {
        			// if first edge, initialize it's startNode as first node of MSTgraph and visited list
        			firstEdge = false;
        			visited.put(edge.startNode, true);
        			node = new Node(edge.startNode, edge);
	        		MSTgraph.add(node); 
        		}
        		
	        	if (!visited.get(edge.startNode)) {
	        		newNode = edge.startNode;
	        		otherNode = edge.endNode;
	        	}
	        	if (!visited.get(edge.endNode)) {
	        		newNode = edge.endNode;
	        		otherNode = edge.startNode;
	        	} 
	        	if (newNode != null) {
	        		visited.put(newNode, true);
        			node = new Node(newNode, edge);
	        		MSTgraph.add(node); 
	        	} else {
	        		// the same weight as an edge used to connect both nodes already,
	        		// therefore an alternate path at the same weight
	        		count ++;
	        	}
        	}
    	}

    	return count;
    }
    
    private static int countOfSpanningTrees(int[][] matrix) {
    	int n = matrix.length;
		int[][] minorMatrix = new int[n-1][n-1]; 
    	
		minorMatrix = new int[n-1][n-1];
		for (int x = 1; x < n; x++) {
    		for (int y = 1;y < n; y++) {
    			minorMatrix[x-1][y-1] = matrix[x][y];
    		}
		}
		
    	return minorDeterminant(minorMatrix);
    }
    
    static int minorDeterminant(int[][] matrix) {
    	int n = matrix.length;
		int[][] minorMatrix = new int[n-1][n-1]; 
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