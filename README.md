# Medservice  API

This is a simple REST API for a medical consultation service. The API serves consultation questions to the frontend and processes user answers to determine eligibility for a prescription.

## Technologies Used

- Java 21
- Spring Boot
- Lombok
- Maven

## How to Run


1. Navigate to the project directory.
2. Run the application using the following command:

   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

3. The application will start on port 8080 | http://localhost:8080 .


## API Endpoints

Get Consultation Questions

- URL: /api/consultation/questions
- Method: GET
- Response: A list of consultation questions. 
 
 
Submit Consultation Answers

- URL: /api/consultation/submit
- Method: POST
- Request Body: A JSON array containing the answers to the consultation questions.
- Response: A JSON object indicating whether the user is eligible for a prescription.

### Accessing Documentation

- **OpenAPI JSON**: The OpenAPI JSON specification can be accessed at `http://localhost:8080/v3/api-docs`.
- **Swagger UI**: An interactive API documentation UI can be accessed at `http://localhost:8080/swagger-ui.html`.

### Example
using cURL
```bash 
get questions
curl -X GET http://localhost:8080/api/consultation/questions

submit answers
curl -X POST http://localhost:8080/api/consultation/submit \
-H "Content-Type: application/json" \
-d '[{"questionId":"1", "answer":"no"}, {"questionId":"2", "answer":"no"}, {"questionId":"3", "answer":"yes"}, {"questionId":"4", "answer":"30"}]'
```
### Author: Raul Rasciclal