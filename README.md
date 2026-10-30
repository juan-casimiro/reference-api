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


## Local access defaults

The API binds to `127.0.0.1` and the H2 web console is disabled. It has no
application authentication; the former Spring Security user properties did not
enforce access control because Spring Security is not installed. Keep it local.
Do not change `server.address` or publish a tunnel without adding authentication
and reviewing access to write/delete endpoints.
