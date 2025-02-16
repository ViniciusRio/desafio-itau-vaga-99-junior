FROM openjdk:21-jdk-slim
WORKDIR /app
COPY . .
EXPOSE 8080
ENTRYPOINT ["./gradlew", "bootRun"]