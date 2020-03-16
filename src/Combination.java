package kr.co.miracom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Combination {

    private static void helper(List<int[]> combinations, int data[], int start, int end, int index){
        if(index == data.length){
            int[] combination = data.clone();
            combinations.add(combination);
        }else if(start<=end){
            data[index] = start;
            helper(combinations, data, start+1, end, index+1);
            helper(combinations, data, start+1, end, index);
        }
    }
    public static List<int[]> generate(int n, int r){
        List<int[]> combinations = new ArrayList<>();
        helper(combinations, new int[r] ,0, n-1, 0);
        return combinations;
    }

    public static void main(String...args){
        int N = 3, R= 3;

        List<int[]> combinations = generate(N, R);
        for (int[] combination : combinations) {
            System.out.println(Arrays.toString(combination));
        }
        System.out.printf("generated %d combinations of %d items from %d ", combinations.size(), R, N);
    }
}
