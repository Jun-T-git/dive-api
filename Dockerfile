FROM openjdk:17

WORKDIR /app

COPY ./app /app

CMD ["./mvnw", "spring-boot:run"]