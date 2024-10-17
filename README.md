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
* Apache Kafka
* 

## Setup and Run
1. Clone the repository
2. Download and install Java 21 and Apache Kafka
3. Launch Kafka server and Zookeeper
4. Launch Eureka Server and API Gateway
5. Launch all the microservices
6. Navigate to the Eureka Server to verify the microservices are running
7. Communicate with the API Gateway to access the microservices

## Endpoints and Ports
### For api access
* Customers - localhost:8080/customer/*
* Products - localhost:8080/product/*
* Carts - localhost:8080/cart/*
### Actual port locations and development tools
* Customers - localhost:8081/customer/*
* Products - localhost:8082/product/*
* Carts - localhost:8083/cart/*
* Orders localhost:8085/orders/*
* API Gateway - localhost:8080/* (API gateway to allow access and management of all endpoints from single location)
* Eureka Server - localhost:8761 (Discovery server for microservice communication)
