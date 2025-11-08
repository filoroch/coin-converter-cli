package com.alura;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URISyntaxException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.alura.connection.Connection;
import com.alura.connection.ExchangeRateConnection;

public class ConnectionTest {
    
    private Connection connection;

    @BeforeEach
    public void setUp(){
        connection = new ExchangeRateConnection();
    }

    @Test
    @DisplayName("Verifica se a api esta sendo chamada conforme esperado")
    public void testLatest_precisaRetonar200() throws URISyntaxException {
        var response = connection.getCoinToTax("BRL");

        assertNotNull(response);
        assertFalse(response.contains("Erro"));
        assertTrue(response.contains("conversion_rates"));
        assertTrue(response.contains("BRL"));
    }

    @Test
    @DisplayName("Verifica se a api vai falhar caso não seja passado uma moeda vlida")
    public void testLatest_precisaFalhar() throws URISyntaxException {
        var response = connection.getCoinToTax("BRASIL");

        assertNotNull(response);
        assertFalse(response.contains("conversion_rates"));
        assertTrue(response.contains("Erro"));
    }

    @Test
    @DisplayName("Teste")
    public void testPair() {

    }
}
