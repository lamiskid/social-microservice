FROM maven:3.8.4-openjdk-17 AS builder

# Copy the application files from the host machine to the image filesystem
COPY . /home/app/src

# Set the working directory within the image for executing future commands
WORKDIR /home/app/src

# Run Maven build within the container - NOT on the host machine
RUN mvn clean package -DskipTests

# Package stage
FROM openjdk:17-jdk

RUN mkdir /app

COPY --from=builder /home/app/src/target/*.jar /app/api-gateway.jar

# Find a port that works for you
EXPOSE 8084

ENTRYPOINT ["java","-jar","/app/api-gateway-0.0.1-SNAPSHOT.jar"]