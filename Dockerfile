# Use an official OpenJDK runtime as a parent image
FROM openjdk:8

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the Jenkins workspace to the container
COPY achat/target/achat-1.0.jar /app/

# Expose the port the app runs on
EXPOSE 8089

# Run the JAR file
CMD ["java", "-jar", "achat-1.0.jar"]
