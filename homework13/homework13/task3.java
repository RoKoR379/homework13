package homework13;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Stream;

public class task3 {
    private static final String TASKS_URI = "https://jsonplaceholder.typicode.com/users";

    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException, ParseException {
        Tasks tasks = new Tasks();
        tasks.setUserId("1");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String toJson = gson.toJson(getNonCompletedTasks(tasks));
        System.out.println("toJson = " + toJson);

        FileWriter fileWriter = new FileWriter("todos.json");
        gson.toJson(getNonCompletedTasks(tasks), fileWriter);
        fileWriter.close();



    }

    public static List<Tasks> getNonCompletedTasks(Tasks tasks) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(TASKS_URI + "/" + tasks.getUserId() + "/todos"))
                .GET()
                .build();
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        return gson.fromJson(response.body(), new TypeToken<List<Tasks>>() {}.getType());



    }
}
