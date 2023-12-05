FROM openjdk:11
COPY target/mcs.auth.jar auth-app.jar

ENTRYPOINT ["java","-jar","auth-app.jar"]
EXPOSE 8081