FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/*.jar /app.jar
RUN java -jar /app.jar
EXPOSE 8080