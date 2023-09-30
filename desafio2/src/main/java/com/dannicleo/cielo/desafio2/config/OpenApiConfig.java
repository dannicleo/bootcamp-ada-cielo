package com.dannicleo.cielo.desafio2.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "API Clientes",
                contact = @Contact(
                        name = "Dannicléo Teles",
                        email = "dannicleo@gmail.com",
                        url = "https://google.com.br"
                ),
                description = "API Clientes para o Bootcamp da Adah/Cielo",
                version = "1.0",
                license = @License(
                    name = "Licence name",
                    url = "https://google.com.br"
                ),
                termsOfService = "Terms of Service"
        ),
        servers = {
                @Server(
                        description = "Local Enviroment",
                        url = "http://localhost:8080"
                )
        }
)

public class OpenApiConfig {




}
