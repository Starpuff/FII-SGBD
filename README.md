# Event Management System — Full-Stack Java Application

A full-stack web application for managing events and locations, built with a Java 17 (Spring Boot) backend and a vanilla HTML/CSS/JavaScript frontend. Provides separate admin and public interfaces for event and location management, including CRUD operations, search, authentication, and database-level data integrity.

Built as part of the FII-SGBD (Database Systems) coursework project.

## Project Structure

```
FII-SGBD/
├── client/                     # Frontend assets
│   ├── index.html
│   ├── script.js, styles.css
│   ├── admin-search-events/    # Admin event management UI
│   ├── admin-search-locations/ # Admin location management UI
│   ├── search-events/          # Public event search UI
│   ├── search-locations/       # Public location search UI
│   ├── js/                     # Reusable JS modules
│   └── images/
├── server/                     # Backend (Spring Boot)
│   ├── pom.xml
│   ├── src/main/java/ro/org/events/
│   │   ├── Controllers/        # REST API endpoints
│   │   ├── Services/           # Business logic
│   │   ├── Repository/         # Data access layer
│   │   └── EventsApplication.java
│   ├── src/main/resources/
│   │   ├── application.properties
│   │   └── sql_scripts/        # Schema, triggers, packages
│   └── src/test/               # Unit & integration tests
```

## Features

- Authentication for admin and public users
- Full CRUD for events and locations
- Search and filtering across both admin and public interfaces
- Database-level integrity via PostgreSQL/Oracle triggers and PL/SQL packages, used to prevent overlapping bookings
- Unit and integration tests (JUnit)

## Technologies

| Layer | Technologies |
|---|---|
| Frontend | HTML5, CSS3, vanilla JavaScript |
| Backend | Java 17, Spring Boot, Maven, JDBC |
| Database | Oracle SQL, PL/SQL packages, triggers |
| Testing | JUnit 5 |

## Setup

### Prerequisites
- Java 17+
- Maven
- Oracle DB instance

### Backend
```bash
cd server
./mvnw spring-boot:run
```
Configure the database connection in `server/src/main/resources/application.properties`.

### Frontend
Open `client/index.html` in a browser. Ensure CORS is configured on the backend for API access.

## Database Scripts

Located in `server/src/main/resources/sql_scripts/`:
- `create-tables.sql` — initializes required tables
- `triggers.sql` — enforces data integrity, including overlap prevention for bookings
- `user_package-schema.sql` — PL/SQL procedures for advanced operations

## Testing

```bash
./mvnw test
```
Covers the service layer, controllers, and repository queries.

## Authors

Developed by Cristian-Ioan Roman and Ioana-Stefana Hanus as part of the FII-SGBD academic project.
