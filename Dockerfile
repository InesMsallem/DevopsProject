FROM openjdk:8

WORKDIR /app

COPY target/achat-1.0.jar /app/

EXPOSE 8089

CMD ["java", "-jar", "achat-1.0.jar"]
