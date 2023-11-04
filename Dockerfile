FROM openjdk:11
EXPOSE 8089
ADD target/5TWIN6-G1-Kaddem-0.0.1-SNAPSHOT.jar 5TWIN6-G1-kaddem.jar
ENTRYPOINT ["java","-jar","5TWIN6-G1-kaddem.jar"]
