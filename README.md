### Spring Boot Microservices with Spring Cloud
[Spring Boot Microservices with Spring Cloud](https://www.udemy.com/spring-boot-microservices-with-spring-cloud-beginner-to-guru/)
##### Default Port Mappings - For Single Host
| Service Name | Port | 
| --------| -----|
| Brewery Beer Service | 8080 |
| [Swagger UI](http://localhost:8080/swagger-ui/) | 8080/swagger-ui/|
| [Brewery Beer Order Service](https://github.com/springframeworkguru/mssc-beer-order-service) | 8081 |
| [Brewery Beer Inventory Service](https://github.com/springframeworkguru/mssc-beer-inventory-service) | 8082 |


**Beer Service** is responsible for generating the Beer objects used in the application and stores that Beer object data in a database. 
**Beer Order Service** and **Beer Inventory** make calls to **Beer Service** to get information about the Beer objects.

Beer object example:
```json5
{
  "beerName": "Mango Bobs",
  "beerStyle": "ALE",
  "id": "0a818933-087d-47f2-ad83-2f986ed087eb",
  "price": "12.95",
  "quantityOnHand": 12,
  "upc": "0631234200036",
  "version": 1
}
```
  
#### [Notes from the course:](#top)
1. [Java Messaging Service (JMS)](#java-messaging-service-jms)
2. [Data Source(MySQL) Connection Pooling](#data-sourcemysql-connection-pooling)
3. [HikariCP with Spring Boot 2.x](#hikaricp-with-spring-boot-2x)
4. [Ehcache](#ehcache)
 

#### [Java Messaging Service (JMS)](#java-messaging-service-jms)
- What is JMS?
    - JMS is a Java API which allows a Java Application to send a message to another application
        - Generally the other application is a Java application - but not always!
    - JMS is a standard Java API which requires an underlying implementation to be provided
        - For example, JPA - where JPA is the API standard, and Hibernate is the implementation
    - JMS is highly scalable and allows you to loosely couple applications using **asynchronous messaging**
- JMS Implementations
    - Amazon SQS
    - Apache ActiveMQ - used in this project
    - JBoss Messaging
    - IBM MQ (closed source / paid)
    - OracleAQ - (closed source / paid)
    - RabbitMQ
    - Many more!

- Why use JMS over REST?
    - JMS is a true messaging service
    - Asynchronous - send it and forget it!
    - Greater throughput - the HTTP protocol is slow comparatively
        - JMS protocols are **VERY** performant
    - Flexibility in message delivery - Deliver to one or many consumers
    - Security - JMS has very robust security
    - Reliability - Can guarantee message delivery
    
- Types of Messaging
    - **Point to Point**
        - Message is queued and deliver to one consumer
        - Can have multiple consumers - but message will be delivered only ***ONCE*** (ie to exactly one consumer)
        - Consumers connect to a queue
        
    - **Publish / Subscribe**
        - Message is delivered to one or more subscribers
        - Subscribers will subscribe to a ***topic***, then receive a copy of all messages sent to the topic
        
- Key Terms
    - **JMS Provider** - JMS Implementation
    - **JMS Client** - Application which sends or receives messages from the JMS Provider
    - **JMS Producer (Publisher)** - JMS Client which sends messages
    - **JMS Consumer (Subscriber)** - JMS Client which receives messages
    - **JMS Message** - the entity of data sent (see below)
    - **JMS Queue** - Queue for point to point messages. Often, not always, FIFO
    - **JMS Topic** - Similiar to a queue - but for publish & subscribe
    
[Top](#top)

#### [Data Source(MySQL) Connection Pooling](#data-sourcemysql-connection-pooling)
- ###### Establishing a Database Connection is an expensive operation
    - Call out to Database Server to get authenticated
    - Database Server needs to authenticate credentials
    - Database Server establishes a connection
    - Database Server establishes a session - ie allocate memory and resources
    
- ###### Datasource Optimizations
    - Prepared Statements: SQL Statements with placeholders for variables
        - Saves server from having to parse and optimize execution plan
        - Huge Cost Savings (performance)
        - Avoid SQL Injection attacks (security)
    - Optimizations within a single datasource connection:
        - Ability to cache prepared statements (may be at the server level too)
        - Use serve side prepared statements
        - Statement Batching (series of INSERTs or series of UPDATEs)
    - Datasource Connection Pooling
        - In between the Database Server and the Client exists a connection pool of existing, established connections waiting for work to do
        - When a Client Request comes in, it grabs a connection, does its work in the Database Server and then releases the connection
        - Spring Boot 1.x used Tomcat
        - Spring Boot 2.x moved to HikariCP
            - HikariCP is very light weight
            - Very high performance!
            - Hikari has a number of configuration options
    - Hacker's Guide to Connection Pool Tuning
        - Every RDMS will accept a max number of connections - each connection has a cost (Server memory, Server CPU, etc.)!
        - If running multiple instances of your microservice (multiple Spring Boot Contexts), keep number of pool connections lower
            - If fewer instances, can go to a higher number of connections per instance
            - Every instance you add, adds 5-10+ pool connections, and each of those connections has to be managed by the BackEnd Server
        - MySQL defaults to a limit of 151 connections
            - Can be adjusted to much higher - depending on the hardware running MySQL
        - Statement caching is good
            - BUT - does consume memory on the server
        - Disabling autocommit can help improve performance
        - **More Connections is ***NOT*** always better!**
        
[Top](#top)
        
#### [HikariCP with Spring Boot 2.x](#hikaricp-with-spring-boot-2x)

- https://github.com/brettwooldridge/HikariCP
- ###### Recommended settings:

```properties
    spring.datasource.hikari.maximum-pool-size=5 (relative to # of instances, server capabilities, etc.)

    spring.datasource.hikari.data-source-properties.cachePrepStmts=true
    spring.datasource.hikari.data-source-properties.prepStmtCacheSize=250
    spring.datasource.hikari.data-source-properties.prepStmtCacheSqlLimit=2048
    spring.datasource.hikari.data-source-properties.useServerPrepStmts=true
    spring.datasource.hikari.data-source-properties.useLocalSessionState=true
    spring.datasource.hikari.data-source-properties.rewriteBatchedStatements=true
    spring.datasource.hikari.data-source-properties.cacheResultSetMetadata=true
    spring.datasource.hikari.data-source-properties.cacheServerConfiguration=true
    spring.datasource.hikari.data-source-properties.elideSetAutoCommits=true
    spring.datasource.hikari.data-source-properties.maintainTimeStats=false
```

- ###### Enable logging for Hikari Connection Pool tuning, config, troubleshooting
     
```properties
    logging.level.org.hibernate.SQL=DEBUG
    logging.level.com.zaxxer.hikari.HikariConfig=DEBUG
    logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
```
        
[Top](#top)
    
##### [Ehcache](#ehcache)

- https://www.baeldung.com/ehcache
- Ehcache utilizes Java's on-heap RAM memory to store cache entries
- this application will use Ehcache - very popular, robust and one of the top Java caching managers
- will add caching layer for listBeers, getBeerById and getBeerByUpc  - Ehcache is a good candidate because the beer information isn't going to change that much
- ###### what it will do?
    - provide fast access to the Beer data while avoiding a call to the database 
    - significantly improves the performance of our getBeer APIs
    - here we set it up to only run when we are NOT getting BeerInventory information (conditional caching)
        - because inventory is dynamic and changes often and quickly
        
- each running instance is going to have its own local cache, so if you have 3 instances running you have 1 in 3 chance of getting a cache response
- there are technologies available where instances can share a cache 
- Ehcache can be configured so that if you do have multiple instances running, it reads from a single cache

[Top](#top)