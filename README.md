# User Service – Fable Flow

User management microservice for **Fable Flow**, built with Spring Boot.

## Features
- User CRUD operations
- DTO-based API design
- Input validation
- Custom exception handling
- Pagination support
- PostgreSQL integration
- Actuator health endpoint

## Tech Stack
- Java 21
- Spring Boot 3.x
- Spring Data JPA
- PostgreSQL
- Maven

## API Endpoints

### Create User
POST `/users`

### Get User by ID
GET `/users/{id}`

### Get Users (Paginated)
GET `/users?page=0&size=10`

## Running Locally

```bash
mvn spring-boot:run
