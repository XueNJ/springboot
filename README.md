# springboot
Project Overview
This project is a shopping system for a mall, providing features such as shopping functionality, member management, and backend product management. It implements both frontend and backend management functionalities.

System Requirements
Development Tool: Spring Tool Suite 4
Java Version: Java SE 1.8
Spring Boot Version: 2.3.10.RELEASE

Environment Configuration
The following is the environment configuration for the Spring Boot project, including port configuration and database connection:

yaml
Copy code
server:
  port: 8088
  servlet:
    context-path: /training
  tomcat:
    threads:
      max: 200
    uri-encoding: UTF-8
    basedir: /data/logs/training/access_log
    accesslog:
      enabled: true
      pattern: '{"i":"%{X-Forwarded-For}i","a":"%a","A":"%A","t":"%{yyyy-MM-dd HH:mm:ss.SSS}t","m":"%m","U":"%U","s":%s,"b":%b,"D":%D,"I":"%I"}'
      suffix: .log

management:
  endpoint:
    shutdown:
      enabled: true
  endpoints:
    web:
      exposure:
        include: shutdown

spring:
  profiles:
    active: local
  jpa:
    show-sql: true
    hibernate:
      ddl-auto: update
    properties:
      hibernate:
        format_sql: true

springboot:
  datasource:
    oracle:
      jdbc-url: jdbc:oracle:thin:@localhost:1521:xe
      username: LOCAL
      password: root
      driverClassName: oracle.jdbc.driver.OracleDriver
      maximumPoolSize: 2
      connectionTimeout: 30000
    mysql:
      jdbc-url: jdbc:mysql://localhost:3306/local_db
      username: root
      password: root
      driverClassName: com.mysql.cj.jdbc.Driver
      maximumPoolSize: 2
      connectionTimeout: 30000
Architecture Design
The project's architecture design includes directory structure and the roles and responsibilities of key components. Please provide relevant information.

Features and APIs
The following are detailed descriptions of the main features and APIs provided by the project, including their purposes, input/output formats, etc.:

Frontend Controller
POST /training/FrontendController/addCartGoods: Add goods to the shopping cart.
POST /training/FrontendController/clearCartGoods: Clear the shopping cart.
POST /training/FrontendController/login: Log in to the system.
POST /training/FrontendController/loginCheck: Log in to the system (check session).
POST /training/FrontendController/logout: Log out of the system.
POST /training/FrontendController/memberModify: Add or update member information.
POST /training/FrontendController/memberQuery: Query member information.
POST /training/FrontendController/paymentGoods: Perform payment using JPA DML operation.
GET /training/FrontendController/queryCartGoods: Query the shopping cart goods.
POST /training/FrontendController/queryGoods: Query goods using JPA Criteria.
POST /training/FrontendController/queryOrder: Query orders using JPA Criteria.
Backend Controller
POST /training/BackendController/createGoods: Create or update goods using JPA DML operation.
DELETE /training/BackendController/deleteGoods: Delete goods using JPA DML operation.
POST /training/BackendController/queryGoods: Query the list of goods using JPA Criteria.
POST /training/BackendController/queryOrder: Query the list of orders using JPA Criteria.
GET /training/BackendController/queryOrder_homework: (Homework) Query the list of orders using JPA Criteria.
PUT /training/BackendController/updateGoods: Update goods using JPA DML operation.
Other Technical Integrations
The project integrates the following technologies:

Database: Oracle and MySQL are used as database systems.
Logging: Log4j2 is used for logging.
Swagger UI: Springfox is used to generate Swagger documentation.
Testing and Deployment
To test and deploy the project, follow these steps:

Define a testing strategy including unit testing, integration testing, and system testing.
Set up the required testing environment.
Configure the deployment environment to ensure all necessary dependencies and configurations are properly set.
Perform the deployment steps based on the specific requirements of the deployment environment.
