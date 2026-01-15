# -----------------------------
# Backend Spring Boot
# -----------------------------
FROM maven:3.9.2-eclipse-temurin-17 AS build
WORKDIR /app

# Copiar archivos de Maven y descargar dependencias
COPY pom.xml ./
RUN mvn dependency:go-offline

# Copiar código fuente
COPY . .

# Construir el JAR del backend
RUN mvn clean package -DskipTests

# Runtime con JDK
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copiar JAR compilado
COPY --from=build /app/target/*.jar app.jar

# Exponer puerto 8080
EXPOSE 8080

# Ejecutar aplicación
ENTRYPOINT ["java","-jar","app.jar"]
