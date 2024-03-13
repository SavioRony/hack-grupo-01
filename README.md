## Gestão de quartos

### Cadastro de hospedagem

```bash
curl -X POST http://localhost:8080/hospedagem -H "Content-Type: application/json" -d "{\"nome\": \"Nova Hospedagem\", \"amenidades\": [\"Wi-Fi\", \"TV\"], \"endereco\": {\"ruaAvenida\": \"Rua Nova\", \"cep\": \"54321-987\", \"cidade\": \"Nova Cidade\", \"estado\": \"Novo Estado\"}}"
```
### Cadastro de prédio

```bash
curl -X POST http://localhost:8080/hospedagem/predio -H "Content-Type: application/json" -d "{\"nome\": \"Novo Prédio\", \"hospedagem\": {\"id\": 2}}"
```

### Cadastro de quarto

```bash
curl -X POST "http://localhost:8080/hospedagem/quarto" -H "Content-Type: application/json" -d "{\"tipo\": \"Quarto Standard\", \"totalPessoas\": 2, \"camas\": [\"Cama de Casal\"], \"outrosMoveis\": [\"Mesa de Trabalho\"], \"banheiro\": [\"Chuveiro\", \"Pia\"], \"valorDiaria\": 100.0, \"quantidade\": 5, \"predio\": {\"id\": 456}}"
```

### Buscar hospedagem por id

```bash
curl -X GET http://localhost:8080/hospedagem/3
```

### Alterar hospedagem

```bash
curl -X PUT http://localhost:8080/hospedagem/3 -H "Content-Type: application/json" -d "{\"nome\": \"Novo Nome da Hospedagem\", \"amenidades\": [\"Wi-Fi\", \"TV\"], \"endereco\": {\"ruaAvenida\": \"Rua Nova\", \"cep\": \"54321-987\", \"cidade\": \"Nova Cidade\", \"estado\": \"Novo Estado\"}}"
```

### Deletar hospedagem por id

```bash
curl -X DELETE http://localhost:8080/hospedagem/3
```

### Buscar hospedagem por id do quarto

```bash
curl -X GET http://localhost:8080/hospedagem/quarto/5
```

### Alterar quarto

```bash
curl -X PUT "http://localhost:8080/hospedagem/quarto/456" -H "Content-Type: application/json" -d "{\"tipo\": \"Quarto Luxo\", \"totalPessoas\": 3, \"camas\": [\"Cama de Casal\", \"Cama de Solteiro\"], \"outrosMoveis\": [\"Mesa de Trabalho\"], \"banheiro\": [\"Chuveiro\", \"Pia\", \"Banheira\"], \"valorDiaria\": 150.0, \"quantidade\": 10}"
```

### Deletar quarto por id

```bash
curl -X DELETE "http://localhost:8080/hospedagem/quarto/456"
```

### Buscar hospedagem por id do prédio

```bash
curl -X GET http://localhost:8080/hospedagem/predio/5
```

### Alterar prédio

```bash
curl -X PUT http://localhost:8080/hospedagem/predio/5 -H "Content-Type: application/json" -d "{\"nome\": \"Novo Nome do Prédio.\"}"
```

### Deletar prédio por id

```bash
curl -X DELETE http://localhost:8080/hospedagem/predio/5
```

### Buscar todas as hospedagens

```bash
curl -X GET http://localhost:8080/hospedagem
```
## Gestão de serviços e opcionais

###  Cadastro de serviço

```bash
curl -X POST "http://localhost:8080/servico" -H "Content-Type: application/json" -d "{\"nome\": \"Nome do Serviço\", \"valor\": 30.00, \"hospedagem\": {\"id\": 1}}"
```

###  Cadastro de item

```bash
curl -X POST "http://localhost:8080/item" -H "Content-Type: application/json" -d "{\"nome\": \"Nome do Item\", \"valor\": 20.00, \"hospedagem\": {\"id\": 1}}"
```
###  Buscar serviço por id

```bash
curl -X GET "http://localhost:8080/servico/2"
```
###  Buscar item por ID

```bash
curl -X GET "http://localhost:8080/item/456"
```

###  Buscar serviço por hospedagem

```bash
curl -X GET "http://localhost:8080/servico/hospedagem/1"
```

###  Buscar itens por hospedagem

```bash
curl -X GET "http://localhost:8080/item/hospedagem/1"
```

###  Alterar serviço

```bash
curl -X PUT "http://localhost:8080/servico/2" -H "Content-Type: application/json" -d "{\"nome\": \"Novo Nome do Serviço\", \"valor\": 50.00}"
```

###  Alterar item

```bash
curl -X PUT "http://localhost:8080/item/2" -H "Content-Type: application/json" -d "{\"nome\": \"Novo Nome do Item\", \"valor\": 25.00}"
```

###  Deletar item por ID

```bash
curl -X DELETE "http://localhost:8080/item/2"
```
###  Deletar serviço por id

```bash
curl -X DELETE "http://localhost:8080/servico/2"
```
