package kr.co.miracom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class PickTest {

    static ArrayList<int[]> arr= new ArrayList<>();

    public static void main(String...args){
        LinkedList<Integer> pickData = new LinkedList<>();
        pick(3,pickData, 3);
        for(int[] temp : arr) {
            System.out.println(temp);
        }
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


    //이건 nPr 이다..
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
}
