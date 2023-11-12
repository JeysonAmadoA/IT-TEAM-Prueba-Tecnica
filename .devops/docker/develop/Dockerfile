FROM maven:3.8-openjdk-17 AS build

WORKDIR /Medicalert

COPY pom.xml .

RUN mvn dependency:go-offline -B

COPY src ./src

RUN mvn clean install

RUN mvn clean package

FROM openjdk:17.0.1-jdk-slim

COPY --from=build /Medicalert/target/*.jar /Medicalert/Medicalert-0.0.1-SNAPSHOT.jar

EXPOSE 9090

ENTRYPOINT ["java","-jar","/Medicalert/Medicalert-0.0.1-SNAPSHOT.jar"]

