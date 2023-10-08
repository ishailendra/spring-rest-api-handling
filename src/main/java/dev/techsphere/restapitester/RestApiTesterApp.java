package dev.techsphere.restapitester;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableScheduling
public class RestApiTesterApp {


	public static void main(String[] args) {
		SpringApplication.run(RestApiTesterApp.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	/*@Bean
	public RestTemplate customRestTemplate() {
		return new RestTemplate(getClientHttpRequestFactory());
	}

	private ClientHttpRequestFactory getClientHttpRequestFactory() {

		// Create an instance of Apache HttpClient
		HttpComponentsClientHttpRequestFactory httpClientFactory = new HttpComponentsClientHttpRequestFactory();

		int connectTimeout = 5000;

		httpClientFactory.setConnectTimeout(connectTimeout);
		httpClientFactory.setConnectionRequestTimeout(5000);

		return httpClientFactory;
	}*/
}
