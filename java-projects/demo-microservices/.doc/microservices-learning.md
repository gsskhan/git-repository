# Java Spring Boot Microservices Learning Project

## 📌 Objective
Design and implement a **microservices-based system in Java using Spring Boot** to learn key distributed system concepts:
- API Gateway
- Routing
- Service Discovery
- Load Balancing
- Fault Tolerance
- Inter-service Communication

---

## 🧱 Project Structure

Use **Maven** with a **parent POM** and all services as **submodules**.
demo-microservices/
│── pom.xml (parent)
│
├── api-gateway/
├── service-registry/
├── config-server/
├── user-service/
├── order-service/
├── product-service/
├── notification-service/
└── common-lib/


---

## ⚙️ Core Components

### 1. Service Registry
- Implement a service registry (Eureka or equivalent).
- All services must register themselves.

---

### 2. API Gateway
- Central entry point for all requests.
- Responsibilities:
  - Route requests to services
  - Apply filters (logging, auth stub)
  - Load balancing

---

### 3. Config Server
- Centralized configuration management.
- Externalize all service configurations.

---

### 4. Microservices

#### User Service
- Manage user data
- CRUD APIs

#### Product Service
- Manage product catalog
- CRUD APIs

#### Order Service
- Create and manage orders
- Fetch data from User & Product services

#### Notification Service (Optional)
- Simulate sending notifications

---

## 🔁 Inter-Service Communication
- Order Service must:
  - Call User Service
  - Call Product Service
- Use REST clients (Feign or WebClient)

---

## 🛡️ Fault Tolerance
Implement:
- Circuit Breaker
- Retry Mechanism
- Fallback Methods

---

## ⚖️ Load Balancing
- Client-side load balancing across instances

---

## 🔍 Observability (Optional)
- Distributed tracing
- Request correlation
- Logging across services

---

## 🔐 Security (Basic)
- API Gateway should include:
  - Simple API key validation OR
  - Basic authentication filter

---

## 📦 Common Library (`common-lib`)
Shared module for:
- DTOs
- Common exceptions
- Utility classes

---

## 🧪 Testing
- Unit Tests (JUnit, Mockito)
- Basic Integration Tests

---

## 🚀 Learning Outcomes
By completing this project, you will understand:
- Microservice architecture
- Service discovery
- API Gateway patterns
- Fault tolerance strategies
- Inter-service communication
- Centralized configuration

---

## 📝 Bonus Enhancements
- Dockerize all services
- Add message broker (Kafka/RabbitMQ)
- Use Docker Compose for orchestration
- Add monitoring dashboards

---

## ✅ Deliverables
- Fully working multi-module Maven project
- Each service independently runnable
- API Gateway routing correctly
- Fault tolerance mechanisms implemented