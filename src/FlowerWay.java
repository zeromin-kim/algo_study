package kr.co.miracom;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class FlowerWay {
    static BufferedReader br;
    static int N;
    static int map[][];
    static int []dx = {0,0,1,-1};
    static int []dy = {1,-1,0,0};
    static boolean[][] visited;
    static boolean[][][] checked; //for dfs2
    static int minCost = Integer.MAX_VALUE;

    static class Info{
        int x; int y; int cnt; int sum;
        boolean check[][];
        Info(int x, int y, int cnt, int sum){
            this.x = x; this.y =y; this.cnt = cnt;this.sum = sum;
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        //왜 BufferedWriter 를 쓰면 더 느릴까?
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        //st = new StringTokenizer(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];
        checked = new boolean[3][N][N];
        for(int i= 0; i< N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //dfs(0,0,1);
        dfs3();
        System.out.println(minCost);
    }

    static void dfs(int cnt, int sum, int x){
        if(cnt == 3){
            minCost = Math.min(minCost, sum);
            return;
        }
        for(int i =x; i<N-1;i++){
            for(int j= 1; j<N-1; j++){
                if(visited[i][j]) continue;
                if(!check(i,j)) continue;
                int tempCost = map[i][j];

                visited[i][j] = true;
                for(int k= 0; k<4; k++){
                    visited[i+dx[k]][j+dy[k]] = true;
                    tempCost += map[i+dx[k]][j+dy[k]];
                }

                dfs(cnt+1, sum+tempCost, i);

                visited[i][j] = false;
                for(int k= 0; k<4; k++){
                    visited[i+dx[k]][j+dy[k]] = false;
                }
            }
        }
    }

    static boolean check(int x, int y){
        for(int i= 0 ; i<4; i++){
            int cx = x+dx[i];
            int cy = y + dy[i];
            if(x < 0 || x >=N || y<0 || y>=N||visited[cx][cy]) {
                return false;
            }
        }
        return true;
    }

    static boolean check2(int x, int y, boolean[][] check){
        for(int i= 0 ; i<4; i++){
            int cx = x+dx[i];
            int cy = y + dy[i];
            if(x < 0 || x >=N || y<0 || y>=N||check[cx][cy]) {
                return false;
            }
        }
        return true;
    }

    static void dfs3(){
        ArrayDeque<Info> stack = new ArrayDeque<>(10000);

        for(int i = 1; i<N-1; i++){
            Info info = new Info(i, 1,0,0);
            info.check = new boolean[N][N];
            info.check[i][1] = true;
            stack.push(info);
            while(!stack.isEmpty()){
                Info temp = stack.pop();
                int cnt = temp.cnt;
                int sum = temp.sum;
                int x = temp.x;
                // int y = temp.y;
                boolean [][]checkVisit = temp.check.clone();

                if(cnt == 3){
                    minCost = Math.min(minCost, sum);
                    continue;
                }

                for(int j= 1; j<N-1; j++){
                    if(checkVisit[i][j]) continue;
                    if(!check2(i,j, checkVisit)) continue;
                    int tempCost = map[i][j];


                    checkVisit[i][j] = true;
                    for(int k= 0; k<4; k++){
                        checkVisit[i+dx[k]][j+dy[k]] = true;
                        tempCost += map[i+dx[k]][j+dy[k]];
                    }
                    Info val = new Info(i,j,cnt+1,sum+tempCost);
                    val.check = checkVisit.clone();
                    stack.push(val);

                    checkVisit[i][j] = false;
                    for(int k= 0; k<4; k++){
                        checkVisit[i+dx[k]][j+dy[k]] = false;
                    }

                }

            }
        }
    }

    static void dfs2(){
        ArrayDeque<Info> stack = new ArrayDeque<>(20000);
        Info info = new Info(1,1, 0,0);
        info.check = new boolean[N][N];
        info.check[1][1] = true;
        stack.push(info);

        while(! stack.isEmpty()) {
            Info temp = stack.pop();
            int cnt = temp.cnt;
            int sum = temp.sum;
            int x = temp.x;
           // int y = temp.y;
            boolean [][]checkVisit = temp.check.clone();

            if(cnt == 3){
                minCost = Math.min(minCost, sum);
                continue;
            }
            for(int i =x; i<N-1;i++){
                for(int j= 1; j<N-1; j++){
                    if(checkVisit[i][j]) continue;
                    if(!check2(i,j, checkVisit)) continue;
                    int tempCost = map[i][j];


                    checkVisit[i][j] = true;
                    for(int k= 0; k<4; k++){
                        checkVisit[i+dx[k]][j+dy[k]] = true;
                        tempCost += map[i+dx[k]][j+dy[k]];
                    }
                    Info val = new Info(i,j,cnt+1,sum+tempCost);
                    val.check = checkVisit.clone();
                    stack.push(val);

                    checkVisit[i][j] = false;
                    for(int k= 0; k<4; k++){
                        checkVisit[i+dx[k]][j+dy[k]] = false;
                    }

                }
            }
        }

    }

}
