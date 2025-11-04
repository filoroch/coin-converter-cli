package com.alura;

import java.io.IOException;
import java.net.URISyntaxException;

import com.alura.connection.Connection;
import com.alura.connection.ExchangeRateConnection;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException {
        // Configuração do json
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        // Conexão TEM QUE CRIAR UM TESTE AQUI
        var connection = Connection.create("exchangerate");
        
        var responsePair = connection.getCoinComparatorToCoin("BRL", "USD");
        // System.out.println("\nCotação do BRL em relação ao USD");
        // System.out.println(responsePair);
        
        var responseLatest = connection.getCoinToTax("BRL");
        System.out.println("\nCotaçõo atual do BRL em relação a outras moedas");
        System.out.println(responseLatest);
        
        // Conversão para prettry json
        // JsonElement response = JsonParser.parseReader(new InputStreamReader(conection.getInputStream()));
        // var json = gson.toJson(response);
        
        // Retorno
        
    }
}