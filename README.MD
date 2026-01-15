# Proyecto Integrado – Angular, Spring Boot y MySQL con Docker

Proyecto Full Stack desarrollado como aplicación web utilizando **Angular** para el frontend, **Spring Boot** para el backend y **MySQL** como sistema gestor de base de datos.  
Toda la aplicación está **contenedorizada con Docker y Docker Compose**, permitiendo su ejecución completa con un solo comando.

---

## 🧱 Tecnologías utilizadas

- **Frontend**: Angular
- **Backend**: Spring Boot (Java)
- **Base de datos**: MySQL 8
- **Contenedores**: Docker & Docker Compose
- **Servidor frontend**: Angular / Node
- **Control de versiones**: Git

---

## 📁 Estructura del proyecto

ProyectoIntegrado-JaimeRomanTejero/
│
├── Back/
│ └── BackEndProyectoIntegrado/
│ ├── Dockerfile
│ ├── pom.xml
│ └── src/
│
├── Front/
│ ├── Dockerfile
│ ├── angular.json
│ ├── package.json
│ └── src/
│
├── docker-compose.yml
├── README.md

---

## ▶️ Ejecución del proyecto

### Requisitos previos

- Docker
- Docker Compose

---

### Levantar la aplicación

Desde la carpeta raíz del proyecto ejecutar:

```bash
docker-compose up --build