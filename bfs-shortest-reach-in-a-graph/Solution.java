
import java.util.*;

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
        public static ArrayList<ArrayList<Integer>> adjacencyList; // list of adjacent nodes
        public HashMap<Integer, Integer> distances; // key: node#, value: distance from starting node.

	    private HashMap<Integer, Boolean> settled = new HashMap<Integer, Boolean>();
	    
        public Graph(int size) {
        	adjacencyList = new ArrayList<ArrayList<Integer>>(size);
        	settled = new HashMap<Integer, Boolean>(size);
        	ArrayList<Integer> emptyNodeArray;
            distances = new HashMap<Integer, Integer>(size);
            for (int i = 0; i < size; i++) {
            	emptyNodeArray = new ArrayList<Integer>();
            	adjacencyList.add(i, emptyNodeArray);
            	distances.put(i, Integer.MAX_VALUE); // initialize all node distances largest possible Integer value
            	settled.put(i, false); // initialize all nodes to not visited
            }
        }

        public void addEdge(int n1, int n2) {

    		ArrayList<Integer> destinationsList = adjacencyList.get(n1);
    		destinationsList.add((Integer) n2);
    		adjacencyList.set(n1, destinationsList);

    		destinationsList = adjacencyList.get(n2);
    		destinationsList.add((Integer) n1);
    		adjacencyList.set(n2, destinationsList);
        	
        	return;
        }
        
        public void shortestReach(int startId) { // 0 indexed
            distances.put(startId, 0);
            int currentNode, adjacentNode, distance, newDistance, oldDistance;
            currentNode = startId;
            
                
            while (currentNode != -1) {
            	distance = distances.get(currentNode);
            	ArrayList<Integer> adjacentNodes = adjacencyList.get(currentNode);
            	int s = adjacentNodes.size();
            	for (int i = 0; i < s; i++) {
            		adjacentNode = adjacentNodes.get(i);
            		if (adjacentNode == startId || settled.get(adjacentNode)) continue;
	            	oldDistance = distances.get(adjacentNode);
	            	newDistance = distance + 6;
	            	if (newDistance < oldDistance) {
	                	distances.put(adjacentNode, newDistance);
	            	}
	            	
            	}
            	settled.put(currentNode, true);
            	// find minimum distance among unsettled nodes
            	int minDistance = Integer.MAX_VALUE;
            	
            	for(Map.Entry<Integer, Integer> entry : distances.entrySet()) {
            		if (settled.get(entry.getKey())) continue;
            		distance = entry.getValue();
            	    if (distance < minDistance) {
            	    	minDistance = distance;
            	    	currentNode = entry.getKey();
            	    }
            	}
            	if (minDistance == Integer.MAX_VALUE) {
            		currentNode = -1;
            	}
            }
            
            Iterator<Map.Entry<Integer, Integer>> it = distances.entrySet().iterator();
            while (it.hasNext()) {
            	Map.Entry<Integer, Integer> nodeEntry = it.next();
            	currentNode = nodeEntry.getKey();
            	distance = nodeEntry.getValue();
            	if (distance == Integer.MAX_VALUE) distance = -1;
                if (currentNode != startId) {
                    System.out.print(distance);
                    if (it.hasNext()) {
                        System.out.print(" ");
                    }
                }
            }
            
            return;
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
//            int[] distances = 
            graph.shortestReach(startId);

            if (t != queries - 1) System.out.println();   
 
//            for (int i = 0; i < distances.length; i++) {
//                if (i != startId) {
//                    System.out.print(distances[i]);
//                    System.out.print(" ");
//                }
//            }
//            System.out.println();            
        }
        
        scanner.close();
//    	long endTime = System.currentTimeMillis( );
//    	System.out.println(new Date());
//    	System.out.println("Elapsed Milliseconds: " + (endTime - startTime));
    }
}
