
class Graph 
{
    public int numVertex;
    public Edge edges[];

    public Graph(int numVerteces) 
    {
		int i;
		numVertex = numVerteces;
		edges = new Edge[numVerteces];
		
		for (i=0; i<numVertex;i++)
		{
		    edges[i] = null;
		}
    }

    public void insert(int vertex1, int vertex2, int cost) 
    {
		    edges[vertex1] = new Edge(vertex2, edges[vertex1],cost);
    }
    
}