# Use Java 17 (stable for Spring Boot)
FROM openjdk:17-jdk-slim

# Copy jar file
COPY target/sattva-backend-0.0.1-SNAPSHOT.jar app.jar

# Run app
ENTRYPOINT ["java","-jar","/app.jar"]
