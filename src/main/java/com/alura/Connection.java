package com.alura;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;

/**
 * <h2>Connection</h2>
 * <Strong>Objetivo:</Strong> fornecer uma abstração para a conexão com a api, fazendo com que, mesmo se novos endpoints forem introduzidos ou uma nova api seja usada, tenha metodos comuns que possam ser usados
 */

public class Connection {
    private String baseUrl = "https://v6.exchangerate-api.com/v6/";
    private String apiToken = System.getenv("ExchangeToken");
    private String url = baseUrl + apiToken;

    // Metodo que faz a conexão de fato e retorna um response
    private HttpResponse<String> tryConnect () throws URISyntaxException {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(new URI(this.url))
            .GET()
            .build();

        HttpResponse<String> response = null;

        try {
            response = HttpClient.newHttpClient()
                .send(request, BodyHandlers.ofString());
        } catch (IOException | InterruptedException e){
            System.out.println("Ocorreu um erro mano, olha aqui: \n" + e);
        }

        if (response.statusCode() != 200) {
            throw new RuntimeException("Conexao mal sucedida: " + response.statusCode());
        }

        return response;
    }
    // Metodo que constroi o endpoint com base no baseURL + token + endpoint
    public HttpResponse<String> latest (String coinCode) throws URISyntaxException{
        this.url = url + "/latest/" + coinCode;
        var response = tryConnect();
        return response;
    }
    // Metodo que recebe os parametros e finaliza o requestURL
    //  
}
