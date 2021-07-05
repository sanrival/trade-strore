# trade-store

API is to fetch a/all trade or add a trade to the trade store

Repo master build status : green

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

> Prerequisite

* Maven
* Java 8

> Steps

1. Clone the repository.
2. Go inside the project directory using command line.
3. Install mvn and execute mvn clean spring-boot:run. Application port is defaulted 8080.
4. Test the endpoints using swagger http://localhost:8080/swagger-ui.html#/

* GET http://localhost:8080/api/v1/trade/{id}  - Sample input Ta
* GET http://localhost:8080/api/v1/trades - Sample input * {
  "id": "T1",
  "version": 1,
  "counterpartyId": "CP-1",
  "bookId": "B1",
  "maturity": "2021-12-02",
  "created": "2021-07-02",
  "expired": false }

* PUT http://localhost:8080/api/v1/trade/{trade}

or you can use any REST API test tools such as Postman or SoapUI.

a Sample trade with ID "T1" loaded at application start up.

6) Authorize yourself as the api end points are secure. Two users are inbuilt in the application for test.

* Username- kamal@db.com password valecha
* Username- sandeep@db.com password nare

7) a scheduled cron job runs at midnight every date to update which expired.

## Task Description

There will be three APIs:

* Get Trade (/trade/{id}) API should return 404 status with below details when Trade with specified {id} doesn’t exist in database:
  {
  "message": "Cannot find trade with given id",
  "status": 404 } else shows the trade details with Https status 200


* Get Trade (/trades/) API should return all trades in the system
* PUT Trade (/trade/{trade}) should create or update a trade with input trade details. It has addition validations

  * During transmission if the lower version is being received by the store, it should reject the trade and throw an exception. But Caller should not receive the exception, but rather a meaningful
    response as given below with Status as 400. If the version is same, it should override the existing record. {
    "message": "Invalid Request. Trade Version is lower than current.",
    "status": 400 }

  * Store should not allow the trade which has less maturity date then today date. It should return Response with 400 status as below:
    {
    "message": "Invalid Request. Trade Maturity Date is earlier than current.",
    "status": 400 }

  * Endpoint should return response code 200 when the Trade is successfully updated. The response body should be the same as the request body

* Include API Documentation using Swagger OpenAPI or any API documentation mechanism you may have used!

* Include Basic Authentication for the API. Only authenticated users should be able to access the API.

* Store should also provide implementation to automatically update the expiry flag for the Trades that cross the maturity date

## Development and Architecture Aspects

> Technology used

* Java 8
* Spring boot
* Maven
* Swagger for documentation
* Spring Security

> Following Development principles are used

<I>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Clean Code, KISS, DRY, SOLID, Event Driven and Oops principle.</I>

## Test Cases

Unit Test cases and Integration test cases were written. Please use below command to run test cases

```
mvn clean test
```

Find the test coverage report under project working directory - **target/site/jacoco/index.html**

Test coverage is above 90% . The left overs are few entity setters and main spring boot application class.

## Assumptions

Treated Trade Id to be unique hence allowing only one version of a trade in the system.It doesnt matched requirement db pic but logically looks ok to me what I implemented.

Put request accepts entire trade instead of just Trade id as given in requirement.

## Comments

I loved the challenge though it was hard to get all requirements from scratch in minimal time. It definetly needs more polish. 
