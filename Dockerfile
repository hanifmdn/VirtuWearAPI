# Gunakan Java 17 sebagai base image
FROM openjdk:17-jdk-slim

# Set direktori kerja di dalam container
WORKDIR /app

# ⛳ GANTI jika nama file jar berubah saat build
COPY target/rest-0.0.1-SNAPSHOT.jar app.jar
COPY src/main/resources/firebase-service-account.json src/main/resources/firebase-service-account.json

# Jalankan aplikasi Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
