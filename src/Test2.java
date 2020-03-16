package kr.co.miracom;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Test2 {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<ArrayList<Integer>> arrayLists; // 그래프

    static final int RED = 1;
    static final int BLUE = -1;
    static int[] colors; // 색 {RED 1 or BLUE -1}
    static boolean checkBipartite; // 이분 그래프인지 아닌지 확인

    public static void main(String[] args) {
        int testCase = scanner.nextInt(); // 테스트 케이스

        while (testCase-- > 0) {
            int V = scanner.nextInt(); // 정점의 개수 V (1≤V≤20,000)
            int E = scanner.nextInt(); // 간선의 개수 E (1≤E≤200,000)

            arrayLists = new ArrayList<>();
            colors = new int[V + 1]; // 각 정점의 색을 구분
            checkBipartite = true; // 초기: 이분 그래프이다.

            for (int i = 0; i < V + 1; i++) {
                arrayLists.add(new ArrayList<Integer>()); // 정점의 수 + 1만큼 초기화
                colors[i] = 0; // 방문하지 않은 정점의 색을 0으로 초기화
            }

            // 양방향 그래프 연결
            while (E-- > 0) {
                int v1 = scanner.nextInt();
                int v2 = scanner.nextInt();

                arrayLists.get(v1).add(v2);
                arrayLists.get(v2).add(v1);
            }

            for (int i = 1; i < V + 1; i++) {
                // 이분 그래프가 아니면 반복문 탈출
                if (!checkBipartite)
                    break;

                // 방문하지 않은 정점에 대해서 탐색 수행
                if (colors[i] == 0) {
                    dfs(i, RED);
                }
            }

            System.out.println(checkBipartite ? "YES" : "NO");
        }
    }
    static void dfs(int startV, int color) {
        colors[startV] = color; // 시작 정점의 색을 설정

        for (int adjV : arrayLists.get(startV)) {
            // 시작 정점의 색과 인접한 정점의 색이 같으면 이분 그래프가 아니다.
            if (colors[adjV] == color) {
                checkBipartite = false;
                return;
            }

            // 시작 정점과 인접한 정점이 방문하지 않은 정점이면 dfs 실행
            if (colors[adjV] == 0) {
                // 인접한 정점을 다른 색으로 지정
                dfs(adjV, -color);
            }
        }

    }


}
