# Этап сборки
FROM maven:3.9.6-eclipse-temurin-17 AS builder

# Устанавливаем рабочую директорию
WORKDIR /build

# Копируем весь монорепозиторий (включая parent POM и все модули)
COPY ../../ /build

# Переходим в директорию микросервиса
WORKDIR /build/api-gateway

# Скачиваем зависимости
RUN mvn dependency:go-offline -B

# Сборка проекта
RUN mvn clean package -DskipTests

# Этап выполнения
FROM eclipse-temurin:17-jdk-jammy

# Создаём рабочую директорию
WORKDIR /app

# Копируем JAR-файл из стадии сборки
COPY --from=builder /build/api-gateway/target/*.jar app.jar

# Пробрасываем порт
EXPOSE 8080

# Точка входа
ENTRYPOINT ["java", "-jar", "app.jar"]