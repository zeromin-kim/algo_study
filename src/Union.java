package kr.co.miracom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Union {

	static int count;// 연결쌍의 개수
	public static int arr[];
	public static int k,vertex,edge;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		k = Integer.parseInt(st.nextToken());
		int c, start, end;
		for(int t = 0; t<k; t++){
			st = new StringTokenizer(br.readLine());
			vertex = Integer.parseInt(st.nextToken());
			edge = Integer.parseInt(st.nextToken());
			arr = new int[vertex + 1];
			count = vertex;
			for (int i = 0; i <= vertex; i++) {
				arr[i] = i;
			}


			for (int i = 0; i < edge; i++) {
				st = new StringTokenizer(br.readLine());
				start = Integer.parseInt(st.nextToken());
				end = Integer.parseInt(st.nextToken());
				if(connected(start, end)) continue;
				union(start,end);
			}

			if(count == 2){
				System.out.println("YES"+count);
			}else{
				System.out.println("NO"+count);
			}

		}
	}

	public static int find(int i){
	//recursive
//		if( i == arr[i]){
//			return i;
//		}else{
//			return arr[i] = find(arr[i]);
//		}
		while(i !=arr[i])i=arr[i];
		return i;
	}

	public static void union(int i, int j){
		int root1 = find(i);
		int root2 = find(j);
		if(root1==root2){
			return;
		}
		arr[root2] = root1;
		count--;
	}
	public static boolean connected(int p, int q){
		return find(p) == find(q);
	}
}
