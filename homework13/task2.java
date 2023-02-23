package homework13;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.net.URISyntaxException;


public class task2 {
    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
        posts(1);
    }
    public static void posts(Integer number) throws IOException, InterruptedException, URISyntaxException {
        String uri = "https://jsonplaceholder.typicode.com/users/" + number + "/posts";
        Document document = Jsoup.connect(uri)
                .ignoreContentType(true)
                .get();
        String allposts = document.text();
        String ch = "\"id\"";
        int index = allposts.indexOf(ch);
        int idOccurrences = 0;
        while (index != -1) {
            index = allposts.indexOf(ch, index + 1);
            idOccurrences++;
        }
        uri = "https://jsonplaceholder.typicode.com/posts/" + idOccurrences + "/comments";
        document = Jsoup.connect(uri)
                .ignoreContentType(true)
                .get();
        String allcomments = document.text();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String toJson = gson.toJson(allcomments);
        System.out.println("toJson = " + toJson);

        FileWriter fileWriter = new FileWriter("posts.json");
        gson.toJson(allcomments, fileWriter);
        fileWriter.close();
    }

}
