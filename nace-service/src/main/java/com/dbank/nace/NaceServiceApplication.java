package com.dbank.nace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collection;
import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
public class NaceServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NaceServiceApplication.class, args);
	}

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.dbank"))
				.build()
				.apiInfo(getApiInfo());
//				.apis(RequestHandlerSelectors.any())
//				.paths()
//				.build();
	}

	private ApiInfo getApiInfo() {
		String title = "NACE Controller app";
		String description = "Application to perform CRUD operations on NACE data";
		String version = "1.0";
		String termsOfServiceUrl = "example.com";
		Contact contact = new Contact("John Doe", "luxoft.com", "jdoe@luxoft.com");
		String license = "For use in interview and demo";
		String licenseUrl = "";
		return new ApiInfo(title, description, version, termsOfServiceUrl,
				contact, license, licenseUrl, Collections.emptyList());
	}
}
