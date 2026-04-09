# 🚗 Professional Car Rental System (Java)

A robust, object-oriented Java application designed to manage client registrations, fleet inventory, and rental transactions with persistent file-based storage. This project serves as a showcase of clean code practices, advanced type safety, and decoupled architecture.

---

## 🌟 Key Features

* **Polymorphic Vehicle Management:** Supports multiple vehicle types (Cars, Motorcycles) using inheritance and abstract classes.
* **Decoupled Architecture:** Implementation of the **Repository Pattern** for data access and a dedicated **Service Layer** for business logic.
* **Automated Persistence:** Custom file-handling logic to serialize and deserialize objects into `.txt` formats, ensuring data integrity without external heavy-weight libraries.
* **Strict Business Logic:** Validation checks for client existence, vehicle availability, and ID verification during the rental lifecycle.

---

## 🏗️ Technical Highlights

### 1. Advanced Java Implementation
This project leverages deep Java language features to create a scalable and type-safe codebase:
* **Generics & Wildcards:** Implemented a `RepositoryGeneric<T>` class to handle CRUD operations for any model. Used **Generic Methods** and **Wildcards** (e.g., `<? extends Vehicle>`) to provide flexible, type-safe data handling.
* **Abstract Classes:** Utilized `Abstract` base classes for `Vehicle` to enforce a strict contract for all sub-types, ensuring consistent behavior across the fleet.
* **Nested Classes:** Employed **Nested Classes** to encapsulate logic tightly coupled with specific outer classes, improving package-private visibility and code organization.

### 2. SOLID Principles & Design Patterns
* **Single Responsibility Principle (SRP):** Distinct services for registration and rentals to ensure modularity.
* **Open/Closed Principle:** Designed to easily add new vehicle types (e.g., Trucks, EVs) by extending the base `Vehicle` model without modifying core rental logic.
* **Dependency Inversion:** Use of interfaces (`Registration`, `RentService`) allows for flexible implementations and simplifies future migration to different data sources.

### 3. Data Persistence Strategy
The application utilizes a custom-built persistence engine. It parses flat-file data into domain objects using a robust string-tokenization approach, ensuring that all data is automatically loaded on startup and saved after every transaction to the `src/data` directory.

## 📂 Project Structure

```text
src/
├── model/              # Domain entities (Client, Car, Moto, Rental)
├── repository/         # Data access layer (Generic & Wildcard implementation)
├── service/            # Core business logic & Interfaces (Registration, RentService)
├── data/               # Persistent text-based storage (.txt files)
└── Main.java           # Entry point and CLI menu controller