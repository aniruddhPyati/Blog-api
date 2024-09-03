package com.ani.springmvcboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@OpenAPIDefinition(
		info = @Info(
				title = "Blog Rest API",
				description= "Based on CRUD principles of REST",
				contact= @Contact(
						name="Aniruddh Pyati",
						email="aniruddh7pyati@gmail.com"
						)
				)
		)


@Configuration
public class OpenApiConfig {
	
	
	@Bean
	public OpenAPI openAPI() {
		
		String schemeName = "bearerScheme";
		
		return new OpenAPI()
				.addSecurityItem(new SecurityRequirement()
						.addList(schemeName)
						)
				.components(new Components().addSecuritySchemes(schemeName,new SecurityScheme()
						                          .name(schemeName)
						                          .type(SecurityScheme.Type.HTTP)
						                          .bearerFormat("JWT")
						                          .scheme("bearer")
						                          
						)
						);
	}
	

}
