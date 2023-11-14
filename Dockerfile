FROM openjdk:11
WORKDIR /app
EXPOSE 9091
# Specify the absolute link to the JAR file
ADD http://192.168.36.62:8081/repository/maven-releases/tn/esprit/rh/achat/1.0/achat-1.0.jar /app/
ENTRYPOINT ["java", "-jar","achat-1.0.jar"]