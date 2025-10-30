package com.alura;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException {
        
        // Configurações essenciais
        String baseUrl = "https://v6.exchangerate-api.com/v6/";
        String token = System.getenv("ExchangeToken");
        // String path = "/pair/USD/BRL";
        String path = "/latest/BRL";
        String url_str = baseUrl + token + path;
        
        // Configuração do json
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        // Conexão
        URL url = new URI(url_str).toURL();
        HttpURLConnection conection = (HttpURLConnection) url.openConnection();
        conection.connect();
    
        // Conversão para prettru jon
        JsonElement response = JsonParser.parseReader(new InputStreamReader(conection.getInputStream()));
        var json = gson.toJson(response);
        
        // Retorno
        System.out.println(json);
    }
}