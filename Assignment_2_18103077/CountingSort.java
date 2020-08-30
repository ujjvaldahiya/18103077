import java.util.Arrays;

public class CountingSort {
    
    public void sort(int[] arr){
        int[] bucket = new int[21];
        
        for(int i: arr) bucket[i]++;
        
        int lo = 0;
        
        for(int i=0; i<21; ++i){
            int temp = lo;
            while(lo < temp+bucket[i])
                arr[lo++] = i;
        }
        
    }

    public static void main(String[] args) {
        
        int[] arr = {3, 3, 2, 5, 6, 3, 5, 6, 1, 1, 6, 7, 9, 0};
        (new CountingSort()).sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
