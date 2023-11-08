FROM openjdk:11
EXPOSE 8085
WORKDIR /app
RUN apt-get update && apt-get install -y curl
RUN curl -o kaddem-7.jar -L "http://10.0.2.15:8081/repository/maven-releases/com/esprit/kaddem/7/kaddem-7.jar"
#ADD target/Kaddem-7.jar kaddem.jar
ENTRYPOINT ["java", "-jar", "kaddem.jar"]