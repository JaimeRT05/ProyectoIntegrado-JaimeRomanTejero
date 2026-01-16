FROM maven:3.9.2-eclipse-temurin-17 AS build
WORKDIR /app

# Copiar pom y bajar dependencias primero (capa cacheable)
COPY pom.xml ./
RUN mvn dependency:go-offline

# Copiar solo src
COPY src ./src

# Compilar
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
