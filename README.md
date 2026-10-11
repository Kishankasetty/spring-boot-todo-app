# Spring Boot To-Do Application

A full-stack To-Do application built using Java, Spring Boot, MySQL, HTML, CSS, and JavaScript. The application allows users to manage tasks through a REST API and a responsive web interface.

## Features

* Create, view, update, and delete tasks (CRUD operations).
* Mark tasks as completed or pending.
* Set task priority: Low, Medium, or High.
* Assign optional due dates.
* Track task creation and modification timestamps.
* Search tasks by title.
* Filter tasks by All, Pending, and Completed status.
* Validate task input and handle API errors.
* Automated service and controller tests using JUnit, Mockito, and MockMvc.

## Technologies Used

* **Backend:** Java 17, Spring Boot, Spring Web
* **Database:** MySQL, Spring Data JPA, Hibernate
* **Frontend:** HTML, CSS, JavaScript
* **Testing:** JUnit, Mockito, MockMvc
* **Build Tool:** Maven
* **Version Control:** Git and GitHub

## Project Structure

```text
src/
├── main/
│   ├── java/com/todo_app/
│   │   ├── controller/
│   │   ├── entity/
│   │   ├── exception/
│   │   ├── repository/
│   │   └── service/
│   └── resources/
│       ├── static/
│       └── application.properties
└── test/java/com/todo_app/
```

## Getting Started

### Prerequisites

* Java 17 or compatible configured JDK
* MySQL Server
* Maven
* Git

### 1. Clone the repository

```bash
git clone https://github.com/Kishankasetty/spring-boot-todo-app.git
cd spring-boot-todo-app
```

### 2. Create the database

Run this SQL command in MySQL:

```sql
CREATE DATABASE todo_db;
```

### 3. Configure database credentials

Set these environment variables before running the application:

* `DB_URL` — `jdbc:mysql://localhost:3306/todo_db`
* `DB_USERNAME` — `root`
* `DB_PASSWORD` — your MySQL password

Never commit real database credentials to GitHub.

### 4. Run the application

From the project root, run:

```bash
mvn spring-boot:run
```

Open `http://localhost:8080/` in your browser.

## REST API Endpoints

| Method | Endpoint          | Description           |
| ------ | ----------------- | --------------------- |
| GET    | `/api/tasks`      | Retrieve all tasks    |
| GET    | `/api/tasks/{id}` | Retrieve a task by ID |
| POST   | `/api/tasks`      | Create a task         |
| PUT    | `/api/tasks/{id}` | Update a task         |
| DELETE | `/api/tasks/{id}` | Delete a task         |

## Run Automated Tests

```bash
mvn test
```

The project includes service unit tests and controller tests for API responses and validation.

## Future Improvements

* User registration and authentication
* Pagination and sorting
* Integration testing with a dedicated test database
* Deployment to a cloud hosting platform

## Author

Developed as a learning project to practice Java backend development, REST APIs, database integration, frontend development, and automated testing.
