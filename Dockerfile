FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD *.jar /app.jar
RUN java -jar /app.jar
EXPOSE 8080