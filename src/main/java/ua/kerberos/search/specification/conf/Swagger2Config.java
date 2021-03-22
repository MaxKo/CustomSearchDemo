//package ua.kerberos.search.specification.conf;
//
//import io.swagger.v3.oas.annotations.OpenAPIDefinition;
//import io.swagger.v3.oas.annotations.security.SecurityRequirement;
//import io.swagger.v3.oas.models.Components;
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.headers.Header;
//import io.swagger.v3.oas.models.info.Info;
//import io.swagger.v3.oas.models.security.SecurityScheme;
//import io.swagger.v3.oas.models.servers.Server;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Import;
//
//import java.io.IOException;
//
//@OpenAPIDefinition(security = {@SecurityRequirement(name="bearer-key")})
//@Configuration
////@Import({org.springdoc.core.SpringDocConfiguration.class, org.springdoc.core.SpringDocWebMvcConfiguration.class,
////		org.springdoc.ui.SwaggerConfig.class, org.springdoc.core.SwaggerUiConfigProperties.class,
////		org.springdoc.core.SwaggerUiOAuthProperties.class,
////		org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration.class})
//public class Swagger2Config {
//
//	@Bean
//	public OpenAPI customOpenAPI() throws IOException {
//		final String locUrl = "http://localhost:8080/ib/";
//
//		return new OpenAPI()
//					//.addServersItem(new Server().url(locUrl))
//
//				.info(new Info()
//						.title("Hello")
//						.description("WARNING: Swagger doesn't work correctly with Chrome. With Firefox works fine!<br/>"))
//				.components(new Components()
//						.addSecuritySchemes("bearer-key", new SecurityScheme()
//								.type(SecurityScheme.Type.APIKEY)
//								.scheme("bearer")).addHeaders("bearer", new Header()));
//
//	}
//}