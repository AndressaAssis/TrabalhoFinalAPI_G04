package org.serratec.ecommerce.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import org.serratec.ecommerce.dto.EnderecoViacepDto;

import com.google.gson.Gson;

public class ViaCepService {
		public static EnderecoViacepDto buscaEndereco(String cep) {
		
	            HttpClient client = HttpClient.newHttpClient();
	            HttpRequest request = HttpRequest.newBuilder()
	                    .uri(URI.create("https://viacep.com.br/ws/" + cep + "/json/")) 
	                    .build();
	            HttpResponse<String> response = null;
	            
	            EnderecoViacepDto endereco = new EnderecoViacepDto();
	            try {
	                response = client.send(request, BodyHandlers.ofString());
	                System.out.println(response.body());
	                
	                Gson gson = new Gson();

	                endereco = gson.fromJson(response.body(), EnderecoViacepDto.class);

	                return endereco;
	            } catch (IOException | InterruptedException e) {
	                e.printStackTrace();
	            }
	            return endereco;
		}
}
