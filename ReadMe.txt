Certainly! Here's a sample README file that includes the information you specified:

Assignment Test for MakerSharks

This project is an assignment test for MakerSharks, implementing a RESTful API for user registration and user details fetching using Java 17 and Spring Boot 3.3.0.

Prerequisites

To run this application, you need:
- Java 17
- MySQL database (create the database and run provided queries)
- Spring Boot 3.3.0

Dependencies

The dependencies used in this project include:
- Spring Web
- Spring Data JPA
- Spring Security
- MySQL Connector
- BCrypt for password encryption
- objdbc
- spring-boot-starter-jdbc
- jakarta.ws.rs-api
- org.json


Setup Instructions

1. Database Setup:
   - Create a MySQL database using the provided SQL queries (`sql/user.sql`).
   - Modify `application.properties` with your database credentials.
   - Make sure that you have created the username and password through which you can access the database. These all details have to be mentioned in the "application.properties" in spring project

2. Running the Application:
   - Clone the repository.
   - Open the project in your IDE.
   - Run the application as a Spring Boot application.

Endpoints

POST /api/user/register

Request Body:
```json
{
    "username": "MakerSharks",
    "email": "MakerSharks@gmail.com",
    "password": "MakerSharks"
}
```

Response:
- Success: 200 OK
  ```json
  {
      "success":true,
      "message": "User registered successfully"
  }
  ```

- Validation Error (e.g., password length less than 8): 400 Bad Request
  ```json
  {
      "success":false,
      "error": "Password should of length greater than or equal to 8"
  }
  ```

- Internal Server Error: 500 Internal Server Error
  ```json
{
    "success": false,
    "message": "Internal Server Error"
}
  ```

GET /api/user/fetch/{username}

Request:
- URL Parameter: `test1234`

Response:
- Success: 200 OK
  ```json
   {
       "success": true,
       "user_details": {
           "email": "test123@gmail.com",
           "username": "test1234"
       },
       "message": "User Found"
   }
  ```

- User Not Found: 404 Not Found
  ```json
   {
       "success": false,
       "message": "User Not Found"
   }
  ```

Security

- Passwords are stored securely using BCrypt encryption.
- JPA prevents SQL Injection
- Use of Https increase security though I have not implemented it in this project due to time constraints.

Areas of Improvement

- Implement a custom exception class for better error handling.
- Enable CSRF(Cross-Site Request Forgery) protection in Spring Security.
- Implementing rate limits.
- Avoid exposing internal stack trace in response body.


This README file provides an overview of the project, setup instructions, details about endpoints, security measures, areas of improvement, and references to attached screenshots. Adjust the content as per your specific project details and requirements.
