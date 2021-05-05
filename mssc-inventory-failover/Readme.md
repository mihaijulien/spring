## Inventory Failover Service
- **You need to run Eureka Server** with ("local-discovery") Profile set so that the Failover Service can be found (GET requested by the other Microservices)
    at **http://localhost:8083/inventory-failover**
- Access Eureka Console to verify @ http://localhost:8761/

- The idea with this Inventory Failover Service is if our main Beer Inventory Service fails or is unavailable, this Failover service
    will kick in and at least provide inventory values for new Beer Inventory Requests. It may not be able to fulfill any Order Requests,
    but can at least prevent some downstream errors of failed inventory requests

### Default Port Mappings - For Single Host
| Service Name | Port | 
| --------| -----|
| [Brewery Beer Service](https://github.com/mihaijulien/spring-microservices/tree/main/mssc-brewery-service) | 8080 |
| [Brewery Beer Order Service](https://github.com/springframeworkguru/mssc-beer-order-service) | 8081 |
| [Brewery Beer Inventory Service](https://github.com/springframeworkguru/mssc-beer-inventory-service) | 8082 |
| [Inventory Failover Service](https://github.com/mihaijulien/spring-microservices/tree/main/mssc-inventory-failover) | 8083 |

- ####  Circuit Breaker Pattern

    - The Circuit Breaker Pattern is a simple concept which allows you to recover from errors
    - If a mission critical service is unavailable or has unrecoverable errors, via the Circuit Breaker Pattern you can specify an alternative action
    - For example, we wish to always have inventory for potential orders
        - If the inventory service is down, we can provide a fallback service to respond with inventory

- ####  Spring Cloud Circuit Breaker
    - Spring Cloud Circuit Breaker is a project which provides abstractions across several circuit breaker implementations
        - Thus your source code is not tied t a specific implementation (like SLF4J)
    - **Supported:**
        - Netflix Hystrix
        - Resilience4J
        - Sentinel
        - Spring Retry
        
- ####  Spring Cloud Gateway Circuit Breakers
    - Spring Cloud Gateway supports Netflix Hystrix and Resilience4J
    - **Gateway Filters** are used on top of the Spring Cloud Circuit Breaker APIs
    - Netflix has placed Hystrix into maintenance mode, Spring suggests to use Resilience4J
