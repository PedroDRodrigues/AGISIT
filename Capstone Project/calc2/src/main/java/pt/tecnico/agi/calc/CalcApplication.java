package pt.tecnico.agi.calc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@Slf4j
public class CalcApplication {
	public static void main(String[] args) {
		SpringApplication.run(CalcApplication.class, args);
	}


	@Value("${storage.url}")
	private String storageUrl;

	@Bean
	public WebClient webClient() {
		log.info("Storage URL: {}", storageUrl);
		return WebClient.builder().baseUrl(storageUrl).build();
	}
}
