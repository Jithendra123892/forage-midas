# Midas - JPMC Advanced Software Engineering Forage

This project is a fork of the JPMC Advanced Software Engineering Forage program. It has been extended to include a RESTful API for balance inquiries and a Kafka-based transaction processing system with an external incentive service integration.

## My Contributions

*   **RESTful Balance API:** Implemented a REST endpoint (`/balance`) to retrieve the current balance for a given user.
*   **Kafka-based Transaction Processing:** Created a Kafka listener to process transactions from a dedicated topic. This listener updates user balances, records the transaction, and interacts with an external incentive service to apply rewards.
*   **Microservices Integration:** Designed the transaction listener to communicate with an external incentive service via a RESTful API, demonstrating a practical application of microservices architecture.
*   **Database Integration:** Utilized Spring Data JPA to interact with an H2 in-memory database for storing user and transaction data.
*   **Unit and Integration Testing:** Wrote comprehensive tests for the new features, including integration tests for the Kafka listener and REST controller.

## Technologies Used

*   **Java 17**
*   **Spring Boot 3.2.5**
*   **Spring Data JPA**
*   **Spring Kafka**
*   **H2 Database**
*   **Maven**
*   **Testcontainers**

## Architecture

The application follows a microservices-based architecture, with a core service responsible for managing user balances and processing transactions. It communicates with an external incentive service via a RESTful API and consumes transaction events from a Kafka topic.

## Getting Started

### Prerequisites

*   Java 17
*   Maven
*   Docker (for running the Kafka and incentive services)

### Building and Running the Application

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/Jithendra123892/forage-midas.git
    cd forage-midas
    ```

2.  **Build the application:**
    ```bash
    mvn clean install
    ```

3.  **Run the application:**
    ```bash
    java -jar target/midas-core-1.0.0.jar
    ```

### Running the Tests

```bash
mvn test
```

## API Endpoints

*   `GET /balance?userId={userId}`: Retrieves the current balance for the specified user.

## Kafka Integration

The application listens for transaction events on the `transactions` topic. Each event should be a JSON object with the following structure:

```json
{
  "senderId": 1,
  "receiverId": 2,
  "amount": 100.00
}
```