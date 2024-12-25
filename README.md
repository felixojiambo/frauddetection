# Fraud Detection System

A robust Spring Boot application designed to process financial transactions and detect potential fraud using a combination of rule-based strategies and machine learning (ML) algorithms. This system leverages advanced software engineering principles, including Data Transfer Objects (DTOs), GoF Design Patterns, SOLID Design Principles, Aspect-Oriented Programming (AOP), and integrates ML models using the Smile library.

## Table of Contents

- [Features](#features)
- [Technologies](#technologies)
- [Architecture](#architecture)
- [Setup and Installation](#setup-and-installation)
- [Running the Application](#running-the-application)
- [API Documentation](#api-documentation)
- [Machine Learning Integration](#machine-learning-integration)
- [Design Patterns and Principles](#design-patterns-and-principles)
- [Logging and Security](#logging-and-security)
- [Future Enhancements](#future-enhancements)
- [Contributing](#contributing)
- [License](#license)

## Features

- **User Management:** Register, retrieve, update, and delete users with role-based access control.
- **Transaction Management:** Process financial transactions (deposits and withdrawals) with status tracking.
- **Fraud Detection:** Analyze transactions for potential fraud using both rule-based and ML-driven strategies.
- **Aspect-Oriented Programming (AOP):** Modularize cross-cutting concerns such as logging, security, and fraud detection.
- **Data Transfer Objects (DTOs):** Decouple internal data models from external API representations for enhanced security and flexibility.
- **Design Patterns:** Utilize GoF Design Patterns (Strategy, Observer, Singleton, Factory Method) for a maintainable and scalable architecture.
- **Machine Learning Integration:** Incorporate ML algorithms (Decision Tree, Isolation Forest) using the Smile library for advanced fraud detection.
- **API Documentation:** Interactive API documentation using Swagger/OpenAPI.

## Technologies

- **Spring Boot:** Framework for building the application.
- **Spring Data JPA:** For database interactions.
- **H2 Database:** In-memory database for development (can be switched to PostgreSQL/MySQL).
- **Spring AOP:** Implementing cross-cutting concerns.
- **Lombok:** Reduces boilerplate code.
- **MapStruct:** For mapping between entities and DTOs.
- **Smile:** Java-based ML library for integrating machine learning algorithms.
- **Maven:** Project management and build automation.
- **Swagger/OpenAPI:** API documentation.
- **PostgreSQL/MySQL (Optional):** For production-ready database storage.
- **Docker & Kubernetes (Future Enhancements):** For containerization and orchestration.

## Architecture

The application follows a layered architecture with clear separation of concerns:

1. **Controller Layer:** Handles HTTP requests and responses.
2. **Service Layer:** Contains business logic and interacts with repositories.
3. **Repository Layer:** Manages data persistence using Spring Data JPA.
4. **DTOs and Mappers:** Facilitate data transfer between layers without exposing internal entities.
5. **AOP Aspects:** Modularize logging, security, and fraud detection.
6. **Strategy and Observer Patterns:** Implement flexible fraud detection strategies and notification mechanisms.
7. **ML Integration:** Uses serialized ML models for real-time fraud detection.

## Setup and Installation

### Prerequisites

- **Java 17** or higher
- **Maven**
- **Git**
- **PostgreSQL/MySQL** (if switching from H2)
- **Docker & Kubernetes** (optional, for future enhancements)

### Steps

1. **Clone the Repository:**

   ```bash
   git clone https://github.com/felixojiambo/frauddetection.git
   cd frauddetection
   ```

2. **Configure the Database:**

   - **Using H2 (Default):** No additional setup required.
   - **Switching to PostgreSQL/MySQL:**
     - Install PostgreSQL/MySQL.
     - Create a database named `frauddetectiondb`.
     - Update `src/main/resources/application.properties` with your database credentials.

     ```properties
     spring.datasource.url=jdbc:postgresql://localhost:5432/frauddetectiondb
     spring.datasource.username=your_db_username
     spring.datasource.password=your_db_password
     spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
     spring.jpa.hibernate.ddl-auto=update
     spring.h2.console.enabled=false
     ```

3. **Build the Project:**

   ```bash
   mvn clean install
   ```

4. **Train and Serialize ML Models:**

   Ensure your dataset (`path_to_dataset.csv`) is properly preprocessed and placed in the project's root directory or a specified path.

   - **Decision Tree Model:**

     ```bash
     mvn exec:java -Dexec.mainClass="com.example.fraud.model.DecisionTreeModelTrainer"
     ```

   - **Isolation Forest Model:**

     ```bash
     mvn exec:java -Dexec.mainClass="com.example.fraud.model.IsolationForestModelTrainer"
     ```

   **Note:** If you choose to use K-Means clustering, ensure the `KMeansFraudDetectionStrategy` is correctly implemented and train the model accordingly.

5. **Place Serialized Models:**

   Move the serialized model files (`decision_tree_model.ser`, `isolation_forest_model.ser`) to the `src/main/resources/models/` directory.

   ```bash
   mkdir -p src/main/resources/models
   mv decision_tree_model.ser src/main/resources/models/
   mv isolation_forest_model.ser src/main/resources/models/
   ```

6. **Run the Application:**

   ```bash
   mvn spring-boot:run
   ```

## Running the Application

Once the application is running, you can interact with it using tools like **Postman** or **cURL**.

### Default Admin User

On startup, a default admin user is created:

- **Username:** `admin`
- **Email:** `admin@fraudsystem.com`
- **Role:** `ADMIN`

## API Documentation

Interactive API documentation is available via Swagger. Access it at:

```
http://localhost:8080/swagger-ui/
```

### User Management

- **Register a New User**
  - **Endpoint:** `POST /api/users`
  - **Body:**
    ```json
    {
      "username": "jane_doe",
      "email": "jane@example.com"
    }
    ```
  - **Response:** `UserDTO`

- **Retrieve a User by ID**
  - **Endpoint:** `GET /api/users/{id}`
  - **Response:** `UserDTO`

- **Retrieve All Users**
  - **Endpoint:** `GET /api/users`
  - **Response:** `List<UserDTO>`

- **Update a User**
  - **Endpoint:** `PUT /api/users/{id}`
  - **Body:**
    ```json
    {
      "username": "jane_doe_updated",
      "email": "jane_updated@example.com",
      "role": "ADMIN"
    }
    ```
  - **Response:** `UserDTO`

- **Delete a User**
  - **Endpoint:** `DELETE /api/users/{id}`
  - **Response:** `204 No Content`

### Transaction Management

- **Process a New Transaction**
  - **Endpoint:** `POST /api/transactions`
  - **Body:**
    ```json
    {
      "type": "DEPOSIT",
      "amount": 7500,
      "timestamp": "2024-04-27T12:00:00",
      "userId": 1
    }
    ```
  - **Response:** `TransactionDTO`

- **Retrieve a Transaction by ID**
  - **Endpoint:** `GET /api/transactions/{id}`
  - **Response:** `TransactionDTO`

- **Retrieve All Transactions**
  - **Endpoint:** `GET /api/transactions`
  - **Response:** `List<TransactionDTO>`

- **Update Transaction Status**
  - **Endpoint:** `PUT /api/transactions/{id}/status?status=COMPLETED`
  - **Response:** `TransactionDTO`

- **Delete a Transaction**
  - **Endpoint:** `DELETE /api/transactions/{id}`
  - **Response:** `204 No Content`

## Machine Learning Integration

### Models Used

1. **Decision Tree Classifier**
   - **Purpose:** Classifies transactions as fraudulent or not based on input features.
   - **Serialized Model:** `decision_tree_model.ser`

2. **Isolation Forest**
   - **Purpose:** Detects anomalies in transaction data.
   - **Serialized Model:** `isolation_forest_model.ser`

### Training and Serialization

1. **Prepare Dataset:**
   - Ensure the dataset is cleaned, encoded, and normalized.
   - Include necessary features like transaction amount, type, timestamp, user behavior, etc.

2. **Train Models:**
   - Use the provided trainer classes (`DecisionTreeModelTrainer`, `IsolationForestModelTrainer`) to train models.

3. **Serialize Models:**
   - The trainer classes will generate serialized model files which are loaded by the application at startup.

### Model Usage

- **Fraud Detection Strategies:**
  - **Rule-Based Strategies:**
    - **Amount-Based:** Flags transactions exceeding a predefined threshold.
    - **Frequency-Based:** Flags users with an unusually high number of transactions in a short timeframe.
  - **ML-Based Strategies:**
    - **Decision Tree:** Predicts fraud based on learned patterns.
    - **Isolation Forest:** Identifies anomalous transactions.

- **FraudDetectionContext:**
  - Aggregates results from all fraud detection strategies.
  - Flags transactions as fraudulent if any strategy detects fraud.

## Design Patterns and Principles

### GoF Design Patterns

1. **Strategy Pattern:**
   - **Usage:** Defines various fraud detection algorithms as interchangeable strategies.
   - **Benefit:** Easily extendable to add new detection methods without modifying existing code.

2. **Observer Pattern:**
   - **Usage:** Notifies multiple listeners (e.g., Email, SMS) when a transaction is flagged as fraudulent.
   - **Benefit:** Decouples fraud detection from notification mechanisms.

3. **Singleton Pattern:**
   - **Usage:** `FraudDetectionContext` is managed as a singleton by Spring.
   - **Benefit:** Ensures a single instance of fraud detection logic is used throughout the application.

4. **Factory Method Pattern:**
   - **Usage:** Implicitly used through MapStruct’s mapper generation and Spring's dependency injection to create instances of mappers and strategies.
   - **Benefit:** Provides a way to instantiate components without coupling the code to concrete classes.

### SOLID Design Principles

1. **Single Responsibility:** Each class and service has one responsibility (e.g., `UserService` handles user operations).
2. **Open-Closed:** Services and strategies are open for extension but closed for modification.
3. **Liskov Substitution:** Service implementations can be substituted wherever their interfaces are expected.
4. **Interface Segregation:** Interfaces are kept lean with only necessary methods.
5. **Dependency Inversion:** High-level modules depend on abstractions (interfaces), not concretions.

### Aspect-Oriented Programming (AOP)

- **Logging Aspect:** Logs method executions in service layers.
- **Security Aspect:** Checks user roles before processing transactions.
- **Fraud Detection Aspect:** Flags high-value or suspicious transactions and notifies listeners.

## Logging and Security

### Logging

Implemented using **Spring AOP**:

- **LoggingAspect:** Logs method executions in service layers using `@Before`, `@AfterReturning`, `@AfterThrowing`, and `@Around` advices.

### Security

- **SecurityAspect:** Checks user roles before processing transactions. Initially simplified by assuming a default user, but recommended to integrate **Spring Security** for robust authentication and authorization.

**Future Enhancement:**

- Replace the simplified `SecurityAspect` with **Spring Security** for comprehensive security management, including JWT-based authentication.

## Future Enhancements

1. **Advanced Feature Engineering:**
   - Incorporate more features like user behavior, device information, geolocation, etc., to improve model accuracy.

2. **Model Deployment and Monitoring:**
   - Implement model versioning and monitoring to track performance over time.
   - Automate model retraining with new data.

3. **Real-Time Processing:**
   - Integrate with streaming platforms like **Apache Kafka** for real-time transaction processing and fraud detection.

4. **Scalability Enhancements:**
   - Deploy the application using **Docker** and orchestrate with **Kubernetes** for scalable deployments.

5. **User Interface:**
   - Develop an administrative dashboard using front-end frameworks (e.g., React, Angular) to visualize transactions and fraud alerts.

6. **Integration with External Systems:**
   - Connect with payment gateways, user management systems, and notification services for a comprehensive ecosystem.

7. **Compliance and Reporting:**
   - Ensure the system complies with financial regulations (e.g., AML, KYC) and generate necessary reports.

8. **Security Enhancements:**
   - Integrate **Spring Security** for robust authentication and authorization mechanisms.
   - Implement JWT-based authentication to secure API endpoints and manage user sessions.

9. **Persisting Data with a Production-Ready Database:**
   - Transition from an in-memory H2 database to a persistent database like **PostgreSQL** or **MySQL** for production readiness.

10. **API Documentation:**
    - Integrate Swagger/OpenAPI for interactive API documentation.

11. **Continuous Integration/Continuous Deployment (CI/CD):**
    - Set up CI/CD pipelines using tools like **GitHub Actions**, **Jenkins**, or **Travis CI** to automate testing and deployment processes.

12. **Error Handling Enhancements:**
    - Enhance global exception handling with more detailed error responses and logging mechanisms.

13. **Performance Optimization:**
    - Optimize database queries, caching strategies, and ML model inference for better performance.

14. **Monitoring and Logging Enhancements:**
    - Integrate monitoring tools like **Prometheus** and logging tools like **ELK Stack** for comprehensive system monitoring and logging.

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request for any enhancements or bug fixes.

1. **Fork the Repository**
2. **Create a Feature Branch**
   ```bash
   git checkout -b feature/YourFeature
   ```
3. **Commit Your Changes**
   ```bash
   git commit -m "Add Your Feature"
   ```
4. **Push to the Branch**
   ```bash
   git push origin feature/YourFeature
   ```
5. **Open a Pull Request**

## License

This project is licensed under the [MIT License](LICENSE).
