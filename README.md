## Money transfer Rest API
Java RESTful API for money transfer between user accounts

### How to Run

mvn exec:java

### Technologies

* Spark Java (Light weight microservices backend framework with embedded Jetty container)
* Junit 4 (for unit testing)
* Cucumber (for acceptance testing of features)
* Mockito (for mocking database calls and methods in unit testing)
* Dagger 2 (It is very light dependency injection framework from google like Guice)
* Lombok (for creating getter/setter using annotations)
* Rest Assured (for Integration testing)

### Design Pattren 

* Double check locking singleton pattren
* Builder pattren

### Thread safety
For thread safety, i am using java 1.8 StampedLock, in which we have optimistic read lock. Which make synchronization overhead is very low.

#### Dagger 2
I have used Dagger to construct services objects and inject databse
layer objects in it to demo that we can use light weight DI tools
in order to avoid boiler plate code of building dependencies through 
code. In real systems, it comes really handy to build up dependencies.
Since Dagger build up dependencies on compile time, it is fast as well 
on runtime to fetch those dependencies.

Application starts a jetty server on localhost port 
4567.I am using port method to set port to 8080.Developer can update port according.In memory hashmap based data structure initialized
with some sample user, account, and transaction data to play around with.

Few example calls are given below.
* localhost:8080/user/u1
* localhost:8080/user/u2
* localhost:8080/account/a1
* localhost:8080/account/a2

### Testing
TDD and BDD practice has been followed in developing the API in following
areas
* Unit Testing
* Integration Testing
* Functional Testing 
* Acceptance Testing

Although, acceptance testing overlaps with the functional testing
in a sense that in acceptance tests , features are tested as in functional
testing but in acceptance testing, the point is to write tests in such a way which can be even
read by product as they are defined in English and are converted 
into domain language. For example, it can be defined like following

##### Feature: Money Transfer
	@MoneyTransfer
		Feature: Money Transfer

  		Scenario: Successful money transfer
    		Given Transaction request with sender account id, reciever account and amount 
   		When transfer action is called
    		Then amount is deducted from sender account
    		And reciever will recieve amount

#### Rollback transaction
For the sake of this assignment, and simpicity i am using in memory data, using concurrentHashMap.I could 
not use transactional features of database with rollback option but in
real systems for testing, it it necessary to perform tests in transactional
manner and rollback after running each transaction to persist the data or
use in memory databases in development environment like H2 for fast execution of tests.
To mimic the rollback operation, I have reversed every transaction in the
final blocks of every test which included database calls.



### API Usage

HTTP METHOD | PATH | USAGE
--- | --- | ---
GET| /user/all | Get all users
GET| /user/:id | Get user by its id
POST| /user/ | Create a new user
PUT| /user/:id | Update an existing user
DELETE| /user/:id | Delete an exisiting user by its id
OPTION| /user/:id | Check user exists by its id
GET| /account/all | Get all accounts
GET| /account/:id | Get account details by id
POST| /account/ | Create new account
DELETE| /account/:id | Delete an exisiting account by its id
PUT| /account/:id | Update an existing account
OPTION| /account/:id | Check account exists by its id
POST| /account/withdraw/ | Withdraw amount from account
POST| /account/deposit/ | deposit amount from account
GET| /account/balance/:id | get amount from account by account id
POST| /trans/ | transaction between two accounts
GET| /health | Check health of spark server

### Sample Request objects
Sample requesta and response objects

##### Sample JSON for User Create/Update request
        {
           "user" : {
	                    "id":"23211",
	                    "address":"test12323",
  	                  "CNIC":"weqjh1k2378172",
	                    "email":"test@gmail.com",
	                    "firstName":"Ali",
	                    "contactNumber":"+262711",
	                    "lastName":"Javed"
	
                     }
        }
        {
           "user" : {
	                    "id":"23222",
	                    "address":"wqsgq123",
  	                  "CNIC":"123-7895-9996-9",
	                    "email":"lemp@gmail.com",
	                    "firstName":"Levo",
	                    "contactNumber":"+964623",
	                    "lastName":"Iop"
	
                     }
        }
        
##### Sample JSON for Account Create/Update request
        { 
           "account" : {
                        "id": "23122",
                        "accountTitle": "TEMP2",
                        "balance": 8310,
                        "currency": "EUR",
                        "userId": "23211"
                        }
        }
	
##### Sample JSON for Transaction request

	{
		"fromAccount":"23122",
		"toAccount":"23ww122",
		"amount":310
	}        
 
### Sample Response objects
Sample requests and response objects

##### Sample JSON for User Create/Update response
	{
    		"statusType": "SUCCESS",
    		"data": {
        		"id": "23211",
        		"firstName": "Ali",
        		"lastName": "Javed",
        		"email": "test@gmail.com",
        		"CNIC": "weqjh1k2378172",
        		"address": "test12323",
        		"contactNumber": "+262711"
    			},
    		"message": "User for ID [23211] created successfully"
	}

##### Sample JSON for Account Create/Update response
	{
    		"statusType": "SUCCESS",
    		"data": {
        		"id": "23122",
        		"accountTitle": "TEMP2",
        		"balance": 8310,
        		"currency": "EUR",
        		"userId": "23211"
    			},
    		"message": "Account for ID [23122] created successfully"
	}

### Sample Error response
	{
    		"statusType": "ERROR",
    		"message": "User already exists against id [23211]"
	}
	

##### Sample JSON for Transaction response
	{
    		"statusType": "SUCCESS",
    		"data": {
        	"reciever": {
            		"id": "23ww122",
            		"accountTitle": "TEMP2",
            		"balance": 620,
            		"currency": "EUR",
            		"userId": "231111"
        		},
        	"sender": {
            		"id": "23122",
            		"accountTitle": "TEMP2",
            		"balance": 8000,
            		"currency": "EUR",
            		"userId": "23211"
        		}
    		},
    		"message": "Amount transfer successfully"
	}
	

### Https Status
* 200 OK: The request has succeeded
* 400 Bad Request: The request could not be understood by the server
* 404 Not Found: The requested resource cannot be found
* 417 Bad Exception: Internal server exception
* 500 Internal Server Error: The server encountered an unexpected condition 
