FROM openjdk:8-jre-alpine as mvn-build
WORKDIR /app
COPY settings.xml /root/.m2/settings.xml
COPY --from=mvn-build /app/build/target/*.jar ./spring-app.jar
CMD ["java", "-jar", "/app/spring-app.jar"]