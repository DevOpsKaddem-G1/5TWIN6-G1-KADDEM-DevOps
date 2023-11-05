FROM openjdk:11-jre-slim
EXPOSE 8085
WORKDIR /app

RUN apt-get update && apt-get install -y curl
RUN curl -o kaddem-1.0.jar -L "http://10.0.2.15:8081/repository/maven-releases/tn/esprit/spring/kaddem/2.0/kaddem-2.0.jar"

ENTRYPOINT ["java", "-jar", "kaddem-2.0.jar"]