# Construindo a aplicação com o Maven
FROM maven:3.8.3-openjdk-11 AS build
WORKDIR /app

COPY . .

# Executar o comando de compilação do Maven para criar a pasta "target"
RUN mvn package

# Executando a aplicação com o OpenJDK
FROM adoptopenjdk/openjdk11
WORKDIR /back-app

# Copiar o JAR gerado durante a compilação a partir do estágio anterior
COPY --from=build /app/target/todolist-0.0.1-SNAPSHOT.jar ./

EXPOSE 8080

CMD ["java", "-jar", "todolist-0.0.1-SNAPSHOT.jar"]
