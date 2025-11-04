package com.alura.connection;

import java.net.URISyntaxException;

public class ExchangeRateConnection extends Connection{

    private String baseUrl = "https://v6.exchangerate-api.com/v6/";
    private String apiToken = System.getenv("ExchangeToken");
    private String url = baseUrl + apiToken;

    @Override
    public String getCoinToTax(String coinCode) throws URISyntaxException {
        String fullUrl = url + "/latest/" + coinCode;
        var response = tryConnect(fullUrl);
        return response;
    }

    @Override
    public String getCoinComparatorToCoin(String baseCoin, String convertedCoin) throws URISyntaxException {
        String fullUrl = url + "/pair/" + baseCoin + "/" + convertedCoin;
        var response = tryConnect(fullUrl);
        return response;
    }
    
}
