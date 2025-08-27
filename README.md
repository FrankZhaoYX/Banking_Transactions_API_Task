# Banking_Transactions_API_Task
This is an take home assement for BrainRidge Consulting
A RESTful API for managing banking accounts and transactions built with Spring Boot.
##  Architecture

This application follows a **three-layer architecture** pattern:
Controllers: REST API Endpoints (Presentation Layer)
Services: Business Logic (Service Layer)
Repositories: Data Access (Repository Layer)

### Layer Responsibilities
1. **Controller Layer (`/controller`)**
   - Handle HTTP requests and responses
   - HTTP status code management

2. **Service Layer (`/service`)**
   - Transaction management
   - Data transformation 
   - any further functions add here.

3. **Repository Layer (`/repository`)**
   - Data storage and retrieval
   - Data management
   - Data access abstraction