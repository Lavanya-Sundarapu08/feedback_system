FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY target/*.jar cse.jar

ENTRYPOINT ["java","-jar","cse.jar"]