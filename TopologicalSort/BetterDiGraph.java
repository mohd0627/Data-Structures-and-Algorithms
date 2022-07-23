/**
 * Program for building Digraph.
 * 
 * @author Mohammad Hamdan, Acuna
 * @version 1.0
 */

import java.util.NoSuchElementException;
import java.util.LinkedList;
import java.util.HashMap;

public class BetterDiGraph implements EditableDiGraph {

	private int V; 
	private int E; 
	private HashMap <Integer, LinkedList<Integer>> map ; 
	
	public BetterDiGraph() {
		map = new HashMap<>(); 
		V=0;
		E=0;
	}
	

	public void addEdge(int v, int w) {
		
		if  ( !map.containsKey(v) ) {
			map.put(v, new LinkedList<Integer>()); 
			V++;
		}
		
		
		if  ( !map.containsKey(w) ) {
			map.put(w, new LinkedList<Integer>()); 
			V++;
		}
		
		map.get(v).add(w); 
		E++;
	}


	public void addVertex(int v) {
		
		if (map.containsKey(v)) return; 
		
		map.put(v, new LinkedList<Integer>()); 
		V++;
	}


	public Iterable<Integer> getAdj(int v) {
		
		return map.get(v);
	}


	public int getEdgeCount() {
		
		return E;
	}


	public int getIndegree(int v)  {
		
		if ( !map.containsKey(v) )
			throw new NoSuchElementException(); 
		
		int degree=0; 
		
		for (int i : map.keySet()) {
			if (i != v)
				if (map.get(i).contains(v)) 
					degree++; 
			
		}
		
		return degree;
	}


	public int getVertexCount() {
	
		return V;
	}


	public void removeEdge(int v, int w) {
		if  ( !map.containsKey(v) ) {
			return; 
		}
		
		
		if  ( !map.containsKey(w) ) {
			return;
		}
		
		map.get(v).remove(w);
		E--;
		
	}

	
	public void removeVertex(int v) {
		
		if  ( !map.containsKey(v) ) {
			return; 
		}
		
		
		for (int i : map.keySet()) {
			
			 if (i != v) {
	                int destIndex = map.get(i).indexOf(v);
	                if (destIndex != -1) {
	                    removeEdge(i, v);
	                }
	            }
	        }
		map.remove(v); 
		
		V--;
	}

	
	public Iterable<Integer> vertices() {
		
		return map.keySet(); 
	}

	
	public boolean isEmpty() {
		
		return V==0;
	}


	public boolean containsVertex(int v) {
		
		return map.containsKey(v); 
	}

}
