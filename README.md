# Background
This section describes the JayCoin application’s architecture. JayCoin is a simple
implementation of a Blockchain. It's capable of mining a block, validating the block,
and returning the list of blocks (blockchain).

`Note: ` this is a simple application to help understand the concept of block creation,
mining (proof of work), block hashing, and block validating.

# Prerequisite
* Java 11 or Higher
* Docker

# Requirements
## Functional Requirements
* User can get all the blocks in the blockchain
* When a user mines a coin, a block created on top of the previous block
* The blockchain should have an initial Genesis block
* User can validate if the blockchain is valid.


# Overall Architecture

### Overview
Jaycoin exposes REST APIs for seamless interaction, it uses Java (Spring boot) at the 
backend as the language of implementation. This helps create a modern, robust, easy to maintain, 
and reliable system.

This architecture facilitates fast and parallel development, making existing business 
objects reusable. Test data injection via setter methods was made easy because of the 
architecture creation of JavaBeans classes.


## Application
This application comprises controller and service. Every layer has a well-defined role.
It’s extremely important service layer defines an interface that helps other layers interface with it,
as this will make the service much easier to maintain.
For example, the controller layer can only interact with the service layer. This helps
achieve separation of concerns.

### Controller
Exposes APIs to clients. This layer receives the query requests and passes them to the Business Logic layer (Service).

### Business Logic
Also known as the Service layer receives the query requests from the controller, validates them, performs business logic,
and returns the response to the controller layer.


### Get Started With Application
The application is written using Java, and Docker for containerization. To start the application, open the root 
folder of the project on a terminal, then run the command below:


```
% ./mvnw clean install
```
```
% docker-compose -f ./infrastructure/docker/docker-compose01.yml up -d
```


### Access Application
Use `docker ps` on the same terminal to confirm the applications are up. Use a REST client (cURL or POSTMAN) to access the APIs.

| Requirement      | API |  Status Code |
| ----------- | ----------- | ----------- |
| Get Blockchain      | http://127.0.0.1:9090/api/v1/getChain      | 200 |
| Validate Chain   | http://127.0.0.1:9090/api/v1/validateChain        | 200 |
| Mine Block   | http://127.0.0.1:9090/api/v1/mineBlock        | 200 |
