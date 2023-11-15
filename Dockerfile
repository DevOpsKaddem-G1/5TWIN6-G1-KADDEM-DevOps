FROM openjdk:11
EXPOSE 8044
#RUN mkdir /app
WORKDIR /app

RUN apt-get update && apt-get install -y curl
RUN curl -o Kaddem-9.jar -L "http://192.168.33.10:8081/repository/maven-releases/com/esprit/Kaddem/9/Kaddem-9.jar"
#ADD http://192.168.33.10:8081/repository/maven-releases/com/esprit/5TWIN6-G1-Kaddem/0.0.1/5TWIN6-G1-Kaddem-0.0.1.jar /app/
CMD ["java", "-jar", "5TWIN6-G1-Kaddem-0.0.1.jar"]
