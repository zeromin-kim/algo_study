import java.util.ArrayDeque;
import java.util.ArrayList;

public class Graph {
    private final int V;
    private int E;
    private ArrayList<ArrayList<Integer>> adj;

    public Graph(int v){
        this.V = v; this.E = 0;
        adj = new ArrayList<>();
        for(ArrayList<Integer> a : adj){
            a = new ArrayList<Integer>();
        }
    }

//    public Graph(In in){
//
//    }
    public int V(){return V;}
    public int E(){return E;}
    public void addEdge(int v, int w){
        adj.get(v).add(w);
        adj.get(w).add(v);
        E++;
    }

    public Iterable<Integer> adj(int v){
       return adj.get(v);
    }
}
