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
* SprintBoot 3.3.3
* Eureka Server / Client
* Reactive Gateway


## Endpoints and Ports
### For api access
* Customers - localhost:8080/customer/*
* Products - localhost:8080/product/*
* Carts - localhost:8080/cart/*
### Actual port locations and development tools
* Customers - localhost:8081/customer/*
* Products - localhost:8082/product/*
* Carts - localhost:8083/cart/*
* API Gateway - localhost:8080/* (API gateway to allow access and management of all endpoints from single location)
* Eureka Server - localhost:8761 (Discovery server for microservice communication)