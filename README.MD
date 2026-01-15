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
Este comando:

Construye las imágenes del frontend y backend

Levanta los contenedores de Angular, Spring Boot y MySQL

Configura la red entre servicios automáticamente

🌐 Accesos

Servicio	URL
Frontend	http://localhost:4200

Backend	http://localhost:8080

MySQL	localhost:3306

🗄️ Base de datos

Nombre: GestionOfertas_BD

Usuario: usuario

Contraseña: usuario

Motor: MySQL 8

La base de datos se crea automáticamente al iniciar los contenedores.

👤 Usuarios por defecto

Al arrancar el backend, se crean automáticamente los siguientes usuarios:

Usuario	Contraseña	Perfil
usuario	usuario	USER
admin	admin	ADMIN

Las contraseñas se almacenan cifradas mediante BCrypt.

🔐 Seguridad

Autenticación gestionada con Spring Security

Control de acceso por perfiles (USER, ADMIN)

Login consumido desde el frontend Angular

🛑 Detener la aplicación

Para parar y eliminar los contenedores:

docker-compose down

👨‍💻 Autor

Proyecto desarrollado por Jaime Román Tejero
Proyecto Integrado – Desarrollo de Aplicaciones Web