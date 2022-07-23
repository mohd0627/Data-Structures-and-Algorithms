/**
 * A Two probe chaining Hashtable based implementation of a symbol table.
 * 
 * @author Mohammad Hamdan
 * @version 1.1
 */

import java.util.LinkedList;
import java.util.Queue;



public class TwoProbeChainHT <Key, Value> implements SymbolTable<Key, Value> {
	
	private class HTObject {
        public Key key;
        public Value val;
    }

	private int N; // number of key-value pairs
	private int M; // hash table size
	LinkedList<HTObject>[] chains; 
	
	public TwoProbeChainHT() {
		this(997);
		
		
		}
	
	
		public TwoProbeChainHT(int M) {
			
		this.M = M;
		
		chains = new LinkedList[M];
		
		for (int i=0; i<M; i++) {
			
		 chains [i] = new LinkedList<HTObject>();
			
		}
		
		
		}
	private int hash1(Key key) {
		
		return (key.hashCode() & 0x7fffffff) % M;
		
		}

	
	private int hash2(Key key) {
		
		return  (((key.hashCode() & 0x7fffffff) % M) * 31) % M;
		
		}

	
	@Override
	public void put(Key key, Value val) {
		
		int hash1 = hash1(key);
		int hash2 = hash2(key);
		
		
		if (chains[hash1].size() < chains[hash2].size()) {
			
			HTObject pair = new HTObject();
			pair.key = key;
			pair.val = val;
			chains[hash1].add(pair);
			
			
			N++;
		}
		
		else {
			HTObject pair = new HTObject();
			pair.key = key;
			pair.val = val;
			chains[hash2].add(pair);
			N++;
		}
	
	}

	@Override
	public Value get(Key key) {
		
		int hash1 = hash1(key);
		int hash2 = hash2(key);
	    
	if (chains[hash1] == null) return null;
	
	if (chains[hash2] == null) return null;

	for (int i=0; i< chains[hash1].size(); i++) {
		
		if (chains[hash1].get(i).key == key) 
			
			return chains[hash1].get(i).val;
		}
	
	for (int j=0; j < chains[hash2].size(); j++) {
		
		 if (chains[hash2].get(j).key == key) 
			
			return chains[hash2].get(j).val;
			
		}
	
	return null; 
	
	}

	@Override
	public void delete(Key key) {
		
		int hash1 = hash1(key);
		int hash2 = hash2(key);
	    
	if (chains[hash1] == null) return;
	
	if (chains[hash2] == null) return ;

	for (int i=0; i< chains[hash1].size(); i++) {
		
		if (chains[hash1].get(i).key == key) {
			
			chains[hash1].remove(i);
		 
		    N--;
		}
		}
	
	for (int j=0; j < chains[hash2].size(); j++) {
		
		 if (chains[hash2].get(j).key == key) {
			
		    chains[hash2].remove(j); 
		    
		    N--;
		 }
			
		}

		
		
	}

	@Override
	public boolean isEmpty() {
		
		return N==0;
	}

	@Override
	public int size() {
	
		return N;
	}

	@Override
	public Iterable<Key> keys() {
		Queue<Key> queue = new LinkedList<>(); 
		
		for (int i=0; i< M; i++) {
			for (int j=0; j < chains[i].size() && chains[i] != null; j++) {
			
			queue.add(chains[i].get(j).key); 
			
		}
		}
		return queue; 
	}

	@Override
	public boolean contains(Key key) {
		
		return get(key) != null; 
	}






}
