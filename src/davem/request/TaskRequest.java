package davem.request;

import davem.models.Task;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;

public class TaskRequest {

    private static String json;
    public ArrayList<Task> taskArrayList = new ArrayList<>();

    private static String sendGetRequest(String url) throws IOException {
        HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();
        httpClient.setRequestMethod("GET");

        InputStream is = httpClient.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));

        return rd.readLine();
    }

    public static String getAllTasksFromServer() throws IOException {
        String url = "http://localhost:8080/tasks/";
        return sendGetRequest(url);
    }

    public static void parseFunc(ArrayList<Task> taskArrayList, JSONObject obj) {

        Long id = Long.parseLong(obj.get("id").toString());
//        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        LocalDate complDate = LocalDate.parse(obj.getString("completionDate"));
        String description = obj.getString("description");
        Boolean status = obj.getBoolean("status");
        String title = obj.getString("title");

        Task station = new Task(id, complDate, description, status, title);

        taskArrayList.add(station);
    }

    private static ArrayList<Task> getStations(JSONArray obj) {
        ArrayList<Task> taskArrayList = new ArrayList<>();
        for (int i=0;i<obj.length();i++) {
            JSONObject taskToParse = obj.getJSONObject(i);

            parseFunc(taskArrayList, taskToParse);
        }

        return taskArrayList;
    }

    public static ArrayList<Task> getAllTasks() throws IOException {
        json = getAllTasksFromServer();
        JSONObject object = new JSONObject(json);
        JSONArray content = object.getJSONArray("content");
        return getStations(content);
    }
}
