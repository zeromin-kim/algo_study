import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayDeque;
import java.util.Stack;

public class BreathFirstPaths {
    private boolean[]   marked;
    private int[]       edgeTo;
    private final int   s;

    public BreathFirstPaths(Graph G, int s){
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        bfs(G,s);
    }

    private void bfs(Graph G, int s){
        ArrayDeque<Integer> q = new ArrayDeque<>();
        marked[s] = true;
        q.offer(s);
        while(!q.isEmpty()){
            int v = q.poll();
            for(int w : G.adj(v)){
                if(!marked[w]){
                    edgeTo[w]=v;
                    marked[w] = true;
                    q.offer(w);
                }
            }
        }
    }

    public boolean hasPathTo(int v){
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v){
        if(!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<>();
        for(int x = v; x!=s; x=edgeTo[x]){
            path.push(x);
        }
        path.push(s);
        return path;
    }

}
