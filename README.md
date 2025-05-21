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

## Deploying to Kubernetes with Kind
Kind (Kubernetes in Docker) is a tool for running local Kubernetes clusters using Docker containers as "nodes". This is an excellent way to test your Kubernetes deployments without the overhead of cloud providers or more complex local setups like Minikube. 

### Understanding Kind
What it is: Kind allows you to spin up a multi-node Kubernetes cluster entirely within Docker containers. Each "node" in your Kind cluster is simply a Docker container running the necessary Kubernetes components (kubelet, kube-proxy, control plane, etc.).
### How it works:
Kind creates one or more Docker containers.
Inside these containers, it bootstraps a minimal Kubernetes cluster.

The kubeconfig file is automatically generated and merged with your default kubeconfig (e.g., ~/.kube/config), allowing kubectl to interact with your Kind cluster.
Networking between containers (nodes) is handled by Docker's internal networking.
Image building and loading: Kind has features to load Docker images directly into the cluster nodes, making development and testing cycles faster for containerized applications.
Requirements for using Kind

#### Docker: You must have Docker installed and running on your system. Kind relies heavily on Docker to create and manage the cluster nodes.
#### kubectl: The Kubernetes command-line tool. While Kind can help set up the cluster, kubectl is essential for interacting with it (deploying applications, checking status, etc.).
#### Kind CLI: The Kind command-line interface itself.
### How to use Kind
Install Kind:

macOS (using Homebrew):
```

brew install kind 
```

### Linux/WSL (using go install for Go users, or download binary):


### If you have Go installed
go install sigs.k8s.io/kind@v0.22.0 
### Use the latest stable version
### Or download from releases:
### For Linux:
```
### curl -Lo ./kind https://kind.sigs.k8s.io/dl/v0.22.0/kind-linux-amd64
### chmod +x ./kind
### mv ./kind /usr/local/bin/kind
```
Windows (using Chocolatey or download binary):
```
choco install kind
```
# Or download from releases:
 https://kind.sigs.k8s.io/docs/user/quick-start/#installation
#### Create a Kind Cluster:

Default single-node cluster:

```
kind create cluster --name retail-platform-cluster
```

This creates a cluster named retail-platform-cluster. If you omit --name, it defaults to kind.

Multi-node cluster (optional, requires a kind-config.yaml):
Create a file named kind-config.yaml:

YAML

# kind-config.yaml
kind: Cluster
apiVersion: kind.x-k8s.io/v1alpha4
nodes:
- role: control-plane
- role: worker
- role: worker
Then create the cluster:

```

kind create cluster --config kind-config.yaml --name 
retail-platform-cluster-multi
```
### Verify Cluster Creation:
```


kubectl cluster-info --context kind-retail-platform-cluster
```
You should see output indicating the Kubernetes control plane is running.

To list your Kind clusters:



kind get clusters
Build Docker Images for your Services:
Before deploying to Kind, your Spring Boot microservices need to be containerized. Ensure you have Dockerfiles for each service.
For each service (e.g., inventory-service):
```
cd inventory-service
docker build -t retail-platform/inventory-service:latest .
cd ..
```
# Repeat for order-service, api-gateway, etc.
```
docker build -t retail-platform/order-service:latest ./order-service
docker build -t retail-platform/api-gateway:latest ./api-gateway
docker build -t retail-platform/notification-service:latest ./notification-service
docker build -t retail-platform/product-service:latest ./product-service
```
Note: Replace retail-platform/service-name with your desired image names.

Load Docker Images into Kind Cluster:
Kind nodes are Docker containers and don't automatically share the Docker daemon's image cache. You need to explicitly load images into the Kind cluster.


```
kind load docker-image retail-platform/inventory-service:latest --name retail-platform-cluster
kind load docker-image retail-platform/order-service:latest --name retail-platform-cluster
kind load docker-image retail-platform/api-gateway:latest --name retail-platform-cluster
kind load docker-image retail-platform/notification-service:latest --name retail-platform-cluster
kind load docker-image retail-platform/product-service:latest --name retail-platform-cluster
```
This pushes the local Docker images into the Kind cluster's internal image registry.

### Apply Kubernetes Manifests:
You will need Kubernetes YAML files (Deployments, Services, Ingress, PersistentVolumes, etc.) for each of your microservices and supporting infrastructure (databases, Kafka, Keycloak). Create a k8s directory in your project root for these files.

Example structure:
```
retail-management-platform/
├── inventory-service/
├── order-service/
├── ...
└── k8s/
    ├── inventory-deployment.yaml
    ├── inventory-service.yaml
    ├── order-deployment.yaml
    ├── order-service.yaml
    ├── api-gateway-deployment.yaml
    ├── api-gateway-service.yaml
    ├── mysql-deployment.yaml
    ├── mysql-service.yaml
    ├── mongodb-deployment.yaml
    ├── mongodb-service.yaml
    ├── kafka-deployment.yaml
    ├── kafka-service.yaml
    ├── zookeeper-deployment.yaml
    ├── zookeeper-service.yaml
    ├── keycloak-deployment.yaml
    ├── keycloak-service.yaml
    ├── ingress.yaml
    └── prometheus-grafana-loki/
        ├── prometheus-configmap.yaml
        ├── prometheus-deployment.yaml
        ├── grafana-deployment.yaml
        ├── loki-deployment.yaml
        └── ...
```
Once your manifests are ready, apply them to the Kind cluster:


```
kubectl apply -f k8s/ --context kind-retail-platform-cluster```
Or apply individually:
```

kubectl apply -f k8s/mysql-deployment.yaml -f k8s/mysql-service.yaml --context kind-retail-platform-cluster```
# ... and so on for all services and dependencies
Check Deployment Status:

```

kubectl get pods -A --context kind-retail-platform-cluster
kubectl get services -A --context kind-retail-platform-cluster
kubectl get deployments -A --context kind-retail-platform-cluster```
### Accessing Services:

Port Forwarding: For local testing, you can use kubectl port-forward to access services running in the cluster.
```

kubectl port-forward service/api-gateway-service 8080:8080 --context kind-retail-platform-cluster```
# Access API Gateway at http://localhost:8080
Ingress: If you've deployed an Ingress controller (like Nginx Ingress Controller, which is often used with Kind) and configured an Ingress resource, you can access your services via the ingress IP/hostname. You might need to adjust your /etc/hosts file or use a local DNS resolver to point to the Kind cluster's ingress.
### Delete the Kind Cluster:
When you're done, clean up the cluster:

```

kind delete cluster --name retail-platform-cluster
```
## Further Information

Refer to the individual service directories for more detailed documentation, API specifications, and configuration options.


