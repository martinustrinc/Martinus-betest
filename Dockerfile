# Dockerfile for Account Management Service
FROM openjdk:11
WORKDIR /app
COPY target/ms_name_account.jar /app
EXPOSE 8080
CMD ["java", "-jar", "ms_name_account.jar"]
