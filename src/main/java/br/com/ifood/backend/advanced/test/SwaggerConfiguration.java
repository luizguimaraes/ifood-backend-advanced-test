package br.com.ifood.backend.advanced.test;

import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	@Bean
	public Docket api() {
		return new Docket(SWAGGER_2).useDefaultResponseMessages(false).apiInfo(apiInfo()).select()
				.apis(basePackage("br.com.ifood.backend.advanced.test.controller")).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfo("API de Sugestão de playlists",
				"Esta API provê sugestão de playlists baseado na localização indicada!", "1.0.0", "",
				new Contact("Luiz Guimarães", "", "luizccgjr@gmail.com"), "", "", Collections.emptyList());
	}
}
