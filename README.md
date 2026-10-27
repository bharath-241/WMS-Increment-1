# Warehouse Management System (WMS)

## Project Overview

The Warehouse Management System (WMS) is a Java-based application designed to manage warehouse operations, including warehouse locations, storage bins, goods receipts, put-away operations, and inventory-related processing.

The project uses Spring Boot to build REST APIs, MySQL for data persistence, Apache Kafka for event-driven communication, and JWT for authentication.

## Technologies Used

- **Java:** 21
- **Spring Boot:** 4.1.1
- **Build Tool:** Maven
- **Database:** MySQL 8
- **Messaging:** Apache Kafka
- **Security:** Spring Security and JWT
- **API Testing:** Bruno
- **Version Control:** Git and GitHub

## Implemented Modules

- **Authentication:** User registration, login, and JWT authentication.
- **Warehouse Management:** Create, retrieve, update, and manage warehouse information.
- **Location Management:** Manage warehouse locations.
- **Bin Management:** Manage warehouse storage bins.
- **Goods Receipt:** Record and retrieve incoming goods receipts.
- **Put-Away:** Manage put-away operations and their lifecycle.
- **Inventory:** Inventory-related entities and service logic.
- **Event Processing:** Kafka-based goods receipt event publishing and consumption.

## Project Structure

```text
warehouse-service/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/company/wms/warehouse/
│   │   │       ├── auth/
│   │   │       ├── bin/
│   │   │       ├── config/
│   │   │       ├── consumer/
│   │   │       ├── controller/
│   │   │       ├── domain/
│   │   │       ├── event/
│   │   │       ├── exception/
│   │   │       ├── inbound/
│   │   │       ├── inventory/
│   │   │       ├── location/
│   │   │       ├── putaway/
│   │   │       ├── repository/
│   │   │       ├── service/
│   │   │       └── WarehouseServiceApplication.java
│   │   └── resources/
│   └── test/
├── pom.xml
└── .gitignore
```

## Prerequisites

Install the following before running the application:

- Java 21
- Maven (or use the included Maven Wrapper)
- MySQL 8
- Apache Kafka, if testing Kafka-based functionality

## Configuration

Configure the MySQL database connection, Kafka broker address, and JWT secret in your local application configuration.

Set sensitive values such as database passwords and JWT secrets through environment variables. Do not commit credentials or production secrets to GitHub.

## Running the Application

1. Clone the repository:

   ```bash
   git clone https://github.com/bharath-241/WMS-Increment-1.git
   ```

2. Open the project in IntelliJ IDEA.

3. Open the `warehouse-service` directory containing `pom.xml` as the Maven project.

4. Configure your MySQL database and required environment variables.

5. Build the application using the Maven Wrapper.

   **Windows:**

   ```powershell
   .\mvnw.cmd clean package
   ```

6. Run the Spring Boot application using IntelliJ IDEA by running `WarehouseServiceApplication.java`.

## API Testing

Use Bruno to test the REST APIs for authentication, warehouse, location, bin, goods receipt, and put-away operations.

For protected endpoints, register or log in as required and provide the JWT token in the request's Authorization header:

```text
Authorization: Bearer <your-jwt-token>
```

Use the actual endpoints and port configured in the application.

## Current Architecture

The current implementation is maintained in a single Spring Boot application named `warehouse-service`, with functionality organized into Java packages. Separating these modules into independently deployable microservices can be considered as a future architectural improvement.

## Version Control

The source code is maintained in GitHub:

[WMS-Increment-1 Repository](https://github.com/bharath-241/WMS-Increment-1)
