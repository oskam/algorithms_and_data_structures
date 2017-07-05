

import java.util.ArrayList;
import java.util.List;

public class PrimSpanningTree {
    public Graph graph;
    
    public PrimSpanningTree( Graph g ) {
        this.graph = g;
    }
    
    public Graph prim() {
        PriorityQueue<Vertice> q;
        
        for (Vertice v : graph.vertices) {
            v.dist = Double.POSITIVE_INFINITY;
        }
        graph.vertices[0].dist = 0.;
        
        q = new PriorityQueue<>(graph.vertices);
        
        while (!q.isEmpty()) {
            Vertice u = q.extractMin();
            
            for (Edge e : u.edges) {
                if (q.indexes.get(e.end) != null && e.weight.compareTo(graph.vertices[e.end.id].dist) < 0) {
                    graph.vertices[e.end.id].prev = u;
                    graph.vertices[e.end.id].dist = e.weight;
                    q.decreaseKey(q.indexes.get(e.end), graph.vertices[e.end.id]);
                }
            }
        }
        
        return graph;
    }
    
    public List<Edge> getMinimalSpanningTree() {
        List<Edge> tree = new ArrayList<>();
        
        for (Vertice v : graph.vertices) {
            if (v.prev == null)
                continue;
            else {
                tree.add(new Edge(v, v.prev, v.dist));
            }
        }
        
        return tree;
    }
}
