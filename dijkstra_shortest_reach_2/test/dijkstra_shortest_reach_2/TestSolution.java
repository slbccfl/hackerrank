package dijkstra_shortest_reach_2;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

public class TestSolution {

	@Test
	public void testAddToGraph() {
		Solution.graph.clear();
        int x = 1, y = 2;
        long z = 0;
        Edge edge = new Edge(x,y,z);
        Solution.addToGraph(x, edge);
        Solution.addToGraph(y, edge);
		assertEquals(2, Solution.graph.size());
		Node node = Solution.graph.get(x);
		assertEquals(1, node.nodeID);
//		assertSame(edge, node.adjacencyList.peek());
		node = Solution.graph.get(y);
		assertEquals(2, node.nodeID);
//		assertSame(edge, node.adjacencyList.peek());
	}
	
	@Test
	public void testQueueNodeEdges() {
		Solution.graph.clear();
    	List<Edge> edgesQueue = new ArrayList<Edge>();
        Solution.settled = new HashMap<Integer, Boolean>();
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
    	for (int i = 0; i < testEdges.length;i++) {
    		int x = testEdges[i][0];
    		int y = testEdges[i][1];
            Solution.settled.put(x, false);
            Solution.settled.put(y, false);
    		Long z = Long.valueOf(testEdges[i][2].longValue());
            Solution.addToGraph(x, new Edge(x,y,z));
            Solution.addToGraph(y, new Edge(x,y,z));
    	}
    	Edge lowestEdge;
//    	edgesQueue = Solution.queueNodeEdges(edgesQueue, Solution.graph.get(7));
//    	lowestEdge = Solution.findLowestEdge(edgesQueue);
//    	assertEquals(1, Solution.graph.get(lowestEdge.endNode).distance);
//    	edgesQueue = Solution.queueNodeEdges(edgesQueue, Solution.graph.get(6));
//    	lowestEdge = Solution.findLowestEdge(edgesQueue);
//    	assertEquals(2, Solution.graph.get(lowestEdge.endNode).distance);
//    	edgesQueue = Solution.queueNodeEdges(edgesQueue, Solution.graph.get(5));
//    	lowestEdge = Solution.findLowestEdge(edgesQueue);
//    	assertEquals(4, Solution.graph.get(lowestEdge.endNode).distance);
//    	edgesQueue = Solution.queueNodeEdges(edgesQueue, Solution.graph.get(2));
//    	lowestEdge = Solution.findLowestEdge(edgesQueue);
//    	assertEquals(2, Solution.graph.get(lowestEdge.endNode).distance);
//    	edgesQueue = Solution.queueNodeEdges(edgesQueue, Solution.graph.get(8));
//    	lowestEdge = Solution.findLowestEdge(edgesQueue);
//    	assertEquals(6, Solution.graph.get(lowestEdge.endNode).distance);
    	
	}
	
	@Test
	public void testDijkstraSPT() {
		Solution.graph.clear();
    	TreeMap<Long, LinkedList<Edge>> edgesQueue = new TreeMap<Long, LinkedList<Edge>>();
        Solution.settled = new HashMap<Integer, Boolean>();
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
    	for (int i = 0; i < testEdges.length;i++) {
    		int x = testEdges[i][0];
    		int y = testEdges[i][1];
            Solution.settled.put(x, false);
            Solution.settled.put(y, false);
    		Long z = Long.valueOf(testEdges[i][2].longValue());
            Solution.addToGraph(x, new Edge(x,y,z));
            Solution.addToGraph(y, new Edge(x,y,z));
    	}
    	Solution.dijkstraSPT(0);
        long SPTdistance = 0;
        for (Edge edge : Solution.SPTgraph) {
        	System.out.println(edge.startNode + " - " + edge.endNode + ": " + Solution.graph.get(edge.endNode).distance);
        	SPTdistance += edge.weight;
        }

        assertEquals(42, SPTdistance);
	}

}
