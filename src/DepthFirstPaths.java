import java.util.ArrayDeque;
import java.util.Stack;

public class DepthFirstPaths {
    private boolean[]   marked; //dfs()가 해당정점에 호출된 적이 있는가....
    private int[]       edgeTo; //해당 정점으로 가는 경로의 마지막 정점
    private final int   s;      //원점

    public DepthFirstPaths(Graph G, int s){
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        dfs(G,s);
    }

    private void dfs(Graph G, int v){
        marked[v] = true;
        for(int w: G.adj(v)){
            if(!marked[w]){
                edgeTo[w] = v;
                dfs(G,w);
            }
        }
    }

    private void dfs_stqck(Graph G, int v){
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        marked[v] = true;
        stack.push(v);
        while(!stack.isEmpty()){
            int x = stack.pop();
            for(int w: G.adj(x)){
                if(!marked[w]){
                    marked[w] = true;
                    edgeTo[w] = x;
                    stack.push(w);
                }
            }
        }

    }

    public boolean hasPathTo(int v){
        return marked[v];
    }
    public Iterable<Integer> pathTo(int v){
        if(!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        for(int x = v; x!=s; x=edgeTo[x]){
            path.push(x);
        }
        path.push(s);
        return path;
    }

}
