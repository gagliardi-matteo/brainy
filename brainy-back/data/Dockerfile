# Utilizza un'immagine Maven per la fase di build
FROM maven:3.9.5-eclipse-temurin-21 as builder

# Imposta la directory di lavoro all'interno del container
WORKDIR /app

# Copia il file pom.xml e le dipendenze per scaricarle
COPY pom.xml ./
RUN mvn dependency:resolve

# Copia tutto il progetto nella directory di lavoro
COPY . ./

# Compila il progetto e genera il jar
RUN mvn package -DskipTests

# Seconda fase: utilizza un'immagine runtime Java
FROM openjdk:21

# Imposta la directory di lavoro
WORKDIR /app

# Copia il jar generato nella fase di build
COPY --from=builder /app/target/*.jar data-0.0.1-SNAPSHOT.jar

# Esponi la porta 8080
EXPOSE 8080

# Comando di avvio
ENTRYPOINT ["java", "-jar", "data-0.0.1-SNAPSHOT.jar"]
