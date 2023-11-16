FROM openjdk:8-jre-alpine
WORKDIR /app
COPY ./build/target/*.jar ./spring-app.jar
CMD ["java", "-jar", "/app/spring-app.jar"]