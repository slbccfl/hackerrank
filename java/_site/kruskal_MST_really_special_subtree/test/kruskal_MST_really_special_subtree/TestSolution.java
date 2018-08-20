package kruskal_MST_really_special_subtree;


import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

public class TestSolution {
	
	@Test
	public void testKruskalsMST() {
		Solution.numberOfNodes = 9;
		Solution.graph.clear();
    	Solution.edgesQueue = new TreeMap<Long, LinkedList<Edge>>();
        Solution.visited = new ArrayList<Set<Integer>>(Solution.numberOfNodes+2);
    	Integer[][] testEdges = {
    			{0,1,4},
    			{0,7,8},
    			{2,1,8},
    			{1,7,11},
    			{7,8,7},
    			{6,7,1},
    			{6,8,6},
    			{6,5,2},
    			{2,8,2},
    			{2,3,7},
    			{2,5,4},
    			{3,5,14},
    			{3,4,9},
    			{4,5,10}
    	};
        Solution.visited = new ArrayList<Set<Integer>>();
        for (int i = 0; i < Solution.numberOfNodes+2; i++) {
    		Set<Integer> s = new HashSet<Integer>();
        	s.add(i);
            Solution.visited.add(i,s);
        }
    	Edge e;
    	LinkedList<Edge> edges;
		Solution.numberOfEdges = testEdges.length;
    	for (int i = 0; i < testEdges.length;i++) {
    		int x = testEdges[i][0];
    		int y = testEdges[i][1];
    		Long z = Long.valueOf(testEdges[i][2].longValue());
    		e = new Edge(x, y, z);
    		edges = Solution.edgesQueue.get(z);
    		if (edges == null) edges = new LinkedList<Edge>();
    		edges.add(e);
    		Solution.edgesQueue.put(z, edges);
    		
    	}
    	Solution.kruskalsMST();
        long MSTweight = 0;
        for (Edge edge : Solution.MSTgraph) {
        	System.out.println(edge.startNode + " - " + edge.endNode + ": " + edge.weight);
        	MSTweight += edge.weight;
        }

        assertEquals(37, MSTweight);
	}

}
