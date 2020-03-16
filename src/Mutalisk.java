package kr.co.miracom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Mutalisk{
    static int N ;
    static int hp[] = new int[3];
    static int memory[][][] = new int[100][100][100]; //체력은 60보다 작으니까
    static ArrayList<int[]> arr = new ArrayList<>();
    //    static Info start;
//    static class Info{
//        public int arr[];
//        public Info(int a, int b, int c){
//            arr= new int[3];
//            arr[0] = a;
//            arr[1] = b;
//            arr[2] = c;
//        }
//    }
    public static void main(String...args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        // start = new Info(0,0,0);
        st = new StringTokenizer(br.readLine());
        for(int i =0; i<N; i++){
            hp[i]= Integer.parseInt(st.nextToken());
        }
        //bfs()
        for(int i =0; i<100; i++){
            for(int j= 0; j<100; j++){
                for(int k = 0; k<100; k++){
                    memory[i][j][k] = -1;
                }
            }
        }
        LinkedList<Integer> s = new LinkedList<>();
        pick(N,s ,N);

        int count = attack(hp[0], hp[1], hp[2]);

        System.out.println(count);

        br.close();
    }

    static void copyPick(int n, LinkedList<Integer> data){
        int[] temp = new int[n];
        Iterator value = data.iterator();
        int i =0;
        for(Integer x : data){
            temp[i++] = x;
        }
        arr.add(temp);
    }

    static void  pick(int n, LinkedList<Integer> picked, int toPick) {
        if(toPick == 0) {
            copyPick(n, picked);
            return;
        }
        int small = picked.isEmpty()?0:picked.peekLast()+1;
        for(int next = small; next<n; next++) {
            picked.add(next);
            pick(n, picked, toPick-1);
            picked.pollLast();
        }
    }


    public static  int attack(int a, int b, int c){

        if(a<0) return attack(0, b,c);
        if(b<0) return attack(a, 0,c);
        if(c<0) return attack(a, b,0);

        if(a==0 && b==0 && c==0) return 0;
        if(memory[a][b][c]!=-1){ //이미계산된 경우, 공격받은 횟수가 기록이 되어있음...
            return memory[a][b][c];
        }

        memory[a][b][c] = Integer.MAX_VALUE;
        int [] a1={a-9,a-3,a-1};
        int[] b1 ={b-9,b-3,b-1};
        int[] c1 = {c-9, c-3, c-1};

        for(int[] tempArr : arr){
            memory[a][b][c] = Math.min(memory[a][b][c], attack(a1[tempArr[0]], b1[tempArr[1]], c1[tempArr[2]]));
        }
        return memory[a][b][c];


//        memory[a][b][c] = Math.min(memory[a][b][c], attack(a-9, b-3, c-1)+1);
//        memory[a][b][c] = Math.min(memory[a][b][c], attack(a-9, b-1, c-3)+1);
//        memory[a][b][c] = Math.min(memory[a][b][c], attack(a-3, b-9, c-1)+1);
//        memory[a][b][c] = Math.min(memory[a][b][c], attack(a-3, b-1, c-9)+1);
//        memory[a][b][c] = Math.min(memory[a][b][c], attack(a-1, b-9, c-3)+1);
//        memory[a][b][c] = Math.min(memory[a][b][c], attack(a-1, b-3, c-9)+1);
//
//        return memory[a][b][c];
    }

//    public static void bfs(){
////        ArrayDeque<Info> q = new ArrayDeque<Info>();
////        q.offer(start);
////
////        int attack[] = {9,3,1};
////
////        while(!q.isEmpty()) {
////            Info p = q.poll();
////
////          for(int i=0;i<3;i++){
////              for(int j= 1;j<3;j++){
////                  p.arr[i] -= attack[i];
////              }
////          }
////        }
////
////    }
}
