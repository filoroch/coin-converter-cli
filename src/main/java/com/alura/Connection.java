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
    private final HttpClient CLIENT = HttpClient.newHttpClient();

    // Metodo que faz a conexão de fato e retorna um response
    private String tryConnect (String url) throws URISyntaxException {

        // Criar a requisição
        HttpRequest request = HttpRequest.newBuilder()
            .uri(new URI(url))
            .GET()
            .build();

        // Cria a resposta
        HttpResponse<String> response = null;

        // Tenta fazer a conexão de fato
        try {
            response = CLIENT.send(request, BodyHandlers.ofString());
        } catch (IOException | InterruptedException e){
            System.out.println("Ocorreu um erro mano, olha aqui: \n" + e);
        }

        if (response.statusCode() != 200) {
            return "Erro: codStatus " + response.statusCode() ;
        }

        return response.body();
    }
    // Metodo que constroi o endpoint com base no baseURL + token + endpoint
    public String latest (String coinCode) throws URISyntaxException{
        String fullUrl = url + "/latest/" + coinCode;
        var response = tryConnect(fullUrl);
        return response;
    }

    public String pair (String baseCoin, String targetCoin) throws URISyntaxException {
        String fullUrl = url + "/pair/" + baseCoin + "/" + targetCoin;
        var response = tryConnect(fullUrl);
        return response;
    }
}
