# ¡A pachas! Application Backend

## Requirements

- Java 11
- Intellij IDEA

## Setup

- Enable annotation processing in Intellij IDEA: ([guide](https://docs.micronaut.io/1.0.0/guide/index.html#_configuring_intellij_idea))

## Environment config

- **JDBC_URL**: JDBC URL to the PostgreSQL database for the app
- **JDBC_USERNAME**: Username to authenticate with the PostgreSQL database
- **JDBC_PASSWORD**: Password to authenticate with the PostgreSQL database

## Day-to-day commands

### Run docker compose stack for development (no auto refresh)

Every time you want to start the app, build it and bring the docker compose stack with the `include_api_service` profile

```bash
mvn package && docker-compose --profile include_api_service up --build
```

### Run docker compose stack for development (only db, run app in IDE)

For better DX, only start the PostgreSQL service and start the app via the IDE

```bash
docker-compose up
```
