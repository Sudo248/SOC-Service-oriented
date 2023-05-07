FROM sudo248dev/soc:0.0.1

WORKDIR /app

ENV JAR_FILE=chat-service-0.0.1.jar
ENV APP=/app/$JAR_FILE
ENV PORT=8091

COPY target/$JAR_FILE $APP
EXPOSE $PORT

CMD ["sh", "-c", "java -jar ${APP}"]