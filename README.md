# Reference API

A REST API for managing academic references, built with modern Java and Spring Boot.

## Tech stack
- Java 21
- Spring Boot 4.1
- Spring Data JPA + Hibernate 7
- H2 (in-memory)
- Bean Validation
- JUnit 5 + MockMvc

## Endpoints
- `GET /references` — list all references
- `GET /references/{id}` — get by ID
- `POST /references` — create a reference
- `DELETE /references/{id}` — delete a reference

## Running locally
\`\`\`bash
./mvnw spring-boot:run
\`\`\`
