package com.alura.connection;

import java.net.URISyntaxException;
/**
 * <h2> ApiEndpoins Interface
 * <p> Responsavel por fornecer uma base comum de endpoints esperados em todas as subclasses de Connection
 */
public interface IApiEndpoints {

    /**<h2> Recuperar taxas por moeda
     * <p>Recupera todas as taxas de conversão em relação a moeda base
     * @param coin -> String da moeda que sera usada como base para recuperar as conversões. Sempre no formato "BRL", "USD" e etc
     * @return retorna um <code>response.body()</code> com os dados da requisições na formatação (ate então) em que foram feitos
     * */
    String getCoinToTax(String coin) throws URISyntaxException;
    String getCoinComparatorToCoin(String baseCoin, String convertedCoin) throws URISyntaxException;
}
