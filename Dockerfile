# Use Maven image to build the application
FROM maven:3.9.4-eclipse-temurin-21 AS builder

WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Use a slim JDK image to run the app
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

# Copy only the built jar from the previous stage
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
