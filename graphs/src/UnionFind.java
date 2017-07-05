

import java.util.HashMap;
import java.util.Map;

public class UnionFind {

	public Map<Object, Element> map;

	public UnionFind(Object[] objects) {
		map = new HashMap<Object, Element>();
		
		for (Object o : objects) {
			Element e = new Element(o);
			map.put(o, e);
		}
	}

	public Element find(Element x) {

		if (!x.parent.equals(x)) {
			x.parent = find(x.parent);
		}
		return x.parent;

	}

	public void union(Element x, Element y) {

		Element xRoot = find(x);
		Element yRoot = find(y);
		
		if (xRoot.equals(yRoot))
			return;

		// x and y are not already in same set. Merge them.
		if (xRoot.rank < yRoot.rank) {
			xRoot.parent = yRoot;
		} else if (xRoot.rank > yRoot.rank) {
			yRoot.parent = xRoot;
		} else {
			yRoot.parent = xRoot;
			xRoot.rank++;
		}
	}
}