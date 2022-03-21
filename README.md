# Ecommerce shop - backend
This backend repository is part of my ecommerce shop project. In this module, I created a REST API with Spring boot. I use an h2 as my database.

Preview: http://ecommerce.api.bartoszsredzinski.pl/swagger-ui/#/

## Table of contents
* [Description](#description)
* [Technologies](#technologies)
* [Screenshots](#screenshots)

## Description
The main idea of this project is a creation simple ecommerce shop with computers parts.\
As a user, we can do some actions like:
* search products,
* filter products,
* add products to the shopping cart,
* delete products from cart,
* modify our user details,
* buy products(generating invoice as PDF),

Admin has some more action for management:
* add a new product,
* manage users,
* edit products,
* add product's stock

Whole services is secured by Spring Security. To authorization, I'm using JWT. To get all possibility of service user can create an account. After completing the form, system send an email with token to the given user's email. When user clicked the link with token, system enabled the user's account. 

Service is documented by swagger.

## Technologies
* Java 17
* Spring boot 2
* Spring security
* h2 database
* REST
* Maven
* Lombok
* swagger
* itext

## Screenshots
![swagger doc](/screenshots/back_1.png?raw=true)
![swagger doc](/screenshots/back_2.png?raw=true)
![swagger doc](/screenshots/back_3.png?raw=true)
![swagger doc](/screenshots/back_4.png?raw=true)
![swagger doc](/screenshots/back_5.png?raw=true)
![swagger doc](/screenshots/back_6.png?raw=true)
![swagger doc](/screenshots/back_7.png?raw=true)
![swagger doc](/screenshots/back_8.png?raw=true)