# Banking_Transactions_API_Task
This is an take home assement for BrainRidge Consulting
A RESTful API for managing banking accounts and transactions built with Spring Boot.
##  Architecture

This application follows a **three-layer architecture** pattern:
Controllers: REST API Endpoints (Presentation Layer)
Services: Business Logic (Service Layer)
Repositories: Data Access (Repository Layer)

### 1. Three  Layer Responsibilities
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

### 2. Dependency Injection Implementation

The application uses **Spring's built-in dependency injection** with `@Autowired`:
```java
@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountService accountService;

    @Autowired  // ← Constructor injection (recommended)
    public TransactionService(TransactionRepository transactionRepository, AccountService accountService) {
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
    }
}
```

### 3. DTOs

#### **Request DTOs**
```java
public class CreateAccountRequest {
    private String accountHolderName;
    private String email;
    private BigDecimal initialBalance;
    
    // rest part
}
```

#### **Response DTOs**
```java
public class AccountResponse {
    private String accountId;
    private String accountHolderName;
    private String email;
    private BigDecimal balance;
    private LocalDateTime createdAt;
    
    // rest part
}
```

### 4. input validation
#### Create Account validation
```java
public class CreateAccountRequest {
    @NotBlank(message = "Account holder name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String accountHolderName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Initial balance is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Initial balance must be non-negative")
    @Digits(integer = 10, fraction = 2, message = "Invalid balance format")
    private Double initialBalance;
```

#### Transfer Validation

```java
public class TransferRequest {
    @NotBlank(message = "From account ID is required")
    private String fromAccountId;

    @NotBlank(message = "To account ID is required")
    private String toAccountId;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    @Positive(message = "Amount must be positive")
    private Double amount;
    // rest part goes on
}
```

### 5. Handle errors

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAccountNotFoundException(AccountNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // rest part goes on
}
```

### 6. Store data in memory

``` java

@Repository
public class TransactionRepository {

    private Map<String, Transaction> transactionMap = new ConcurrentHashMap<>();

    public Transaction save(Transaction transaction) {
        transactionMap.put(transaction.getTransactionId(), transaction);
        return transaction;
    }

    public Optional<Transaction> findById(String id) {
        return Optional.ofNullable(transactionMap.get(id));
    }
    // rest part goes on
}
```


### Technology Apply

- **Java 8**
- **Spring Boot 2.7.18**
- **Spring Web** - RESTful web services
- **Maven** - Build tool and dependency management

###  Project Structure

```
src/
├── main/java/com/bank/frank_BrainRidge_interview/
│   ├── controller/           # REST API Controllers
│   │   ├── AccountController.java
│   │   └── TransactionController.java
│   ├── service/              # Business Logic Services
│   │   ├── AccountService.java
│   │   └── TransactionService.java
│   ├── repository/           # Data Access Repositories
│   │   ├── AccountRepository.java
│   │   └── TransactionRepository.java
│   ├── dto/                  # Data Transfer Objects
│   │   ├── CreateAccountRequest.java
│   │   ├── AccountResponse.java
│   │   ├── TransferRequest.java
│   │   └── TransferResponse.java
        └── TransactionResponse.java
│   ├── model/                # Domain Models
│   │   ├── Account.java
│   │   └── Transaction.java
│   ├── exception/            # Custom Exceptions & Error Handling
│   │   ├── GlobalExceptionHandler.java
│   │   ├── AccountNotFoundException.java
│   │   ├── AccountAlreadyExistsException.java
│   │   ├── InsufficientFundsException.java
│   │   └── InvalidTransactionException.java
│   │   └── ValidationErrorResponse.java
│   │   └── ErrorResponse.java
│   └── FrankBrainRidgeInterviewApplication.java

```



### API Endpoints

#### Account Management
| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/v1/accounts` | Create a new account |
| `GET` | `/api/v1/accounts` | Retrieve all accounts |
| `GET` | `/api/v1/accounts/{accountId}` | Retrieve specific account |

#### Transaction Management
| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/v1/transactions/transfer` | Transfer funds between accounts |
| `GET` | `/api/v1/transactions/account/{accountId}` | Get transaction history for account |
| `GET` | `/api/v1/transactions/{transactionId}` | Get specific transaction details |

###  API Usage Examples

#### Create Account
```bash
curl -X POST http://localhost:8080/api/v1/accounts \
  -H "Content-Type: application/json" \
  -d '{
    "accountHolderName": "test_account_1",
    "email": "test_account_1@email.com",
    "initialBalance": 1000.00
  }'
```

**Response:**
```json
{
    "accountId":"ACCEF5A5AD8",
    "accountHolderName":"test_account_1",
    "email":"test_account_1@email.com",
    "balance":1000.0,
    "createdAt":"2025-08-27T23:46:41.57"
}
```

#### Retrieve Accounts

```bash
curl -X GET http://localhost:8080/api/v1/accounts
```

**Response:**
```json
[
    {
        "accountId":"ACC5FB018FF",
        "accountHolderName":"test_account_2",
        "email":"test_account_2@email.com",
        "balance":500.0,
        "createdAt":"2025-08-27T23:50:32.624"
    },
    {
        "accountId":"ACCEF5A5AD8",
        "accountHolderName":"test_account_1",
        "email":"test_account_1@email.com",
        "balance":1000.0,
        "createdAt":"2025-08-27T23:46:41.57"
    }
]
```

#### Transfer Funds
```bash
curl -X POST http://localhost:8080/api/v1/transactions/transfer \
  -H "Content-Type: application/json" \
  -d '{
    "fromAccountId": "ACC5FB018FF",
    "toAccountId": "ACCEF5A5AD8",
    "amount": 500,
    "description": "Payment for services"
  }'
```

**Response:**
```json
{
    "transactionId":"TXN1412C600",
    "fromAccountId":"ACC5FB018FF",
    "toAccountId":"ACCEF5A5AD8",
    "amount":500.0,
    "description":"Payment for services",
    "timestamp":"2025-08-27T23:58:48.112",
    "status":"COMPLETED"
}
```

## Implementation
### Prerequisites
- **Java 8** 
- **Maven 3.6.1+**

1. **Build the application**
```bash
mvn clean compile
```

2. **Start the application**
```bash
mvn spring-boot:run
```


The application will start on `http://localhost:8080`




