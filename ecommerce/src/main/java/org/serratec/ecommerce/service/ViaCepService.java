package org.serratec.ecommerce.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class ViaCepService {
		public static String buscaEndereco(String cep) {
		
	            HttpClient client = HttpClient.newHttpClient();
	            HttpRequest request = HttpRequest.newBuilder()//navegador
	                    .uri(URI.create("URL: viacep.com.br/ws/01001000/json/" + cep +"/json/"))//o URI que é para se acessar
	                    .build();
	            HttpResponse<String> response = null;
	            try {
	                response = client.send(request, BodyHandlers.ofString());
	                System.out.println(response.body());
	            } catch (IOException | InterruptedException e) {
	                e.printStackTrace();
	            }
	            return response.body();
		}
}
