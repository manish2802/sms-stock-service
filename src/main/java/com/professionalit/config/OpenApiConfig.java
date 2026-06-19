//package com.professionalit.config;
//
//
//import java.util.List;
//
//import org.springframework.context.annotation.Bean;
//
//import io.swagger.v3.oas.annotations.OpenAPIDefinition;
//import io.swagger.v3.oas.annotations.info.Info;
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.servers.Server;
//
//@OpenAPIDefinition(
//	    info = @Info(
//	        title = "Stock Service API",
//	        version = "1.0"
//	    )
//	)
//public class OpenApiConfig {
//	@Bean
//    public OpenAPI customOpenAPI() {
//        return new OpenAPI()
//                .servers(List.of(
//                        new Server().url("/")
//                ));
//    }
//}
