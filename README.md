# API de autenticação de APIs.

## Por meio de uma chamada REST com as credenciais é gerado um token JWT como retorno à API.

## Cada API deve ter mapeado o username e password em uma tabela relacional SQL. 

## Tecnologias utilizadas no desenvolvimento: Java com Spring Framework.

## Banco de dados PostgreSQL.

### Exemplos de chamadas:

### POST http://localhost:PORT/autentica

### Body da chamada { 	"username" : "***", 	"password" : "***" }

### POST http://localhost:PORT/autentica/autenticado  - Verifica se o token ainda é valido

### Body da chamada "TokenJWT"  
