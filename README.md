# Retail Management Platform

This project comprises a suite of microservices designed to manage various aspects of a retail operation, including inventory, orders, API gateway, notifications, and product information.

## Services Overview

This platform is built using a microservices architecture, where each core functionality is separated into independent, deployable services.

* **Inventory Service:**
    * **Description:** Manages the stock levels of products, tracks inventory movements, and provides APIs for querying and updating inventory.
    * **Technology Stack:** Spring Boot, MySQL
    * **Purpose:** Ensures accurate and real-time tracking of product availability.
* **Order Service:**
    * **Description:** Handles the creation, processing, and management of customer orders. This includes order placement, status updates, and integration with inventory and notification services.
    * **Technology Stack:** Spring Boot, MongoDB
    * **Purpose:** Streamlines the order fulfillment process.
* **API Gateway:**
    * **Description:** Acts as a single entry point for all client requests, routing them to the appropriate backend services. It also handles authentication and authorization.
    * **Technology Stack:** Spring Boot, Keycloak
    * **Purpose:** Provides a unified and secure interface for accessing the platform's functionalities.
* **Notification Service:**
    * **Description:** Responsible for sending various types of notifications to users (e.g., order confirmations, shipping updates, low stock alerts).
    * **Technology Stack:** Spring Boot, Kafka
    * **Purpose:** Keeps users informed and facilitates timely communication.
* **Product Service:**
    * **Description:** Manages product information, including details, pricing, and categories.
    * **Technology Stack:** Spring Boot, MySQL
    * **Purpose:** Provides a central repository for product-related data.

## Core Technologies Used

* **Spring Boot:** Framework for building robust and scalable microservices in Java.
* **Angular:** Frontend framework for building the user interface.
* **Kafka & Zookeeper:** Distributed event streaming platform for real-time, decoupled communication between services.
* **Keycloak:** Open-source identity and access management solution for securing the application.
* **Resilience4j:** Fault tolerance library for building resilient and stable services.
* **MySQL & MongoDB:** Databases used for persistent data storage, chosen based on the specific needs of each service.
* **Docker:** Containerization platform for packaging applications and their dependencies.
* **Kubernetes:** Container orchestration system for automating deployment, scaling, and management of containerized applications.
* **Prometheus:** Monitoring system for collecting and storing metrics.
* **Grafana:** Data visualization and dashboarding tool.
* **Loki:** Log aggregation system.

## Getting Started

Follow these steps to clone and run the project:

### Prerequisites

* **Git:** Ensure Git is installed on your system.
* **Java Development Kit (JDK):** Required for building and running the Spring Boot microservices.
* **Maven:** Build automation tool for Spring Boot projects.
* **Node.js and npm (or yarn):** Required for the Angular frontend.
* **Docker:** For containerizing and running services.
* **Docker Compose (Optional but Recommended):** For orchestrating multiple Docker containers locally.
* **Kubernetes Cluster (Optional):** If you intend to deploy and run using Kubernetes.

### Cloning the Repository

1.  Open your terminal or command prompt.
2.  Navigate to the directory where you want to clone the repository.
3.  Run the following command:
    ```bash
    git clone <repository_url>
    cd <repository_name>
    ```
    *(Replace `<repository_url>` with the actual URL of your Git repository and `<repository_name>` with the name of the cloned directory.)*

### Running the Spring Boot Microservices

Each microservice is a separate Spring Boot project. Navigate to the directory of each service to build and run it.

#### Inventory Service

1.  Navigate to the `inventory-service` directory:
    ```bash
    cd inventory-service
    ```
2.  Build the project using Maven:
    ```bash
    mvn clean install
    ```
3.  Run the application:
    ```bash
    mvn spring-boot:run
    ```
    *(Ensure you have MySQL running and configured in `src/main/resources/application.properties`)*

#### Order Service

1.  Navigate to the `order-service` directory:
    ```bash
    cd ../order-service
    ```
2.  Build the project:
    ```bash
    mvn clean install
    ```
3.  Run the application:
    ```bash
    mvn spring-boot:run
    ```
    *(Ensure you have MongoDB running and configured in `src/main/resources/application.properties`)*

#### API Gateway

1.  Navigate to the `api-gateway` directory:
    ```bash
    cd ../api-gateway
    ```
2.  Build the project:
    ```bash
    mvn clean install
    ```
3.  Run the application:
    ```bash
    mvn spring-boot:run
    ```
    *(Ensure Keycloak is running and configured in `src/main/resources/application.properties`)*

#### Notification Service

1.  Navigate to the `notification-service` directory:
    ```bash
    cd ../notification-service
    ```
2.  Build the project:
    ```bash
    mvn clean install
    ```
3.  Run the application:
    ```bash
    mvn spring-boot:run
    ```
    *(Ensure Kafka and Zookeeper are running and configured in `src/main/resources/application.properties`)*

#### Product Service

1.  Navigate to the `product-service` directory:
    ```bash
    cd ../product-service
    ```
2.  Build the project:
    ```bash
    mvn clean install
    ```
3.  Run the application:
    ```bash
    mvn spring-boot:run
    ```
    *(Ensure you have MySQL running and configured in `src/main/resources/application.properties`)*

### Running the Angular Frontend

1.  Navigate to the `angular-frontend` directory:
    ```bash
    cd ../angular-frontend
    ```
2.  Install the project dependencies:
    ```bash
    npm install
    # or
    yarn install
    ```
3.  Start the development server:
    ```bash
    npm start
    # or
    yarn start
    ```
    This will typically run the Angular application on `http://localhost:4200`. *(Ensure the API Gateway is running for the frontend to communicate with the backend services. You might need to configure the API base URL in the Angular application's environment files.)*

### Running with Docker Compose (Recommended for Local Development)

A `docker-compose.yml` file (if provided in the repository root) can be used to orchestrate all the services, including databases, Kafka, Zookeeper, Keycloak, Prometheus, Grafana, and Loki.

1.  Navigate to the root directory of the project.
2.  Ensure you have Docker Compose installed.
3.  Run the following command:
    ```bash
    docker-compose up -d
    ```
    This will build and start all the defined services in detached mode. Refer to the `docker-compose.yml` file for details on the services and their configurations.

### Accessing Observability Tools

* **Prometheus:** Typically accessible at `http://localhost:9090`.
* **Grafana:** Typically accessible at `http://localhost:3000`. You might need to configure data sources to connect to Prometheus and Loki.
* **Loki:** Typically accessible at `http://localhost:3100`.

## Further Information

Refer to the individual service directories for more detailed documentation, API specifications, and configuration options.
