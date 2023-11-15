FROM openjdk:11
COPY target/mcs.auth-0.0.1-SNAPSHOT.jar auth-app.jar

ENTRYPOINT ["java","-jar","auth-app.jar"]
EXPOSE 8081