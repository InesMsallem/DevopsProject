FROM openjdk:8-jre-alpine
WORKDIR /app
ADD /app/build/target/*.jar ./spring-app.jar
CMD ["java", "-jar", "/app/spring-app.jar"]