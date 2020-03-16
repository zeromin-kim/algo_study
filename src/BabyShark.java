package kr.co.miracom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BabyShark {
    static class Info{
        int x,y;
        public Info(int x, int y){
            this.x = x; this.y = y;
        }
    }
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1,0,1, 0};
    static int[] dy = {0,-1,0,1};
    static int babySize=2;
    static int result;
    static Info start ;
    static ArrayList<Info> arr = new ArrayList<>();

    public static void main(String...args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        visited = new boolean[N][N];

        for(int i= 0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int  j=0 ; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]==9) {
                    start = new Info(i,j);
                    map[i][j]=0;
                }
            }
        }
        int exp=0;
        while(true){
            boolean find = bfs();
            if(find == false) break;
            else{
                arr.sort((i1,i2)->{if(i1.x<=i2.x) {return i1.x-i2.x;}else{return i2.y-i1.y;}});
//                arr.sort(new Comparator<Info>() {
//                    @Override
//                    public int compare(Info i1, Info i2) {
//                        //정렬 기준이 위쪽, 왼쪽 우선
//                        if(i1.x<= i2.x){
//                            return i1.x-i2.x;
//                        }else{
//                            return i1.y - i2.y;
//                        }
//                    }
//                });
                Info v = arr.get(0);
                map[v.x][v.y] = 0;
                exp++;
                if(exp == babySize){
                    babySize++;
                    exp = 0;
                }
                arr.clear();
                start = v;
            }
        }
        System.out.println(result);

        br.close();
    }
    public static boolean bfs() {
        ArrayDeque<Info> q = new ArrayDeque<>(1000);
        Arrays.stream(visited).forEach(a->Arrays.fill(a,false));

        q.offer(start);
        visited[start.x][start.y] = true;
        int dist = 0;
        while(!q.isEmpty()) {
            dist++;
            int size = q.size();
            for(int s = 0; s<size;s++){
                Info temp = q.poll();
                for(int i=0; i<4; i++) {
                    int nx = temp.x + dx[i];
                    int ny = temp.y + dy[i];
                    if(nx >=N || ny >=N || nx<0|| ny<0) continue;
                    if(visited[nx][ny] ) continue;
                    if(map[nx][ny] > babySize) continue;
                    if(map[nx][ny] !=0 && map[nx][ny]< babySize){
                        arr.add(new Info(nx, ny));
                    }
                    q.offer(new Info(nx, ny));
                    visited[nx][ny] = true;
                }
            }
            if(arr.size() !=0){
                result += dist;
                return true;
            }
        }
        return false;
    }
}




