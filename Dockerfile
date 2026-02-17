FROM eclipse-temurin:25-jre-alpine
WORKDIR /app
ADD target/hw_spring_boot-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]
