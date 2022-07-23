import java.util.Random;

/**
 * This following program is for testing insertion sort and
 * shell sort algorithms and benchmarking both algorithms
 * using three different generated data
 * 
 * Completion time: 5 hours
 *
 * @author Mohammad Hamdan, Acuna, Sedgewick
 * @version 1.1
 */
 class Stopwatch { 

    private final long start;

    /**
     * Initializes a new stopwatch.
     */
    public Stopwatch() {
        start = System.nanoTime();
    } 

    /**
     * Returns the elapsed CPU time (in seconds) since the stopwatch was created.
     *
     * @return elapsed CPU time (in seconds) since the stopwatch was created
     */
    public double elapsedTime() {
        long now = System.nanoTime();
        return (now - start) / 1000000000.0;
    }
} 

public class HamdanNonUniform implements SER222_02_01_HW02_Submission {
    
    /***************************************************************************
     * START - SORTING UTILITIES, DO NOT MODIFY (FROM SEDGEWICK)               *
     **************************************************************************/
    
    public static void insertionSort(Comparable[] a) {
        int N = a.length;
        
        for (int i = 1; i < N; i++)
        {
            // Insert a[i] among a[i-1], a[i-2], a[i-3]... ..          
            for (int j = i; j > 0 && less(a[j], a[j-1]); j--)
                exch(a, j, j-1);
        }
    }
    
    
    public static void shellsort(Comparable[] a) {
        int N = a.length;
        int h = 1;
        
        while (h < N/3) h = 3*h + 1; // 1, 4, 13, 40, 121, 364, 1093, ...
        
        while (h >= 1) {
            // h-sort the array.
            for (int i = h; i < N; i++) {
                // Insert a[i] among a[i-h], a[i-2*h], a[i-3*h]... .
                for (int j = i; j >= h && less(a[j], a[j-h]); j -= h)
                exch(a, j, j-h);
            }
            h = h/3;
        }
    }
    
    
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    
    
    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i]; a[i] = a[j]; a[j] = t;
    }
    
    /***************************************************************************
     * END - SORTING UTILITIES, DO NOT MODIFY                                  *
     **************************************************************************/
   

    //TODO: implement interface methods.

    private static void show(Comparable[] a) {
    	for(int i = 0; i < a.length; i++)
    	System.out.print(a[i] + " ");
    	System.out.println();
    	}

	
	public Integer[] generateTestDataBinary(int size) {
		
		Integer[]a= new Integer [size];
		
		for (int i=0; i<a.length; i++) {
			
			if (i<(a.length)/2)
				a[i]=0;
		
			else a[i]=1; 
	}
		return a; 
	}

	
	public Integer[] generateTestDataHalfs(int size) {
		
            Integer[]a= new Integer [size];
            
		    int half = (size / 2) + (size % 2);
		    int index = half;
		    int val = 0;
		    
		    for (int i = 0; i < size; i++) {
		        if (i == index) {
		            half = (half / 2) + (half % 2);
		            index = index + half;
		            val++;
		        }
		        a[i] = val;
		    }
		    return a;
	   
	        }

	


	
	public Integer[] generateTestDataHalfRandom(int size) {
        
		Random rand= new Random(); 
		Integer[]a= new Integer [size];
		
		for (int i=0; i<a.length; i++) {
			
			if (i<(a.length)/2)
				a[i]=0;
		
			else a[i]= rand.nextInt(); 
	}
		return a; 
	}


	
	public double computeDoublingFormula(double t1, double t2) {
		
		double ratio = t2/t1;
		double b =  (double) (Math.log(ratio) / Math.log(2));
		return b; 
	}


	@Override
	public double benchmarkInsertionSort(Integer[] small, Integer[] large) {
		
		Stopwatch stopwatch1 = new Stopwatch();
        insertionSort(small);
        double time1 = stopwatch1.elapsedTime();
        
        Stopwatch stopwatch2 = new Stopwatch();
        insertionSort(large);
        double time2 = stopwatch2.elapsedTime();
        
      double b =  computeDoublingFormula(time1, time2);
        
        return b; 
        
        
	}


	
	public double benchmarkShellsort(Integer[] small, Integer[] large) {
		
		 Stopwatch stopwatch1 = new Stopwatch();
	        shellsort(small);
	        double time1 = stopwatch1.elapsedTime();
	        
	        Stopwatch stopwatch2 = new Stopwatch();
	        shellsort(large);
	        double time2 = stopwatch2.elapsedTime();
	        
	        double b =  computeDoublingFormula(time1, time2);
	        
	        return b; 
        
	}


	
	public void runBenchmarks(int size) {
	
		int doubleSize = size*2;
		
		    Integer[] a1 = generateTestDataBinary(size);
		    Integer[] a2 = generateTestDataBinary(doubleSize);
		    Integer[] a3 = generateTestDataHalfs(size);
		    Integer[] a4 = generateTestDataHalfs(doubleSize);
		    Integer[] a5 = generateTestDataHalfRandom(size);
		    Integer[] a6 = generateTestDataHalfRandom(doubleSize);
	        
		 double  b1 =  benchmarkInsertionSort(a1, a2);
		 double  b2 =  benchmarkInsertionSort(a3, a4);
		 double  b3 = benchmarkInsertionSort(a5, a6);
		    
		 double  b4 = benchmarkShellsort(a1,a2);
		 double  b5 = benchmarkShellsort(a3,a4);
		 double  b6 =  benchmarkShellsort(a5,a6);
		    
		    System.out.println("           Insertion                      shellsort");
		    System.out.println("Bin"+"        "+b1+"           " + b4          );
		    System.out.println("Half"+"        "+b2+"           " + b5          );
		    System.out.println("RanInt"+"      "+b3+"           " + b6          );
	       
	}
	


	public static void main(String args[]) {
		
	        SER222_02_01_HW02_Submission me = new HamdanNonUniform();
	        int size = 4096;
	        
	        //NOTE: feel free to change size here. all other code must go in the
	        //      methods.
	        
	       me.runBenchmarks(size);
	    }
}