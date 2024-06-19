# UserVault

## Overview
The **UserVault** project provides a comprehensive system for managing user data. It allows for user registration, updating, deletion, and retrieval of user information. Additional functionalities include hashing passwords for security. The project is implemented using Java Spring Boot and Hibernate.

## Features
- **Register User:** Users can register with details such as username, email, password, contact number, and address.
- **Update User Information:** Users can update attributes like passwords.
- **Delete User:** Users can delete a user from the database using their email.
- **Retrieve User Information:** Users can fetch details of a user by their username.

## Tech Stack
- **Java:** Core programming language for backend development.
- **Spring Boot:** Framework for building RESTful APIs and managing dependencies.
- **Hibernate:** ORM framework for database interactions.
- **MySQL:** Relational database management system for storing user data.

## Setup Instructions
1. **Clone the Repository**

    ```bash
    git clone https://github.com/yourusername/uservault.git
    cd uservault
    ```

2. **Set Up MySQL Database**

    Configure your MySQL server and create a database named `uservault`. Update the `application.properties` with your database credentials:

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/user
    spring.datasource.username=root
    spring.datasource.password=root
    spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.properties.hibernate.show_sql=true
    spring.jpa.properties.hibernate.use_sql_comments=true
    spring.jpa.properties.hibernate.format_sql=true
    ```

3. **Build and Run the Project**

    ```bash
    ./mvnw clean install
    ./mvnw spring-boot:run
    ```

4. **Access APIs**

    Use tools like Swagger or Postman to interact with the RESTful APIs.

## API Documentation

### **POST /api/user/register:** Register a new user.
     - The API allows users to register by submitting their details. Below is an example of the required request body, along with a corresponding Swagger sample screenshot.

   **Request Body:**

    json
    {
      "userName": "username",
      "userEmail": "user@example.com",
      "userPassword": "password",
      "contactNumber": "1234567890",
      "address": "123 Main St"
    }

    ![Swagger Screenshot](https://github.com/manikanta-km/EmployeeDatabase/assets/142763418/026502e1-deaf-40da-9002-bad3bf3da0c4)

    
    - Below is the Swagger screenshot illustrating the expected output.

    ![Screenshot (966)](https://github.com/manikanta-km/EmployeeDatabase/assets/142763418/bca37743-82c0-455c-80e6-7cae3b20d8a5)


### **GET /api/user/fetch:** Retrieve user information by username.

   **Parameters:**
   - `userName`: String

   **Response:**

    ```json
    {
      "userId": 1,
      "userName": "username",
      "userEmail": "user@example.com",
      "contactNumber": "1234567890",
      "address": "123 Main St"
    }
    ```

    ![Fetch User](https://github.com/manikanta-km/UserVault/assets/your-asset-id/fetch-user.png)

### **PUT /api/user/updatePassword:** Update user password.

   **Parameters:**
   - `email`: String
   - `password`: String

   **Response:**

    ```json
    "Password updated successfully"
    ```

    ![Update Password](https://github.com/manikanta-km/UserVault/assets/your-asset-id/update-password.png)

### **DELETE /api/user:** Delete a user by email.

   **Parameters:**
   - `email`: String

   **Response:**

    ```json
    "User deleted successfully"
    ```

    ![Delete User](https://github.com/manikanta-km/UserVault/assets/your-asset-id/delete-user.png)

### **GET /api/user/list:** Retrieve a paginated list of users.

   **Parameters:**
   - `page`: Integer
   - `size`: Integer
   - `sort`: String (optional)

   **Response:**

    ```json
    [
      {
        "userId": 1,
        "userName": "username",
        "userEmail": "user@example.com",
        "contactNumber": "1234567890",
        "address": "123 Main St"
      },
      ...
    ]
    ```

    ![Paginated User List](https://github.com/manikanta-km/UserVault/assets/your-asset-id/paginated-user-list.png)

## Data Models

### **User:**

Represents a user entity with attributes such as ID, username, email, password, contact number, and address.

```java
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Size(min = 4, max = 20, message = "User name must be 4-20 characters long")
    private String userName;
    @Email(message = "Please provide a valid email address")
    private String userEmail;
    @NotBlank(message = "Password cannot be blank")
    private String userPassword;
    @Size(min = 10, max = 10, message = "Phone number must be exactly 10 digits long")
    @Pattern(regexp = "\\d+", message = "Phone number must contain only digits")
    private String contactNumber;
    @NotBlank(message = "Address cannot be blank")
    private String address;
}
