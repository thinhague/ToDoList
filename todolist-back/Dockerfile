FROM adoptopenjdk/openjdk11

WORKDIR /back-app

COPY . /back-app

EXPOSE 8080

# Define o comando a ser executado quando o contêiner for iniciado
CMD ["java", "-jar", "./target/todolist-0.0.1-SNAPSHOT.jar"]