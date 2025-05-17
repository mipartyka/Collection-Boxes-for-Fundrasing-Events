# Use an official OpenJDK image
FROM eclipse-temurin:21-jdk

# Set working directory inside the container
WORKDIR /app

# Copy project JAR file to the container
COPY target/Collection-Boxes-for-Fundraising-Events-0.0.1-SNAPSHOT.jar app.jar

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
