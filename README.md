Student Result Analysis – REST API (No Database)

A Spring Boot REST API project that manages and analyzes student academic results using in-memory data structures.
The project focuses on backend logic, RESTful design, and result analysis without using any database or UI layer.

🎯 Project Objective

Build a REST-based backend system

Understand how APIs work internally

Implement business logic for:

Student management

Course enrollment

Marks entry

Result analysis

Avoid databases to focus on core backend concepts

🛠️ Technologies Used

Java

Spring Boot

REST APIs

Maven

Postman

In-Memory Storage (Map, AtomicLong)

🚫 Excluded Technologies

No MySQL / No Database

No JPA / Hibernate

No Thymeleaf / HTML

No Frontend UI

🏗️ Project Structure
student-result-analysis/
├── src/main/java/com/example/demo
│   ├── StudentResultAnalysisApplication.java
│   ├── HealthController.java
│   ├── ApiController.java
│   ├── ApiExceptionHandler.java
│   ├── DataStore.java
│   └── Models.java
├── src/main/resources
│   └── application.properties
├── pom.xml
└── README.md
📂 Core Components Explanation

Models.java
Contains all entity classes such as Student, Course, Faculty, Marks, Enrollment, etc.

DataStore.java
Simulates a database using ConcurrentHashMap and AtomicLong.

ApiController.java
Contains all REST endpoints for CRUD operations and analysis.

ApiExceptionHandler.java
Handles exceptions and returns clean JSON error responses.

HealthController.java
Provides a simple health check endpoint.

🔁 API Flow Overview
Client (Postman)
      ↓ JSON
REST Controller
      ↓
In-Memory DataStore
      ↓
Business Logic (Marks, Grade, Pass/Fail)
      ↓
JSON Response
🌐 API Endpoints
🔹 Health Check
GET /health
🔹 Student Management
POST /api/students
GET  /api/students
🔹 Faculty & Course
POST /api/faculty
POST /api/courses
🔹 Enrollment
POST /api/enrollments
🔹 Marks Entry
POST /api/marks
🔹 Result Analysis
GET /api/analysis/student/{id}?semester=1
GET /api/analysis/semester/{semester}/toppers
📊 Result Analysis Logic

Total Marks = Internal + External

Pass Criteria: Total ≥ 40

Grades assigned based on total marks

Semester toppers calculated by sorting total marks

▶️ How to Run the Project
Step 1: Clone the repository
git clone https://github.com/2400030303/student-result-analysis.git
Step 2: Navigate to project
cd student-result-analysis
Step 3: Run the application
mvn spring-boot:run
Step 4: Test APIs

Use Postman to test the REST endpoints.

🧪 Testing

APIs tested using Postman

JSON request and response format

Screenshots can be added for lab submission

🎓 Learning Outcomes

Understanding REST API architecture

Handling backend business logic

Managing data without a database

Using Spring Boot annotations

Writing clean and maintainable backend code

📌 Author

Y . Pavan Sai Santhosh
B.Tech – Computer Science Engineering
KL UNIVERSITY , VIJAYAWADA

🏁 Final Note

This project is designed for academic learning and backend skill development.
It serves as a foundation for building database-driven REST APIs in the future.
