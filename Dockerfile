FROM openjdk:11
EXPOSE 8085
#WORKDIR /app
#RUN apt-get update && apt-get install -y curl
#RUN curl -o kaddem-1.2.0.jar -L "http://10.0.2.15:8081/repository/maven-releases/com/esprit/kaddem/1.2.0/kaddem-1.2.0.jar"
ADD target/Kaddem-4.jar kaddem.jar
ENTRYPOINT ["java", "-jar", "kaddem.jar"]