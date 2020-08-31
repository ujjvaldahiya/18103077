import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class HailStone {
    
    public Integer[] hailstone(int n){
        if(n <= 0)
            throw new IllegalArgumentException("n<=0, n should be a positive integer");
        
        ArrayList<Integer> list = new ArrayList<>();
        while(n != 1){
            list.add(n);
            n = (n%2 == 0)? n/2: n*3+1;
        }
        
        list.add(1);
        
        return list.toArray(new Integer[list.size()]);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Integer n = sc.nextInt();
        sc.close();
        Integer[] list = (new HailStone()).hailstone(n);
        System.out.println(Arrays.toString(list));
    }
}
