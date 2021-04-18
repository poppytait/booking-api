# booking-api
A booking API where users can view, book and cancel fitness classes

## Install & Run
`./gradlew bootRun`

## Authentication
Each request needs to be authenticated using basic auth. 

| Username | Password | Role |
| ------- | --------- | --- |
| linda  | password | instructor |
| kyle  | password | customer |

Customers can read fitness classes & read/write bookings. 
Instructors can read/write fitness classes.

## API Docs
Docs can be accessed [here](https://documenter.getpostman.com/view/10876522/TzCQb6ah)

## Next Steps
1. Users should not be able to manage other users' bookings. 
2. Users should not be able to book classes that have already taken place. 
3. Users should not be able to book onto classes that are at full capacity. 
