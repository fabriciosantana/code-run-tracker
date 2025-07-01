# Etapa 1: construir o JAR usando Maven com Java 21
FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /app
COPY . .

# Compila o projeto e gera o JAR (ignora testes para agilizar)
RUN mvn clean package -DskipTests

# Etapa 2: imagem final para execução — leve e com JDK 21
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copia o JAR gerado na etapa anterior
COPY --from=builder /app/target/code-run-tracker-api-*.jar app.jar

# Expõe a porta 8080 usada pela API
EXPOSE 8080

# Executa o JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
