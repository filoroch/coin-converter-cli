package com.alura;

public interface IConnection {
    String latestEndpoint(String coin);
    String pairEndpoint(String baseCoin, String convertedCoin);

}
