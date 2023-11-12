package com.heritage.heritagemacedonia.Data;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OSMDataImporter {
    private static final String DB_URL = "jdbc:sqlite:src/main/resources/mkdheritageDb.db";

    public void insertDataFromXML(String xmlData) {

        try (Connection connection = DriverManager.getConnection(DB_URL)) {
            // SQL statement to create a table with a flexible schema
            String createTableSQL = "CREATE TABLE IF NOT EXISTS heritage_sites ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "node_id INTEGER NOT NULL,"
                    + "attributes TEXT NOT NULL)";

            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate("PRAGMA encoding = 'UTF-8';");
            }
            try (PreparedStatement createTableStatement = connection.prepareStatement(createTableSQL)) {
                // Execute the CREATE TABLE statement
                createTableStatement.executeUpdate();
            }
            // Assume 'xmlData' is the XML data received from the API

            // Parse XML data and extract relevant information
            List<Map<String, String>> nodes = parseXML(xmlData);

            for (Map<String, String> nodeAttributes : nodes) {
                insertNode(connection, nodeAttributes);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<Map<String, String>> parseXML(String xmlData) {
        List<Map<String, String>> nodes = new ArrayList<>();

        Document document = Jsoup.parse(xmlData);
        Elements nodeElements = document.select("node");

        for (Element nodeElement : nodeElements) {
            Map<String, String> nodeAttributes = new HashMap<>();
            String nodeId = nodeElement.attr("id");
            String nodeLat = nodeElement.attr("lat");
            String nodeLon = nodeElement.attr("lon");

            Elements tagElements = nodeElement.select("tag");
            for (Element tagElement : tagElements) {
                String key = tagElement.attr("k");
                String value = tagElement.attr("v");
                nodeAttributes.put(key, value);
            }

            // Add node attributes
            nodeAttributes.put("id", nodeId);
            nodeAttributes.put("lat", nodeLat);
            nodeAttributes.put("lon", nodeLon);

            // Add the node to the list
            nodes.add(nodeAttributes);
        }

        return nodes;
    }

    private void insertNode(Connection connection, Map<String, String> nodeAttributes) throws SQLException {
        // SQL statement to insert data
        String insertDataSQL = "INSERT INTO heritage_sites (node_id, attributes) VALUES (?, ?)";
        try (PreparedStatement insertDataStatement = connection.prepareStatement(insertDataSQL)) {
            insertDataStatement.setString(1, nodeAttributes.get("id"));
            // Store attributes as JSON string
            insertDataStatement.setString(2, mapToJson(nodeAttributes));

            // Execute the insert statement
            insertDataStatement.executeUpdate();
        }
    }

    private String mapToJson(Map<String, String> map) {
        // Convert a map to a JSON string (you can use a JSON library for better handling)
        StringBuilder json = new StringBuilder("{");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            json.append("\"").append(entry.getKey()).append("\":\"").append(entry.getValue()).append("\",");
        }
        json.deleteCharAt(json.length() - 1); // Remove the trailing comma
        json.append("}");
        return json.toString();
    }
}