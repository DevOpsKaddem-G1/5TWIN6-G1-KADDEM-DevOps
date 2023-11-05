FROM openjdk:11
EXPOSE 8085
ADD target/Kaddem-2.2.jar Kaddem-2.2.jar
ENTRYPOINT ["java","-jar","Kaddem-2.2.jar"]
