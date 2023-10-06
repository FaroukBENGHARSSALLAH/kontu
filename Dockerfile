#FROM openjdk:8-jdk-alpine
#VOLUME /tmp
#COPY target/*.jar app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]

FROM tomcat:8.0.20-jre8
COPY tomcat-users.xml /usr/local/tomcat/conf/tomcat-users.xml
COPY context.xml /usr/local/tomcat/webapps/manager/META-INF/context.xml
#ADD quod.war /usr/local/tomcat/webapps/
COPY /quod /usr/local/tomcat/webapps/ /quod
#ADD kontu.war /usr/local/tomcat/webapps/
#COPY /kontu /usr/local/tomcat/webapps/ /kontu
RUN mkdir /usr/local/tomcat/webapps/myapp
EXPOSE 8080

