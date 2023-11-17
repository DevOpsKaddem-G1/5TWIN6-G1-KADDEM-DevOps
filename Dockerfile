FROM openjdk:11
EXPOSE 8085
WORKDIR /app
RUN apt-get update && apt-get install -y curl
RUN curl -o 5SE1-G1-Kaddem.jar -L "http://10.0.2.15:8081/repository/maven-releases/com/esprit/Kaddem/9/Kaddem-9.jar"
#ADD target/Kaddem-9.jar 5SE1-G1-Kaddem.jar
ENTRYPOINT ["java", "-jar", "5SE1-G1-Kaddem.jar"]