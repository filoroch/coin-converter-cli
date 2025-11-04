package com.alura.connection;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

/**
 * <h2>Connection</h2>
 * <Strong>Objetivo:</Strong> fornecer uma abstração para a conexão com a api, fazendo com que, mesmo se novos endpoints forem introduzidos ou uma nova api seja usada, tenha metodos comuns que possam ser usados
 */

public abstract class Connection implements IApiEndpoints{
    // protected String baseUrl = "https://v6.exchangerate-api.com/v6/";
    // protected String apiToken = System.getenv("ExchangeToken");
    // protected String url = baseUrl + apiToken;
    private final HttpClient CLIENT = HttpClient.newHttpClient();

    /**Factory de connection para dinamicamente definir qual api sera usada */
    public static Connection create(String useApi){
        if (useApi.contains("exchangerate")) {
            return new ExchangeRateConnection();   
        }

        throw new IllegalArgumentException("Api desconhecida");
    }

    /**Metodo responsavel por montar a requisição, definir a resposta e tentar executar a consulta
     * @param url -> url montada ja com endpoint passada diretamente pelo metodo que monta o endpoint (altamente acoplado)
    */
    protected String tryConnect (String url) throws URISyntaxException {

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

}
