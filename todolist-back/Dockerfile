FROM adoptopenjdk/openjdk11

WORKDIR /back-app

COPY target/todolist-0.0.1-SNAPSHOT.jar ./

EXPOSE 8080

CMD ["java", "-jar", "todolist-0.0.1-SNAPSHOT.jar"]