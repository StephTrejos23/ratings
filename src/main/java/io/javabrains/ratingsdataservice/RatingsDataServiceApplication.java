package io.javabrains.ratingsdataservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;

@SpringBootApplication
public class RatingsDataServiceApplication {

	public static void main(String[] args) {
		Properties systemProps = System.getProperties();
		systemProps.put("javax.net.ssl.keyStorePassword","passwordForKeystore");
		systemProps.put("javax.net.ssl.keyStore","pathToKeystore.ks");
		systemProps.put("javax.net.ssl.trustStore", "pathToTruststore.ts");
		systemProps.put("javax.net.ssl.trustStorePassword","passwordForTrustStore");
		System.setProperties(systemProps);
		SpringApplication.run(RatingsDataServiceApplication.class, args);
	}

}
//keytool -list -v -keystore "C:\Users\steph10\.android\debug.keystore" -alias androiddebugkey -storepass android -keypass android
