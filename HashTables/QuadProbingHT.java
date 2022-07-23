/**
 * A quad probing Hashtable based implementation of a symbol table.
 * 
 * @author Mohammad Hamdan
 * @version 1.1
 */



import java.util.LinkedList;
import java.util.Queue;









public class QuadProbingHT <Key, Value> extends LinearProbingHT<Key, Value> implements SymbolTable<Key, Value>  {

		


	private int hash(Key key, int i) {
		
		return ((key.hashCode() &  0x7fffffff) + i*i) % M;
		
	}

	
}
