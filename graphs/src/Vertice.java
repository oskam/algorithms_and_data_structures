
import java.util.ArrayList;
import java.util.List;

public class Vertice implements Comparable<Vertice> {
    public final int id;
    public Vertice prev;
    public Double dist;
    public List<Edge> edges;
    
    public Vertice( int id ) {
        this.id = id;
        this.dist = Double.POSITIVE_INFINITY;
        this.edges = new ArrayList<>();
    }
    
    public void addEdge(Edge e) {
        edges.add(e);
    }
    
    @Override
    public int compareTo(Vertice v) {
        return Double.compare(dist, v.dist);
    }
    
    @Override
    public String toString() {
    	return "" + id;
    }
}
