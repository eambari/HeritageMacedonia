package com.heritage.heritagemacedonia;

import com.heritage.heritagemacedonia.Impl.AttributeFilter;
import com.heritage.heritagemacedonia.Impl.Pipe;
import com.heritage.heritagemacedonia.Impl.UppercaseFilter;
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
                Pipe<String> pipeHistoric = new Pipe<>();
                pipeHistoric.addFilter(new AttributeFilter());
                Pipe<String> pipeName = new Pipe<>();
                pipeName.addFilter(new AttributeFilter());
                pipeName.addFilter(new UppercaseFilter());

                // Iterate over the result set and apply the pipe to each row
                int i = 1;
                while (resultSet.next()) {
                    // Assuming the 'attributes' column is of type TEXT
                    String attributes = resultSet.getString("attributes");

                    // Run the filters on each row
                    String attributeResult = pipeHistoric.runFilters(attributes, "historic");
                    String nameResult = pipeName.runFilters(attributes, "name");
                    System.out.println("Historical heritage number " + i++ + " is a:" + attributeResult + "\nName:" + nameResult);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
