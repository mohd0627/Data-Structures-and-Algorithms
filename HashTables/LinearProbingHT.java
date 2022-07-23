/**
 * A linear probing Hashtable based implementation of a symbol table.
 * 
 * @author Mohammad Hamdan
 * @version 1.1
 */



import java.util.LinkedList;
import java.util.Queue;






public class LinearProbingHT<Key, Value> implements SymbolTable<Key, Value> {
	
	private class HTObject<Key, Value>  {
        public Key key;
        public Value val;
    }

	
	private int N; // number of key-value pairs in the tab
	protected int M; // size of linear-probing table

	
	private HTObject<Key, Value> [] array ;
	
	public LinearProbingHT() {
		
		M = 997; 
		
		array =  new HTObject[M];
		
		}

	private int hash(Key key) {
		
		return (key.hashCode() & 0x7fffffff) % M;
		}

	@Override
	public void put(Key key, Value val) {
		
		int hash;
		
		for (hash = hash(key); array[hash] != null; hash = (hash+1) % M) {
			
			if (array[hash].key.equals(key)) {
				
				array[hash].val = val;
			}
		}
		HTObject pair = new HTObject(); 
		
		pair.key = key;
		pair.val = val;
		array[hash]= pair;
		N++;
		
	}

	@Override
	public Value get(Key key) {
		
		for (int i = hash(key); array[i] != null; i = (i + 1) % M) {
			
			if (array[i].key.equals(key))
			return (Value) array[i].val;
			
			}
		return null; 
	}

	@Override
	public void delete(Key key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(Key key) {
		
		return get(key) != null; 
	}

	@Override
	public boolean isEmpty() {
		
		return size() == 0; 
	}

	@Override
	public int size() {
		
		return N;
	}

	@Override
	public Iterable<Key> keys() {
		
		
		Queue<Key> queue = new LinkedList<>();
				
				for (int i=0; i < array.length; i++) {
					
					if (array[i] != null)
					queue.add(array[i].key);
				}
	     return queue; 
	}

	
}
