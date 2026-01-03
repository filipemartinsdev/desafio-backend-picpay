FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY ./app.jar app.jar

ENV ACTIVE_PROFILE=prod

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]