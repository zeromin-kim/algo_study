public class Permutation {
    static void main(String...args){

    }

    static void permutaion(int[] arr, int depth, int n, int r){
        if(depth == r){
            print(arr, r);
            return;
        }
        for(int i =depth; i<n; i++){
            swap(arr, depth, i);
            permutaion(arr, depth+1, n, r);
            swap(arr, depth, i);
        }
    }

    static void swap(int[] arr, int depth, int i) {
        int temp = arr[depth];
        arr[depth] = arr[i];
        arr[i] = temp;
    }


    static void perm(int [] arr, int[] output, boolean[] visited, int depth, int n, int r){
        if(depth == r){
            print(output, r);
            return;
        }
        for(int  i= 0; i<n; i++){
            if(!visited[i]){
                visited[i]= true;
                output[depth] = arr[i];
                perm(arr, output, visited, depth+1, n, r);
                output[depth] = 0;
                visited[i] = false;
            }
        }
    }
    // 배열 출력
    static void print(int[] arr, int r) {
        for(int i=0; i<r; i++)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
}
