
FROM openjdk:11
EXPOSE 8044

# Create a directory for the application
WORKDIR /app

# Install curl
RUN apt-get update && apt-get install -y curl

# Download the JAR file
RUN curl -o Kaddem-9.jar -L "http://192.168.33.10:8081/repository/maven-releases/com/esprit/Kaddem/9/Kaddem-9.jar"

# Specify the correct JAR file in the CMD instruction
CMD ["java", "-jar", "Kaddem-9.jar"]


