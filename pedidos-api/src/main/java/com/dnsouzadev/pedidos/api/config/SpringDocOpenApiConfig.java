package com.dnsouzadev.pedidos.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocOpenApiConfig {

    @Bean
    public OpenAPI OpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                            .title("Rest API - Pedidos")
                            .description("API para realizacao de pedidos")
                            .license(new License().name("Apache 2.0").url("http://www.apache.org/licenses/LICENSE-2.0.html"))
                            .contact(new Contact().name("Daniel Souza").email("workdndsza@gmail.com"))
                            .version("v1.0.0")
                );
    }
}
