
FROM openjdk:11
EXPOSE 8082
ADD target/Kaddem-9.jar  kaddem.jar
ENTRYPOINT ["java" , "-jar" ,"kaddem.jar"]