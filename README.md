# 🎭 Event Management System – Full-Stack Java Application

A full-stack web application for managing events and locations, built with **Java 17 (Spring Boot)** backend and a **vanilla HTML/CSS/JavaScript** frontend. The system provides admin and public interfaces for event and location management, including CRUD operations, search functionality, authentication, and database-level data integrity.

Part of the **FII-SGBD** academic project, emphasizing backend architecture, database design, and multithreaded-safe operations.

---

## 📁 Project Structure

```text
FII-SGBD/
├── client/                     # Frontend assets
│   ├── index.html              # Landing page
│   ├── script.js               # Global client-side logic
│   ├── styles.css              # Global styling
│   ├── admin-search-events/    # Admin event management UI
│   ├── admin-search-locations/ # Admin location management UI
│   ├── search-events/          # Public event search UI
│   ├── search-locations/       # Public location search UI
│   ├── js/                     # Reusable JS modules
│   └── images/                 # Icons and images
├── server/                     # Backend (Spring Boot)
│   ├── pom.xml                 # Maven project descriptor
│   ├── src/main/java/ro/org/events/
│   │   ├── Controllers/        # REST API endpoints
│   │   ├── Services/           # Business logic
│   │   ├── Repository/         # Data access layer
│   │   └── EventsApplication.java # Main application runner
│   ├── src/main/resources/
│   │   ├── application.properties # Spring configuration
│   │   └── sql_scripts/        # Database schema, triggers, packages
│   └── src/test/               # Unit & integration tests
```

---

## 🚀 Features & Highlights

* 🔐 **Authentication:** Secure login system for admins and users.
* 📍 **Full CRUD:** Manage locations and events seamlessly.
* 🔎 **Advanced Search:** Robust filtering for both public and admin interfaces.
* 🧾 **Database Excellence:** Custom schema initialization, triggers, and PL/SQL packages.
* 🧪 **Automated Testing:** Unit & integration tests powered by JUnit.
* ⚡ **Optimized Backend:** Layered architecture using JDBC best practices and modular code.

> [!TIP]
> **Highlight:** Implemented PostgreSQL/Oracle triggers and stored procedures to ensure data consistency and prevent conflicting bookings, simulating production-grade backend logic.

---

## 🛠️ Technologies Used

| Layer | Technologies / Tools |
| :--- | :--- |
| **Frontend** | HTML5, CSS3, JavaScript (Vanilla) |
| **Backend** | Java 17, Spring Boot, Maven, JDBC |
| **Database** | Oracle SQL, PL/SQL packages, Triggers |
| **Testing** | JUnit 5 |

---

## ⚡ Setup Instructions

### Prerequisites
* Java 17+
* Maven
* Oracle DB instance

### Backend
1. Navigate to the server folder:
   ```bash
   cd server
   ```
2. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```
3. Configure your database connection in `server/src/main/resources/application.properties`.

### Frontend
1. Open `client/index.html` in your preferred browser.
2. Ensure CORS is configured correctly on the backend for API availability.

---

## 🗄️ Database Scripts

All scripts are located in `server/src/main/resources/sql_scripts/`:

* `create-tables.sql` – Initializes the required tables.
* `triggers.sql` – Adds automatic data integrity logic.
* `user_package-schema.sql` – PL/SQL procedures for advanced operations.

*Designed specifically for preventing booking overlaps and enforcing business rules at the database layer.*

---

## 🧪 Testing

Run backend unit tests to ensure stability:

```bash
./mvnw test
```

* Tests cover the service layer, controllers, and repository queries.
* Validates data consistency, API correctness, and business logic.

---

## 👥 Author
**Developed by Cristian-Ioan Roman and Ioana-Stefana Hanus** As part of the **FII-SGBD** academic project.  
*Focus: Backend architecture, database design, modular services, and Spring Boot best practices.*
