package com.heritage.heritagemacedonia;

import com.heritage.heritagemacedonia.Impl.AttributeFilter;
import com.heritage.heritagemacedonia.Impl.Pipe;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;

@SpringBootApplication
public class HeritageMacedoniaApplication {

    public static void main(String[] args) {
    // Database connection parameters
        String DB_URL = "jdbc:sqlite:src/main/resources/mkdheritageDb.db";

        try (Connection connection = DriverManager.getConnection(DB_URL)) {
            // SQL statement to select all rows from the heritage_sites table
            String selectDataSQL = "SELECT * FROM heritage_sites";

            try (PreparedStatement selectDataStatement = connection.prepareStatement(selectDataSQL);
                 ResultSet resultSet = selectDataStatement.executeQuery()) {

                // Create a Pipe
                Pipe<String> pipe = new Pipe<>();
                pipe.addFilter(new AttributeFilter());
                pipe.addFilter(new NameFormatFilter());

                // Iterate over the result set and apply the pipe to each row
                while (resultSet.next()) {
                    // Assuming the 'attributes' column is of type TEXT
                    String attributes = resultSet.getString("attributes");

                    // Specify the attribute name you want to extract
                    String attributeName = "historic"; // Change this to the attribute you want to extract

                    // Run the filters on each row
                    String result = pipe.runFilters(attributes, attributeName);
                    System.out.println(result);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
