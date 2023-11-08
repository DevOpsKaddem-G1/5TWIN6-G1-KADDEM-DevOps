FROM openjdk:11
EXPOSE 8089
RUN mkdir /app

RUN wget -O /app/5TWIN6-G1-Kaddem-0.0.1.jar http://192.168.33.10:8081/repository/maven-releases/com/esprit/5TWIN6-G1-Kaddem/0.0.1/5TWIN6-G1-Kaddem-0.0.1.jar

WORKDIR /app

CMD ["java", "-jar", "5TWIN6-G1-Kaddem-0.0.1-SNAPSHOT.jar"]
