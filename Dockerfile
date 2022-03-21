FROM maven:3.6.0-jdk-8-slim AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package

FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY --from=build /usr/src/app/target/employee-mgmt-1.0.0-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://mongodb/micros", "-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]