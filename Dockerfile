FROM openjdk:22-jdk-slim
COPY company-server-appl-0.0.1.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]