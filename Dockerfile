
FROM openjdk:11
EXPOSE 8082
ADD target/Kaddem-9.jar.original  Kaddem.jar
ENTRYPOINT ["java" , "-jar" ,"Kaddem.jar"]