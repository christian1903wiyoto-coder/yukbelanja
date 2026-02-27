# YukBelanja API

A demo Spring Boot application for an e-commerce platform back-end, featuring Category and Product management with advanced RESTful patterns.

## 🚀 Features

- **CRUD Operations**: Full Create, Read, Update, and Delete functionality for `Kategori` and `Produk`.
- **Relationship**: Many-to-One relationship between Products and Categories.
- **Pagination & Sorting**: Support for large datasets with configurable page size, sorting, and direction.
- **Search**: Case-insensitive search by name for both categories and products.
- **Global Exception Handling**: Unified error response using `@RestControllerAdvice`.
- **Standardized API Response**: Consistent JSON structure using a generic `ApiResponse` wrapper.
- **Security**:
  - Spring Security configuration (Permit All for development).
  - CORS configuration allowed for `http://localhost:5173` and `https://domain.id`.
- **Data Seeder**: Automatic population of initial categories and products on application startup.
- **Validation**: Input validation using `jakarta.validation`.

## 🛠 Tech Stack

- **Java 21**
- **Spring Boot 3.5.x**
- **Spring Data JPA**
- **Spring Security**
- **PostgreSQL**
- **Lombok**
- **Hibernate**

## 📦 Prerequisites

- JDK 21
- Maven
- PostgreSQL database named `yukbelanjadb`

## ⚙️ Configuration

Update `src/main/resources/application.properties` with your database credentials:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/yukbelanjadb
spring.datasource.username=your_username
spring.datasource.password=your_password
```

## 🏃 How to Run

1. Clone the repository.
2. Ensure PostgreSQL is running and the database exists.
3. Run the application using Maven:
   ```bash
   mvn spring-boot:run
   ```

## 📑 API Documentation

### Kategori Endpoints (`/api/kategori`)

| Method | Endpoint             | Description                                                                   |
| ------ | -------------------- | ----------------------------------------------------------------------------- |
| GET    | `/api/kategori`      | Get all categories (supports `search`, `page`, `size`, `sortBy`, `direction`) |
| GET    | `/api/kategori/{id}` | Get category by ID                                                            |
| POST   | `/api/kategori`      | Create new category                                                           |
| PUT    | `/api/kategori/{id}` | Update existing category                                                      |
| DELETE | `/api/kategori/{id}` | Delete category                                                               |

### Produk Endpoints (`/api/produk`)

| Method | Endpoint           | Description                                                                 |
| ------ | ------------------ | --------------------------------------------------------------------------- |
| GET    | `/api/produk`      | Get all products (supports `search`, `page`, `size`, `sortBy`, `direction`) |
| GET    | `/api/produk/{id}` | Get product by ID                                                           |
| POST   | `/api/produk`      | Create new product                                                          |
| PUT    | `/api/produk/{id}` | Update existing product                                                     |
| DELETE | `/api/produk/{id}` | Delete product                                                              |

### Example Query Parameters

`GET /api/produk?search=bose&page=0&size=10&sortBy=nama&direction=asc`

## 🧪 Response Format

```json
{
  "success": true,
  "message": "Data produk berhasil diambil",
  "data": { ... },
  "timestamp": "2026-02-25T14:52:57"
}
```

## 🏗 Project Structure

- `model`: JPA Entities (`Kategori`, `Produk`)
- `repository`: Spring Data JPA Repositories
- `service`: Business Logic layer
- `controller`: REST Controllers
- `dto`: Request and Response Data Transfer Objects
- `exception`: Custom exceptions and Global Exception Handler
- `config`: Security and Seeder configurations
