package kr.co.miracom;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class FindBeed {
    static int N,M;
    static int cnt;
    static boolean result[];
    static boolean visit[][];
    static ArrayList<Integer>[] Heavy;
    static ArrayList<Integer>[] Light;

    public static void main(String...args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        result = new boolean[N+1];
        visit = new boolean[2][N+1];
        Heavy = new ArrayList[N+1];
        Light = new ArrayList[N+1];

        for(int i =0; i<=N; i++){
            Heavy[i] = new ArrayList<Integer>();
            Light[i] = new ArrayList<Integer>();
        }

        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            Heavy[a].add(b);
            Light[b].add(a);
        }

        for(int i=1; i<=N; i++) {
            Arrays.fill(visit[0], false);
            Arrays.fill(visit[1], false);
            visit[0][i] = true;
            visit[1][i] = true;

            int High = dfs(i, Heavy, 0);
            int Low = dfs(i, Light, 1);

            int Mid =  (N + 1) / 2;
            if (High > Mid || Low > Mid) {
                result[i] = true;
            }
        }
        for(int i =0; i<=N; i++){
            if(result[i]){
                cnt++;
            }
        }
        System.out.println(cnt);
        br.close();

    }

    static  int dfs(int n, ArrayList<Integer>[] arr, int row){
        int result = 1;
        for(int i=0; i < arr[n].size(); i++){
            int temp = arr[n].get(i);
            if(!visit[row][temp]){
                visit[row][temp] = true;
                result+= dfs(temp, arr, row);
            }
        }
        return result;
    }
}
