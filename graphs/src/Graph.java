

public class Graph {
    public Vertice[] vertices;
    public Edge[] edges;
    
    public Graph( Vertice[] vertices, Edge[] edges ) {
        this.vertices = vertices;
        this.edges = edges;
        
        for (Edge e : edges) {
            vertices[e.start.id].addEdge(e);
        }
    }
}
