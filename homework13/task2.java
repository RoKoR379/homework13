package homework13;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import org.jsoup.nodes.Document;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;


public class task2 {
    private static final String USERS_URI = "https://jsonplaceholder.typicode.com/users";
    private static final String POST_URI = "https://jsonplaceholder.typicode.com/posts";

    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException, ParseException {
        User user = new User();
        user.setId("1");
        user.setName("Vasyl");
        user.setUsername("Vasyliok");
        user.setEmail("vasyl@gmail.com");
        user.setStreet("Vasylkivska");
        user.setSuite("12");
        user.setCity("Kyiv");
        user.setZipcode("05732");
        user.setLat("-37.315");
        user.setLng("81.149");
        user.setPhone("1-770-736-8031");
        user.setWebsite("hildegard.org");
        user.setCompany("Romaguera-Crona");
        user.setCatchPhrase("Multi-layered client-server neural-net");
        user.setBs("harness real-time e-markets");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();


        FileWriter fileWriter = new FileWriter("posts.json");
        gson.toJson(getUserPosts(user), fileWriter);

        Post post = new Post();
        post.setId("10");

        Gson gson2 = new GsonBuilder().setPrettyPrinting().create();

        FileWriter fileWriter2 = new FileWriter("comments.json");
        gson2.toJson(getPostsComments(post), fileWriter2);
        fileWriter.close();

    }

    public static List<Post> getUserPosts(User user) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(USERS_URI + "/" + user.getId() + "/posts"))
                .GET()
                .build();
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        return gson.fromJson(response.body(), new TypeToken<List<Post>>() {}.getType());

    }

    public static List<Comment> getPostsComments(Post post) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(POST_URI + "/" + post.getId() + "/comments"))
                .GET()
                .build();
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        return gson.fromJson(response.body(), new TypeToken<List<Comment>>() {}.getType());

    }


}
