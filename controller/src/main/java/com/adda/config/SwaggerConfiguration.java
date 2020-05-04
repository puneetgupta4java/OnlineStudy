package com.adda.config;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	@Bean
	public Docket deviceIntegrationApi() {
		return new Docket(DocumentationType.SWAGGER_2).useDefaultResponseMessages(false).groupName("Adda")
				.apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.basePackage("com.adda.controller"))
				.paths(regex("/adda-admin/.*")).build();
	}

	public ApiInfo apiInfo() {

		return new ApiInfoBuilder().title("Adda").description("Online Study content")
				.contact(new Contact("Puneet", "", "puneet.gupta@hsc.com")).version("0.1").build();

	}

}
