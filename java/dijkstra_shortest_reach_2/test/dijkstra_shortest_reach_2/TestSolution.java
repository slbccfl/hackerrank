package dijkstra_shortest_reach_2;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

public class TestSolution {
	
	@Test
	public void testAddToGraph() {
		Solution.graph = new HashMap<Integer, Node>();
        int x = 1, y = 2;
        long z = 0;
        Edge edge = new Edge(x,y,z);
        Solution.addToGraph(x, edge);
        Solution.addToGraph(y, edge);
		assertEquals(2, Solution.graph.size());
		Node node = Solution.graph.get(x);
		assertEquals(1, node.nodeID);
		assertSame(edge, node.adjacencyList.get(2));
		node = Solution.graph.get(y);
		assertEquals(2, node.nodeID);
		assertSame(edge, node.adjacencyList.get(1));
	}
	
	@Test
	public void testQueueNodeEdges() {
		Solution.graph = new HashMap<Integer, Node>();
		TreeMap<Long, LinkedList<Integer>> nodesQueue = new TreeMap<Long, LinkedList<Integer>>();
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
    	Integer[] orderOfNodes = {1, 7, 6, 5, 2, 8, 8, 3, 4, 3};
    	Integer lowestEdgeID = 0;
    	Node node = Solution.graph.get(lowestEdgeID);
    	node.distance = 0;
    	for (Integer nextNode : orderOfNodes) {
        	Solution.queueNodeEdges(lowestEdgeID);
        	lowestEdgeID = Solution.findLowestEdge();
    		assertEquals(nextNode, lowestEdgeID);
    		
    	}
    	
	}
	
	@Test
	public void testDijkstraSPT() {
		Solution.graph = new HashMap<Integer, Node>();
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
        Iterator<Map.Entry<Integer, Node>> it = Solution.graph.entrySet().iterator();
        while (it.hasNext()) {
        	Map.Entry<Integer, Node> graphEntry = (Map.Entry<Integer, Node>)it.next();
        	Node node = graphEntry.getValue();
        	SPTdistance += node.distance;
        }
        assertEquals(98, SPTdistance);
	}

}
