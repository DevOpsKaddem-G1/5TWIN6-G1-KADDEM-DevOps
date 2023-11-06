FROM openjdk:11
EXPOSE 8085
WORKDIR /app
RUN apt-get update && apt-get install -y curl
RUN curl -o kaddem-1.0.1.jar -L "http://10.0.2.15:8081/repository/maven-releases/com/esprit/kaddem/1.0.1/kaddem-1.0.1.jar"
ENTRYPOINT ["java", "-jar", "kaddem-1.0.1.jar"]