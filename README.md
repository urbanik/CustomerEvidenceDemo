# Customer evidence REST API
REST API application backend for customers, products and orders evidence. Project includes two microservices: Gatewayservice and Userservice.

## Getting started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Built with
```
Maven
```
### Installation
For running application on your local machine follow these steps:
* 1 - Pull project to your local machine
* 2 - Build microservices (parent, userservice, gatewayservice) with maven (clean install) for fresh .wars
* 3 - Build docker images (database, gatwewayservice and userservice) and then docker compose in dockerfiles folder

## Tests
You can test this application via Postman or Swagger. Firstly you have to register new user in users/register endpoint, then you have to login (users/login) to receive JWT access token, which give you authorization to other endpoints like customers, products, orders...
### Postman
Postman request collection is available in the root folder (GatewayService.postman_collection.json)
### Swagger
On localhost:
```
localhost:8080/customerevidence/api/apiee/
```

### Authors
**Lukas Urbanik** Inloopx by Avast
