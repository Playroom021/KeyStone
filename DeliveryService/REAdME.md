# 🚀 KeyStone Delivery Service Backend

A Spring Boot REST API developed as part of the **KEYSTONE Field Service Management** project.

This backend provides secure JWT authentication, customer management, and work order management using Spring Boot, Spring Security, JPA, Hibernate, and MySQL.

---

# 📌 Project Overview

The KeyStone Delivery Service Backend is designed to manage customers and work orders for a field service organization.

The application provides:

- Secure JWT Authentication
- Customer Management
- Work Order Management
- Role-Based Authentication
- REST APIs
- Swagger API Documentation

---

# ✨ Features

## Authentication

- User Registration
- User Login
- JWT Token Generation
- Forgot Password
- Reset Password
- Logout

---

## Customer Management

- Create Customer
- Update Customer
- Delete Customer
- Get Customer by ID
- Get Customer by Email
- Get All Customers

---

## Work Order Management

- Create Work Order
- Get All Work Orders
- Get Work Order by ID
- Update Work Order
- Delete Work Order

---

## Security

- Spring Security
- JWT Authentication
- BCrypt Password Encryption
- Protected REST APIs

---

## API Documentation

- Swagger UI
- OpenAPI 3

---

# 🛠 Tech Stack

## Backend

- Java 17
- Spring Boot 4.1
- Spring Security
- Spring Data JPA
- Hibernate
- Maven

## Database

- MySQL 8

## Authentication

- JWT (JSON Web Token)

## Documentation

- Swagger / OpenAPI

---

# 📂 Project Structure

```
src
├── Controller
├── DTO
├── Entity
├── ENUM
├── Exception
├── Repository
├── Security
└── Service
```

---

# ⚙️ Installation

## Clone Repository

```bash
git clone <YOUR_GITHUB_REPOSITORY_URL>
```

---

## Navigate to Project

```bash
cd DeliveryService
```

---

## Configure Database

Update the database configuration in:

```
src/main/resources/application.properties
```

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/keystone_db
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
```

---

## Build Project

```bash
./mvnw clean install
```

---

## Run Application

```bash
./mvnw spring-boot:run
```

---

# 📖 Swagger API Documentation

Once the application is running:

```
http://localhost:6767/swagger-ui/index.html
```

OpenAPI JSON:

```
http://localhost:6767/v3/api-docs
```

---

# 🔑 Authentication

Login API returns a JWT token.

Use the token in secured endpoints.

Example Header:

```
Authorization: Bearer YOUR_JWT_TOKEN
```

---

# 📌 API Endpoints

## Authentication

```
POST /api/user_auth/register

POST /api/user_auth/login

POST /api/user_auth/forgot_password

POST /api/user_auth/reset_password

POST /api/user_auth/loggedOut
```

---

## Customer

```
POST /api/customer

GET /api/customer

GET /api/customer/{id}

PUT /api/customer/{id}

DELETE /api/customer/{email}
```

---

## Work Order

```
POST /api/workorders

GET /api/workorders

GET /api/workorders/{id}

PUT /api/workorders/{id}

DELETE /api/workorders/{id}
```

---

# 📸 Screenshots

Add screenshots here:

- Login API
- Swagger UI
- Customer APIs
- Work Order APIs

---

# 🚀 Future Enhancements

- Technician Management
- Site Management
- Dashboard
- Reports
- Email Notifications
- PostgreSQL Support
- Docker Deployment
- Role-Based Authorization
- Unit Testing
- CI/CD Pipeline

---

# 👨‍💻 Developed By

**Gyan Sharma**

Java Full Stack Intern

---

# 📄 License

This project was developed for educational and internship purposes.