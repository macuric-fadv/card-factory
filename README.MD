
# Card Factory App

This is a simple Card Factory application.

## Requirements

For building and running the application you need:

- JDK 17
- Maven 3.9.9

## Running the application locally

Build the project and run the tests by running
```shell
mvn clean package
```

Run the application locally using [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html):

```shell
mvn spring-boot:run
```
To rune the application in debug mode use this command:
```shell
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8787"
```

## H2 Database
Application uses in-memory H2 database. Database console can be found at http://localhost:8080/h2-console. 

## REST API Endpoints

Application exposes REST API endpoints for card request operations. Postman collection holding set of API requests can be found in the file card-factory.postman_collection.json in the project root directory.   

## Kafka

Application has an implementation of a kafka consumer. In order to put a kafka consumer in action kafka server needs to be installed on the machine.
1. Install kafka server following instructions found [here](https://www.geeksforgeeks.org/how-to-install-and-run-apache-kafka-on-windows/).
2. Open command prompt and move to the root kafka directory, like C:\kafka
3. Start the zookeeper service:
```shell
.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
```
4. Open another command prompt, move to the root kafka directory, and start the kafka broker service:
```shell
\bin\windows\kafka-server-start.bat .\config\server.properties
```
5. Create kafka topic card-request-events:
```shell
bin\windows\kafka-topics.bat --create --topic card-request-events --bootstrap-server localhost:9092
```
6. Open another command prompt, move to the root kafka directory, and start kafka producer:
```shell
bin\windows\kafka-console-producer.bat --topic card-request-events --bootstrap-server localhost:9092
```
7. Start sending messages, messages are in format: {"clientId": ID, "request": {"requestId": ID, "status": STATUS}}\
Example message: {"clientId": 8, "request": {"requestId": 16, "status": "APPROVED"}}
8. Kafka consumer running within Spring Boot application should pick up the messages.