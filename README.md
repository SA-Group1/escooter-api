# Escooter Api
## API Reference User

### register
```
POST /api/register
```
| Parameter |  Type  | Description   |
| --------- | :----: | ------------- |
| account   | String | **Required.** |
| userName  | String | **Required.** |
| password  | String | **Required.** |
Example input
```json
{
    "account": "acc004",
    "password": "pwd004",
    "userName": "user444",
    "email": "user444@test.com"
}
```

Example Response
```json
{
    "status":true,
    "message":"create user success"
}
```

### login
```
POST /api/login
```
| Parameter |  Type  | Description   |
| --------- | :----: | ------------- |
| account   | String | **Required.** |
| password  | String | **Required.** |
Example input
```json
{
    "account": "acc004",
    "password": "pwd004"
}
```

Example Response
```json
{
    "status": true,
    "message": "login success",
    "user": {
        "userId": 0,
        "account": "acc004",
        "userName": "user444",
        "password": "pwd004",
        "email": "user444@test.com",
        "registrationTime": "2024-05-15T23:36:01",
        "creditCard": {
            "cardNumber": "",
            "expirationDate": "",
            "cardHolderName": ""
        },
        "memberCard": {
            "cardNumber": "",
            "expirationDate": "",
            "vaild": true
        },
        "rentalRecords": []
    }
}
```

### updateUserData
```
PUT /api/updateUserData
```
| Parameter |  Type  | Description   |
| --------- | :----: | ------------- |
| account   | String | **Required.** |
| password  | String | **Required.** |

Example input

```json
{
    "account": "acc001",
    "password": "pwd001",
    "userName": "user111",
    "email": "user001@test.com",
    "phoneNumber": "0987654320"
}
```


Example Response
```json
{
    "status": true,
    "message": "update success"
}
```
### bindCreditCard
```
POST /api/bindCreditCard
```
| Parameter  |     Type      | Description   |
| ---------  | :-----------: | ------------- |
| user       | UserDTO       | **Required.** |
| creditCard | CreditCardDTO | **Required.** |

Example Input
```json
{
    "user":{
        "account": "acc001"
    },
    "creditCard":{
        "cardNumber": "0000111100001111",
        "expirationDate": "0425",
        "cardHolderName": "acc001",
        "cvv": "000"
    }
}
```
Example Response
```json
{
    "status": true,
    "message":"Binding credit card success."
}
```
## API Reference Payment

### bindCreditCard
```
POST /api/bindCreditCard
```
| Parameter  |     Type      | Description   |
| ---------  | :-----------: | ------------- |
| user       | UserDTO       | **Required.** |
| creditCard | CreditCardDTO | **Required.** |

Example Input
```json
{
    "user":{
        "account": "acc001"
    },
    "creditCard":{
        "cardNumber": "0000111100001111",
        "expirationDate": "0425",
        "cardHolderName": "acc001",
        "cvv": "000"
    }
}
```
Example Response
```json
{
    "status": true,
    "message":"Binding credit card success."
}
```

### bindMemberCard
```
POST /api/bindMemberCard
```



## API Reference RentalRecord 
### get rental record list
```
POST /api/getRentalRecordList
```
| Parameter  |     Type      | Description   |
| ---------  | :-----------: | ------------- |
| user       | UserDTO       | **Required.** |
| creditCard | CreditCardDTO | **Required.** |

Example Input
```json
{
    "account": "acc001"
}
```
Example Response
```json
{
    "status": true,
    "message": "return rental record",
    "rentalRecords": {
        "rentalRecord1": {
            "userAccount": "acc001",
            "escooterId": "1",
            "startTime": "2024-05-13T12:07:40",
            "endTime": "2024-05-13T13:07:40",
            "isPaid": true
        },
        "rentalRecord2": {
            "userAccount": "acc001",
            "escooterId": "2",
            "startTime": "2024-05-13T13:10:45",
            "endTime": "2024-05-13T15:11:45",
            "isPaid": false
        }
    }
}


```
## API Reference Rental
### get rentable escooter list
```
POST /api/getRentableEscooterList
```
| Parameter  |     Type      | Description   |
| ---------  | :-----------: | ------------- |
| user       | UserDTO       | **Required.** |
| creditCard | CreditCardDTO | **Required.** |

Example Input
```json
{
    "longitude": 122,
    "latitude": 23.5
}
```
Example Response
```json
{
    "status": true,
    "message": "return escooters",
    "escooters": {
        "escooter1": {
            "escooterId": 1,
            "status": "Available",
            "betteryLevel": 0,
            "feePerMinutes": 0,
            "maintenanceRecords": null,
            "gps": {
                "longitude": 122.000000001,
                "latitude": 23.49999999999
            },
            "modelName": "LCE151"
        },
        "escooter2": {
            "escooterId": 2,
            "status": "Available",
            "betteryLevel": 0,
            "feePerMinutes": 0,
            "maintenanceRecords": null,
            "gps": {
                "longitude": 122,
                "latitude": 23.5
            },
            "modelName": "LCE151"
        }
    }
}
```


