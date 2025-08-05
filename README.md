# Cryptography

**Cryptography** é um projeto de exemplo utilizando **Spring Boot** que demonstra como armazenar dados sensíveis de forma segura usando criptografia simétrica. O serviço expõe uma API REST para gerenciar transações financeiras, garantindo que o documento do usuário e o token de cartão de crédito sejam criptografados antes de serem persistidos no banco de dados e descriptografados ao serem lidos novamente.

---

## 🛠️ Tecnologias Utilizadas

- Java 17
- Spring Boot 3
- Spring Data JPA
- PostgreSQL
- Jasypt (Java Simplified Encryption)
- Docker

---

## 🔐 Funcionamento da Criptografia

A criptografia é realizada usando a biblioteca **Jasypt**, com chave simétrica definida pela variável de ambiente `CRYPTO_KEY`. A classe `CryptoService` centraliza os métodos de criptografar e descriptografar os dados sensíveis.

```java
encryptor.setPassword(System.getenv("CRYPTO_KEY"));
```

A criptografia ocorre automaticamente com anotações da JPA:

- `@PrePersist`: antes de salvar no banco de dados
- `@PostLoad`: após carregar do banco de dados

---

## 📦 Entidade Principal: `TransactionEntity`

Campos criptografados:

- `userDocument` 
- `creditCardToken`

Esses campos são convertidos dinamicamente no momento da persistência e leitura usando os métodos do `CryptoService`.

---

## 🧪 Endpoints REST

| Método | Rota                    | Descrição                          |
|--------|-------------------------|------------------------------------|
| GET    | `/transactions`         | Lista todas as transações          |
| GET    | `/transactions/{id}`    | Retorna uma transação por ID       |
| POST   | `/transactions`         | Cria uma nova transação            |
| PATCH  | `/transactions/{id}`    | Atualiza uma transação existente   |
| DELETE | `/transactions/{id}`    | Remove uma transação               |

---

## ⚙️ Como Rodar Localmente

### Pré-requisitos

- Java 17+
- Maven
- PostgreSQL (local ou via Docker)
- Variável de ambiente `CRYPTO_KEY`

### Configuração

No arquivo `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/crypto
spring.datasource.username=admin
spring.datasource.password=123
spring.jpa.hibernate.ddl-auto=update
```

### Executando a aplicação

```bash
mvn spring-boot:run
```

---

## 🐳 Utilizando com Docker

```bash
cd docker
docker-compose up
```

Isso inicia a aplicação junto com um container PostgreSQL.

---

## 🔐 Variáveis de Ambiente

Defina a variável de ambiente `CRYPTO_KEY` com a senha usada na criptografia:

```bash
export CRYPTO_KEY="sua-chave-secreta"
```

---

## 📁 Estrutura do Projeto

```
src
└── main
    ├── java
    │   └── com.springboot.cryptography
    │       ├── controller
    │       ├── dto
    │       ├── entity
    │       ├── repository
    │       └── service
    └── resources
        └── application.properties
```

