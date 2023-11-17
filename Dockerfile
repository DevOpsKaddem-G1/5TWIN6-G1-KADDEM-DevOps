FROM openjdk:11

EXPOSE 8044

COPY target/Kaddem-0.0.1-SNAPSHOT.jar Kaddem-0.0.1.jar

ENTRYPOINT ["java", "-jar", "Kaddem-0.0.1.jar"]
