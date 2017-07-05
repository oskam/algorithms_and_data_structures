
public class Edge implements Comparable<Edge>{
    public final Vertice start;
    public final Vertice end;
    public final Double weight;
    
    public Edge( Vertice start, Vertice end, double weight ) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
    
    @Override
    public int compareTo(Edge e) {
        return Double.compare(weight, e.weight);
    }
    
    @Override
    public String toString() {
    	return "{" + start.id + "," + end.id + "} " + String.format("%.5f", weight);
    }
}
