import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Dijkstra {
	
	public static void main (String args[]) throws IOException, IllegalArgumentException{
		
		//check to make sure user entered a file
		if (args.length == 0) {
			
			throw new IllegalArgumentException("No arguments detected");

		}
		
		int size=0;
		BufferedReader input;
		String nextLine;
		int intvalue;
		
		//make a hashtable!
		HashTable theHash=new HashTable(); 
		
		try {
		    input = new BufferedReader(new FileReader(args[0]));
		    
		    nextLine = input.readLine();
		    
		    while (nextLine.compareTo(".") != 0) {  
		    	size++;
			    theHash.insert(nextLine);
				
				//add vertex to the name table
				nextLine = input.readLine();
		    	}
		    
		    
		    //create a new graph
			Graph theGraph= new Graph(size); //make a graph!
		    nextLine = input.readLine();
		    
		    while (nextLine != null) {
		    	
		    	String vertex1= nextLine;
				nextLine = input.readLine();				
				String vertex2= nextLine;			
				nextLine = input.readLine();
				
				//int value is the value between vertex 1 and vertex 2
				intvalue = Integer.valueOf(nextLine).intValue();
				
				theGraph.insert((int) theHash.find(vertex1), (int) theHash.find(vertex2), intvalue);
				nextLine = input.readLine();
		    	}
		    
		    //view array of the names
//		    System.out.println(Arrays.toString(theHash.names));
		    
		    //print the graph
		   	printGraph(theGraph, theHash);
		    
		   	//make a binomial queue based off the size
		    BinomialQueue Q = new BinomialQueue(size); 
		    
		    //make a dijkstra table
			tableEntry[] theTable= new tableEntry[size]; 
			
			
			//the vertex to start the dijstra table from
			int initialVertex= (int) theHash.find("SF");
			
			theTable= createDijkstraTable(theGraph, initialVertex, theTable, theHash);
			
			buildDijkstra(Q, theGraph, initialVertex , theTable, theHash);
		    
		} catch (IOException e) {
			
		    System.out.println("File Error");
		}
		
	

	}

	private static tableEntry[] createDijkstraTable(Graph theGraph, int initialVertex, tableEntry[] theTable, HashTable theHash) {

		for(int j=0; j<theGraph.numVertex; j++){
			
			theTable[j]=new tableEntry();
		}

		for(int i=0; i<theGraph.numVertex; i++) {
			
			if(i==initialVertex){
				
				theTable[initialVertex].distance = 0;
			}
			else{
			
				theTable[i].distance = Integer.MAX_VALUE;
				theTable[i].path = -1;
				theTable[i].known = false;
				
			}		
		}
		
		return theTable;
	}
	
	private static void buildDijkstra(BinomialQueue Q, Graph theGraph, int initialVertex, tableEntry theTable[], HashTable theHash){
	
		Edge e;
		int next=0; 
	
		for (int i = 0; i < theGraph.numVertex; i++)
		{
			if (i==initialVertex){
				
				Q.insertElem(i, 0);
			}
			else{
				
				Q.insertElem(i, Integer.MAX_VALUE);
			}

		}
		
		Q.printQueue();	

		System.out.println();
		
		while(!Q.isEmpty()){
			
			next = Q.removeSmallest();
			
			theTable[next].known=true;
			
			//update the cost of the vertices in Q
			for (e=theGraph.edges[next]; e!=null; e=e.next){
					
					if(theTable[e.neighbor].distance> theTable[next].distance+e.cost){
						
						Q.decreaseKey(e.neighbor, theTable[next].distance+e.cost);
						theTable[e.neighbor].distance=theTable[next].distance+e.cost;
						theTable[e.neighbor].path=next;
					}				
				
			}
			
			
		}
		printTable(theTable,theGraph, theHash);
		printShortestPaths(theTable, theGraph, theHash);
				
		
	}
	
	private static void printShortestPaths(tableEntry[] theTable, Graph theGraph, HashTable theHash) {
		
		System.out.println("******Shortest Paths******");
		
		for(int i=0; i<theGraph.numVertex; i++){
			
			System.out.printf("%-3s %3d: ", theHash.names[i],theTable[i].distance);
			buildPath(theTable, theHash, i);
			System.out.println();
		}
		
	}

	public static void printTable(tableEntry[] theTable, Graph theGraph, HashTable theHash){
		System.out.println();
		System.out.println("******Dijkstra Table******");
		System.out.printf("%s %5s %6s %6s \n","Vertex ", "Known", "Cost","Path");
	
		for(int i=0; i<theGraph.numVertex; i++) {
		
			System.out.print(i+": "+theHash.names[i]);
			System.out.printf("\t"+theTable[i].known);
			System.out.print("\t"+theTable[i].distance);
			System.out.print("\t"+theTable[i].path);
			System.out.println();
		}
		System.out.println();
	}
	

	
    public static void printGraph(Graph theGraph, HashTable theHash) 
    {
    	System.out.println("******Original Graph*******");
    	int i;
    	for (i=0; i<theGraph.numVertex; i++) {
    		System.out.print(theHash.names[i]+": ");
    		
    		if (theGraph.edges[i] == null)
            {
	    		System.out.println("<empty>");
            }
    		else 
            {
				for (Edge tmp = theGraph.edges[i]; tmp != null; tmp = tmp.next){
				    System.out.print("-> "+theHash.names[tmp.neighbor]+": "+tmp.cost);
				}
				System.out.println();
            }
    	}
    	
    	System.out.println();
    }
    

    private static void buildPath(tableEntry[] theTable, HashTable theHash, int endVertex) {
		if (theTable[endVertex].path==-1){
			
			System.out.print(theHash.names[endVertex]);
			return;
		}
		
		buildPath(theTable, theHash, theTable[endVertex].path);
		
		System.out.print("-->"+theHash.names[endVertex]);
		
	}

}

