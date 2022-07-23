/**
 * A binary search tree based implementation of a symbol table.
 * 
 * @author Mohammad Hamdan, Sedgewick and Wayne, Acuna
 * @version 1.1
 */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


        
public class HamdanBSTST<Key extends Comparable<Key>, Value> implements OrderedSymbolTable<Key, Value> {
    private Node root;

    private class Node
    {
        private final Key key;
        private Value val;
        private Node left, right;
        private int N;

        public Node(Key key, Value val, int N) {
            this.key = key; this.val = val; this.N = N;
        }
    }
    
    @Override
    public int size() {
        return size(root);
    }
    
    private int size(Node x) {
        if (x == null)
            return 0;
        else
            return x.N;
    }
    
    @Override
    public Value get(Key key) {
        return get(root, key);
    }
    
    private Value get(Node x, Key key) {
        // Return value associated with key in the subtree rooted at x;
        // return null if key not present in subtree rooted at x.
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.val;
    }
    
    @Override
    public void put(Key key, Value val) {
        root = put(root, key, val);
    }
    
    private Node put(Node x, Key key, Value val) {
        // Change key’s value to val if key in subtree rooted at x.
        // Otherwise, add new node to subtree associating key with val.
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else x.val = val;
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }
    
    @Override
    public Key min() {
      return min(root).key;
    }
    
    private Node min(Node x) {
        if (x.left == null)
            return x;
        return min(x.left);
    }
    
    @Override
    public Key max() {
      return max(root).key;
    }
    
    private Node max(Node x) {
        if (x.right == null) return x;
        return max(x.right);
    }
    
    @Override
    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null)
            return null;
        return x.key;
    }
    
    private Node floor(Node x, Key key) {
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) return floor(x.left, key);
        Node t = floor(x.right, key);
        if (t != null) return t;
        else return x;
    }
    
    @Override
    public Key select(int k) {
        return select(root, k).key;
    }
    
    private Node select(Node x, int k) {
        if (x == null) return null;
        int t = size(x.left);
        if (t > k) return select(x.left, k);
        else if (t < k) return select(x.right, k-t-1);
        else return x;
    }
    
    @Override
    public int rank(Key key) {
        return rank(key, root);
    }
    
    private int rank(Key key, Node x) {
        // Return number of keys less than x.key in the subtree rooted at x.
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
        else return size(x.left);
    }
    
    @Override
    public void deleteMin() {
        root = deleteMin(root);
    }
    
    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }
    
    @Override
    public void delete(Key key) {
        root = delete(root, key);
    }
    
    private Node delete(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else
        {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    @Override
    public Iterable<Key> keys() {
        return keys(min(), max());
    }
    
    @Override
    public Iterable<Key> keys(Key lo, Key hi)
    {
        Queue<Key> queue = new LinkedList<>();
        keys(root, queue, lo, hi);
        return queue;
    }
    
    private void keys(Node x, Queue<Key> queue, Key lo, Key hi)
    {
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) keys(x.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.add(x.key);
        if (cmphi > 0) keys(x.right, queue, lo, hi);
    }
    
    @Override
    public boolean contains(Key key) {
    	
      Queue<Key> queue = new LinkedList<>();
        
        queue = (Queue<Key>) keys(); 
        
        while (!queue.isEmpty()) {
        	if (queue.peek() == key) return true;
        	queue.remove(); 
        	
        }
        
        return false; 
        
        
    }

    @Override
    public boolean isEmpty() {
        if (size() == 0) {
        	
        	return false;
        }
        
        return true; 
    }

    
    @Override
    public void deleteMax() {
    	 root = deleteMin(root);
    }
    
    private Node deleteMax(Node x) {
    	
    	 if (x.right == null) return x.left;
         x.right = deleteMin(x.right);
         x.N = size(x.left) + size(x.right) + 1;
         return x;
    }

    @Override
    public int size(Key lo, Key hi) {
    	Queue<Key> queue = new LinkedList<>();
        
        queue = (Queue<Key>) keys(lo, hi); 
        int val=0;
        while (!queue.isEmpty()) {
        	queue.remove(); 
        	val++;
        }
        
        return val; 
    }
    
    public void balance() {
    	
    	ArrayList<Node> nodes = new ArrayList<>();
        order(nodes, root); 
        root = createTree(nodes,0,nodes.size()-1);
    }
    
  private Node createTree(ArrayList<Node> nodes, int lo, int hi) {
    	
    	if(lo > hi) return null ;
        int mid = lo + (hi-lo)/2;
     
        Node node = nodes.get(mid);
    //  System.out.println(node.val);
        
        node.left =  createTree(nodes,lo,mid-1);
        node.right = createTree(nodes,mid+1,hi);
        
        return node;
		
	}

	private void order(ArrayList<Node> nodes, Node x) {
		
		if(x == null) return ;
		
		nodes.add(x);
		order(nodes, x.right);
		order(nodes, x.left);
			 
		
		
	}

	public Value getFast(Key key) {
    	
    	Node x = root;
    	while ( x != null ) {
    		
    		int cmp = key.compareTo(x.key);
    		
    		if (cmp == 0) return x.val;
    		else if (cmp < 0) x =  x.left;
    		else if (cmp > 0) x = x.right; 
    	}
    	
    	return null; 
    		
    }
    
    public void putFast(Key key, Value value) {
      
        boolean keyExists = false;

        Node current = root;

        while (current != null) {
            int compare = key.compareTo(current.key);

            if (compare < 0) {
                current = current.left;
            } else if (compare > 0) {
                current = current.right;
            } else {
                current.val = value;
                keyExists = true;
                break;
            }
        }

        if (keyExists) {
            return;
        }

        if (root == null) {
            root = new Node(key, value, 1);
            return;
        }

        current = root;

        while (current != null) {

            int compare = key.compareTo(current.key);
            current.N = current.N + 1;

            if (compare < 0) {

                if (current.left != null) {
                    current = current.left;
                } else {
                    current.left = new Node(key, value, 1);
                    break;
                }
            } else if (compare > 0) {

                if (current.right != null) {
                    current = current.right;
                } else {
                    current.right = new Node(key, value, 1);
                    break;
                }
            }
        }
    }


    
    
    public void printLevel(Key key) {
    	
    	Node x = root; 
    	// System.out.println(x.val);
    	 
    	int cmp = key.compareTo(x.key);
    	
    	while  (cmp != 0 ) {
    		
    	
    	if (cmp < 0) x = x.left;
    	
    	if (cmp > 0) x = x.right;
    	
    	cmp = key.compareTo(x.key);
    	
    	
    	}
    	
    	
    	 Queue<Node> queue =  new LinkedList<>();
         queue.add(x);
        

         while (!queue.isEmpty()) {
         	
             Node current = queue.remove();
             System.out.println(current.val);

             if (current.left != null) {
                 queue.add(current.left);
             }
             if (current.right != null) {
                 queue.add(current.right);
             }
         }

    	
    }
    
    public Key ceiling (Key key) {
       
        Node x = ceiling(root, key);
        if (x == null) return null; 
        
        else return x.key;
    }

    private Node ceiling(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) { 
            Node t = ceiling(x.left, key); 
            if (t != null) return t;
            else return x; 
        } 
        return ceiling(x.right, key); 
    } 
    int height(Node root)
    {
        if (root == null)
           return 0;
        else
        {
            /* compute  height of each subtree */
            int lheight = height(root.left);
            int rheight = height(root.right);
             
            /* use the larger one */
            if (lheight > rheight)
                return(lheight+1);
            else return(rheight+1); 
        }
    }

    /**
     * entry point for testing.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        HamdanBSTST<Integer, String> bst = new HamdanBSTST();
        
        bst.put(10, "TEN");
        bst.put(3, "THREE");
        bst.put(1, "ONE");
        bst.put(5, "FIVE");
        bst.put(2, "TWO");
        bst.put(7, "SEVEN");
        
      
        
        System.out.println("Before balance:");
       bst.printLevel(10); //root
        
        System.out.println("After balance:");
       bst.balance();
       bst.printLevel(5); //root
    }
}