<h1 align=center> Coin-converter-cli</h1>
Conversor de moedas desenvolvido em Java 21 para o desafio da Oracle Next Education em parceria com a Alura

## Sobre o projeto
O projeto consiste em um conversor de moedas que roda no terminal (cli/linha de comando), que exibe um menu de opções e permite converter moedas

### Fluxo
- Inicio
- Exibe menu de opções iterativo
    1. Converter \$ para R$
    2. Converter R$ para $
    3. Sair
- Usuario insere opção de conversão de moeda
- Conversão e feita
- renderização no cli
- Fim
### Esquema de classes
- classe de conexão -> responsavel por criar o request, passar os parametros, fazer a requisição, lidar com erros e retornar o response
    ```mermaid
        classDiagram
            class connection {
                - String ApiToken
                - String baseUrl
                - String latestEndpoint 
                - String pairEndpoint

                + tryConnect() // Recebe um endpoint completo e retorna um response
                + buildEndpoint(baseUrl, endpoint)
                + getPairEndpoint() // recebe os parametros, chama buildEndpoint 
                + getLatestEndpoint(baseUrl, latestEndpoint)

            }
    ```

- classe de tratamento/parsing -> responsavel por tratar a saida recebida para o padrão legivel JSON , alem de fornecer somente os dados esperados
```mermaid
---
title: Coin-converter-cli fluxogram
---
flowchart TD
    U1["Inicia o programa"] --> P1{"Exibe opções"} 
    P1 --> | Convert | P2["R$ to $"] 
    P1 --> | Convert | P3["$ to R$"]
    P1 --> P4["Sair"] 
    
    
```

## Como rodar
## Ideias futuras
- [ ] Implementar interface de conexão para que a api usada seja passada concretamente na classe de conexão
## Creditos