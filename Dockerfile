FROM openjdk:11
EXPOSE 8085
ADD target/*.jar kaddem.jar
CMD ["java", "-jar", "kaddem.jar"]