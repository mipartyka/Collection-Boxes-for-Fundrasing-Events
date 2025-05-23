# INSTRUCTIONS HOW TO RUN THE APP

## REQUIREMENTS
- Docker Engine

## Step 1 - Build the docker image
In the repository directory open a new command line and paste and run this command:
- docker build -t app .

## Step 2 - Run the container
Once the building process is done, paste and run this command:
- docker run -p 8080:8080 EXCHANGE_API_KEY={api key provided privately} app

## Step 3 - Easy endpoint access
When the container is up and running all of the endpoints will be available under this address:
- http://localhost:8080/swagger-ui/index.html

There you can try out every endpoint with ease.

Endpoints available:
- http://localhost:8080/event/create
- http://localhost:8080/event/display
- http://localhost:8080/collection-box/register
- http://localhost:8080/collection-box/list-all
- http://localhost:8080/collection-box/unregister
- http://localhost:8080/collection-box/assign
- http://localhost:8080/collection-box/add-money
- http://localhost:8080/collection-box/transfer-to-account

Database console available at:
- http://localhost:8080/h2-console
- url: jdbc:h2:mem:charitydb
- user: sa
- password: pass

## Step 4 - Sample queries
Here is a set of sample queries presenting a simple use case of the application:
- http://localhost:8080/event/create?name=SiiCharity&currency=PLN

This creates an event with name "SiiCharity", currency PLN and id 1
- http://localhost:8080/collection-box/register
- http://localhost:8080/collection-box/register

This creates two new empty collection boxes with id 1 and 2
- http://localhost:8080/collection-box/list-all

This lists all the collection boxes registered, and we can see that both collection boxes are empty and not assigned
- http://localhost:8080/collection-box/assign?collectionBoxId=1&fundraisingEventId=1

This assigns the collection box with id 1 to the event with id 1
- http://localhost:8080/collection-box/add-money?collectionBoxId=1&currency=EUR&amount=207.99
- http://localhost:8080/collection-box/add-money?collectionBoxId=1&currency=PLN&amount=73.07
- http://localhost:8080/collection-box/add-money?collectionBoxId=1&currency=USD&amount=355

These add different amounts of money in different currencies to the collection box with id 1. Added amounts:
- 207.99 EUR
- 73.07 PLN
- 355 USD

Allowed currencies: [PLN, EUR, USD, GBP]

- http://localhost:8080/collection-box/list-all

We can see that the collection box with id 1 is no longer empty
- http://localhost:8080/collection-box/transfer-to-account?collectionBoxId=1

This transfers all the money in every currency from the collection box with id 1 to the connected events account. Exchange rates are dynamically fetched from https://exchangerate.host
- http://localhost:8080/event/display

This displays every event along with its balance and name

# TASK DESCRIPTION

## Create an application for managing collection boxes during fundrising events for charity organizations.

# Functional requirements
1. Every collection box should have a unique identifier.
2. A collection box can be assigned to only one fundraising event at a time.
3. You can only assign a collection box to a fundraising event if the collection box is empty.
4. When a collection box is unregistered, it is automatically emptied. No money is transfered to any
fundraising event’s account.
5. A collection box can contain money in different currencies. For simplicity, you can limit possible
currencies to a subset of your choice (e.g. three different currencies).
6. Fundraising events have their own accounts where they store collected money. The account has
only one currency.
7. When money is transfered from a collection box to a fundraising event’s account, it is converted
to the currency used by the fundraising event. The exchange rates can be hardcoded.
Optional: Fetch currency exchange rates from an online API.

# REST API endpoints
The application should expose all REST endpoint defined below. For simplicity, the data input and
output format can be JSON.
1. Create a new fundraising event.
2. Register a new collection box.
3. List all collection boxes. Include information if the box is assigned (but don’t expose to what
fundraising event) and if it is empty or not (but don’t expose the actual value in the box).
4. Unregister (remove) a collection box (e.g. in case it was damaged or stolen).
5. Assign the collection box to an existing fundraising event.
6. Put (add) some money inside the collection box.
7. Empty the collection box i.e. “transfer” money from the box to the fundraising event’s account.
8. Display a financial report with all fundraising events and the sum of their accounts.
Financial report example:

Fundraising event name Amount Currency
Charity One 2048.00 EUR
All for hope 512.64 GBP

# Non-functional requirements
1. Create only the backend part (no UI is required).
2. The application should expose a REST API.
3. Use Java programming language and Spring Framework.
4. Use Maven or Gradle.
5. Use relational in-memory database (e.g. H2).
6. No security features (authorization, authentication, encryption etc.) are required.
