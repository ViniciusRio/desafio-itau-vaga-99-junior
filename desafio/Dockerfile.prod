# Build
FROM openjdk:21-jdk-slim AS build
WORKDIR /app
COPY . .
RUN ./gradlew build


# Final
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /app/build/libs/desafio-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]