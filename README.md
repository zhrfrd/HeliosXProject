# HeliosX consultation project

This consultation service allows the user to use a simple REST API to:
- Get a questionnaire to check the eligibility for a medicine prescription.
- Respond to each question and submit the answer.
- Receive a positive or negative response from the API based on the answers.

## Requirements
- Java 17
- Spring Boot (Spring Web)
- Maven

## How to run

To run the app locally type in your terminal:

```
mvn spring-boot:run
```

The application runs on http://localhost:8080 by default. Change the port number if it's already being used.

## List of API endpoints

- GET /heliosx/consultation/questions
- POST /heliosx/consultation/answers

To see and test all the endpoints you can visit the [link to the Postman collection](https://zhrfrd-9442626.postman.co/workspace/zhrfrd's-Workspace~f01c425e-37ae-41ba-8eb8-d39081ac6f1d/collection/51937494-12d990d3-f58a-48e9-a491-1976d0ba28ae?action=share&source=copy-link&creator=51937494).

## How to get the questionnaire

- Run the app.
- Open Postman and run "GET questions" request.
- The response will be a JSON object like this:

```json
[
  {
    "id": "1",
    "text": "What is your name?",
    "type": "TEXT"
  },
  {
    "id": "2",
    "text": "Are you more than 18 years old.",
    "type": "BOOLEAN"
  },
  {
    "id": "3",
    "text": "Are you allergic to the product?",
    "type": "BOOLEAN"
  },
  {
    "id": "4",
    "text": "How much do you weight?",
    "type": "NUMBER"
  }
]
```

## How to post your answers

- Run the app.
- Open Postman and run "POST answers" request.
- Fill up the Body of the request with a JSON text representing your answers to each question like this:

```json
[
  {
    "questionId": "1",
    "answer": "Farid"
  },
  {
    "questionId": "2",
    "answer": "true"
  },
  {
    "questionId": "3",
    "answer": "false"
  },
  {
    "questionId": "4",
    "answer": "65"
  }
]
```
- It might be necessary to add the Header `Content-Type: application/json`.

The response from the "POST answers" request will look like this if the user passes the validation process:

```json
{
    "reason": "Eligible for prescription",
    "eligible": true
}
```

Or like this if the is a validation error:

```json
{
    "reason": "You must weight more than 60Kg.",
    "eligible": false
}
```

## Validations
- You cannot skip any answer. 
- You must answer all the questions without leaving `questionId` or `answer` fields blank.
- You must answer with the right type (BOOLEAN, TEXT or NUMBER).
- To be eligible for prescription you must submit `true` to `questionId` 2 (Are you more than 18 years old?).
- To be eligible for prescription you must submit `false` to `questionId` 3 (Have you ever had an adverse reaction to the product?).

## Decisions and trade-offs
Given the 2–3 hour time constraint, I focused mainly on  delivering the basic working API based on the requirements. 
However, I tried to make the code as clear as I could, extensible (by separating business logic inside its `ConsultationService`
and the HTTP requests inside `ConsultationControler`) and I tried to support it with some basic unit tests. I've also added support
for returning exception details in the JSON response, allowing API users to clearly see validation errors.

- I didn't use a database but the data is saved in memory.
- I handled some validation errors but this can be extended further.
- The unit tests cover some basic scenarios but the suite can be extended.
- The current design can be improved for better scalability. i.e. `ConsultationService` may become harder to maintain as the project grows.

## Author

Farid Zouheir