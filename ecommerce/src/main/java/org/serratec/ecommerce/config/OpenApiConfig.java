package org.serratec.ecommerce.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(info = @Info(
		title = "API de E-commerce de Venda de Chaves para Jogos",
		version = "1.0",
		description = "Esta API é utilizada para gerenciar um e-commerce que vende chaves de ativação de jogos digitais. Os usuários podem explorar a lista de jogos disponíveis, realizar compras e receber chaves para baixar os jogos. A documentação segue a especificação OpenAPI 3.0."
		))
public class OpenApiConfig {

}
