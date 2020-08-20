import java.util.Scanner;

public class ReplaceWords {
    
    public String replaceWords(String paragraph, String[] words){
        if(paragraph == null || paragraph.length() < 1)
            return null;
        if(words == null || words.length < 1)
            return paragraph;
        
        StringBuilder substitute;
        for(String word: words){
            substitute = new StringBuilder();
            substitute.append(word.charAt(0));
            for(int i=0; i<word.length()-1; ++i)
                substitute.append('*');
            
            paragraph = paragraph.replaceAll(word, substitute.toString());
        }
        
        return paragraph;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the paragraph in a single line:");
        String paragraph = sc.nextLine();
        System.out.println("Enter the no. of words:");
        int n = sc.nextInt();
        sc.nextLine();
        String[] words = new String[n];
        if(n==1) System.out.println("Enter the word to be replaced: ");
        else System.out.println("Enter the words to be replaced: ");
        for(int i=0; i<n; ++i){
            words[i] = sc.nextLine();
        }
        sc.close();
        System.out.println((new ReplaceWords()).replaceWords(paragraph, words));
    }
    
}
