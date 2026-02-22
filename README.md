# 🔐 JWT Authentication & Authorization using Spring Boot and Spring Security

## 📌 Overview

This project is an enterprise-style backend application implementing **JWT (JSON Web Token) based authentication and authorization** using **Spring Boot** and **Spring Security**.

The system provides **secure, stateless REST APIs** with proper authentication flow, password encryption, and token validation mechanism. It follows standard **layered architecture and backend best practices** used in real-world enterprise applications.

This project was developed to gain hands-on experience in **Spring Security, JWT, and secure backend development**.

---

## 🚀 Features

- User Registration
- User Login Authentication
- JWT Token Generation
- JWT Token Validation
- Stateless Authentication
- Secure Password Encryption using BCrypt
- Protected REST APIs
- Exception Handling (if implemented)
- Layered Architecture (Controller, Service, Repository)

---

## 🛠 Tech Stack

| Technology | Version |
|----------|---------|
| Java | 17 |
| Spring Boot | 3.x |
| Spring Security | 6.x |
| JWT | Latest |
| Spring Data JPA | Latest |
| Hibernate | Latest |
| MySQL | Database |
| Maven | Build Tool |

---

## 🧠 Architecture Overview

This project follows **standard enterprise layered architecture**:

```
Controller Layer  → Handles HTTP Requests

Service Layer     → Business Logic

Repository Layer  → Database Operations

Entity Layer      → Database Models

Security Layer    → JWT & Spring Security Config

Utility Layer     → JWT Token Generation & Validation

Filter Layer      → JWT Authentication Filter
```

---

## 🔑 Authentication Workflow

### Step 1: User Registration

User registers using:

```
POST /api/auth/register
```

User data is stored securely with encrypted password.

---

### Step 2: User Login

User logs in using:

```
POST /api/auth/login
```

Spring Security authenticates credentials.

If valid → JWT Token is generated.

---

### Step 3: Access Protected APIs

Client sends token in header:

```
Authorization: Bearer <JWT_TOKEN>
```

JWT Filter validates token.

If valid → Access granted

If invalid → Access denied

---

## 📡 API Endpoints

### 🔹 Register

```
POST /api/auth/register
```

Request:

```json
{
  "username": "asim",
  "password": "password123"
}
```

---

### 🔹 Login

```
POST /api/auth/login
```

Response:

```json
{
  "token": "jwt_token_here"
}
```

---

### 🔹 Protected API Example

```
GET /api/test/hello
```

Header:

```
Authorization: Bearer jwt_token_here
```

Response:

```
Hello, Authenticated User
```

---

## 🔒 Security Implementation

This project uses:

- Spring Security Authentication Manager
- BCrypt Password Encoder
- JWT Token based authentication
- SecurityFilterChain configuration
- Stateless Session Management
- Custom JWT Authentication Filter

Password stored in encrypted format:

```
$2a$10$exampleEncryptedPassword
```

---

## ⚙️ Configuration

### application.properties

### MySQL Example:

```
spring.datasource.url=jdbc:mysql://localhost:3306/jwt_db
spring.datasource.username=root
spring.datasource.password=root

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

---

## ▶️ Running the Application

### Step 1: Clone Repository

```
git clone https://github.com/yourusername/jwt-springboot-security.git
```

---

### Step 2: Navigate

```
cd jwt-springboot-security
```

---

### Step 3: Run

Using Maven:

```
mvn spring-boot:run
```

OR

Run main class:

```
JwtApplication.java
```

---

### Server runs at:

```
http://localhost:8091
```

---

## 🧪 Testing using Postman

### Login → Copy Token

Add Header:

```
Authorization: Bearer your_token
```

Access protected APIs

---

## 💡 Key Concepts Covered

- Spring Security Configuration
- JWT Authentication
- Stateless Security
- Password Encryption
- REST API Security
- Authentication Flow
- Filter Chain
- Token Validation

---

## 📈 Real-World Use Cases

This implementation is used in:

- Banking Applications
- Enterprise Backend Systems
- Microservices Authentication
- Secure REST APIs
- SaaS Applications

---

## 🔮 Future Enhancements

- Role Based Authorization (ADMIN / USER)
- Refresh Token Implementation
- Global Exception Handling
- Docker Deployment
- Unit Testing
- Cloud Deployment (AWS)

---

## 👨‍💻 Author

**Asim Sutar**

Backend Developer  
Java | Spring Boot | Spring Security | REST API  

---

## ⭐ If you like this project

Give it a ⭐ on GitHub

---

## 📜 License

This project is for learning and demonstration purposes.
