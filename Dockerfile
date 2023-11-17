FROM openjdk:11

EXPOSE 8044

COPY target/Kaddem-0.0.1-SNAPSHOT.jar amenjouini.jar

ENTRYPOINT ["java", "-jar", "amenjouini.jar"]
