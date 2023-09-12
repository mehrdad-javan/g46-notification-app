package se.lexicon.model;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class SMSNotification {
    // Replace with your HelloSMS API credentials
    private static final String apiUsername = "API_USERNAME";
    private static final String apiPassword = "API_PASSWORD";

    // Define the API endpoint URL
    private static final String apiUrl = "https://api.hellosms.se/v1/sms/send/";

    public static void main(String[] args) {

        try {
            // Create JSON data for the SMS
            String jsonData = "{"
                    + "\"to\": [\"+46700000000\"],"
                    + "\"from\": \"TEST\","
                    + "\"subject\": \"Test\","
                    + "\"message\": \"Dont forget your  meeting at 15.00\","
                    + "\"delay\": 1671890400,"
                    + "\"shortLinks\": true,"
                    + "\"testMode\": false"
                    + "}";

            // Encode API credentials for Basic Authentication
            String authString = apiUsername + ":" + apiPassword;
            String encodedAuth = Base64.getEncoder().encodeToString(authString.getBytes(StandardCharsets.UTF_8));

            // Create an HTTP connection to the HelloSMS API
            try {
                final HttpURLConnection connection = getHttpURLConnection(encodedAuth, jsonData);

                // Get the HTTP response code
                int responseCode = connection.getResponseCode();

                if (responseCode == 200) {
                    System.out.println("SMS sent successfully!");
                } else {
                    System.out.println("Failed to send SMS. Status code: " + responseCode);
                }

                // Close the connection
                connection.disconnect();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static HttpURLConnection getHttpURLConnection(String encodedAuth, String jsonData) throws IOException {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Basic " + encodedAuth);
        connection.setDoOutput(true);

        // Send the JSON data as the request body
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonData.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }
        return connection;
    }
}
