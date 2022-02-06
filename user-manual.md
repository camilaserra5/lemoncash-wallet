# User manual


## Lemon Cash REST API

### Register a new user
#### Request
`POST /users/`

    curl -i -H 'Accept: application/json' --data '{"firstName":"Camila", "lastName":"Serra", "username":"camilaserra", "email":"camila@gmail.com", "password":"mypass"}' http://localhost:8081/users

#### Response
    User {id} created


### Get a user

#### Request
`GET /users/`

    curl -i -H 'Accept: application/json' http://localhost:8081/users/{id}

#### Response
    {
    "id": 139,
    "firstName": "Camila",
    "lastName": "Serra",
    "username": "camilaserra",
    "password": {hashedPassword},
    "email": "camila@gmail.com"
    }

### Register a new movement

#### Request
`POST /movements/`

    curl -i -H 'Accept: application/json' --data '{"amount":10, "userId":143, "currencyName":"ARS", "movementType":"deposit"}' http://localhost:8081/users

#### Response
    Movement {id} performed


### List movements

#### Request
`GET /movements/`

    curl -i -H 'Accept: application/json' http://localhost:8081/movements/user_id={user_id}

***Optional parameters:***
- limit & offset: for pagination
- movement_type: [extraction, deposit]
- currency_name: [ARS, BTC, USDT]

#### Response
    [
        {
            "id": 147,
            "amount": 10,
            "movementType": "extraction",
            "currencyName": "ARS",
            "userId": 143
        },
        {
            "id": 148,
            "amount": 100,
            "movementType": "deposit",
            "currencyName": "ARS",
            "userId": 143
        }
    ]
