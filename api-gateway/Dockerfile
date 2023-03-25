FROM openjdk:11-jre-slim

WORKDIR /app

COPY target/api-gateway-0.0.1.jar api-gateway.jar

EXPOSE 9030

ENTRYPOINT ["java","-jar", "/app/api-gateway.jar"]
