# Usa una imagen oficial de Java
FROM eclipse-temurin:17-jdk

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar todo el proyecto al contenedor
COPY . .

# Construir el proyecto con Maven
RUN mvn clean package

# Comando para ejecutar la app
CMD ["java", "-jar", "target/crud_basic-0.0.1-SNAPSHOT.jar"]
