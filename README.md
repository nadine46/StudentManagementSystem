
# Student Management System

## Project Description

The Student Management System is a web application developed using Java, Spring Boot, Thymeleaf, and PostgreSQL. It allows users to register students and view registered student records.

## Technologies Used

- Java
- Spring Boot
- Spring MVC
- Thymeleaf
- PostgreSQL
- JDBC
- Maven
- HTML and CSS

## Features

- Register a student using first name, last name, email, and age.
- Save student records in a PostgreSQL database.
- Display all registered students.
- Search for a student by ID.
- Display a message when no students are registered.
- Navigate between the registration form and student list.

## Project Structure

- `model` — contains the Student class.
- `controller` — handles web requests.
- `service` — contains student-related operations.
- `repository` — handles database operations.
- `resources/templates` — contains the Thymeleaf HTML pages.
- `application.properties` — contains application and database configuration.

## Database Configuration

Create a PostgreSQL database named `student_management`.

Configure the database connection in `src/main/resources/application.properties` with your local PostgreSQL host, port, username, and password.

Do not commit real passwords or other sensitive credentials to GitHub.

## How to Run

1. Install Java and Maven.
2. Install PostgreSQL and create the database.
3. Configure the database connection.
4. Open the project in IntelliJ IDEA.
5. Run `StudentManagementSystemApplication`.
6. Open `http://localhost:8080/student/register` to register a student.
7. Open `http://localhost:8080/student/all` to view registered students.

## Project Status

The main application structure and web pages have been implemented. Database operations and student registration should be tested before the project is considered complete.
