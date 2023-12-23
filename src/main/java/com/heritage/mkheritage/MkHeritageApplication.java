package com.heritage.mkheritage;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Objects;

@SpringBootApplication
public class MkHeritageApplication {

	public static void main(String[] args) {
		ClassLoader classLoader = MkHeritageApplication.class.getClassLoader();

		synchronized (MkHeritageApplication.class) {
			if (FirebaseApp.getApps().isEmpty()) {
				try (InputStream serviceAccount = classLoader.getResourceAsStream("serviceAccountKey/serviceAccountKey.json")) {
					FirebaseOptions options = FirebaseOptions.builder()
							.setCredentials(GoogleCredentials.fromStream(serviceAccount))
							.build();
					FirebaseApp.initializeApp(options);
					System.out.println("FirebaseApp initialized successfully");
				} catch (Exception e) {
					System.out.println("An error occurred while initializing FirebaseApp");
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
			}
		}


		SpringApplication.run(MkHeritageApplication.class, args);
	}
}
