import java.util.Scanner;
import java.util.Stack;

public class ShortestPath {
    
    private Graph graph;

    private class Graph{

        private final int V, E;
        private final Edge[] edges;

        public Graph(int V, int E) {
            this.V = V;
            this.E = E;
            this.edges = new Edge[E];
        }

        private class Edge{
            int src, dest, weight;

            Edge(int src, int dest, int weight){
                this.src = src;
                this.dest = dest;
                this.weight = weight;
            }
        }
        
        private void addEdge(int[] src, int[] dest, int[] weight){
            for(int i=0; i<this.E; ++i)
                this.edges[i] = new Edge(src[i], dest[i], weight[i]);
        }
         
    }
    
    public ShortestPath(int V, int E, int[] src, int[] dest, int[] weight) {
        this.graph = new Graph(V, E);
        this.graph.addEdge(src, dest, weight);
    }
    
    public Iterable<Integer> BellmanFord(int src, int dest) 
        { 
            int[] dist = new int[this.graph.V]; 
            int[] edgeTo = new int[this.graph.V];

            for (int i = 0; i < this.graph.V; ++i) 
                dist[i] = Integer.MAX_VALUE; 
            dist[src] = 0;
            edgeTo[src] = -1;

            for (int i = 1; i < this.graph.V; ++i) { 
                for (int j = 0; j < this.graph.E; ++j) { 
                    int u = this.graph.edges[j].src; 
                    int v = this.graph.edges[j].dest; 
                    int weight = this.graph.edges[j].weight; 
                    if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]){ 
                        dist[v] = dist[u] + weight; 
                        edgeTo[v] = u;
                    }
                } 
            } 

            for (int j = 0; j < this.graph.E; ++j) { 
                int u = this.graph.edges[j].src; 
                int v = this.graph.edges[j].dest; 
                int weight = this.graph.edges[j].weight; 
                if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) { 
                    return null; 
                } 
            }
            
            Stack<Integer> path = new Stack<>();
            int u = dest;
            path.push(u);
            while(edgeTo[u] != -1){
                path.push(edgeTo[u]);
                u = edgeTo[u];
            }
            
            return path;
        }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the no. of vertices and edges: ");
        int v = sc.nextInt();
        int e = sc.nextInt();
        int[] src = new int[e], dest = new int[e], weight = new int[e];
        System.out.println("Enter source, destination and weight for each edge");
        for(int i=0; i<e; ++i){
            src[i] = sc.nextInt();
            dest[i] = sc.nextInt();
            weight[i] = sc.nextInt();
        }
        ShortestPath sp = new ShortestPath(v, e, src, dest, weight);
        System.out.println("Enter source and destination: ");
        int s = sc.nextInt();
        int d = sc.nextInt();
        sc.close();
        Iterable<Integer> itr = sp.BellmanFord(s, d);
        if(itr == null)
            System.out.println("Negative cycles exist");
        else{
            for(int i: itr){
                System.out.print(i + " -> ");
            }
        }
    }
    
}
