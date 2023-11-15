FROM openjdk:11
EXPOSE 8089
RUN apt-get update && apt-get install -y curl
RUN curl -o achat-1.0.jar http://192.168.0.5:8081/#browse/browse:maven-releases:tn%2Fesprit%2Frh%2Fachat%2F1.0%2Fachat-1.0.jar
ADD target/*.jar achat-1.0.jar
ENTRYPOINT ["java","-jar","achat-1.0.jar"]