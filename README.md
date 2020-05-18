# Jokenpo-Pedra-Papel-Tesoura-Lagarto-Spock
Desafio Jogo Jokenpo - API REST em Java 8 e Spring

#### DESAFIO :

Escreva um programa que analise o resultado de múltiplos jogadores em um jogo de jokenpo, 
você deve utilizar técnicas de programação de forma que a solução seja resiliente para possíveis mudanças como por exemplo adicionar e remover jogadas, 
e inserir e remover novos jogadores.

#### REQUISITOS :

Os jogadores deverão informar as entradas através das jogadas e o sistema 
deverá indicar qual o jogador ganhador. 
 
As entradas das jogadas são disponibilizadas através de APIs REST, 
além da aplicação disponibilizar APIs para realização do cadastro dos jogadores 
e das jogadas também tem a possibilidade de consulta-los e excluí-los.

Exemplos:
1. Entrada 1 – Jogador 1 e Jogada Pedra
2. Entrada 2 – Jogador 2 e Jogada Tesoura
3. Entrada 3 – Jogador 3 e Jogada Tesoura
4. Jogar 
5. Resultado Jogador 1 Vitória

#### FLUXO DO JOGO :

![Fluxo do jogo](https://i.imgur.com/nG2CJTg.png)


#### ABORDAGEM TÉCNICA :

- Linguagem: Java 8
- Framework: Spring Boot 2.2
- Gerenciamento de dependência: Gradle
- Realização de Testes Unitário: JUnit
- Utilizada as práticas de Clean Code : Nomes precisos, comentários necessários, DRY (Don’t repeat yourself), entre outros aspectos.
- Não há utilização de banco de dados.
- Não há utilização de bibliotecas utilitárias externas.

#### OBSERVAÇÃO

-	Não utilizar banco de dados.
-	Não utilizar bibliotecas utilitárias externas.
-	O objetivo deste desafio é avaliar o seu conhecimento técnico, estilo de código, conhecimento de arquiteturas, padrões de programação e boas práticas. Faça disso uma oportunidade pra mostrar todo o seu conhecimento.


## ENDPOINTS E EXEMPLOS DE CHAMADAS : 

### 1. JOGADORES (Player)

##### 1.1 Inserção

###### 1.1.1 Exemplo de Chamada

```curl
curl --anyauth --user spring:secret --location --request POST 'http://localhost:8080/jogadores' --header 'Content-Type: application/json' --data-raw '{
    "nome" : "JOGADOR 1"
}'
```

##### 1.2 Listagem de Jogadores Inseridos

###### 1.2.1 Exemplo de Chamada

```curl
curl --anyauth --user spring:secret --location --request GET 'http://localhost:8080/jogadores'
```

###### 1.2.2 Exemplo de Retorno de Sucesso - 200 OK

```json
[
  {
    "id":1,
    "nome":"JOGADOR 1"
  },
  {
    "id":2,
    "nome":"JOGADOR 2"
  } 
]
```

##### 1.3 Buscar jogador inserido por ID

###### 1.3.1 Exemplo de Chamada

```curl
curl --anyauth --user spring:secret --location --request GET 'http://localhost:8080/jogadores/1'
```
###### 1.3.2 Exemplo de Retorno de Sucesso - 200 OK

```json
{
  "id":1,
  "nome":"JOGADOR 1"
}
```

##### 1.4 Exclusão de Jogador por ID

###### 1.4.1 Exemplo de Chamada

```curl
curl --anyauth --user spring:secret --location --request DELETE 'http://localhost:8080/jogadores/1'
```

### 2. JOGADA (Movimento)

##### 2.1 Inserção de Jogada Para Jogador de ID 1

###### 2.1.1 Exemplo de Chamada

```curl
curl --anyauth --user spring:secret --location --request POST 'http://localhost:8080/jogadores/1/movimento' --header 'Content-Type: application/json' --data-raw '{
    "movimento" : "PEDRA"
}'
```

##### 2.2 Buscar movimento inserido por ID

###### 2.2.1 Exemplo de Chamada

```curl
curl --anyauth --user spring:secret --location --request GET 'http://localhost:8080/jogadores/1/movimento'
```
###### 1.3.2 Exemplo de Retorno de Sucesso - 200 OK

```json
{
  "movimento":"PEDRA"
}
```
##### 2.3 Atualizar movimento inserido por ID

```curl
curl --anyauth --user spring:secret --location --request PUT 'http://localhost:8080/jogadores/1/movimento' --header 'Content-Type: application/json' --data-raw '{
    "movimento" : "TESOURA"
}'
```

##### 2.4 Listagem

###### 2.4.1 Exemplo de Chamada

```curl
curl --anyauth --user spring:secret --location --request GET 'http://localhost:8080/jogadores'
```

###### 2.4.2 Exemplo de Retorno de Sucesso - 200 OK

```json
[
    {
        "id": 1,
        "nome": "JOGADOR 1",
        "movimento": {
            "movimento": "TESOURA"
        }
    },
    {
        "id": 2,
        "nome": "JOGADOR 2",
        "movimento": {
            "movimento": "SPOCK"
        }
    }
]
```

### 3. RESULTADO DO JOGO (Play)

##### 3.1 Obter Resultado do Jogo

###### 3.1.1 Exemplo de Chamada

```curl
curl --anyauth --user spring:secret --location --request GET 'http://localhost:8080/partida'
```

###### 3.1.2 Exemplo de Retorno de Sucesso - 200 OK

```json
{
       "resultado": "JOGADOR 1 É O GANHADOR!",
       "historico": [
           "JOGADOR 1 (PEDRA)",
           "JOGADOR 2 (TESOURA)",
           "JOGADOR 3 (TESOURA)"
       ]
}
```

###### 3.1.3 Exemplo de Retorno de Erro - Não Há Jogadores Cadastrados/Jogando

```json
{
    "titulo": "Nenhum jogador encontrado.",
    "status": 409,
    "timestamp": 1589815194102,
    "mensagemDesenvolvedor": "https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Status"
}
```

###### 3.1.4 Exemplo de Retorno de Erro - Há Jogadores Que Ainda Não Realizaram a Jogada

```json
{
    "titulo": "Numero de Movimentos é Insuficiente.",
    "status": 409,
    "timestamp": 1589815221883,
    "mensagemDesenvolvedor": "https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Status"
}
```

##### 3.2 Limpar Todos os Dados

###### 3.2.1 Exemplo de Chamada

```curl
curl --location --request DELETE 'http://localhost:8080/partida'
```