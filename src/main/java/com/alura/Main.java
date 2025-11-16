package com.alura;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

import com.alura.connection.Connection;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Scanner userInput = new Scanner(System.in);

        var connection = Connection.create("exchangerate");
        var ctrol = 1;

        while (ctrol != 4) {
            String presentation = """

                    [Coin-converter-cli]
                    An util tool from converter coin to coin

                    [tools]
                    1. Cotação atual de uma moeda em relação as principais moedas
                    2. Cotação de uma moeda em relação a outra
                    3. Conversão de um valor de uma moeda a outra
                    4. Sair
                    """;

            System.out.println(presentation);
            var userInputControl = userInput.nextInt();
            userInput.nextLine();

            switch (userInputControl) {
                case 1: 
                {
                    System.out.println("""
                            Digite a moeda referencia. Ex:
                            - BRL: Real brasileiro
                            - USD: Dolar estadunidense
                            - EUR: Euro
                           """);
                    var userInputCoinReference = userInput.nextLine();
                    var responseCoinTax = connection.getCoinToTax(userInputCoinReference);
                    
                    System.out.println("\nCotaçõo atual do " + userInputCoinReference + " em relação a outras moedas");
                    System.out.println(responseCoinTax);
                }
                break;

                case 2: 
                {
                    System.out.println("""
                            Digite a moeda referencia. Ex:
                            - BRL: Real brasileiro
                            - USD: Dolar estadunidense
                            - EUR: Euro
                           """);
                    var userInputBaseCoin = userInput.nextLine();

                    System.out.printf("""
                            Digite a moeda referencia da qual você deseja obter a cotação em relação a %s. Ex:
                            - BRL: Real brasileiro
                            - USD: Dolar estadunidense
                            - EUR: Euro
                           """, userInputBaseCoin);
                    var userInputTargetCoin = userInput.nextLine();
                    var responseComparator = connection.getCoinComparatorToCoin(userInputBaseCoin, userInputTargetCoin);

                    JsonObject responseComparatorJson = gson.fromJson(responseComparator, JsonObject.class);
                    Double coinTargetTax = responseComparatorJson.get("conversion_rate").getAsDouble();

                    System.out.println("\nCotação do " + userInputBaseCoin + " em relação ao " + userInputTargetCoin);
                    System.out.println(userInputTargetCoin + " "+ coinTargetTax);
                }

                break;

                case 3: 
                {

                    System.out.println("Digite a moeda referencia base Ex: \n-BRL - Real brasileiro\n-USD - Dolar estadunidense");
                    var userInputBaseCoin = userInput.nextLine();

                    System.out.println("Digite a moeda referencia da qual você deseja obter a cotação em relação a " + userInputBaseCoin + " Ex: \n-BRL - Real brasileiro\n-USD - Dolar estadunidense");
                    var userInputTargetCoin = userInput.nextLine();

                    System.out.println("Digite o valor em " + userInputBaseCoin + " que sera convertido para " + userInputTargetCoin);
                    var userInputValueCoinConverter = userInput.nextDouble();

                    var responseComparator = connection.getCoinComparatorToCoin(userInputBaseCoin, userInputTargetCoin);
                    
                    JsonObject responseComparatorJson = gson.fromJson(responseComparator, JsonObject.class);
                    Double coinTargetTax = responseComparatorJson.get("conversion_rate").getAsDouble();

                    var result = operator(userInputValueCoinConverter, coinTargetTax);

                    System.out.println("\nCotação do valor " + userInputValueCoinConverter + " em " + userInputBaseCoin + " para " + userInputTargetCoin + "\n");
                    System.out.println(result);
                    
                }

                break;

                case 4:
                    // userInput.close();
                    System.out.println("Encerrando a aplicação");
                    ctrol = 4;
                    break;

                default:
                    // userInput.close();
                    System.out.println("Encerrando a aplicação");
                    ctrol = 4;
                    break;
            }
        }        
    }
    static Double operator (Double valueForConverter, Double tax){
        return valueForConverter * tax;
    }
}