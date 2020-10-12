# pegcontas-test

The challenge is to build an API to post and get activities and cards

To send requests to the API, I used Postman. The routes are:

Get all activity : /api/v1/activity/all (GET)

Add activity : /api/v1/activity (POST)
- In the Body

``` json 
{ "title": "OPME", "subtitle": "Finalizar conta", "sla": 5}
```
Get card by : /api/v1/card?q=patientName&filter=PRIORITY&value=Graziely Scharf Borelli

- q can assume ActivityId, patientName, visitId or billId as value
- filter can assume PRIORITY, TO_RECEIVE or TO_SEND as value (optional)
- value contains the query value

Add card : /api/v1/card (POST)
- In the Body  

``` json 
{
    "activityId": 1,
    "daysSinceCreated":20,
    "slaStatus":"DELAYED",
    "visitId":266684,
    "numberOfDocuments":5,
    "numberOfNotReceivedDocuments":1,
    "numberOfChecklistItem":0,
    "numberOfDoneChecklistItem":0,
    "billType":"HOSPITALAR",
    "totalAmount":8925.55,
    "numberOfPendencies":0,
    "numberOfOpenPendencies":0,
    "healthInsurance": "Maxima Seguro",
    "patient":"Fernando Leite Serrano"
}
```

IMPORTANTE: In the requests that uses Body, it is important to specify in the Header that Content-Type is equal to application/json

# Required

- [x] Create a Java Spring Boot project;
- [x] Use JPA and Hibernate to persist and get data from a SQL database;
- [x] You can use in-memory databases like H2 or a server database (MySQL or PostgreSQL);
- [x] Show us your work through your commit history in a new GitHub repository;

# Bonus

- [x] Implement unit tests;
- [x] Error handling;
- [x] You can use any library that simplifies your work;
- [ ] Implement integration tests;
- [ ] Document and expose your API with Swagger or alternative;
- [ ] Create sql tables and rows using Flyway or alternative;
- [ ] Use spring security to authorize and authenticate the endpoints;
- [ ] Host the API on the service of your choice;

# Endpoints
    
- [x] Create activity
- [x] Get all activities:
- [x] Create card
- [x] get cards by activityId, patientName, visitId and billId
- [x] filter by PRIORITY, TO_RECEIVE and TO_SEND;
- [x] totalCardsOk, totalCardsWarning and totalCardsDelayed
- [ ] pagination
