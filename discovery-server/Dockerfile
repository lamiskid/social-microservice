# --------------
# Dockerfile for discovery-service
# --------------

# BUILD stage
# --

# Use a Maven base image

# Use an OpenJDK base image
FROM openjdk:17-jdk

# Create a directory within the container
FROM maven:3.8.4-openjdk-17 AS builder
WORKDIR /home/app
COPY target/discovery-server-0.0.1-SNAPSHOT.jar discovery-server.jar

CMD java -jar discovery-server.jar