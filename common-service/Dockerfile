FROM openjdk:11-jre-slim

WORKDIR /app

COPY target/common-service-0.0.1.jar common-service.jar

EXPOSE 8081

ENTRYPOINT ["java","-jar", "/app/common-service.jar"]
