<b> $$$ CURRENCY EXCHANGE RATES & GOLD PRICES REST API $$$ </b>
<hr>


Currency Exchange REST API based on: https://api.exchangerate, http://api.nbp.pl/api/cenyzlota/

Key functionalities:
1. Getting currency exchange rates & gold prices
2. Retrieving basics statistics data using Spring Boot Actuator
3. Storing request response data in db
- Each response returned from external api is stored in db. When user is requesting the data that are already in db then db result is returned instead of fetching the data from API

Technologies:
- Spring Boot Project
- Database: H2

<hr>

---- Currency Exchange Rates Endpoits ---

*** Fetching current exchange rates
Current exchange rates are provided based on the following parameters:

- base currency
- target currency

Request URL:
http://localhost:8080/currency/current?base=EUR&target=PLN -->
Response body:
Base currency: EUR
Target currency: PLN
Rate: 4,6187
Date: 2022-04-19

Request URL:
http://localhost:8080/currency/current?base=123abc&target=xyz456 -->
Response body:
{
  "message": "Cannot get currency exchange data. DB not updated"
}

Request URL:
http://localhost:8080/currency/current?base=PLN&target=PLN -->
Response body:
{
  "message": "Base currency and Target currency are the same. Guess the result :). DB not updated."
}



*** Fetching historical exchange rates
Historical exchange are provided based on the following parameters:

- base currency
- target currency
- date

Request URL:
http://localhost:8080/currency/historical?date=2022-04-19&base=EUR&target=PLN -->
Response body:
Base currency: EUR
Target currency: PLN
Date: 2022-04-19
Rate: 4,6187

*** Fetching currency by base rate

Request URL:
http://localhost:8080/currency/api/currencies/PLN -->
Response body:
[
  {
    "id": 3,
    "base": "PLN",
    "target": "USD",
    "date": "2022-04-19",
    "rate": 0.2334
  },
  {
    "id": 4,
    "base": "PLN",
    "target": "EUR",
    "date": "2022-04-19",
    "rate": 0.2165
  }
]

*** Fetching all currency rates

Request URL:
http://localhost:8080/currency/api/currencies/all	-->
Response body:
[
  {
    "id": 1,
    "base": "EUR",
    "target": "PLN",
    "date": "2022-04-19",
    "rate": 4.6187
  },
  {
    "id": 2,
    "base": "USD",
    "target": "PLN",
    "date": "2022-04-19",
    "rate": 4.2851
  },
  {
    "id": 3,
    "base": "PLN",
    "target": "USD",
    "date": "2022-04-19",
    "rate": 0.2334
  },
  {
    "id": 4,
    "base": "PLN",
    "target": "EUR",
    "date": "2022-04-19",
    "rate": 0.2165
  }
]

*** Fetching the total count of all the records

Request URL:
http://localhost:8080/currency/count -->
Response body:
4

<hr>

--- Gold Prices Endpoits ---

*** Fetching current gold price

Request URL:
http://localhost:8080/gold/current -->
Response body:
[{"data":"2022-04-19","cena":270.56}]

*** Fetching historical gold price

Request URL:
http://localhost:8080/gold/date?date=2022-04-19 -->
Response body:
[{"data":"2022-04-19","cena":270.56}]

*** Fetching periodical gold price

Request URL:
http://localhost:8080/gold/period?startDate=2022-04-10&endDate=2022-04-19 -->
Response body:
[{"data":"2022-04-11","cena":266.55},{"data":"2022-04-12","cena":267.18},{"data":"2022-04-13","cena":270.61},{"data":"2022-04-14","cena":272.45},{"data":"2022-04-15","cena":268.77},{"data":"2022-04-19","cena":270.56}]

<hr>

--- Statstics Counter Endpoits ---

*** Counting current gold price visits

Request URL:
http://localhost:8080/count/gold/dates -->
Response body:
Number of API calls for getting data of Gold price in period of time: 2

*** Counting historical gold price visits

Request URL:
http://localhost:8080/count/gold/current -->
Response body:
Number of API calls for getting current date of Gold rate: 1.0

*** Counting current currency rates visits

Request URL:
http://localhost:8080/count/currencies/ -->
Response body:
Number of API calls for getting current data regarding currencies: 6

*** Counting historical currency rates visits

Request URL:
http://localhost:8080/count/currencies/historical -->
Response body:
Number of API calls for getting data of currency rate in period of time: 1












