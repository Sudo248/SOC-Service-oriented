FROM openjdk:11-jre-slim

WORKDIR /app

COPY target/soc-registry-0.0.1.jar soc-registry.jar

EXPOSE 8761

ENTRYPOINT ["java","-jar", "/app/soc-registry.jar"]