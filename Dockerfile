FROM openjdk:11
EXPOSE 8085
WORKDIR /app
RUN apt-get update && apt-get install -y curl
RUN curl -o kaddem-3.1.jar -L "http://10.0.2.15:8081/repository/maven-releases/com/esprit/kaddem/3.1/kaddem-3.1.jar"
ENTRYPOINT ["java", "-jar", "kaddem-3.1.jar"]