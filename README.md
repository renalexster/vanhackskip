order-api

Sample of API to Hackathon Vanchack

Stack:


* Java 8
* Spring Boot
* JPA
* Apache Camel
* JWT

To create a docker database:
> docker run --name skip-postgres -e POSTGRES_PASSWORD=123456 -e POSTGRES_USER=skip -d postgres:9.5-alpine

To run this project:
Configure the jdbc url in _application.properties_


> mvn spring-boot:run