import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Alphabet {
    static int R, C;
    static int []dx = {-1,1, 0,0};//상하좌우
    static int []dy = {0,0,-1,1};
    static char[][] map;
    static boolean[] visited;
    static int dist=1;
    static int cnt=1;

    public static void main(String...args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R+1][C+1];
        visited = new boolean[26];

        for(int i =1; i<=R; i++){
            st = new StringTokenizer(br.readLine());
            String temp = st.nextToken();
            for(int j=1;j<=C;j++){
                map[i][j] = temp.charAt(j-1);
            }
        }

        dfs(map, visited,1,1);
        System.out.println(dist);


    }

    public static void dfs(char[][]map, boolean[] visited, int x, int y) {
        int idx = (int) map[x][y] - 65;
        visited[idx] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx <= 0 || ny <= 0 || nx > R || ny > C) continue;

            if (!visited[(int) map[nx][ny] - 65]) {
                dist = Math.max(dist, ++cnt);
                dfs(map, visited, nx, ny);
            }
        }
        cnt--;
        visited[idx] = false;
    }





}
