FROM openjdk:11
VOLUME /data
ARG JAR_FILE
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT exec java $JAVA_OPTS -jar /app.jar $ARGS
