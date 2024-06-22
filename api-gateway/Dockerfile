# Sử dụng image chuẩn của OpenJDK 17 làm base image
FROM openjdk:17-jdk-slim

# Tạo thư mục để chứa file JAR của ứng dụng
VOLUME /tmp

# Copy file JAR của ứng dụng vào container
COPY target/api-gateway-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8762
# Chạy ứng dụng
ENTRYPOINT ["java","-jar","/app.jar"]