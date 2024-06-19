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


## Unit Tests

The UserVault project includes comprehensive unit tests to verify the functionality of its components. Below are the main test classes and their purpose:

### UserControllerTest

The `UserControllerTest` class tests the API endpoints in the `UserController` class using Spring's `MockMvc` for HTTP request simulation.

- **testRegisterUser**: Verifies the registration of a new user through the `/api/user/register` endpoint.
- **testGetUserByUserName**: Tests the retrieval of user information by username using the `/api/user/fetch` endpoint.
- **testUpdatePassword**: Validates the functionality to update a user's password via the `/api/user/updatePassword` endpoint.
- **testDeleteUser**: Ensures the deletion of a user from the database using the `/api/user` endpoint.

### UserServiceTest

The `UserServiceTest` class tests the business logic methods in the `UserService` class, mocking the data repository (`IUserRepo`) interactions.

- **testUserSignup_Success**: Tests successful user registration and verifies the save operation in the repository.
- **testUserSignup_EmailAlreadyInUse**: Validates the handling of a scenario where the email is already registered during user signup.
- **testGetUser_Success**: Verifies the retrieval of a user by username.
- **testGetUser_UserNotFound**: Ensures proper exception handling when attempting to retrieve a non-existent user.
- **testUpdatePassword_Success**: Tests the successful update of a user's password and verifies the save operation in the repository.
- **testUpdatePassword_UserNotFound**: Validates the handling of a scenario where the user email is not registered during password update.
- **testDeleteUser_Success**: Tests the successful deletion of a user and verifies the delete operation in the repository.
- **testDeleteUser_UserNotFound**: Ensures proper exception handling when attempting to delete a non-existent user.

### PasswordEncryptorTest

The `PasswordEncryptorTest` class tests the encryption utility used to hash passwords securely.

- **testEncrypt**: Verifies that the password encryption produces a hash that matches the raw password.
- **testEncryptDifferentPasswords**: Tests that different passwords produce different hashes.
- **testEncryptSamePasswordProducesDifferentHashes**: Validates that encrypting the same password multiple times produces different hashes to enhance security.

### Running Unit Tests

To run the unit tests locally:

1. Ensure you have Maven installed.
2. Navigate to the project root directory in your terminal.
3. Run `mvn test` to execute all unit tests.
4. View the test results in your terminal or IDE's test runner.


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
     - The API allows users to register by submitting their details, below is an example of the required request body and response.
     
   **Request Body:**

     ```json
    {
      "userName": "username",
      "userEmail": "user@example.com",
      "userPassword": "password",
      "contactNumber": "1234567890",
      "address": "123 Main St"
    }
    ```

   **Response:**

    ```json
    "User Registered"
    ```

- Below are the screenshots demonstrating the input parameters and the resulting output as seen in Swagger.

![Swagger Screenshot](https://github.com/manikanta-km/EmployeeDatabase/assets/142763418/026502e1-deaf-40da-9002-bad3bf3da0c4)

![Screenshot (966)](https://github.com/manikanta-km/EmployeeDatabase/assets/142763418/bca37743-82c0-455c-80e6-7cae3b20d8a5)


### **GET /api/user/fetch:** Retrieve user information by username.
    -  The API facilitates users to fetch the user details by username, below is an example of the required request body and response.

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
- Below are the screenshots demonstrating the input parameters and the resulting output as seen in Swagger.

![Screenshot (967)](https://github.com/manikanta-km/EmployeeDatabase/assets/142763418/40529191-6abd-4f82-9229-f1b5bc665b3a)

![Screenshot (968)](https://github.com/manikanta-km/EmployeeDatabase/assets/142763418/48b7a9af-237e-498c-9c78-960e5a992482)


### **PUT /api/user/updatePassword:** Update user password.
     -  The API facilitates the users to update the password, below is an example of the required request body and response.

   **Parameters:**
   - `email`: String
   - `password`: String

   **Response:**

    ```json
    "Password updated successfully"
    ```
- Below are the screenshots demonstrating the input parameters and the resulting output as seen in Swagger.

![Screenshot (969)](https://github.com/manikanta-km/EmployeeDatabase/assets/142763418/1454dea5-2082-44e0-9632-f81ddeff2919)

![Screenshot (970)](https://github.com/manikanta-km/EmployeeDatabase/assets/142763418/8af73384-cb79-43a0-a19c-26e3a74f3991)


    

### **DELETE /api/user:** Delete a user by email.
     -  The API facilitates the users to update the password, below is an example of the required request body and response.


   **Parameters:**
   - `email`: String

   **Response:**

    ```json
    "User deleted successfully"
    ```

- Below are the screenshots demonstrating the input parameters and the resulting output as seen in Swagger.

 ![Screenshot (971)](https://github.com/manikanta-km/EmployeeDatabase/assets/142763418/88652ac1-b1a0-4d59-a909-e4c7f9026307)

 ![Screenshot (972)](https://github.com/manikanta-km/EmployeeDatabase/assets/142763418/c6840add-951f-423a-bd7c-5d217e665a5b)


### Open with Preferred IDE

For seamless development, you can download the project from GitHub and open it using your favorite Integrated Development Environment (IDE), such as IntelliJ IDEA or Visual Studio Code (VS Code):

**IntelliJ IDEA:**
1. **Launch IntelliJ IDEA:** Start the IntelliJ IDEA application on your computer.
2. **Open Project:** Navigate to `File > Open` from the main menu.
3. **Select Project Directory:** Browse and select the directory where you cloned the project.
4. **Automatic Import:** IntelliJ IDEA will automatically detect and import the project as a Maven project, including all dependencies and configurations.

**Visual Studio Code (VS Code):**
1. **Launch VS Code:** Open Visual Studio Code on your system.
2. **Open Project Folder:** Go to `File > Open Folder` from the menu.
3. **Choose Project Folder:** Locate and select the folder containing the downloaded project.
4. **Install Java Extension Pack:** If not already installed, ensure you have the [Java Extension Pack](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack) to provide robust Java support and functionalities.
5. **Automatic Setup:** VS Code will set up the project environment based on the detected configurations and dependencies.


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


