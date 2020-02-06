FROM tomcat
COPY bin/*.war /usr/local/tomcat/webapps/
CMD  /usr/local/tomcat/bin/catalina.sh run
