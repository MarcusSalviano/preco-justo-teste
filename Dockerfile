# Use a imagem oficial do Maven para construir a aplicação
FROM maven:3.8.4-amazoncorretto-17 AS build

# Define o diretório de trabalho
WORKDIR /app

# Copia o arquivo pom.xml e as dependências do Maven
COPY pom.xml .

# Baixa as dependências do Maven
RUN mvn dependency:go-offline

# Copia o código-fonte do projeto
COPY src ./src

# Compila a aplicação
RUN mvn package -DskipTests

# Usa a imagem do Amazon Corretto para rodar a aplicação
FROM amazoncorretto:17

# Define o diretório de trabalho
WORKDIR /app

# Copia o arquivo JAR da imagem de build
COPY --from=build /app/target/preco-justo-teste-1.0.jar ./preco-justo-teste.jar

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "preco-justo-teste.jar"]
