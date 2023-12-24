# --------------
# Dockerfile for user-service
# --------------

# BUILD stage
# --

# Use a Maven base image
# Use the official Tomcat image as the base image
FROM openjdk:17-jdk
WORKDIR /home/app/src

COPY target/*.jar /usr/local/tomcat/webapps/ROOT.jar

# Copy the application files from the host machine to the image fi

# Package stageS
# --

# Expose port 8080 for the application
EXPOSE 8904

# Define the default command to start Tomcat
CMD ["java", "-jar", "your-spring-boot-app.jar"]
