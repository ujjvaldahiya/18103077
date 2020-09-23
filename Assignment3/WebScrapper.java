import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.io.FileWriter;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.UnsupportedMimeTypeException;

public class WebScrapper {
    private static Document connect(String url, int tries){
        if(tries > 5){
            System.out.println("Error: Tried 5 times to connect to " + url + " skipping....");
            return null;
        }
        try{
            System.out.print("Trying to connect to " + url + ": ");
            Connection conn = Jsoup.connect(url);
            Document page = conn.get();
            if(conn.response().statusCode() == 200){
                System.out.println("Success");
                if(!conn.response().contentType().contains("text/html")){
                    System.out.println("Error: Not a HTML page, skipping....");
                    return null;
                }
                return page;
            }
            else{
                System.out.println("Error: Retrying");
                return connect(url, tries+1);
            }
        } catch(IOException e){
            System.out.println("Error: " + e.getMessage() + " skipping....");
            return null;
        }
    }
    
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Queue<String> urlQueue = new LinkedList<>();
        HashSet<String> visitedUrl = new HashSet<>();
        HashSet<String> uniqueUrls = new HashSet<>();
        
        FileWriter anchorFile = new FileWriter("anchor.csv");
        anchorFile.write("\"url\",\"text\"\n");

        FileWriter tagsFile = new FileWriter("tags.csv");
        tagsFile.write("\"tag\",\"text\"\n");
        
        FileWriter nonHTMLFiles = new FileWriter("nonHTML.csv");
        nonHTMLFiles.write("\"url\",\"text\"\n");
        
        System.out.print("Base URL: ");
        String baseUrl = sc.nextLine();
        
        System.out.println("Focused search?(y/n)");
        boolean focused = sc.nextLine().equalsIgnoreCase("y");
        
        String keyword = "";
        if(focused){
            System.out.println("Enter keyword: ");
            keyword = sc.nextLine();
        }
       
        if(baseUrl.endsWith("/"))
            baseUrl = baseUrl.substring(0, baseUrl.length()-1);
        
        urlQueue.add(baseUrl);
        uniqueUrls.add(baseUrl);
        while(!urlQueue.isEmpty()){
            String url = urlQueue.poll();
            
            if(url.endsWith("/"))
                url = url.substring(0, url.length()-1);
            
            if(visitedUrl.contains(url))
                continue;
                
            Document page = connect(url, 1);
            if(page == null)
                continue;

            for(Element element: page.getAllElements()){
                String tag = element.tagName();
                //System.out.print(tag + " ");
                if(!tag.equalsIgnoreCase("div")) {
                    String text = element.text();
                    //System.out.println(text);
                    if(tag.equalsIgnoreCase("a")){
                        String nextUrl = element.attr("href");
                        if(nextUrl.startsWith("/"))
                            nextUrl = baseUrl + nextUrl;
                        if(nextUrl.endsWith("/"))
                            nextUrl = nextUrl.substring(0, nextUrl.length()-1);
                        if (!nextUrl.startsWith(baseUrl) || uniqueUrls.contains(nextUrl))
                            continue;
                        if(focused && (!nextUrl.toLowerCase().contains(keyword) && !text.toLowerCase().contains(keyword)))
                            continue;
                        
                        try{
                            Jsoup.connect(nextUrl).execute();
                        }catch(UnsupportedMimeTypeException ume){
                            System.out.println(ume.getMessage());
                            nonHTMLFiles.write("\"" + nextUrl + "\"" + ",\"" + text + "\"\n");
                            continue;
                        }
                        
                        uniqueUrls.add(nextUrl);
                        urlQueue.add(nextUrl);
                        anchorFile.write("\"" + nextUrl + "\"" + ",\"" + text + "\"\n");
                    } else {
                        tagsFile.write("\"" + tag + "\"" + ",\"" + text + "\"\n");
                    }
                }  
            }

            visitedUrl.add(url);
           
        }
        
        tagsFile.close();
        anchorFile.close();
        nonHTMLFiles.close();
    }
    
}
