/**
 * Program for generating an Intuitive Topological sort for a graph and checking for cycles .
 * 
 * @author Mohammad Hamdan, Acuna
 * @version 1.0
 */


import java.util.HashMap;
import java.util.LinkedList;







public class IntuitiveTopological implements TopologicalSort {
	
	 private LinkedList<Integer> order;
	
	BetterDiGraph G ; 
	
	
	public IntuitiveTopological(BetterDiGraph G) {
		
		this.G = G; 
		
	    if( hasCycle(G)) return; 
	    
	    order = new LinkedList<Integer>(); 
	    
	    while (!G.isEmpty()) {
            HashMap<Integer, Integer> indegreesMap = getIndegreesMap(G);
            for (Integer i : indegreesMap.keySet()) {
                if (indegreesMap.get(i) == 0) {
                    order.add(i);
                    G.removeVertex(i);
                }
            }
        }
	}

	
	private HashMap<Integer, Integer> getIndegreesMap(BetterDiGraph G) {
		 HashMap<Integer, Integer> map = new HashMap<>();

	        for (Integer i : G.vertices()) {
	            map.put(i, G.getIndegree(i));
	        }

	        return map;	
	        
	        }


	private boolean hasCycle(BetterDiGraph G) {
		
		HashMap<Integer, Boolean> marked = new HashMap<Integer, Boolean>(); 
		
		HashMap<Integer, Boolean> onStack = new HashMap<Integer, Boolean>(); 
	     
		for (int i : G.vertices()) {
			
		    if( hasCycle(i, marked, onStack) )
		    	return true; 
		}
		
		return false; 
	}


	private boolean hasCycle(int v, HashMap<Integer, Boolean> marked, HashMap<Integer, Boolean> onStack) {
		
		marked.put(v, true);
		
		onStack.put(v, true); 
		
		
		LinkedList<Integer> adjList =  (LinkedList<Integer>) G.getAdj(v); 
		
		for (int i=0; i< adjList.size(); i++) {
			
			int vertex = adjList.get(i); 
			
			if (hasCycle(vertex, marked, onStack) && !marked.get(vertex))
				return true; 
			
			else if (onStack.get(vertex)) return true; 
		}
		
		onStack.put(v, false); 
		
		
		return false; 
	}


	public Iterable<Integer> order() {
		
		return order; 
	}


	public boolean isDAG() {
		
		return order != null; 
	}

}
