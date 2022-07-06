FROM eclipse-temurin:11

COPY ./target/apachas-backend*.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]