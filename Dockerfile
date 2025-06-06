FROM openjdk:17-jdk-slim

WORKDIR /app

COPY build/libs/*.jar app.jar

# 4. 실행
ENTRYPOINT ["java", "-jar", "app.jar"]