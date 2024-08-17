# CSCI318-GroupProj

## Group Members
* Brayden Carswell
* Tomazin Efira
* Matthew Page
* Arnav Singh

## Dependencies / Versions
* Spring Web
* Spring Data JPA
* H2 Database
* Java 21
* SprintBoot 3.3.2

## Sample REST Requests
### Customer
* Create customer
> curl -X POST http://localhost:8080/customer \
> -H "Content-Type: application/json" \
> -d '{
>    "name": "Peter Griffin",
>    "address": "31 Spooner Street",
>    "email": "peterguy@quahog.com",
>    "password": "password"
>  }'
* Get all customers
> curl -X GET http://localhost:8080/customer
> 
### Product
* Creates a product
> curl -X POST http://localhost:8080/products \
> -H "Content-Type: application/json" \
> -d '{
>  "name": "Pet Rock",
>  "description": "The perfect pet",
>  "price": 3.75
> }'

* Get all products
> curl -X GET http://localhost:8080/products

## Setup
