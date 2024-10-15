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

## Setup and Run
1. Clone the repository
2. Launch Kafka server
3. Launch Eureka Server and API Gateway
4. Launch the microservices
5. Navigate to the Eureka Server to verify the microservices are running
6. Communicate with the API Gateway to access the microservices

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