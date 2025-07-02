FROM openjdk:21

WORKDIR /app

COPY target/itau-challenge-0.0.1-SNAPSHOT.jar /app

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "itau-challenge-0.0.1-SNAPSHOT.jar" ]