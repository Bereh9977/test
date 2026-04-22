FROM maven:3.9-eclipse-temurin-25 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src
COPY config.json .

RUN mvn clean package -DskipTests

FROM eclipse-temurin:25-jre

WORKDIR /app

COPY --from=build /app/target/PackageManager-1.0-SNAPSHOT.jar app.jar
COPY --from=build /app/config.json config.json

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
