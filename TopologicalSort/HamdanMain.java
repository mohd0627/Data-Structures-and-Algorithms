/**
 * Program for generating kanji component dependency order via topological sort.
 * 
 * @author Mohammad Hamdan, Acuna
 * @version 1.0
 */

import  java.io.*;
import java.util.HashMap; 


public class HamdanMain {
	
	 

	
	
	private static void printFormatted(HashMap<Integer, Integer> map, int i) {
		
        System.out.print(String.valueOf(Character.toChars(map.get(i))));
    }
	
	
	
	
    /**
     * Entry point for testing.
     * @param args the command line arguments
     * @throws IOException 
     * @throws NumberFormatException 
     */
    public static void main(String[] args) throws NumberFormatException, IOException {
      
        //Freebie: this is one way to load the UTF8 formated character data.
        //BufferedReader indexReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("data-kanji.txt")), "UTF8"));
    	
    	BetterDiGraph	G = new BetterDiGraph(); 
    	
    	File file1 = new File("data-kanji.txt");
    	File file2 = new File("data-components.txt");
    	 HashMap<Integer, Integer> map = new HashMap<>();
    	 
    	 BufferedReader br1 = new BufferedReader(new FileReader(file1)); 
    	 
    	

         String currentLine;
         
         while ((currentLine = br1.readLine()) != null) {
         	
             if (!currentLine.contains("#")) {
            	 
                 String[] splitString = currentLine.split("\t");

                 int id = Integer.parseInt(splitString[0]);
                 int kanjiId = Character.codePointAt(splitString[1], 0);
                 map.put(id, kanjiId);
                 G.addVertex(id);
             }
         }

	        br1.close();
	        System.out.println("Original:");
	        for (Integer i : G.vertices()) {
                printFormatted(map, i);
            }
	      
    	 BufferedReader br2 = new BufferedReader(new FileReader(file2)); 
    	 String currentLine2;
         while ((currentLine2 = br2.readLine()) != null) {
             if (!currentLine2.contains("#")) {
                 String[] splitString2 = currentLine2.split("\t");

                 int v = Integer.parseInt(splitString2[0]);
                 int w = Integer.parseInt(splitString2[1]);

                 G.addEdge(v, w);
             }
         }
    	 
         br2.close(); 
         
         
         TopologicalSort topologicalSort = new IntuitiveTopological(G);

         System.out.println("\nSorted:");
         for (Integer i : topologicalSort.order()) {
             printFormatted(map, i);
         }
    	 
    }
}