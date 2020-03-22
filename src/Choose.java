import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Choose {

    public static void main(String...args){
        int N=5;
        int R = 2;
        List<int[]> combinations = generate(N, R);
        for (int[] combination : combinations) {
            System.out.println(Arrays.toString(combination));
        }
        System.out.printf("generated %d combinations of %d items from %d ", combinations.size(), R, N);

    }

    private static void helper(List<int[]> combinations, int data[], int start, int end, int index){
        if (index == data.length) {
            int[] temp = data.clone();
            combinations.add(temp);
        }else if(start<=end){
            data[index]= start;
            helper(combinations, data, start+1, end, index+1);
            helper(combinations, data, start+1, end, index);
        }
    }

    private void helper2(List<int[]> combinations, int data[], int start, int end, int index) {
        if (index == data.length) {
            int[] combination = data.clone();
            combinations.add(combination);
        } else {
            int max = Math.min(end, end + 1 - data.length + index);
            for (int i = start; i <= max; i++) {
                data[index] = i;
                helper2(combinations, data, i + 1, end, index + 1);
            }
        }
    }

    private static void pick(int n, int toPick, Stack<Integer> picked){
        if(toPick == 0){
            //print stack
            return;
        }
        int smallest = picked.isEmpty()?0:picked.peek()+1;
        for(int i = smallest; i<n; i++){
            picked.push(i);
            pick(n, toPick-1, picked);
            picked.pop();
        }
    }
    public static List<int[]> generate(int n, int r){
        List<int[]> combinations = new ArrayList<>();
        helper(combinations, new int[r], 0, n-1, 0);
        return combinations;
    }

}
