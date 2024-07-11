# FraudDetection
Steps to Test with Postman:

1. Start Your Spring Boot Application
Ensure your Spring Boot application is running. You can do this by running the main class Assignment.java. Once it's running, it will typically be available at http://localhost:8080.

2. Test Endpoints with Postman
You need to test the following endpoints:

  1.POST /api/transactions - To add a transaction and check for fraud.
  2.GET /api/transactions - To retrieve all transactions.
Postman Requests
POST /api/transactions
URL: http://localhost:8080/api/transactions

Method: POST

Headers:
Content-Type: application/json
Body:

json

{
  "userId": 1,
  "amount": 600000
}
