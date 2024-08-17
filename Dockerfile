FROM openjdk:22-jdk-slim
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENV SPRING_PROFILES_ACTIVE=docker
ENTRYPOINT ["java", "-jar", "/app.jar"]