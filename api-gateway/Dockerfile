FROM maven:3.8.4-openjdk-17 AS builder
WORKDIR /home/app
COPY target/api-gateway-0.0.1-SNAPSHOT.jar config-server.jar

CMD java -jar config-server.jar