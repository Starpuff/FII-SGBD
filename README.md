# Event Management System

A full-stack web application for managing events and locations. Built with a vanilla HTML/CSS/JS frontend and a Java (Spring Boot) backend. The system allows both administrative and public interaction with event and location data, including CRUD operations, searching, and authentication.

---

## 📁 Project Structure

```
FII-SGBD-main/
├── client/                     # Frontend assets
│   ├── index.html              # Main landing page
│   ├── script.js               # Global client-side logic
│   ├── styles.css              # Global styling
│   ├── admin-search-events/   # Admin UI for event search
│   ├── admin-search-locations/ # Admin UI for location search
│   ├── search-events/         # Public UI for event search
│   ├── search-locations/      # Public UI for location search
│   ├── js/                    # Reusable JS modules
│   └── images/                # UI images and icons
├── server/                    # Backend - Spring Boot application
│   ├── pom.xml                # Maven project descriptor
│   ├── src/main/java/ro/org/events/
│   │   ├── Controllers/       # REST API endpoints
│   │   ├── Services/          # Business logic
│   │   ├── Repository/        # Data access layer
│   │   └── EventsApplication.java # Main app runner
│   ├── src/main/resources/
│   │   ├── application.properties # Spring config
│   │   └── sql_scripts/       # DB schema and triggers
│   └── src/test/              # Unit and integration tests
```

---

## 🚀 Features

- 🔐 **Authentication System** (admin & users)
- 📍 **Location Management** (CRUD)
- 🎫 **Event Management** (CRUD)
- 🔎 **Search Interfaces** (public & admin)
- 🧾 **Database Initialization** via SQL scripts
- 🧪 **Unit Tests** with JUnit

---

## 🛠️ Technologies

### Frontend
- HTML, CSS, JavaScript
- No frontend frameworks

### Backend
- Java 17
- Spring Boot
- JDBC

### Database
- Oracle SQL
- Schema includes packages, triggers, and table creation scripts

---

## 🧰 Setup Instructions

### Prerequisites
- Java 17+
- Maven
- Oracle DB instance

### Backend
```bash
cd server
./mvnw spring-boot:run
```

Configure DB connection in `application.properties`.

### Frontend
Simply open `client/index.html` in a browser. Ensure CORS and API availability.

---

## 🗄️ Database Scripts

Located in `server/src/main/resources/sql_scripts/`, these include:
- `create-tables.sql` – Initializes all required tables
- `triggers.sql` – Adds logic for automatic data integrity
- `user_package-schema.sql` – Contains PL/SQL procedures

---

## 🧪 Testing

Run backend tests:
```bash
./mvnw test
```

---

## 👥 Authors

- Developed as part of an academic project (FII-SGBD)

