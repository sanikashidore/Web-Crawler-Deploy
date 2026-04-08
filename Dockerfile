FROM eclipse-temurin:17
WORKDIR /app
COPY target/crawler-1.0.jar app.jar
COPY src/main/resources/keywords.csv keywords.csv
CMD ["java", "-jar", "app.jar"]