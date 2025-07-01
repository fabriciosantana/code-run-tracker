package br.edu.idp.cc.coderuntrackerclient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Properties;

public class Main {
    private static final Properties config = new Properties();

    static {

        try (InputStream in = Main.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (in != null) {
                config.load(in);
            } else {
                System.err.println("Properties file not found in classpath: application.properties");
            }
        } catch (IOException e) {
            System.err.println("Error reading application.properties: " + e.getMessage());
        }
    }

    public static void main(String[] args){
        reportExecution("1234", "test");
    }

    public static void reportExecution(String studentId, String taskCode) {
        try {
            String apiUrl = config.getProperty("api.url");
            String institution = config.getProperty("institution");
            String course = config.getProperty("course");
            String subject = config.getProperty("subject");
            String semester = config.getProperty("semester");
            String localUser = System.getProperty("user.name");
            String executedAt = LocalDateTime.now().toString();

            String jsonPayload = """
                {
                  "institution": "%s",
                  "course": "%s",
                  "subject": "%s",
                  "semester": "%s",
                  "studentId": "%s",
                  "taskCode": "%s",
                  "localUser": "%s",
                  "executedAt": "%s"
                }
                """.formatted(institution, course, subject, semester, studentId, taskCode, localUser, executedAt);

            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonPayload.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            System.out.println("Execution reported (HTTP " + responseCode + ").");

        } catch (Exception e) {
            System.err.println("Error reporting execution: " + e.getMessage());
        }
    }
}