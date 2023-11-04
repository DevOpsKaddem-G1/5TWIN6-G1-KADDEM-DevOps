FROM openjdk:11
EXPOSE 8085
ADD target/Kaddem-0.0.1-SNAPSHOT.jar Kaddem-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","Kaddem-0.0.1-SNAPSHOT.jar"]
