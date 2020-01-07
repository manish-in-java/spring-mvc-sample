FROM tomcat:8.0
COPY C:/Program Files (x86)/Jenkins/workspace/docker1/target/spring-mvc-sample-2.0.war /usr/local/tomcat/webapps/
EXPOSE 8080:8080
CMD["catalina.sh","run"]