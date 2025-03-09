# Reqres API Automation Tests

## Overview
This project contains automated tests for the Reqres API (https://reqres.in/). It validates various CRUD operations on the `/users` endpoint and checks for delayed response behavior.

## Test Cases
### 1. CRUD Operations on `/users` Endpoint:
- **POST:** Create a new user by sending a request with the necessary user data and verify the response.
- **PUT:** Update an existing user's information and validate the updated fields.
- **DELETE:** Remove a created user and ensure that the deletion is successful.

### 2. Delayed Response Validation:
- Create a new user.
- Send a request to `https://reqres.in/api/users?delay=5` and verify the delayed response.

## Test Execution
### Prerequisites:
- Java (JDK 8+)
- Maven
- TestNG
- Rest Assured

### How to Run Tests:
1. Clone the repository.
2. Navigate to the project directory.
3. Run the tests using:
   ```sh
   mvn test
   ```

## Project Structure
- **ReqresApiTest.java**: Contains test methods for CRUD operations and delayed response validation.
- **`@BeforeClass`**: Sets up base URI before test execution.
- **`@Test`**: Defines and runs API tests in sequence.

## Dependencies
This project uses the following dependencies:
```xml
<dependencies>
    <dependency>
        <groupId>io.rest-assured</groupId>
        <artifactId>rest-assured</artifactId>
        <version>5.3.0</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.testng</groupId>
        <artifactId>testng</artifactId>
        <version>7.7.1</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

