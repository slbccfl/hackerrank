import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

class Node 
{
    public int label; // this node's label (parent node in path tree)
    public int weight; // weight of edge to this node
 
    public Node(int v, int w) 
    { 
        label = v;
        weight = w;
    }
}

public class Solution {
	
    public static Scanner in; // for standard input
    public static LinkedList<Node>[] graph; // adjacency list representation
    public static int start, edges, end; // start and end points, and number of edges in the graph, end is also "N" the number of nodes
    
    public static void main(String[] args) {
    	timestamp();
        in = new Scanner(System.in);
        String parameters = in.nextLine();
        String[] parmsArray = parameters.split("\\s+");
        
        start = 1;
        end = Integer.parseInt(parmsArray[0]);
        edges = Integer.parseInt(parmsArray[1]);
        
        // Initialize adjacency list structure to empty lists:
        graph = new LinkedList[end+1];
        for (int i = 0; i <= end; i++)
            graph[i] = new LinkedList<Node>();
        
        // Input the graph:
        String[] edgeArray;
        String edge;
        TreeMap<Integer, HashMap<Integer, Integer>> adjacencyMap = new TreeMap<Integer, HashMap<Integer, Integer>>();
        for (int i = 0; i < edges; i++) {
        	edge = in.nextLine();
        	edgeArray = edge.split("\\s+");
            int v1 = Integer.parseInt(edgeArray[0]);
            int v2 = Integer.parseInt(edgeArray[1]);
            int w = Integer.parseInt(edgeArray[2]);
            HashMap<Integer, Integer> verts = adjacencyMap.get(w);
            if (verts == null) {
	            verts = new HashMap<Integer, Integer>();
            }
            verts.put(v1,v2);
        	adjacencyMap.put(w, verts);
        }
        
        Set<Map.Entry<Integer,HashMap<Integer,Integer>>> setOfEdges = adjacencyMap.entrySet();
        for (Map.Entry me : setOfEdges) {
        	int w = (Integer) me.getKey();
        	HashMap<Integer, Integer> verts = (HashMap<Integer, Integer>) me.getValue();
        	Set setOfVerts = verts.entrySet();
        	Iterator v = setOfVerts.iterator();
        	while (v.hasNext()) {
        		Map.Entry ve = (Map.Entry) v.next(); 
                int v1 = (int) ve.getKey();
                int v2 = (int) ve.getValue();
                Node node = new Node(v2, w);
                graph[v1].add(node);
                node = new Node(v1, w);
                graph[v2].add(node);
        	}
        }
        
        if (cheapest() == Integer.MAX_VALUE) {
        	System.out.println("NO PATH EXISTS");
        } else {
        	System.out.println(cheapest());
        }

    	timestamp();
    }
    
    public static int cheapest() {
    	// Initialize boolean table to track which nodes are settled and a table of nodes to hold the least expensive route to each node
    	boolean[] done = new boolean[end + 1];
    	Node[] table = new Node[end + 1];
    	int count;
    	// Initialize  table with -1 to indicate that the node has not been visited and MAX_VALUE to represent infinity
        for (int i = 0; i <= end; i++) {
            table[i] = new Node(-1, Integer.MAX_VALUE); 
        }
 
        table[start].weight = 0;  // the weight of the starting node is 0

        // visit each node and determine the cheapest route for each
        for (count = 0; count < end; count++) {
        	int min = Integer.MAX_VALUE;	// Initialize min for the node as infinity (MAX_VALUE)
        	int minNode = -1;				// 		and use -1 to indicate that no node is the minimum yet
        	// Check each node to determine which non-settled node is now the least expensive path and set it to minNode
    		for (int i = 0; i < end; i++) {
	    		if (!done[i] && table[i].weight < min) {
	    			min = table[i].weight;
	    			minNode = i;
	    		}
    		}
    		
    		
    		if (minNode != -1) {
        		// traverse each of the edges leaving the current minNode
        		ListIterator<Node> iter = graph[minNode].listIterator();
    	    	while (iter.hasNext()) {
    	    		Node nd = (Node) iter.next();
    	    		int v = nd.label;
    	    		int w = nd.weight;
    	    		
    	    		// if the destination node has not been settled and the minNode's route is less expensive, 
    	    		// update the cost to get to this node to the new minimum
		    		if (!done[v] && Math.max(w, table[minNode].weight) < table[v].weight) {
	    				table[v].weight = Math.max(w, table[minNode].weight);	
		    			table[v].label = minNode;	
		    		}
    	    	}
        		done[minNode] = true;
    		}
        }
//        for (int i = 0; i < end + 1 ; i++) 
//        {
//            if (table[i].weight < Integer.MAX_VALUE) 
//            {
//                System.out.print("Wire from " + i + " to " + start
//                        + " with length " + table[i].weight + ": ");
//                int next = table[i].label;
//                while (next >= 0) 
//                {
//                    System.out.print(next + " ");
//                    next = table[next].label;
//                }
//                System.out.println();
//            } else
//                System.out.println("No wire from " + i + " to " + start);
//        }
        return table[end].weight;
        
    }
    public static void timestamp() {
    	Date date = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
    	String formattedDate = sdf.format(date);
    	System.out.println(formattedDate); // 12/01/2011 4:48:16 PM
    }
    
}