FROM openjdk:11
EXPOSE 8085
ADD target/*.jar etudiant.jar
CMD ["java", "-jar", "etudiant.jar"]
