
/**
 * This program provides an implementation of the Deque interface
 * and demonstrates it.
 *  Completion time : 5 hours.
 *  Last updated : 10/20/2020.
 * @author Mohammad Hamdan
 * @version  1.1
 */
import java.util.NoSuchElementException;
    

public class HamdanDeque<Item> implements Deque <Item> {
	
	private LinearNode<Item> head;
	private LinearNode<Item> tail;
	private int n = 0;

	// linear node class to create a doubly linked list
	public class LinearNode<T> {  
		private LinearNode<T> next;
		private LinearNode<T> previous;
		private T element;
		public LinearNode() {
		next = null;
		previous = null; 
		element = null;
		}
		public LinearNode(T elem) {
		next = null;
		previous = null; 
		element = elem;
		}
		public LinearNode<T> getNext() {
		return next;
		}
		public LinearNode<T> getPrevious() {
			return previous;
			}
		public void setNext(LinearNode<T> node) {
		next = node;
		}
		public void setPrevious(LinearNode<T> node) {
			previous = node;
			}
		
		public T getElement() {
			return element;
			}
			public void setElement(T elem) {
			element = elem;
			}
			}

// deque class constructor.
 public HamdanDeque () {
	 
	 head = null; 
	 tail = null; 
 }

	public void enqueueFront(Item element) {
		LinearNode<Item> node = new LinearNode<>(element);
		// if the list is empty then head = tail
		if (head == null) {  
			
			head = node;
			tail = node; 
			
		}
		else {
			
			 
			head.previous = node;
            node.next = head;
            head = node;
			
		}
		n++;
	}

	
	public void enqueueBack(Item element) {
		
LinearNode<Item> node = new LinearNode<>(element);
//if the list is empty then head = tail
		if (tail == null) { 
			
			head = node;
			tail = node; 
			
		}
		else {
			
			 
			 tail.next = node;
	            node.previous = tail;
	            tail = node;
			
		}
		n++;
	}

	
	public Item dequeueFront() {
		if(isEmpty())
			throw new NoSuchElementException();
			Item element = head.getElement();
			
			head = head.getNext();
			n--;
			return element;
	}

	
	public Item dequeueBack() throws NoSuchElementException {
		LinearNode<Item> node = tail;
		
		 if (isEmpty()) {
			 throw new NoSuchElementException();
		    }
		// if the list has one node then tail = head = null
		    if (head == tail) {
		        head = null;
		        tail = null;
		        n = 0;
		        return tail.getElement();
		    }

		    Item result = tail.getElement();
		    tail = node.getPrevious();
		    tail.setNext(null);
		    n--;

		    return result;
	}

	
	public Item first() throws NoSuchElementException {
		if(isEmpty())
			throw new NoSuchElementException();
			return head.getElement();
	}

	
	public Item last() throws NoSuchElementException {
		if(isEmpty())
			throw new NoSuchElementException();
			return tail.getElement();
	}

	
	public boolean isEmpty() {
		return n==0;
	}

	
	public int size() {
		
		return n;
	}
	
	@Override
   public String toString() {
		
		LinearNode<Item> node = tail;
		
	    StringBuilder dequeString = new StringBuilder();
	    while (node != head.getPrevious()) {
	    	
	        dequeString.append(node.getElement() + " ");
	        node = node.getPrevious();

	    }
	    return dequeString.toString() ;
        }  
     
		
	
	   /**
     * Program entry point for deque. 
     * @param args command line arguments
     */    
    public static void main(String[] args) {
        HamdanDeque<Integer> deque = new HamdanDeque<>();

        //standard queue behavior
        deque.enqueueBack(3);
        deque.enqueueBack(7);
        deque.enqueueBack(4);
        deque.dequeueFront();        
        deque.enqueueBack(9);
        deque.enqueueBack(8);
       deque.dequeueFront();
        System.out.println("size: " + deque.size());
      System.out.println("contents:\n" + deque.toString());   

        //deque features
       System.out.println(deque.dequeueFront());        
        deque.enqueueFront(1);
        deque.enqueueFront(11);                         
        deque.enqueueFront(3);                 
        deque.enqueueFront(5);         
        System.out.println(deque.dequeueBack());
        System.out.println(deque.dequeueBack());        
        System.out.println(deque.last());                
        deque.dequeueFront();
        deque.dequeueFront();        
        System.out.println(deque.first());        
        System.out.println("size: " + deque.size());
        System.out.println("contents:\n" + deque.toString());            
    }

} 