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
|  email    | String | **Required.** |
|phoneNumber| String | **Required.** |

Example input
```json
{
    "account": "acc004",
    "password": "pwd004",
    "userName": "user444",
    "email": "user444@test.com",
    "phoneNumber":"0987878787"
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
    "message": "login success"
}
```

### getUserData
```
POST /api/getUserData
```
| Parameter |  Type  | Description   |
| --------- | :----: | ------------- |
| account   | String | **Required.** |
| password  | String | **Required.** |
Example input
```json
{
    "account": "acc001",
    "password": "pwd001"
}
```

Example Response
```json
{
    "status": true,
    "message": "get user data success",
    "user": {
        "userId": 1,
        "account": "acc001",
        "userName": "Test001",
        "password": "pwd001",
        "email": "v0153135@gmail.com",
        "registrationTime": "2024-05-13T12:07:40",
        "phoneNumber": "0973524658",
        "creditCard": {
            "cardNumber": null,
            "expirationDate": "****",
            "cardHolderName": "***"
        },
        "memberCard": {
            "cardNumber": "0000111100001113",
            "expirationDate": "****",
            "vaild": true
        },
        "image": "your image's base64"
    }
}
```

### uploadUserPhoto
```
POST /api/uploadUserPhoto
```
| Parameter |  Type  | Description   |
| --------- | :----: | ------------- |
| account   | String | **Required.** |
| password  | String | **Required.** |
| image     | String | **Required.** |
Example input
```json
{
    "account": "acc001",
    "password": "pwd001",
    "image":"your image's base64"
}
```

Example Response
```json
{
    "status":true,
    "message":"upload image success"
}
```

### updateUserData
```
PUT /api/updateUserData
```
| Parameter   |  Type  | Description   |
| ----------- | :----: | ------------- |
| account     | String | **Required.** |
| password    | String | **Required.** |
| userName    | String | **Required.** |
| email       | String | **Required.** |
| phoneNumber | String | **Required.** |


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

## API Reference Payment

### bindCreditCard
```
POST /api/bindCreditCard
```
| Parameter  |    Type    | Description   |
| ---------  | :--------: | ------------- |
| user       | User       | **Required.** |
| creditCard | CreditCard | **Required.** |

Example Input
```json
{
    "user":{
        "account": "acc001",
        "password": "pwd001"
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

### unbindCreditCard
```
POST /api/unbindCreditCard
```
| Parameter |  Type  | Description   |
| --------- | :----: | ------------- |
| account   | String | **Required.** |
| password  | String | **Required.** |

Example Input
```json
{
    "account": "acc001",
    "password": "pwd001"
}
```
Example Response
```json
{
    "status": true,
    "message":"Unbind credit card success."
}
```

### bindMemberCard
```
POST /api/bindMemberCard
```
| Parameter  |    Type    | Description   |
| ---------  | :--------: | ------------- |
| user       | User       | **Required.** |
| memberCard | MemberCard | **Required.** |

Example Input
```json
{
    "user":{
        "account": "acc001",
        "password": "pwd001"
    },
    "memberCard":{
        "cardNumber": "0000111100001111",
        "expirationDate": "0425"
    }
}
```
Example Response
```json
{
    "status": true,
    "message":"Binding member card success."
}
```

### getCards
```
POST /api/getCards
```
| Parameter |  Type  | Description   |
| --------- | :----: | ------------- |
| account   | String | **Required.** |

Example Input
```json
{
    "account": "acc001",
}
```
Example Response
```json
{
    "status": true,
    "message":"gets cards information success.",
    "creditCard": {
        "cardNumber": "0000111100001111",
        "expirationDate": "0425",
        "cardHolderName": "acc001"
    },
    "memberCard": {
        "cardNumber": "0000111100001111",
        "expirationDate": "0425"
    }
}
```


## API Reference RentalRecord 
### getRentalRecordList
```
POST /api/getRentalRecordList
```
| Parameter |  Type  | Description   |
| --------- | :----: | ------------- |
| account   | String | **Required.** |
| password  | String | **Required.** |


Example Input
```json
{
    "account": "acc001",
    "password": "pwd001"
}
```
Example Response
```json
{
    "status": true,
    "message": "return rental record",
    "rentalRecords": {
        "rentalRecord1": {
            "userId": 1,
            "escooterId": "1",
            "startTime": "2024-05-13T12:07:40",
            "endTime": "2024-05-13T13:07:40",
            "isPaid": true
        },
        "rentalRecord2": {
            "userId": 1,
            "escooterId": "2",
            "startTime": "2024-05-13T13:10:45",
            "endTime": "2024-05-13T15:11:45",
            "isPaid": false
        }
    }
}


```
## API Reference Rental
### getRentableEscooterList
```
POST /api/getRentableEscooterList
```
| Parameter |  Type  | Description   |
| --------- | :----: | ------------- |
| longitude | double | **Required.** |
| latitude  | double | **Required.** |

Example Input
```json
{
    "longitude": 120.533301,
    "latitude": 23.693615
}
```
Example Response
```json
{
    "status": true,
    "message": "return escooters",
    "escooters": {
        "escooter1": {
            "escooterId": "1",
            "status": "Available",
            "batteryLevel": 0,
            "feePerMinutes": 0,
            "maintenanceRecords": null,
            "gps": {
                "longitude": 122.000000000001,
                "latitude": 23.49999999999999
            },
            "modelName": "LCE151"
        },
        "escooter2": {
            "escooterId": "2",
            "status": "Available",
            "batteryLevel": 0,
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

### rentEscooter 
```
POST /api/rentEscooter
```
| Parameter  |  Type  | Description   |
| ---------- | :----: | ------------- |
| account    | String | **Required.** |
| password   | String | **Required.** |
| escooterId | String | **Required.** |

Example Input
```json
{
  "user": {
        "account": "acc001",
        "password": "pwd001"
    },
    "escooter": {
        "escooterId": "EL_07"
    }
}
```
Example Response
```json
{
    "status": true,
    "message": "rent escooter success",
    "escooter": {
        "escooterId": "EL_07",
        "modelId": "TEST",
        "status": "Rented",
        "batteryLevel": 100,
        "feePerMinutes": 0,
        "maintenanceRecords": null,
        "gps": {
            "longitude": 120.535822,
            "latitude": 23.693859
        }
    }
}
```

### updateEscooterParkStatus
```
PUT /api/updateEscooterParkStatus
```
| Parameter |  Type  | Description   |
| --------- | :----: | ------------- |
| account   | String | **Required.** |
| password  | String | **Required.** |

Example Input
```json
{ 
    "account": "acc001",
    "password": "pwd001"
}
```
Example Response
```json
{
    "status": true,
    "message": "update escooter park status success"
}
```

### returnEscooter
```
POST /api/returnEscooter
```
| Parameter  |  Type  | Description   |
| ---------- | :----: | ------------- |
| account    | String | **Required.** |
| password   | String | **Required.** |

Example Input
```json
{ 
    "account": "acc001",
    "password": "pwd001"
}
```
Example Response
```json
{
    "status": true,
    "message": "return and payment successful"
}
```

## API Reference Escooter
### isRent
```
POST /api/isRent
```
| Parameter  |  Type  | Description   |
| ---------- | :----: | ------------- |
| escooterId | String | **Required.** |

Example Input
```json
{
    "escooterId": "PD11"
}
```
Example Response
```json
{
    "status": true,
    "message": "escooter is rentable"
}
```


### isReturn
```
POST /api/isReturn
```
| Parameter  |  Type  | Description   |
| ---------- | :----: | ------------- |
| escooterId | String | **Required.** |

Example Input
```json
{
    "escooterId": "4"
}
```
Example Response
```json
{
    "status": false,
    "message": "escooter status is Rented"
}
```

### getParkingStatus
```
POST /api/getParkingStatus
```
| Parameter  |  Type  | Description   |
| ---------- | :----: | ------------- |
| escooterId | String | **Required.** |

Example Input
```json
{
    "escooterId": "4"
}
```
Example Response
```json
{
    "status": true,
    "message": "escooter is parking"
}
```

### updateGps 
```
PUT /api/updateGps
```
| Parameter  |  Type  | Description   |
| ---------- | :----: | ------------- |
| escooterId | String | **Required.** |
| longitude  | double | **Required.** |
| latitude   | double | **Required.** |

Example Input
```json
{
    "escooter": {
        "escooterId": "4"
    },
    "gps": {
        "longitude": 124,
        "latitude": 24
    }
}
```
Example Response
```json
{
    "status": true,
    "message": "GPS update success"
}
```

### updateBetteryLevel
```
PUT /api/updateBetteryLevel
```
| Parameter    |  Type  | Description   |
| ------------ | :----: | ------------- |
| escooterId   | String | **Required.** |
| batteryLevel |  Int   | **Required.** |


Example Input
```json
{
    "escooterId": "4",
    "batteryLevel":90
}
```
Example Response
```json
{
    "status": true,
    "message": "Battery Level update success"
}
```
