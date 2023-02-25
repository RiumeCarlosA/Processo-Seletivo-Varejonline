package br.com.varejonline.riume.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI customOpenAPI() {

		// @formatter:off
        return new OpenAPI().components(
            new Components()
                .addSecuritySchemes("token", 
                    new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                 )
        ).info(
            new Info()
                .title("Sistema de restaurante desenvolvido por Riume")
                .version("1")
                .description("Sistema de restaurante desenvolvido por Riume")
                .termsOfService("http://swagger.io/terms/")
                .license(new License().name("Apache 2.0").url("http://springdoc.org")));
        // @formatter:on
	}

}
