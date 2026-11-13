# Lavajato API

API REST para gerenciamento de um lava-rápido, desenvolvida com Java, Spring Boot e PostgreSQL. O sistema permite cadastrar clientes, veículos, serviços, registrar atendimentos e consultar os itens vinculados a cada atendimento.

## 🚀 Tecnologias

- Java 17
- Spring Boot 4.1.2
- Maven
- PostgreSQL
- Docker + Docker Compose
- JPA / Hibernate

## 📌 Funcionalidades

- Cadastro, listagem, atualização e exclusão de clientes
- Cadastro, listagem, busca, atualização e exclusão de veículos
- Cadastro, listagem, busca, atualização e exclusão de serviços
- Cadastro, listagem, busca, atualização e exclusão de atendimentos
- Consulta dos itens de atendimento
- Validação de dados de entrada
- Tratamento global de exceções

## 🧱 Estrutura do projeto

```text
lavajato-api/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── br/com/niltonpereira/lavajato_api/
│   │   │       ├── config/
│   │   │       ├── controller/
│   │   │       ├── dto/
│   │   │       ├── exception/
│   │   │       ├── handler/
│   │   │       ├── model/
│   │   │       └── service/
│   │   └── resources/
│   │       └── application.yaml
│   └── test/
├── docker-compose.yaml
├── pom.xml
├── mvnw
├── mvnw.cmd
├── HELP.md
└── README.md
```

## ⚙️ Pré-requisitos

Antes de rodar o projeto, certifique-se de ter instalado:

- Java 17+
- Maven (ou usar o wrapper `./mvnw`)
- Docker e Docker Compose

## 🐳 Banco de dados com Docker

O projeto já inclui um arquivo `docker-compose.yaml` para subir o PostgreSQL localmente.

```bash
docker compose up -d
```

Isso irá criar um banco PostgreSQL na porta `5436` com:

- Banco: `lavajato`
- Usuário: `postgres`
- Senha: `devpassword`

## ▶️ Executando a aplicação

### 1. Clonar o repositório

```bash
git clone <url-do-repositorio>
cd lavajato-api
```

### 2. Subir o banco

```bash
docker compose up -d
```

### 3. Rodar a aplicação

```bash
./mvnw spring-boot:run
```

A API ficará disponível em:

```text
http://localhost:8080
```

## 🧩 Configuração da aplicação

As configurações do banco estão em:

- `src/main/resources/application.yaml`

Exemplo da configuração atual:

```yaml
spring:
  application:
    name: lavajato-api

  datasource:
    url: jdbc:postgresql://localhost:5436/lavajato
    username: postgres
    password: devpassword
    driver-class-name: org.postgresql.Driver

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

## 📡 Endpoints principais

### Clientes

| Método | Endpoint         | Descrição               |
| ------ | ---------------- | ----------------------- |
| POST   | `/clientes`      | Cadastra um cliente     |
| GET    | `/clientes`      | Lista todos os clientes |
| PUT    | `/clientes/{id}` | Atualiza um cliente     |
| DELETE | `/clientes/{id}` | Remove um cliente       |

### Veículos

| Método | Endpoint         | Descrição               |
| ------ | ---------------- | ----------------------- |
| POST   | `/veiculos`      | Cadastra um veículo     |
| GET    | `/veiculos`      | Lista todos os veículos |
| GET    | `/veiculos/{id}` | Busca veículo por ID    |
| PUT    | `/veiculos/{id}` | Atualiza um veículo     |
| DELETE | `/veiculos/{id}` | Remove um veículo       |

### Serviços

| Método | Endpoint         | Descrição               |
| ------ | ---------------- | ----------------------- |
| POST   | `/servicos`      | Cadastra um serviço     |
| GET    | `/servicos`      | Lista todos os serviços |
| GET    | `/servicos/{id}` | Busca serviço por ID    |
| PUT    | `/servicos/{id}` | Atualiza um serviço     |
| DELETE | `/servicos/{id}` | Remove um serviço       |

### Atendimentos

| Método | Endpoint             | Descrição                   |
| ------ | -------------------- | --------------------------- |
| POST   | `/atendimentos`      | Cadastra um atendimento     |
| GET    | `/atendimentos`      | Lista todos os atendimentos |
| GET    | `/atendimentos/{id}` | Busca atendimento por ID    |
| PUT    | `/atendimentos/{id}` | Atualiza um atendimento     |
| DELETE | `/atendimentos/{id}` | Remove um atendimento       |

### Itens de atendimento

| Método | Endpoint                  | Descrição            |
| ------ | ------------------------- | -------------------- |
| GET    | `/itens-atendimento`      | Lista todos os itens |
| GET    | `/itens-atendimento/{id}` | Busca item por ID    |
| DELETE | `/itens-atendimento/{id}` | Remove um item       |

## 🧪 Exemplo de uso

### Criar cliente

```bash
curl -X POST http://localhost:8080/clientes \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "João da Silva",
    "telefone": "11999999999",
    "email": "joao@email.com"
  }'
```

### Listar serviços

```bash
curl http://localhost:8080/servicos
```

## 🛠️ Observações

- O projeto usa JPA com `ddl-auto: update`, então o esquema do banco é gerado automaticamente.
- A API segue uma arquitetura em camadas: controller, service, DTO e model/entity.
- As mensagens de erro são tratadas por meio de `@RestControllerAdvice`.

## 📁 Como contribuir

1. Faça um fork do projeto
2. Crie uma branch para a sua funcionalidade
3. Realize as alterações
4. Envie um pull request com descrição clara do que foi alterado

## 📄 Licença

Este projeto não possui licença definida no momento.

---

Se quiser, posso também criar uma versão mais completa com:

- exemplos de payload JSON para todos os endpoints
- documentação Swagger/OpenAPI
- instruções de deploy em produção
- README com badges e visual mais profissional
