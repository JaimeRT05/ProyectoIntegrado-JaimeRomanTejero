# -----------------------------
# Frontend Angular para desarrollo
# -----------------------------
FROM node:20-alpine
WORKDIR /app

# Copiar package.json y package-lock.json
COPY package*.json ./

# Instalar dependencias
RUN npm install

# Copiar todo el código del frontend
COPY . .

# Exponer el puerto de Angular
EXPOSE 4200

# Comando para servir Angular con hot-reload
CMD ["npm", "start"]
