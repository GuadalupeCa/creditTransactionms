FROM openjdk:11
WORKDIR /creditTransactionms
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY src ./src
EXPOSE 8086
CMD ["./mvnw", "spring-boot:run"]