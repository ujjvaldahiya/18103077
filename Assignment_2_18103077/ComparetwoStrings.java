import java.util.Scanner;

public class ComparetwoStrings {
    
    public int compare(String a, String b){
        if(a == null || b == null) throw new NullPointerException("Atleast one of the strings passed as argument is null");
        
        if(a.isEmpty() && b.isEmpty()) return 0;
        else if(a.isEmpty()) return -1;
        else if(b.isEmpty()) return +1;
        
        int strLength = Math.min(a.length(), b.length());
        
        for(int i=0; i<strLength; ++i)
            if(a.charAt(i) != b.charAt(i)) return a.charAt(i) - b.charAt(i);
        
        return a.length() - b.length();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter the first string: ");
        String a = sc.nextLine();
        System.out.print("Enter the second string: ");
        String b = sc.nextLine();
        sc.close();
        int result = (new ComparetwoStrings()).compare(a, b);
        System.out.println("The first string is " + 
                ((result < 0)? "smaller than ": (result > 0)? "greater than ": "equal to ") + 
                "the second string");
    }
}
