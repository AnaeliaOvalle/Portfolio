class Graphtest {
    
    public static final int GRAPH_SIZE = 15;
    public static final double EDGE_PERCENT = 0.3;
//
    public static void DFS(Graph G, int Parent[], int startVertex, boolean Visited[]) 
    {
    	
      	Edge temp;
        
    	Visited[startVertex] = true;
    	
    	for (temp = G.edges[startVertex]; temp != null; temp = temp.next) {
	    	if (!Visited[temp.neighbor]){
	    		Parent[temp.neighbor]=startVertex;
//	    		System.out.println("Vertex is: "+temp.neighbor);
//	    		System.out.println("Parent is: "+startVertex);
	    		DFS(G, Parent, temp.neighbor, Visited);
	    	}
	    }
    	
    	
    }

    public static void PrintPath(int Parent[], int endvertex) 
    {
    	buildPath(Parent, endvertex);
    	
    	System.out.println();
    
    	
    }

    private static void buildPath(int[] Parent, int endvertex) {
		if (Parent[endvertex]==-1){
			System.out.print(endvertex);
			return;
		}
		buildPath(Parent, Parent[endvertex]);
		System.out.print("-->"+endvertex);
		
	}

	public static void BFS(Graph G, int Parent[], int startVertex, boolean Visited[]) 
    {
		
		
    	int nextVertex;
    	
    	ArrayQueue queue= new ArrayQueue();
    	
    	queue.enqueue(startVertex);
    	Visited[startVertex]=true;
   
    	while(!queue.empty()){
    		nextVertex= ((int)queue.dequeue());
    		System.out.println("Dequed: "+nextVertex);
    		
    			//now get adjacent neighbors of vertex
    			for (Edge temp=G.edges[nextVertex]; temp!=null; temp=temp.next){
    				
        			if(!Visited[temp.neighbor]){ //how to say if that edge hasn't been visited?
            			
            			Visited[temp.neighbor]=true;
            	
//	    				System.out.println("Vertex: "+nextVertex);
//	    				System.out.println("Neighbor: "+(int)temp.neighbor);
	    				
	    				//parent of the neigbor is the original vertex
	    				Parent[(int)temp.neighbor]= nextVertex;
	    				
	    				//enqueue the following neighbor
	    				queue.enqueue((int)temp.neighbor);
	    				
	    				System.out.println("Enqueued: "+(int)temp.neighbor);
    				
        			}
    			}
    		}
    			
    	}
    
    

    public static void main(String args[]) 
    {
        boolean Visited[] = new boolean[GRAPH_SIZE];
        int Parent[] = new int[GRAPH_SIZE];
        Graph G = new Graph(GRAPH_SIZE);
        int i;
        for (i=0; i<G.numVertex;i++) 
        {
            Visited[i] = false;
            Parent[i] = -1;
        }
	        G.randomize(EDGE_PERCENT);
	        G.print();
	        
	        System.out.println("\nBreadth-First Search Initialized.\n");
	        BFS(G,Parent,0,Visited);
	        System.out.println("----------------");
	        System.out.println("BFS:");
	        System.out.println("----------------");
	        
	        
        for (i=0; i<G.numVertex;i++) 
        {
            System.out.println("Path from 0 to " + i + ":");
//            System.out.println("Get this parent: "+Parent[i]+" for vertex: "+i);
            PrintPath(Parent,i);
            
        }
        for (i=0; i<G.numVertex;i++) 
        {
            Visited[i] = false;
            Parent[i] = -1;
        }
        	System.out.println("\nDepth-First Search Initialized.\n");
	        DFS(G,Parent,0,Visited);
	        System.out.println("----------------");
	        System.out.println("DFS:");
	        System.out.println("----------------");
        for (i=0; i<G.numVertex;i++) 
        {
		    System.out.println("Path from 0 to " + i + ":");
//		    System.out.println("Get this parent: "+Parent[i]+" for vertex: "+i);
		    PrintPath(Parent,i);
        }
    }
}