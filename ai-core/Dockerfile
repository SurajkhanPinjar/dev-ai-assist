# Stage 1: Build
FROM gradle:8.7-jdk21 AS build
WORKDIR /app
COPY . .
RUN gradle :ai-core:bootJar -x test --no-daemon

# Stage 2: Run
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/ai-core/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "app.jar"]