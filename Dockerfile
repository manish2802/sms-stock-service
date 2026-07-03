FROM eclipse-temurin:17-jre

WORKDIR /app

COPY target/*.jar smsstock.jar

EXPOSE 9001

ENTRYPOINT ["java","-jar","smsstock.jar"]