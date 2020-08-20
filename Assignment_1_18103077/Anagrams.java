import java.util.Scanner;

public class Anagrams {
    
    public boolean areAnagrams(Integer a, Integer b){
        if(a == null || b == null)
            return false;
        
        return areAnagrams(String.valueOf(a), String.valueOf(b));
    }
    
    public boolean areAnagrams(String a, String b){
        if(a == null || b == null || a.length() < 1 || b.length() < 1)
            return false;
        
        int[] chars = new int[26];
        
        for(char c: a.toCharArray())
            chars[c - 'a']++;
        
        for(char c: b.toCharArray())
            chars[c - 'a']--;
        
        for(int c: chars)
            if(c != 0) return false;
        
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Program to check if two numbers/strings are anagrams");
        System.out.println("Enter numbers/String(lowercase alphabets only[a-z]):");
        String a = sc.nextLine();
        String b = sc.nextLine();
        sc.close();
        System.out.println((new Anagrams()).areAnagrams(a.toLowerCase(), b.toLowerCase()));
    }
    
}
