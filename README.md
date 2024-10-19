# Web Platform with Docker Infrastructure

This repository contains a project that involves building a web platform with user authentication, profile management, and administrative functionality. The entire infrastructure is containerized using Docker.

## Project Overview

The goal of this project is to develop a web platform using **Spring Framework**, with a focus on modular user authentication and registration. The project also demonstrates how to containerize the application and database using Docker.

### Main Features:
- **Dockerized Infrastructure** with separate containers for the web server and the database.
- **User Registration and Authentication** system with proper data validation.
- **Profile Management** for users to view and update their details.
- **Administrator Role** that can manage and delete user accounts.

### Technologies Used:
- **Spring Framework**
- **Docker** for containerization
- **Docker Compose** to manage multi-container applications
- **MySQL**
- **HTML/CSS/JavaScript** for the frontend

## Project Specifications

### 1. Docker Infrastructure
- **Web Server Container**: Build a Docker image for the web server.
- **Database Container**: Build a Docker image for the database.
- **Docker Compose**: Configure communication between the web server and the database using Docker Compose.

### 2. Web Platform 
- **Authentication Module**:
  - Users can log in with their email or phone number and password.
- **Registration Module**:
  - Users can register with the following fields:
    - First Name, Middle Name, Last Name, Phone Number, Date of Birth, Email, Password, Confirm Password.
- **Profile Management**:
  - After logging in, users are redirected to their profile page where they can:
    - View their personal information.
    - Update their profile data and profile picture.
- **Admin Module**:
  - The admin can view all registered users in a table format.
  - The admin can edit or delete user information.

## Project Structure

The project is divided into the following components:

```
├── src
│   ├── main
│   │   └── javafiles
│   └── test
│       └── java
│   ├── resources
│   │   └── assetes
│   │   └── thymeleafhtmlfiles
│   │   └── application.properties
├── docker-compose.yml
├── Dockerfile.database
├── Dockerfile.webserver
├── pom.xml
└── README.md
```

- **Web Server Container**: Contains the backend logic for user authentication, profile management, and the admin module.
- **Database Container**: Manages the user data, such as profile information, authentication credentials, etc.
- **Docker Compose**: Configures the multi-container application to enable communication between the web server and the database.

## How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/elgalika/Web-Platform-with-Docker-Infrastructure
   cd Web-Platform-with-Docker-Infrastructure
   ```

2. Build and run the Docker containers using Docker Compose:
   ```bash
   docker-compose up --build
   ```

3. Access the application:
   - Web platform: `http://localhost:8080`
   - Admin panel (requires login with an admin account): `http://localhost:8080/admin`

4. To stop the containers:
   ```bash
   docker-compose down
   ```
