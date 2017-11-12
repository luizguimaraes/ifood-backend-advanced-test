package br.com.ifood.backend.advanced.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableCaching
public class ApplicationConfiguration {

	@Bean
	public RestTemplate restTemplate(final RestTemplateBuilder builder) {
		return builder.build();
	}

	public static void main(final String[] args) {
		SpringApplication.run(ApplicationConfiguration.class, args);
	}
}
