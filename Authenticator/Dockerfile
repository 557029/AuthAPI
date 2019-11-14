FROM openjdk:8-jdk-alpine
EXPOSE 8082
VOLUME /tmp
ADD /target/mccAuthAPI.jar mccAuthAPI.jar
ENTRYPOINT ["java","-jar","-Dserver.servlet.context-path=/api","mccAuthAPI.jar"]
