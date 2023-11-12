package com.heritage.heritagemacedonia.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ApiDataFetcher {
    public static void main(String[] args) {
        // Fetch data from the API
        String apiData = fetchApi();

        // Now, create an instance of OSMDataImporter and insert the data into the database
        OSMDataImporter osmDataImporter = new OSMDataImporter();
        osmDataImporter.insertDataFromXML(apiData);

    }
    private static String fetchApi() {
        String result = "";
        try {
            URL url = new URL("https://overpass-api.de/api/interpreter");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            connection.setDoOutput(true);

            String requestBody = "data=(\n" +
//                    "  nwr[natural](40.89482963261178,19.898986816406254,42.354484651067466,22.217102050781254);\n" +
                    "  nwr[historic](40.89482963261178,19.898986816406254,42.354484651067466,22.217102050781254);\n" +
                    ");\n" +
                    "out;";

            try (OutputStream outputStream = connection.getOutputStream()) {
                byte[] input = requestBody.getBytes(StandardCharsets.UTF_8);
                outputStream.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line).append('\n');
                }

                reader.close();

//                System.out.println("API Response: " + response.toString());
                result = response.toString();
            } else {
                System.out.println("Error: HTTP request failed with response code " + responseCode);
            }

            connection.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}