import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
	
//	class Node 
//	{
//	    public int label; // this node's label (parent node in path tree)
//	    public int weight; // weight of edge to this node
//	 
//	    public Node(int v) { 
//	        label = v;
//	        int distanceFromStart; // distance from the labeled node to the starting node
//	    }
//	}
    public static class Graph {
        public ArrayList<int[]> adjacencyList; // list of adjacent nodes
        public HashMap<Integer, Integer> nodes; // key: node#, value: distance from starting node.

	    private Queue<Integer> queue = new LinkedList<Integer>();
	    
        public Graph(int size) {
        	adjacencyList = new ArrayList<int[]>(size + 1);
        	int[] emptyNodeArray;
            nodes = new HashMap<Integer, Integer>(size);
            for (int i = 0; i <= size; i++) {
            	emptyNodeArray = new int[] {};
            	adjacencyList.add(i, emptyNodeArray);
            	nodes.put(i, -1); // initialize all node distances to -1
            }
        }

        public void addEdge(int n1, int n2) {
        	
        	int indexToLast;
        	int[] destinationsList = adjacencyList.get(n1);
        	if (destinationsList == null) { 
        		indexToLast = 0;
        	} else {
        		indexToLast = destinationsList.length;
        	}
        	destinationsList[indexToLast] = n2;
        	adjacencyList.set(n1, destinationsList);
        	
        	destinationsList = adjacencyList.get(n2);
        	if (destinationsList == null) { 
        		indexToLast = 0;
        	} else {
        		indexToLast = destinationsList.length;
        	}
        	destinationsList[indexToLast] = n1;
        	adjacencyList.set(n2, destinationsList);
        	
        	return;
        }
        
        public int[] shortestReach(int startId) { // 0 indexed
            queue.add(startId);
            nodes.put(startId, 0);
            int currentNode, adjacentNode, distance, newDistance, oldDistance;
                 
            while (!queue.isEmpty()) {
            	currentNode = queue.poll();
            	distance = nodes.get(currentNode);
            	int[] adjacentNodes = adjacencyList.get(currentNode);
            	for (int i = 0; i < adjacentNodes.length; i++) {
            		adjacentNode = adjacentNodes[i];
	            	oldDistance = nodes.get(adjacentNode);
	            	newDistance = distance + oldDistance;
	            	if (newDistance < oldDistance) {
	                	nodes.put(adjacentNode, newDistance);
	            	}
	            	queue.add(adjacentNode);
            	}
            }
            int[] returnArray = null;
            Iterator<Entry<Integer, Integer>> it = nodes.entrySet().iterator();
            while (it.hasNext()) {
            	returnArray[returnArray.length] = ((Entry<Integer, Integer>) it).getValue();
            }
            
            return returnArray;
        }

    }
    
    public static void main(String[] args) {
//    	long startTime = System.currentTimeMillis( );
//    	System.out.println(new Date());
        Scanner scanner = new Scanner(System.in);
      
        int queries = scanner.nextInt();
        
        for (int t = 0; t < queries; t++) {
            
            // Create a graph of size n where each edge weight is 6:
            Graph graph = new Graph(scanner.nextInt());
            int m = scanner.nextInt();
            
            // read and set edges
            for (int i = 0; i < m; i++) {
                int u = scanner.nextInt() - 1;
                int v = scanner.nextInt() - 1;
                
                // add each edge to the graph
                graph.addEdge(u, v);
            }
            
            // Find shortest reach from node s
            int startId = scanner.nextInt() - 1;
            int[] distances = graph.shortestReach(startId);
 
            for (int i = 0; i < distances.length; i++) {
                if (i != startId) {
                    System.out.print(distances[i]);
                    System.out.print(" ");
                }
            }
            System.out.println();            
        }
        
        scanner.close();
//    	long endTime = System.currentTimeMillis( );
//    	System.out.println(new Date());
//    	System.out.println("Elapsed Milliseconds: " + (endTime - startTime));
    }
}
