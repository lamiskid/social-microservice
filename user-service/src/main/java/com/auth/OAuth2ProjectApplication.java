package com.lamiskid.OAuth2project;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.IOException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OAuth2ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(OAuth2ProjectApplication.class, args);
	}


	@Bean
	public FirebaseApp firebaseApp() throws IOException {
		FirebaseOptions options = FirebaseOptions.builder()
												 .setCredentials(GoogleCredentials.fromStream(getClass().getResourceAsStream("/firebase-credentials.json")))
												 .build();

		return FirebaseApp.initializeApp(options);
	}

}
