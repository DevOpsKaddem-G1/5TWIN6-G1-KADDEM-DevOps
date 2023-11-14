FROM openjdk:11
EXPOSE 8085
WORKDIR /app
#RUN apt-get update && apt-get install -y curl
#RUN curl -o Kaddem-9.jar -L "http://10.0.2.15:8081/repository/maven-releases/com/esprit/Kaddem/9/Kaddem-9.jar"
ADD target/Kaddem-9.jar Kaddem-9.jar
ENTRYPOINT ["java", "-jar", "Kaddem-9.jar"]