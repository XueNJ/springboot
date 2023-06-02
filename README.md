Project Overview:
The project is an online shopping platform that includes shopping functionality, membership management, and backend administration for managing products. The goal of the project is to create a comprehensive marketplace where users can browse and purchase products, while administrators can manage the inventory and user accounts.

System Requirements:
The project requires the following system requirements:
- Development Tool: Spring Tool Suite 4
- Java version: Java SE 1.8
- Spring Boot version: 2.3.10.RELEASE

Environment Setup:
To set up and configure the Spring Boot project, follow these steps:
1. Set the server port to 8088 in the application configuration file.
2. Configure the servlet context path to "/training".
3. Set the maximum number of threads for the Tomcat server to 200.
4. Configure the database connection properties for Oracle and MySQL databases.

Architecture Design:
The project follows a layered architecture design, with separate components for frontend and backend functionalities. The directory structure and important components' roles and responsibilities are as follows:
- Frontend Controller: Handles requests and responses related to frontend operations, such as adding items to the shopping cart, user login/logout, and member management.
- Backend Controller: Manages backend operations, including product management and order management.

Functionality and APIs:
The project provides the following main functionalities and APIs:
- Frontend Controller:
  - POST /training/FrontendController/addCartGoods: Adds items to the shopping cart.
  - POST /training/FrontendController/clearCartGoods: Clears the shopping cart.
  - POST /training/FrontendController/login: Performs user login.
  - POST /training/FrontendController/loginCheck: Checks if a user is logged in.
  - POST /training/FrontendController/logout: Performs user logout.
  - POST /training/FrontendController/memberModify: Adds or updates member information.
  - POST /training/FrontendController/memberQuery: Queries member information.
  - POST /training/FrontendController/paymentGoods: Processes the payment and completes the purchase.
  - GET /training/FrontendController/queryCartGoods: Retrieves the items in the shopping cart.
  - POST /training/FrontendController/queryGoods: Queries products using JPA Criteria Queries.
  - POST /training/FrontendController/queryOrder: Queries order history using JPA Criteria Queries.

- Backend Controller:
  - POST /training/BackendController/createGoods: Creates or updates products using JPA DML Operations.
  - DELETE /training/BackendController/deleteGoods: Deletes products using JPA DML Operations.
  - POST /training/BackendController/queryGoods: Queries product list using JPA Criteria Queries.
  - POST /training/BackendController/queryOrder: Queries order list using JPA Criteria Queries.
  - GET /training/BackendController/queryOrder_homework: (Homework) Queries order list using JPA Criteria Queries.

Integration with Other Technologies:
The project integrates with the following technologies:
- Database: Oracle and MySQL databases are used to store and manage data.
- Log4j2: Logging framework for logging application events and messages.
- Swagger UI: API documentation and testing tool.
- H2 Database: In-memory database used for testing and development.

Testing and Deployment:
To test and deploy the project, follow these steps:
1. Configure the database connection properties in the project configuration.
2. Set up the necessary database tables and schema.
3. Run unit tests to ensure the functionality of each component.
4. Build the project using Maven or the Spring Boot Maven plugin.
5. Deploy the built artifact to the desired server or hosting environment.

Note: The provided information is a summary of the project details. It's advisable to refer to the project documentation or consult with the project team for

 more comprehensive and up-to-date information.
