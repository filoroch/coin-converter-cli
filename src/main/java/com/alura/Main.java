package com.alura;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException {
        // Configuração do json
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        // Conexão
        var connection = new Connection();
        
        var responsePair = connection.pair("BRL", "USD");
        System.out.println("\nCotação do BRL em relação ao USD");
        System.out.println(responsePair);
        
        var responseLatest = connection.latest("BRL");
        System.out.println("\nCotaçõo atual do BRL em relação a outras moedas");
        System.out.println(responseLatest);
        
        // Conversão para prettry json
        // JsonElement response = JsonParser.parseReader(new InputStreamReader(conection.getInputStream()));
        // var json = gson.toJson(response);
        
        // Retorno
        
    }
}