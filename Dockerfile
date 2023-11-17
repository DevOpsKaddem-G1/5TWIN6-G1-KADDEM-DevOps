
FROM openjdk:11
EXPOSE 8082
ADD target/Kaddem-9.jar  Kaddem.jar
ENTRYPOINT ["java" , "-jar" ,"Kaddem.jar"]