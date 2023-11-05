FROM openjdk:1.8
EXPOSE 8085
ADD target/Kaddem-2.7.jar Kaddem-2.7.jar
ENTRYPOINT ["java","-jar","Kaddem-2.7.jar"]
