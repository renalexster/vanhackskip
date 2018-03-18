order-api

To create a docker database:
> docker run --name skip-postgres -e POSTGRES_PASSWORD=123456 -e POSTGRES_USER=skip -d postgres:9.5-alpine

To run this project:
mvn spring-boot:run