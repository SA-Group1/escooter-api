# Escooter Api
## API Reference Account

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
    "account":"Test002",
    "password":"test002",
    "userName":"userTest",
    "email":"Test002@gmail.com",
    "phoneNumber":"0909893352"
}
```

Example Response
```json
{
    "status": true,
    "message": "Create user success."
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
    "account":"Test002",
    "password":"test002"
}
```

Example Response
```json
{
    "status": true,
    "message": "Login success.",
    "data": true
}
```

## API Reference User

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
    "account":"Test002",
    "password":"test002"
}
```

Example Response
```json
{
    "status": true,
    "message": "Get user data success.",
    "data": {
        "userId": 19,
        "account": "Test002",
        "userName": "userTest",
        "email": "Test002@gmail.com",
        "registrationTime": "2024-05-27T04:06:36",
        "phoneNumber": "0909893352",
        "creditCard": {
            "cardNumber": null,
            "expirationDate": null
        },
        "memberCard": {
            "cardNumber": null,
            "expirationDate": null,
            "vaild": true
        }
    }
}
```

### getUserPhoto
```
POST /api/getUserPhoto
```
| Parameter |  Type  | Description   |
| --------- | :----: | ------------- |
| account   | String | **Required.** |
| password  | String | **Required.** |
Example input
```json
{
    "account": "Test002",
    "password": "test002"
}
```

Example Response
```json
"status": true,
    "message": "Get user photo success.",
    "data": {
        "image":"your image's base64"
    }
```

### uploadUserPhoto
```
PUT /api/uploadUserPhoto
```
| Parameter |  Type  | Description   |
| --------- | :----: | ------------- |
| account   | String | **Required.** |
| password  | String | **Required.** |
| image     | String | **Required.** |
Example input
```json
{
    "account": "Test002",
    "password": "test002",
    "image":"your image's base64"
}
```

Example Response
```json
{
    "status": true,
    "message": "Update user photo success.",
    "data": true
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
    "account":"Test002",
    "password":"test002",
    "userName": "userTest",
    "email": "user001@test.com",
    "phoneNumber": "0987654321"
}
```


Example Response
```json
{
    "status": true,
    "message": "Update user data success.",
    "data": true
}
```

## API Reference UserPayment

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
    "account": "Test002",
    "password": "test002",
    "cardNumber": "2222333322223333",
    "expirationDate": "0528",
    "cardHolderName": "userTest",
    "cvv": "520"
}
```
Example Response
```json
{
    "status": true,
    "message": "Bind credit card success."
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
    "account": "Test002",
    "password": "test002"
}
```
Example Response
```json
{
    "status": true,
    "message": "unbind credit card success."
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
    "account": "Test002",
    "password": "test002",
    "cardNumber": "4444888844448888",
    "expirationDate": "0425"
}
```
Example Response
```json
{
    "status": true,
    "message": "Bind member card success."
}
```

### getUserPayment
```
POST /api/getUserPayment
```
| Parameter  |    Type    | Description   |
| ---------  | :--------: | ------------- |
| user       | User       | **Required.** |
| memberCard | MemberCard | **Required.** |

Example Input
```json
{
    "account": "Test002",
    "password": "test002"
}
```
Example Response
```json
{
    "status": true,
    "message": "get user payment success.",
    "data": {
        "creditCard": {
            "cardNumber": null,
            "expirationDate": null,
            "cardHolderName": null
        },
        "memberCard": {
            "cardNumber": "4444888844448888",
            "expirationDate": "0425",
            "vaild": true
        }
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
    "account": "acc003",
    "password": "pwd003"
}
```
Example Response
```json
{
    "status": true,
    "message": "Get rental records success.",
    "data": [
        {
            "userId": 6,
            "escooterId": "A_09",
            "startTime": "2024-05-21T05:50:53",
            "endTime": "2024-05-21T06:14:54",
            "isPaid": true,
            "modelId": "LCE151",
            "feePerMinutes": 0.3,
            "duration": 24,
            "totalFee": 7
        },
        {
            "userId": 6,
            "escooterId": "AI_10",
            "startTime": "2024-05-21T07:27:35",
            "endTime": "2024-05-21T07:28:32",
            "isPaid": true,
            "modelId": "TEST",
            "feePerMinutes": 0.5,
            "duration": 0,
            "totalFee": 0
        },
        {
            "userId": 6,
            "escooterId": "AI_10",
            "startTime": "2024-05-21T04:34:37",
            "endTime": "2024-05-21T05:42:12",
            "isPaid": true,
            "modelId": "TEST",
            "feePerMinutes": 0.5,
            "duration": 67,
            "totalFee": 33
        }
    ]
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
    "message": "Get rentable escooter success.",
    "data": [
        {
            "escooterId": "TEST_00",
            "modelId": "TEST",
            "status": "Available",
            "batteryLevel": 79,
            "feePerMinutes": 0.5,
            "maintenanceRecords": null,
            "gps": {
                "longitude": 120.53295219082112,
                "latitude": 23.693785836720586
            }
        },
        {
            "escooterId": "DH_04",
            "modelId": "TEST",
            "status": "Available",
            "batteryLevel": 70,
            "feePerMinutes": 0.5,
            "maintenanceRecords": null,
            "gps": {
                "longitude": 120.533755,
                "latitude": 23.692902
            }
        },
        {
            "escooterId": "DA_05",
            "modelId": "TEST",
            "status": "Available",
            "batteryLevel": 63,
            "feePerMinutes": 0.5,
            "maintenanceRecords": null,
            "gps": {
                "longitude": 120.534923,
                "latitude": 23.692616
            }
        },
        {
            "escooterId": "EL_07",
            "modelId": "TEST",
            "status": "Available",
            "batteryLevel": 54,
            "feePerMinutes": 0.5,
            "maintenanceRecords": null,
            "gps": {
                "longitude": 120.535822,
                "latitude": 23.693859
            }
        },
        {
            "escooterId": "EM_08",
            "modelId": "TEST",
            "status": "Available",
            "batteryLevel": 26,
            "feePerMinutes": 0.5,
            "maintenanceRecords": null,
            "gps": {
                "longitude": 120.535519,
                "latitude": 23.695188
            }
        },
        {
            "escooterId": "PD_11",
            "modelId": "TEST",
            "status": "Available",
            "batteryLevel": 25,
            "feePerMinutes": 0.5,
            "maintenanceRecords": null,
            "gps": {
                "longitude": 120.536782,
                "latitude": 23.691812
            }
        },
        {
            "escooterId": "G_02",
            "modelId": "LCE151",
            "status": "Available",
            "batteryLevel": 43,
            "feePerMinutes": 0.3,
            "maintenanceRecords": null,
            "gps": {
                "longitude": 120.534336,
                "latitude": 23.689858
            }
        },
        {
            "escooterId": "C_01",
            "modelId": "LCE151",
            "status": "Available",
            "batteryLevel": 96,
            "feePerMinutes": 0.3,
            "maintenanceRecords": null,
            "gps": {
                "longitude": 120.534454,
                "latitude": 23.689305
            }
        }
    ]
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
    "account": "Test002",
    "password": "test002",
    "escooterId": "C_01"
}
```
Example Response
```json
{
    "status": true,
    "message": "Rent escooter success.",
    "data": {
        "escooterId": "C_01",
        "modelId": "LCE151",
        "status": "Rented",
        "batteryLevel": 96,
        "feePerMinutes": 0,
        "maintenanceRecords": null,
        "gps": {
            "longitude": 120.534454,
            "latitude": 23.689305
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
    "account": "Test002",
    "password": "test002"
}
```
Example Response
```json
{
    "status": true,
    "message": "Update escooter park status success.",
    "data": true
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
    "account": "Test002",
    "password": "test002"
}
```
Example Response
```json
{
    "status": true,
    "message": "return and payment successful"
}
```

## API Reference Escooter Management
### addEscooter
```
POST /api/addEscooter
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


### getEscooterIdList
```
GET /api/getEscooterIdList
```
Example Response
```json
{
    "status": true,
    "message": "Get e-scooter ID list success",
    "data": [
        {
            "escooterId": "A_09"
        },
        {
            "escooterId": "C_01"
        },
        {
            "escooterId": "D_03"
        },
        {
            "escooterId": "G_02"
        },
        {
            "escooterId": "AI_10"
        },
        {
            "escooterId": "DA_05"
        },
        {
            "escooterId": "DH_04"
        },
        {
            "escooterId": "EL_07"
        },
        {
            "escooterId": "EM_08"
        },
        {
            "escooterId": "GA_06"
        },
        {
            "escooterId": "PD_11"
        },
        {
            "escooterId": "TEST_00"
        }
    ]
}
```

### getEscooterStatus
```
POST /api/getEscooterStatus
```
| Parameter  |  Type  | Description   |
| ---------- | :----: | ------------- |
| escooterId | String | **Required.** |

Example Input
```json
{
    "escooterId": "AI_10"
}
```
Example Response
```json
{
    "status": true,
    "message": "Get status success",
    "data": "Rented"
}
```

### getEscooterGps 
```
PUT /api/getEscooterGps
```
| Parameter  |  Type  | Description   |
| ---------- | :----: | ------------- |
| escooterId | String | **Required.** |

Example Input
```json
{
    "escooterId": "AI_10"
}
```
Example Response
```json
{
    "status": true,
    "message": "Get escooter gps success",
    "data": {
        "longitude": 120.537809,
        "latitude": 23.690466
    }
}
```

### updateBetteryLevel
```
PUT /api/updateGps
```
| Parameter    |  Type  | Description   |
| ------------ | :----: | ------------- |
| escooterId   | String | **Required.** |
| batteryLevel |  Int   | **Required.** |


Example Input
```json
{
    "escooterId": "AI_10",
    "longitude": 120.537810,
    "latitude": 23.690467
}
```
Example Response
```json
{
    "status": true,
    "message": "GPS update success"
}
```

### updateBatteryLevel
```
PUT /api/updateBatteryLevel
```
| Parameter    |  Type  | Description   |
| ------------ | :----: | ------------- |
| escooterId   | String | **Required.** |
| batteryLevel |  Int   | **Required.** |

Example Input
```json
{
    "escooterId": "AI_10",
    "batteryLevel":90
}

Example Response
```json
{
    "status": true,
    "message": "Battery Level update success"
}
```