# Ecommerce shop - backend
This backend repository is part of my ecommerce shop project. In this module, I created a 
REST API with Spring boot. I use a h2 as my database.

Preview: http://ecommerce.api.bartoszsredzinski.pl/swagger-ui/#/

## Table of contents
* [Description](#description)
* [Preview](#preview)
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

Whole services is secured by Spring Security. To authorization, I'm using JWT. To get all 
possibility of service user can create an account. After completing the form, system send an 
email with token to the given user's email. When user clicked the link with token, system enabled
the user's account. 

Service is documented by swagger.

## Preview
http://ecommerce.api.bartoszsredzinski.pl/swagger-ui/#/

## Technologies
* Java 17
* Spring boot
* Spring security
* swagger
* Lombok
* iText

## Screenshots
![swagger doc](/screenshots/back_1.png?raw=true)\
![swagger doc](/screenshots/back_2.png?raw=true)\
![email](/screenshots/back_3.png?raw=true)\
![pdf](/screenshots/back_4.png?raw=true)\
