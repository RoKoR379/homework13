package homework13;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.nio.charset.StandardCharsets.UTF_8;
import com.fasterxml.jackson.databind.ObjectMapper;



public class task1 {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException{

        addNewUser();
        updateUser(7);
        deleteUserByID(5);

        getAllUsers();
        getUserByID(2);
        getUserByName("Samantha");

    }

    public static void responseStatusCode() throws IOException {
        Connection.Response response = Jsoup.connect("https://jsonplaceholder.typicode.com/users")
                .ignoreContentType(true)
                .execute();
        System.out.println("Response status code = " + response.statusCode());
    }

    public static void getAllUsers() throws IOException {
        Document document = Jsoup.connect("https://jsonplaceholder.typicode.com/users")
                .ignoreContentType(true)
                .get();
        responseStatusCode();
        String allUsersInfo = document.text(); //отримання інформації про всіх користувачів
        System.out.println("Users = " + allUsersInfo);
    }

    public static void getUserByID(Integer number) throws IOException {
        Document document = Jsoup.connect("https://jsonplaceholder.typicode.com/users/" + number)
                .ignoreContentType(true)
                .get();
        responseStatusCode();
        String userInfo = document.text(); //отримання інформації про користувача за id
        System.out.println("User by ID = " + userInfo);
    }

    public static void getUserByName(String name) throws IOException {
        Document document = Jsoup.connect("https://jsonplaceholder.typicode.com/users/?username=" + name)
                .ignoreContentType(true)
                .get();
        responseStatusCode();
        String userInfo = document.text(); //отримання інформації про користувача за username
        System.out.println("User by name = " + userInfo);
    }

    public static void deleteUserByID(Integer number) throws IOException, InterruptedException, URISyntaxException {
        String uri = "https://jsonplaceholder.typicode.com/users/" + number;
        HttpRequest httpRequest = HttpRequest.newBuilder(new URI(uri))
                .DELETE()
                .build();
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println("Delete status code = " + httpResponse.statusCode());

    }

    public static void addNewUser() throws IOException, InterruptedException, URISyntaxException {
        String uri = "https://jsonplaceholder.typicode.com/users/";
        Document document = Jsoup.connect(uri)
                .ignoreContentType(true)
                .get();
        String allUsersInfo = document.text();
        String ch = "\"id\"";
        int index = allUsersInfo.indexOf(ch);
        int idOccurrences = 1;
        while (index != -1) {
            index = allUsersInfo.indexOf(ch, index + 1);
            idOccurrences++;
        }
        HttpRequest httpRequest = HttpRequest.newBuilder(new URI(uri))
                .POST(HttpRequest.BodyPublishers.ofString("{\n" +
                        "    \"id\":" + idOccurrences + "," +
                        "    \"name\": \"Leanne Graham\",\n" +
                        "    \"username\": \"Bret\",\n" +
                        "    \"email\": \"Sincere@april.biz\",\n" +
                        "    \"address\": {\n" +
                        "      \"street\": \"Kulas Light\",\n" +
                        "      \"suite\": \"Apt. 556\",\n" +
                        "      \"city\": \"Gwenborough\",\n" +
                        "      \"zipcode\": \"92998-3874\",\n" +
                        "      \"geo\": {\n" +
                        "        \"lat\": \"-37.3159\",\n" +
                        "        \"lng\": \"81.1496\"\n" +
                        "      }\n" +
                        "    },\n" +
                        "    \"phone\": \"1-770-736-8031 x56442\",\n" +
                        "    \"website\": \"hildegard.org\",\n" +
                        "    \"company\": {\n" +
                        "      \"name\": \"Romaguera-Crona\",\n" +
                        "      \"catchPhrase\": \"Multi-layered client-server neural-net\",\n" +
                        "      \"bs\": \"harness real-time e-markets\"\n" +
                        "    }\n" +
                        "  }"))
                .build();
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println("Adding status code = " + httpResponse.statusCode());

    }

    public static void updateUser(Integer number) throws IOException, InterruptedException, URISyntaxException {
        String uri = "https://jsonplaceholder.typicode.com/users/" + number;

        HttpRequest httpRequest = HttpRequest.newBuilder(new URI(uri))
                .PUT(HttpRequest.BodyPublishers.ofString("{\n" +
                        "    \"id\":" + number + "," +
                        "    \"name\": \"Leanne Graham\",\n" +
                        "    \"username\": \"Bret\",\n" +
                        "    \"email\": \"Sincere@april.biz\",\n" +
                        "    \"address\": {\n" +
                        "      \"street\": \"Kulas Light\",\n" +
                        "      \"suite\": \"Apt. 556\",\n" +
                        "      \"city\": \"Gwenborough\",\n" +
                        "      \"zipcode\": \"92998-3874\",\n" +
                        "      \"geo\": {\n" +
                        "        \"lat\": \"-37.3159\",\n" +
                        "        \"lng\": \"81.1496\"\n" +
                        "      }\n" +
                        "    },\n" +
                        "    \"phone\": \"1-770-736-8031 x56442\",\n" +
                        "    \"website\": \"hildegard.org\",\n" +
                        "    \"company\": {\n" +
                        "      \"name\": \"Romaguera-Crona\",\n" +
                        "      \"catchPhrase\": \"Multi-layered client-server neural-net\",\n" +
                        "      \"bs\": \"harness real-time e-markets\"\n" +
                        "    }\n" +
                        "  }"))
                .build();
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println("Updating status code = " + httpResponse.statusCode());

    }





}
