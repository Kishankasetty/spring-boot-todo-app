# My First Live Full-Stack Project — To-Do Website

A simple full-stack To-Do application built as a learning project to understand Java backend development, REST APIs, database integration, and cloud deployment.

## 🚀 Live Demo

**Try the application:** https://spring-boot-todo-app-xiiv.onrender.com

**Source Code:** https://github.com/Kishankasetty/spring-boot-todo-app

## 📌 Project Overview

This project was developed from scratch to gain practical experience building and deploying a web application using Java and Spring Boot.

Users can create and manage tasks through a simple web interface. Task data is stored in a hosted database and remains available after refreshing the page.

## ✨ Features

* Create, edit, and delete tasks
* Mark tasks as completed or pending
* Assign task priorities: Low, Medium, and High
* Set due dates for tasks
* Search tasks by title
* Filter tasks by status
* Store tasks in a database with persistent data

## 🛠️ Technologies Used

**Frontend**

* HTML
* CSS
* JavaScript

**Backend**

* Java 17
* Spring Boot
* Spring Web
* Spring Data JPA
* Hibernate
* Maven

**Database**

* TiDB Cloud (MySQL-compatible)

**Deployment and Tools**

* Docker
* Render
* Git and GitHub
* Eclipse IDE

## 🏗️ Application Architecture

1. The user interacts with the frontend in the browser.
2. JavaScript sends HTTP requests to the Spring Boot REST API.
3. The controller receives requests and communicates with the service layer.
4. The service layer uses the repository to access task data.
5. Spring Data JPA and Hibernate interact with the database.
6. The API returns responses to the frontend.

## 🔗 REST API Endpoints

Base URL: `/api/tasks`

| Method | Endpoint          | Purpose               |
| ------ | ----------------- | --------------------- |
| GET    | `/api/tasks`      | Retrieve all tasks    |
| GET    | `/api/tasks/{id}` | Retrieve a task by ID |
| POST   | `/api/tasks`      | Create a task         |
| PUT    | `/api/tasks/{id}` | Update a task         |
| DELETE | `/api/tasks/{id}` | Delete a task         |

## 💻 Run Locally

### Prerequisites

* Java 17 or compatible configured JDK
* MySQL 8 or a compatible database
* Git

### 1. Clone the repository

```bash
git clone https://github.com/Kishankasetty/spring-boot-todo-app.git
cd spring-boot-todo-app
```

### 2. Create the database

```sql
CREATE DATABASE todo_db;
```

### 3. Configure environment variables

Configure the following variables for your local environment:

```text
DB_URL=jdbc:mysql://localhost:3306/todo_db
DB_USERNAME=root
DB_PASSWORD=your_database_password
```

Use your actual database credentials. Do not commit passwords or secrets to GitHub.

### 4. Run the application

On Windows PowerShell:

```powershell
$env:DB_URL="jdbc:mysql://localhost:3306/todo_db"
$env:DB_USERNAME="root"
$env:DB_PASSWORD="your_database_password"
.\mvnw.cmd spring-boot:run
```

Open http://localhost:8080 in your browser.

## 📚 What I Learned

* Building REST APIs using Spring Boot
* Understanding controllers, services, and repositories
* Using Spring Data JPA and Hibernate for database operations
* Implementing CRUD functionality
* Validating request data and handling API errors
* Configuring environment variables for database connectivity
* Using Maven to build a Java application
* Containerizing an application with Docker
* Deploying a Spring Boot application on Render
* Using Git and GitHub for version control

## 🎯 Project Goal

The primary goal was to learn full-stack application development by building a working project from scratch and making it accessible online.

This is a learning project, and the current version represents my first successful live deployment.

## 👨‍💻 Author

**Sai Kishan Kasetty**

* GitHub: https://github.com/Kishankasetty

* <img width="896" height="470" alt="image" src="https://github.com/user-attachments/assets/f0a8c5dc-0ee4-4d0f-92b2-f0ca2edc1c3a" />


---

*Built for learning, experimentation, and hands-on experience with full-stack Java development.*
