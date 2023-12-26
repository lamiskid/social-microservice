# springboot-microservice-social-app
 UNI VIBES




Minimal [Spring Boot](http://projects.spring.io/spring-boot/) sample app.

## Requirements

For building and running the application you need:

- [JDK 17](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)
  you can download the JDK directly from IntelliJ.
  Go to settings->project Structure->SDK
  Then select JDK 17 or above

## Running the application locally
For setting up the database
check the docker-compose file at the root of the application "docker-compose.yml"
run the command
``` shell
docker-compose up
```
To run Each service, navigate root  folder of  each service .
run the command 

```shell
mvn spring-boot:run
```
or go to the main method and run it
## Swagger documentation
for user-service:
localhost:8094
``` shell
http://localhost:8094/swagger-ui/index.html#/
```

for chat-service 
localhost:8091
``` shell
http://localhost:8091/swagger-ui/index.html#/
```


for meeting-service:   
localhost:8093
``` shell
http://localhost:8093/swagger-ui/index.html#/
```
for schedule   
localhost:8096
``` shell
http://localhost:8096/swagger-ui/index.html#/
```



