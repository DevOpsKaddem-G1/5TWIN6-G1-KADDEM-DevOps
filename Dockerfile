FROM openjdk:11
EXPOSE 8085
WORKDIR /app
RUN apt-get update && apt-get install -y curl
RUN curl -o kaddem-1.1.5.jar -L "http://10.0.2.15:8081/repository/maven-releases/com/esprit/kaddem/1.1.5/kaddem-1.1.5.jar"
#ADD target/Kaddem-1.0.2.jar Kaddem-1.0.2.jar
ENTRYPOINT ["java", "-jar", "kaddem-1.1.5.jar"]