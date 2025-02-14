FROM openjdk:21-jdk-slim
WORKDIR /app
COPY . .
EXPOSE 8080 35729
ENTRYPOINT ["./gradlew", "bootRun"]