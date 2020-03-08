import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main {
    static int N, M;
    static boolean visit[][];
    static boolean result[];
    static int value =0;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ArrayList<Integer> Heavy[] = new ArrayList[N+1];
        ArrayList<Integer> Light[] = new ArrayList[N+1];

        visit = new boolean[2][N+1];
        result = new boolean[N+1];

        for(int i= 0; i<N+1; i++) {
            Heavy[i] = new ArrayList<Integer>();
            Light[i] = new ArrayList<Integer>();
        }
        for(int i= 0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            Heavy[a].add(b);
            Light[b].add(a);
        }

        for(int i = 1 ; i<=N; i++){
            Arrays.fill(visit[0], false);
            Arrays.fill(visit[1], false);
            visit[0][i] = visit[1][i] = true;

            int High = dfs(i, Heavy, 0);
            int Low = dfs(i, Light, 1);

            if(High > (N+1)/2 || Low >(N+1)/2){
                result[i] = true;
            }
        }
        for(int i=0;i<=N; i++){
            if(result[i]){
                value++;
            }
        }
        System.out.println(value);
        br.close();
    }

    static int dfs(int number, ArrayList<Integer>[] arr, int row){
        int lank = 1;
        for(int i = 0; i < arr[number].size(); i++){
            int temp = arr[number].get(i);
            if(!visit[row][temp]){
                visit[row][temp] = true;
                lank += dfs(temp, arr, row);
            }
        }
        return lank;
    }
}
