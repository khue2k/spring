# Sử dụng một base image có sẵn cho Java
FROM openjdk:22-oraclelinux8

# Thiết lập thư mục làm việc trong container
WORKDIR /app

# Sao chép file .jar từ thư mục target vào /app trong container
COPY target/spring-0.0.1-SNAPSHOT.jar /app

# Chạy ứng dụng khi container được khởi động
CMD ["java", "-jar", "spring-0.0.1-SNAPSHOT.jar"]
