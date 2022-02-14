# Ecommerce shop - backend
This backend repository is part of my ecommerce shop project. In this project I'm creating a REST API in spring. I use a h2 database for my shop data.

## Table of contents
* [Description](#description)
* [Technologies](#technologies)
* [Usage](#usage)
* [Endpoints](#endpoints)

## Description
I am creating online shop, based on angular and spring boot. (Work in progess)

## Technologies
* Java 17+
* Spring boot 2
* h2 database
* REST
* Maven
* Lombok

## Usage
You need JDK 17 or higher(previous versions are not tested).  

## Endpoints
### Images
* #### GET /api/v1/image/{filename}
     return an image with given filename
### Product
* #### GET /api/v1/product/{id}
  return product with id
* #### GET /api/v1/products?name=""&category=""
  return list of products with given name and category