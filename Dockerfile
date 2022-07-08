FROM eclipse-temurin:11

COPY ./build/libs/apachas-backend*-all.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]