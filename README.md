 RevHire Backend

RevHire is a Spring Boot based job portal backend application with JWT authentication and role-based access control.

---

Tech We are going to use:

* Java 21
* Spring Boot 3
* Spring Security (JWT)
* Spring Data JPA
* MySQL
* Maven
* Lombok

---

 ## Project Setup Instructions
Clone the Repository

```bash
git clone https://github.com/tejasubhash01/RevHire.git
cd RevHire
```

---

##  Configure Database

Open:

```
src/main/resources/application.properties
```

# Update with your local MySQL credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/revhire
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

Make sure:

* MySQL is running
* Database `revhire` exists

If not, create it:

```sql
CREATE DATABASE revhireDB;
```

---

## 3️ Build the Project

```bash
mvn clean install
```

---

## 4️⃣ Run the Application

```bash
mvn spring-boot:run
```

OR run from IntelliJ:

* Open `RevHireApplication.java`
* Click  Run

Server starts at:

```
http://localhost:8080
```

---

#  Authentication Flow (Use This as references Postman Testing)

---

## 1️) Register User

### POST

```
http://localhost:8080/api/v1/auth/register
```

### Body (JSON)

```json
{
  "email": "test@gmail.com",
  "password": "password123",
  "role": "JOB_SEEKER"
}
```

Expected Response:

```json
{
  "success": true,
  "message": "Registered successfully",
  "data": {
    "email": "test@gmail.com",
    "role": "JOB_SEEKER",
    "token": "JWT_TOKEN"
  }
}
```

---

## 2️) Login

### POST

```
http://localhost:8080/api/v1/auth/login
```

### Body (JSON)

```json
{
  "email": "test@gmail.com",
  "password": "password123"
}
```

Copy the token from response.

---

## 3️) Call Protected Endpoint

Example:

```
GET http://localhost:8080/api/v1/admin/ping
```

Add Header:

```
Authorization: Bearer <PASTE_TOKEN_HERE>
```

Expected:

```json
{
  "success": true,
  "message": "OK",
  "data": {
    "message": "secured ok"
  }
}
```

Without token → 401 Unauthorized
Invalid role → 403 Forbidden

---

#  Public Endpoints

```
GET /api/health
GET /api/info
POST /api/v1/auth/register
POST /api/v1/auth/login
```

---

#  All Other Endpoints

Require:

```
Authorization: Bearer <JWT>
```

---

# 🧱 Project Structure

```
com.revhire

├── config
├── controller
├── service
├── repository
├── entity
├── dto
├── exception
├── security
└── util
```

---

# Our Team  Branching Strategy

* main → production stable
* develop → integration branch
* feature branches → each team member

Each member must:

1. Create their own branch
2. Develop module
3. Raise Pull Request to `develop`

I will check it 

---

#  Important Note

* Do not modify security module without approval.
* Do not modify other member’s package.
* Follow ApiResponse format for all APIs.
* All APIs must be tested via Postman before PR.

---

#  Current Status

* JWT Authentication implemented
* Role-based access enabled
* Base project architecture completed
* Ready for module development

---
