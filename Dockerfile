FROM openjdk:8-jre-alpine
EXPOSE 3000
ADD ./target/*.jar ./spring-app.jar
ENTRYPOINT ["java","-jar","/spring-app.jar"]