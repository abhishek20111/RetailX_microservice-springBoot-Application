# 🛍️ Retail Management Platform – Microservices Architecture

This project is a full-stack, microservices-based Retail Management System designed to manage various aspects like product listings, inventory, orders, customer data, and real-time notifications. The system is built with **Spring Boot** microservices and an **Angular** frontend, and follows modern DevOps practices using Kafka, Docker, Kubernetes, and observability tools.

---

## 🧩 Microservices Overview

### 1. **Product Service**
- Manages product details like name, description, price, and stock.
- Technology: Spring Boot, MongoDB

### 2. **Inventory Service**
- Handles real-time stock tracking and availability.
- Technology: Spring Boot, MySQL

### 3. **Order Service**
- Manages customer orders, order status, and order history.
- Communicates with Product and Inventory services.
- Technology: Spring Boot, MySQL

### 4. **Notification Service**
- Sends out real-time order, inventory, and system notifications.
- Consumes events from Kafka topics.
- Technology: Spring Boot, Kafka

### 5. **API Gateway**
- Acts as a unified entry point to all microservices.
- Handles routing, rate limiting (via Resilience4j), and authentication (via Keycloak).
- Technology: Spring Cloud Gateway

---

## 🌐 Frontend – Angular App

- Developed with **Angular**
- Provides an interactive UI for managing products, orders, inventory, and customer details
- Communicates with API Gateway for backend interactions
- Styled using Angular Material / Tailwind (if applicable)

---

## 🛠️ Technologies Used

- **Backend:** Spring Boot, Spring Cloud Gateway, Kafka, Resilience4j
- **Frontend:** Angular
- **Databases:** MySQL, MongoDB
- **Authentication:** Keycloak
- **Event Streaming:** Kafka + Zookeeper
- **Containerization:** Docker
- **Orchestration:** Kubernetes
- **Observability:** Prometheus, Grafana, Loki

---

## 🔧 How to Run the Project

### 🐘 Clone the Repository
```bash
git clone https://github.com/your-username/retail-microservices-platform.git
cd retail-microservices-platform
