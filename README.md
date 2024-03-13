# Curl - Gestão de quartos

### Caso de Teste: Cadastro de hospedagem

```bash
curl -X POST http://localhost:8080/hospedagem -H "Content-Type: application/json" -d "{\"nome\": \"Nova Hospedagem\", \"amenidades\": [\"Wi-Fi\", \"TV\"], \"endereco\": {\"ruaAvenida\": \"Rua Nova\", \"cep\": \"54321-987\", \"cidade\": \"Nova Cidade\", \"estado\": \"Novo Estado\"}}"
```
### Caso de Teste: Cadastro de prédio

```bash
curl -X POST http://localhost:8080/hospedagem/predio -H "Content-Type: application/json" -d "{\"nome\": \"Novo Prédio\", \"hospedagem\": {\"id\": 2}}"
```

### Caso de Teste: Cadastro de quarto

```bash
curl -X POST http://localhost:8080/hospedagem/quarto -H "Content-Type: application/json" -d "{\"tipo\": \"Quarto Standard\", \"totalPessoas\": 2, \"camas\": [\"Cama de Casal\"], \"outrosMoveis\": [\"Mesa de Trabalho\"], \"predio\": {\"id\": 5}}"
```

Entendi, aqui estão os comandos `curl` ajustados para o formato que funciona no prompt de comando do Windows (cmd):

### Caso de Teste: Buscar hospedagem por id

```bash
curl -X GET http://localhost:8080/hospedagem/3
```

### Caso de Teste: Alterar hospedagem

```bash
curl -X PUT http://localhost:8080/hospedagem/3 -H "Content-Type: application/json" -d "{\"nome\": \"Novo Nome da Hospedagem\", \"amenidades\": [\"Wi-Fi\", \"TV\"], \"endereco\": {\"ruaAvenida\": \"Rua Nova\", \"cep\": \"54321-987\", \"cidade\": \"Nova Cidade\", \"estado\": \"Novo Estado\"}}"
```

### Caso de Teste: Deletar hospedagem por id

```bash
curl -X DELETE http://localhost:8080/hospedagem/3
```

### Caso de Teste: Buscar hospedagem por id do quarto

```bash
curl -X GET http://localhost:8080/hospedagem/quarto/5
```

### Caso de Teste: Alterar quarto

```bash
curl -X PUT http://localhost:8080/hospedagem/quarto/5 -H "Content-Type: application/json" -d "{\"tipo\": \"Quarto Luxo\", \"totalPessoas\": 3, \"camas\": [\"Cama de Casal\", \"Cama de Solteiro\"], \"outrosMoveis\": [\"Mesa de Trabalho\"]}"
```

### Caso de Teste: Deletar quarto por id

```bash
curl -X DELETE http://localhost:8080/hospedagem/quarto/5
```

### Caso de Teste: Buscar hospedagem por id do prédio

```bash
curl -X GET http://localhost:8080/hospedagem/predio/5
```

### Caso de Teste: Alterar prédio

```bash
curl -X PUT http://localhost:8080/hospedagem/predio/5 -H "Content-Type: application/json" -d "{\"nome\": \"Novo Nome do Prédio.\"}"
```

### Caso de Teste: Deletar prédio por id

```bash
curl -X DELETE http://localhost:8080/hospedagem/predio/5
```

### Caso de Teste: Buscar todas as hospedagens

```bash
curl -X GET http://localhost:8080/hospedagem
```

