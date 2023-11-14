FROM openjdk:11
EXPOSE 8085
ADD target/5TWIN6-G1-Kaddem-0.0.2-SNAPSHOT.jar etudiant.jar
CMD ["java", "-jar", "etudiant.jar"]
