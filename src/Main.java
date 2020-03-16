package kr.co.miracom;

import java.io.*;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static int K,W,H;
    static int [][] map;
    static boolean[][][] visit;

    static int[] dx = {1,-1, 0, 0};
    static int[] dy = {0,0,1,-1};
    static int[] hx = {-2,-2,2,2,1,-1,1,1};
    static int[] hy = {1,-1,1,-1,2,2,-2,-2};
    static BufferedReader br;
    static BufferedWriter bw;
    static class Info    {
        int x;
        int y;
        int cnt;
        int k;
        Info(int x, int y , int cnt, int k){
            this.x = x;this.y=y; this.cnt = cnt; this.k=k;
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[H][W];
        visit = new boolean[H][W][31];
        for(int i= 0; i<H;i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<W; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visit[0][0][0] = true;
        bfs();
        br.close();
        bw.flush();
        bw.close();
    }
    public static void bfs() throws IOException{
        ArrayDeque<Info> q = new ArrayDeque<Info>(1000);
        q.offer(new Info(0,0,0,K));
        while(!q.isEmpty()){
            Info cur = q.poll();
            int curX = cur.x;
            int curY = cur.y;
            int cnt = cur.cnt;
            int curK = cur.k;

            if(curX == W-1 && curY == H-1){
                bw.write(String.valueOf(cnt));
                return ;
            }
            if(curX >= W || curY >=H || curX <0 || curY <0) continue; //범위밖
            if(map[curY][curX]==1) continue; //장애물
            if(visit[curY][curX][curK]) continue; //이미방문
            visit[curY][curX][curK] = true;

            for(int  i=0; i<4; i++){
                int nextX = curX + dx[i];
                int nextY = curY + dy[i];
                q.offer(new Info(nextX, nextY, cnt+1, curK));
            }
            if(curK == 0) continue;
            for(int  i = 0; i<8; i++){
                int nextX = curX + hx[i];
                int nextY = curY + hy[i];
                q.offer(new Info(nextX, nextY, cnt+1, curK-1));
            }

        }
        bw.write("-1");
        return;
    }
}
