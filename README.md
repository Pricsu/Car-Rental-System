# Java Car Rental System

A streamlined management system for handling vehicle fleets, client registrations, and rental transactions. This project emphasizes clean architecture and advanced Java type safety.

## Technical Implementation

This project was developed to showcase proficiency in the following areas:

- Generics and Wildcards: Implemented a type-safe RepositoryGeneric utilizing wildcards to handle diverse data models with a single codebase.
- OOP Foundations: Utilized abstract classes for vehicle hierarchy and nested classes for encapsulated logic.
- Decoupled Architecture: Separated business logic into Services and data management into Repositories, following SOLID principles.
- Custom Persistence: Built a file-based storage system to handle data serialization and state persistence without external databases.

## Features

- Client Management: Registration and lookup functionality.
- Fleet Management: Support for multiple vehicle types with specific attributes (Cylinders for cars, CC for motorcycles).
- Rental Engine: Validation logic ensuring vehicle availability and client verification before processing.
- Data Integrity: Automatic loading and saving of system states on startup and shutdown.

## Project Structure

- src/model: Domain entities (Inheritance and Abstraction)
- src/repository: Data layer (Generics and Wildcards)
- src/service: Business logic (Interfaces and Nested Classes)
- src/data: Flat-file persistence storage

## Contact

Elekes Alfred
LinkedIn: https://www.linkedin.com/in/elekes-alfred-099886285/
