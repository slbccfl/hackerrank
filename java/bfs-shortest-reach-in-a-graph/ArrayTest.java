import java.util.ArrayList;
//import java.util.HashMap;

public class ArrayTest {

    public ArrayList<int[]> adjacencyList; // list of adjacent nodes
    
	public void main()  {
		int size = 100;
    	adjacencyList = new ArrayList<int[]>(size + 1);
    	int[] emptyNodeArray;
//        nodes = new HashMap<Integer, Integer>(size);
        for (int i = 0; i <= size; i++) {
        	emptyNodeArray = new int[] {};
        	adjacencyList.add(i, emptyNodeArray);
//        	nodes.put(i, -1); // initialize all node distances to -1
        }
		int n1 = 0, n2 = 1;
    	int indexToLast;
    	int[] destinationsList = adjacencyList.get(n1);
    	if (destinationsList == null) { 
    		indexToLast = 0;
    	} else {
    		indexToLast = destinationsList.length;
    	}
    	destinationsList[indexToLast] = n2;
	}
}
