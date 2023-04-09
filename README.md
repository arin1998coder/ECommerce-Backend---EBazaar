# ECommerce-Backend---EBazaar

This ecommerce backend project is an online shopping application like Amazon, where users can register, sellers can add products of different category, quantity, and price, and customers can place orders using their preferred choice of card. This project is built using Java, SpringBoot, JPA, Hibernate, and MySQL for the database.

# Features and Functionalities
The following are the features and functionalities of this ecommerce backend project:

User registration

Seller registration

Adding products by sellers

Placing orders by customers

Viewing products by category

Viewing the top 5 cheapest products

Viewing the top 5 expensive products

Viewing an item by the user

# Getting Started

To run and test this ecommerce backend project, follow these steps:

Clone this repository to your local machine.

git clone https://github.com/<your_username>/ecommerce-backend.git

Install the required dependencies by running the following command in the project directory:

mvn clean install

Configure your database settings in the application.properties file located in src/main/resources. 
Update the values of spring.datasource.url, spring.datasource.username, and spring.datasource.password based on your local MySQL database configuration.

Run the project by running the following command:

mvn spring-boot:run

You can now access the project using a web browser or a REST client tool like Postman. The project runs on port 8080 by default, so you can access the endpoints by appending the endpoint path to http://localhost:8080.

# Additional Information:

This ecommerce backend project is still a work in progress. We are continuously working on adding new features and improving existing functionalities. If you encounter any issues or have any suggestions for improvement, please feel free to open an issue or submit a pull request.
