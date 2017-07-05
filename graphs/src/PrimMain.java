

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PrimMain {
    public static Graph createGraph(String path) throws IOException {
        List<String> lines = readFile(path);
        int verticesNum = Integer.parseInt(lines.remove(0));
        int edgesNum = Integer.parseInt(lines.remove(0));
        Vertice[] vertices = new Vertice[verticesNum];
        Edge[] edges = new Edge[edgesNum * 2];
        
        for (int vId = 0; vId < verticesNum; vId++) {
            vertices[vId] = new Vertice(vId);
        }
        
        for (int eId = 0; eId < edgesNum * 2;) {
            String[] edge = lines.remove(0).split(" ");
            Vertice start = vertices[Integer.parseInt(edge[0])];
            Vertice end = vertices[Integer.parseInt(edge[1])];
            double weight = Double.parseDouble(edge[2]);
            edges[eId++] = new Edge(start, end, weight);
            edges[eId++] = new Edge(end, start, weight);
        }
        
        return new Graph(vertices, edges);
    }
    
    private static List<String> readFile(String path) throws IOException {
        File file = new File(path);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        List<String> lines = new ArrayList<>();
        
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        
        reader.close();
        
        return lines;
    }
    
    public static void main(String[] args) throws IOException {
        PrimSpanningTree pMST = new PrimSpanningTree(createGraph(args[0]));
        
        pMST.prim();
        
        List<Edge> tree = pMST.getMinimalSpanningTree();
        
        double treeWeight = 0.;
        
        for (Edge e : tree) {
            treeWeight += e.weight;
            System.out.println(e.toString());
        }
        
        System.out.println("Tree weight: " + String.format("%.5f", treeWeight));
        
    }
    
}
