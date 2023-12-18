# --------------
# Dockerfile for chat-service
# --------------

# BUILD stage
# --

# Use a Maven base image
FROM maven:3.8.4-openjdk-17 AS builder

# Copy the application files from the host machine to the image filesystem
COPY . /home/app/src

# Set the working directory within the image
WORKDIR /home/app/src

# Run Maven build within the container
RUN mvn clean install -DskipTests

# Package stage
# --

# Use an OpenJDK base image
FROM openjdk:17-jdk

# Create a directory within the container
RUN mkdir /app


# Copy the application files from the build stage to the image filesystem
COPY --from=builder /home/app/src/target/*.jar /app/chat-service.jar

# Set environment variables
ENV POSTGRES_USER=admin \
    POSTGRES_PASSWORD=password

# Expose the port where the application will be listening
EXPOSE 8083

# Specify the command to run when the container starts
ENTRYPOINT ["java", "-jar", "/app/chat-service.jar"]
