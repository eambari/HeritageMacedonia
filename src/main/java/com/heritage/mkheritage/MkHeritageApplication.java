package com.heritage.mkheritage;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileInputStream;
import java.util.Objects;

@SpringBootApplication
public class MkHeritageApplication {

	public static void main(String[] args) {
		ClassLoader classLoader = MkHeritageApplication.class.getClassLoader();
		File file = new File(Objects.requireNonNull(classLoader.getResource("serviceAccountKey/serviceAccountKey.json")).getFile());

		try (FileInputStream serviceAccount = new FileInputStream(file.getAbsolutePath())) {
			FirebaseOptions options = FirebaseOptions.builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.build();
			FirebaseApp.initializeApp(options);

		} catch (Exception e) {
			System.out.println("An error occurred while fetching the service key.");
			System.out.println(e.getMessage());
		}

		SpringApplication.run(MkHeritageApplication.class, args);
	}
}
