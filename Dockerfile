FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/agenda-de-equipamentos-cifep-0.0.1-SNAPSHOT.jar /app/agenda-de-equipamentos-cifep.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/agenda-de-equipamentos-cifep.jar"]