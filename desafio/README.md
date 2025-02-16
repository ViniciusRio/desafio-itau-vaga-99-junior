# Desafio API

Este projeto é uma API para gerenciar transações financeiras, desenvolvida utilizando **Spring Boot 3**, **Java 21**, **Gradle** e **PostgreSQL**.

## 📌 Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3** (Web, Actuator, Log4j2, OpenAPI)
- **SpringDoc OpenAPI** (Swagger UI para documentação da API)
- **Docker & Docker Compose**
- **Gradle 8**

## 🚀 Como executar o projeto

### 1️⃣ Pré-requisitos
Antes de iniciar, certifique-se de ter instalado:
- [Java 21](https://adoptium.net/)
- [Gradle 8+](https://gradle.org/install/)
- [Docker e Docker Compose](https://www.docker.com/)

### 2️⃣ Clonar o repositório
```bash
git clone https://github.com/ViniciusRio/desafio-itau-vaga-99-junior
```

### 3️⃣ Configurar as variáveis de ambiente
Vairiaveis de ambiente no proprio docker compose



### 5️⃣ Construir e executar a aplicação
Executar compilado Java
```bash
./gradlew clean build --refresh-dependencies
java -jar build/libs/desafio.jar
```

### 4️⃣ Rodar projeto com docker
```bash
docker-compose up --build
```
A API estará disponível em `http://localhost:8080`

## 🔍 Documentação da API
Após rodar a aplicação, acesse a documentação no Swagger UI:
```
http://localhost:8080/swagger-ui/index.html
```
A documentação detalha todos os endpoints e seus respectivos parâmetros.

## 🧪 Rodando os Testes
Para executar os testes automatizados:
```bash
./gradlew test
```

## 📂 Estrutura do Projeto
```
/desafio-api
│-- src/main/java/com/itau/desafio  # Código-fonte da API
│-- src/main/resources               # Arquivos de configuração (application.properties, log4j2.xml)
│-- src/test/java                    # Testes automatizados
│-- build.gradle.kts                 # Configuração do Gradle
│-- docker-compose.yml                # Configuração do Docker
│-- README.md                         # Documentação do projeto
```

## 📜 Licença
Este projeto está licenciado sob a [Apache 2.0 License](https://www.apache.org/licenses/LICENSE-2.0).

