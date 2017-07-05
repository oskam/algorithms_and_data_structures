
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DijkstraShortestPath {
    public Graph graph;
    
    public DijkstraShortestPath(Graph g) {
        this.graph = g;
    }
    
    public Graph dijkstra(Vertice s) {
        PriorityQueue<Vertice> q;
        
        for (Vertice v : graph.vertices) {
            v.dist = Double.POSITIVE_INFINITY;
        }
        graph.vertices[s.id].dist = 0.;
        
        q = new PriorityQueue<>(graph.vertices);
        
        while (!q.isEmpty()) {
            Vertice u = q.extractMin();
            
            for (Edge e : u.edges) {
                if (graph.vertices[e.end.id].dist > (graph.vertices[e.start.id].dist + e.weight)) {
                    graph.vertices[e.end.id].dist = graph.vertices[e.start.id].dist + e.weight;
                    graph.vertices[e.end.id].prev = u;
                    q.decreaseKey(q.indexes.get(e.end), graph.vertices[e.end.id]);
                }
            }
        }
        
        return graph;
    }
    
    public List<Edge> getShortestPathTo(Vertice target) {
        List<Edge> path = new ArrayList<>();
        Vertice vertex = graph.vertices[target.id];
        while (vertex.prev != null) {
            for (Edge e : vertex.prev.edges)
                if (e.end.equals(vertex))
                    path.add(e);
            vertex = graph.vertices[vertex.prev.id];
        }

        Collections.reverse(path);
        return path;
    }
}
