

import java.util.List;
import java.util.ArrayList;

public class KruskalSpanningTree {
	public Graph graph;
	private List<Edge> mst;

	public KruskalSpanningTree(Graph g) {
		this.graph = g;
	}

	public void kruskal() {
		UnionFind uf;
		PriorityQueue<Edge> q;

		uf = new UnionFind((Object[]) graph.vertices);
		q = new PriorityQueue<>(graph.edges);
		
		mst = new ArrayList<>();

		while (mst.size() < graph.vertices.length - 1) {
			Edge e = q.extractMin();
			Element u = uf.map.get(e.start);
			Element v = uf.map.get(e.end);
			
			if (!uf.find(u).equals(uf.find(v))) {
				mst.add(e);
				uf.union(u, v);
			}
		}
	}

	public List<Edge> getMinimalSpanningTree() {
		return mst;
	}
}

// MST_KRUSKAL(G)
//
// for each vertex v in V[G]
// do define set S(v) ← {v}
// Initialize priority queue Q that contains all edges of G, using the weights
// as keys
// A ← { } ▷ A will ultimately contains the edges of the MST
// while A has less than n − 1 edges
// do Let set S(v) contains v and S(u) contain u
// if S(v) ≠ S(u)
// then Add edge (u, v) to A
// Merge S(v) and S(u) into one set i.e., union
// return A