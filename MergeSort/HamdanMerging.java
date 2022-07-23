import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.*;
/**
 * Implements various merge style algorithms.
 * 
 * Last updated 10/18/2020.
 * 
 * Completion time: (your completion time)
 *
 * @author Mohammad, Acuna, Sedgewick and Wayne
 * @verison 1.1
 */

 class ListQueue <Item> implements Queue <Item> {
	
	 public class LinearNode<Item> {
		    private LinearNode<Item> next;
		    private Item element;
		 
		    /**
		     * Creates a node storing the specified element.
		     * @param elem element to be stored
		     */
		    public LinearNode(Item elem) {
		        next = null;
		        element = elem;
		    }

		    /**
		     * Returns the node that follows this one.
		     * @return reference to next node
		     */
		    public LinearNode<Item> getNext() {
		        return next;
		    }
		 
		    /**
		     * Sets the node that follows this one.
		     * @param node node to follow this one
		     */
		    public void setNext(LinearNode<Item> node) {
		        next = node;
		    }
		 
		    /**
		     * Returns the element stored in this node.
		     * @return element stored at the node
		     */
		    public Item getElement() {
		        return element;
		    }
		 
		    /**
		     * Sets the element stored in this node.
		     * @param elem element to be stored at this node
		     */
		    public void setElement(Item elem) {
		        element = elem;
		    }
		}
    LinearNode<Item> tail; //back
    LinearNode<Item> head; //front
    private int count;

    public ListQueue() {
        head = tail = null;
        count = 0;
    } 

    public boolean isEmpty() {
        return count == 0;
    }

    public void enqueue(Item item) {
     LinearNode newNode = new LinearNode(item);

        if(isEmpty())
            head = newNode;
        else
            tail.setNext(newNode);
        
        tail = newNode;
        count++;
    }

    
    public Item dequeue() {
        if(isEmpty())
            throw new NoSuchElementException();

        Item result = head.getElement();
        head = head.getNext();
        count--;
        
        if(isEmpty())
            tail = null;

        return result;
    }

    
    public Item front() {
        if(isEmpty())
            throw new NoSuchElementException();

        return head.getElement();
    }
    
    @Override
    public String toString()
    {
        LinearNode iter = head;
        String result = "(front)";
        
        while(iter != null) {
            result = iter.getElement() + " " + result;
            iter = iter.getNext();
        }
            
        return result;
    }

    
    public int size() {
        return count;
    }
}

public class HamdanMerging {
	
    
    public static Queue mergeQueues(Queue<Comparable> q1, Queue<Comparable> q2) {
    	
    	
        Queue newQueue = new ListQueue <Comparable>();
               
        while(q1.isEmpty()== false && q2.isEmpty() == false) {
        	
        	 if (less(q1.front(), q2.front())) {
        		 
        		 newQueue.enqueue(q1.front());
        		 q1.dequeue();
        		 
        	 }
             else  {
            	 
            	 newQueue.enqueue(q2.front());
            	 q2.dequeue(); 
             }
        	 
        	 if (q1.isEmpty()== true && q2.isEmpty() == false) {
        		 
        		 newQueue.enqueue(q2.front());
            	 q2.dequeue();
        		 
        	 }
        	 
        	 
        	 if (q1.isEmpty() == false && q2.isEmpty() == true ) {
        		 
        		 newQueue.enqueue(q1.front());
        		 q1.dequeue();
        		 
        	 }
        	
        }
                return newQueue; 
    }
 
    public static void sort(Comparable[] a) {
    	
    	Comparable[] newArray = new Comparable [a.length];
    	
    	  for (int i=0; i< a.length; i++) {
       	   newArray[i] = a[i];
          }
    	
    	newArray=  mergesort(newArray);
    	 
    	 for (int j=0; j< a.length; j++) {
         	   a[j] = newArray[j];
            }
    	 
    	assert isSorted(a);

    }

    
    private static Comparable[] mergesort(Comparable[] a) {
    	   
    	if (a.length <= 1)
       		{return a;}

      	int mid = a.length/2;
          
    	Comparable[] firstHalf = new Comparable [mid];
    	
    	Comparable[] secondHalf; 
    	
       if (a.length % 2 == 0) {
    	   secondHalf = new Comparable [mid];
       }
       
       else {
    	   secondHalf = new Comparable [mid + 1];
       }
     
       for (int i=0; i< mid; i++) {
    	   firstHalf[i] = a[i];
       }
       for (int j=0; j< secondHalf.length; j++) {
    	   secondHalf[j] = a[j+ mid];
       }
       
       
        
        firstHalf =	mergesort(firstHalf);
         secondHalf = mergesort(secondHalf); 
     	
         Comparable[] newArray = new Comparable [a.length]; 
         newArray = merge(firstHalf, secondHalf);
       
 		return newArray; 
        
	
	}
  
	public static Comparable[] merge(Comparable[] a, Comparable [] b) {
    	
		
		Comparable [] c = new Comparable [a.length + b.length];
		
		int i=0;
		int j=0;
		int k=0; 
		while (i < a.length || j < b.length) {
			
		 if (i < a.length && j < b.length) 
	        {
	            if (a[i].compareTo(b[j]) < 0) 
	            {
	               c[k++] = a[i++];
	                
	            } 
	            else
	            {
	               c[k++] = b[j++];
	               
	                
	            }
	            
	        }
		 else if (i < a.length ) {
			 c[k++] = a[i++];
		 }
		 
		 else if (j < b.length) {
			 c[k++] = b[j++];
		 }
		}
	
        return c; 
		
	}

	public static void shuffle(Object[] a) {
		
		if (a.length <= 1) {
			return ;
		}
		
		int mid = a.length/2 ; 
		
		Random rand = new Random(); 
		Random rand2 = new Random();
		
		    Object [] left = new Object [mid]; 
			
			Object [] right; 
			
			
			
			  if (a.length % 2 == 0) {
		    	   right = new Object [mid];
		       }
		       
		       else {
		    	   right = new Object [mid + 1];
		       }
		     
			 
		       for (int i=0; i< mid; i++) {
		    	  
		    	  left[i] = a[i]; 
		    	   
		       }
		       
		     
		       
		       for (int j=0; j< right.length; j++) {
		    	   
		    	  right[j] = a[j+mid]; 
		       }
		  	 
 		
			shuffle(left); 
			shuffle(right);
			
			int index = rand.nextInt( mid ) ; 
			  
			  int randomNum = rand2.nextInt( 2 ) ; 
			  
			 for (int k=0; k < a.length && index < mid ; k++) {
				 
				if (randomNum == 1) {
					
					a[k] = left[index++]; 
					
				}
				
				else  {
					
					a[k]= right[index++]; 
					
				}
				}
			
	        
		
	}

    

    //sorting helper from text
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    //sorting helper from text
    private static void show(Comparable[] a) {
        for (Comparable a1 : a)
            System.out.print(a1 + " ");

        System.out.println();
    }
    
    //sorting helper from text
    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1]))
                return false;
        
        return true;
    }
    /**
     * Entry point for sample output.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	
        Queue q1 = new ListQueue<String>(); q1.enqueue("E"); q1.enqueue("L"); q1.enqueue("O"); q1.enqueue("R"); q1.enqueue("T"); //contains E L O R T (tail)
        Queue q2 = new ListQueue<String>(); q2.enqueue("A"); q2.enqueue("E"); q2.enqueue("M"); q2.enqueue("P"); q2.enqueue("S"); q2.enqueue("X"); ///contains A E M P S X (tail)
        Queue q3 = new ListQueue<Integer>(); q3.enqueue(5); q3.enqueue(12); q3.enqueue(15); q3.enqueue(17); q3.enqueue(20); //contains 5, 12, 15, 17, 20 (tail)
        Queue q4 = new ListQueue<Integer>(); q4.enqueue(1); q4.enqueue(4); q4.enqueue(12); q4.enqueue(13); q4.enqueue(16); q4.enqueue(18); //contains 1, 4, 12, 13, 16, 18 (tail)  
        
        //Q1 - sample test cases
        Queue merged1 = mergeQueues(q1, q2);
        System.out.println(merged1.toString());
        Queue merged2 = mergeQueues(q3, q4);
        System.out.println(merged2.toString());
        
        //Q2 - sample test cases
       String [] a = {"S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        sort(a);
        assert isSorted(a);
        show(a);
        
        //Q3 - sample test cases
        String[] b = {"S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        shuffle(b);
        show(b);
       
        shuffle(b);
        show(b);
    }
    
}