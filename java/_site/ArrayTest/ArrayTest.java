import java.util.ArrayList;

public class ArrayTest {

    public static ArrayList<ArrayList<Integer>> adjacencyList; // list of adjacent nodes
    
    public static void main(String[] args) {
		int size = 100;
    	adjacencyList = new ArrayList<>(size + 1);
    	
    	ArrayList<Integer> emptyInnerArrayList;
//        nodes = new HashMap<Integer, Integer>(size);
        for (int i = 0; i <= size; i++) {
    		emptyInnerArrayList = new ArrayList<Integer>(); 
        	adjacencyList.add(i, emptyInnerArrayList);
//        	nodes.put(i, -1); // initialize all node distances to -1
        }
		int n1 = 0, n2 = 1;
		ArrayList<Integer> destinationsList = adjacencyList.get(n1);
		destinationsList.add((Integer) n2);
		destinationsList.add((Integer) n1);
		adjacencyList.set(n1, destinationsList);
    	
	}
}
