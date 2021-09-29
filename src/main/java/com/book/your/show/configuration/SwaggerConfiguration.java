package com.book.your.show.configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2

@EnableWebMvc
public class SwaggerConfiguration {

//	public static final Contact DEFAULT_CONTACT = new Contact(
//			"Blazeclan Technologies", "https://www.blazeclan.com/in/", "piyush.wadhwani@blazeclan.com");
	
	public static final Contact DEFAULT_CONTACT =new Contact(null, null, null);
	
	public static final ApiInfo DEFAULT_API_INFO = new ApiInfo(
			"Book Your Show", "Book Your Show API Description", "1.0",
			"urn:tos", DEFAULT_CONTACT, 
			"", "http://www.apache.org/licenses/LICENSE-2.0",new ArrayList<>());

	private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = 

			new HashSet<>(Arrays.asList("application/json",
					"application/xml"));
	
	@Bean
	public Docket api() {

		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
				.paths(PathSelectors.any()).build()
				.apiInfo(DEFAULT_API_INFO)
				.produces(DEFAULT_PRODUCES_AND_CONSUMES)
				.consumes(DEFAULT_PRODUCES_AND_CONSUMES);
		
	}
}
