package com.heritage.mkheritage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.heritage.mkheritage.model.HeritageSite;
import com.heritage.mkheritage.repository.HeritageSiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final HeritageSiteRepository repository;

    @Autowired
    public DataLoader(HeritageSiteRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<HeritageSite>> typeReference = new TypeReference<List<HeritageSite>>() {};

        // Load the file as a resource using ClassLoader
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("data/result.json");

        if (inputStream != null) {
            try {
                List<HeritageSite> heritageSites = objectMapper.readValue(inputStream, typeReference);
                repository.saveAll(heritageSites);
                System.out.println("Data Loaded Successfully!");
            } catch (Exception e) {
                System.out.println("Unable to load data: " + e.getMessage());
            } finally {
                inputStream.close(); // Close the stream in a finally block
            }
        } else {
            System.out.println("File not found: data/result.json");
        }
    }
}
