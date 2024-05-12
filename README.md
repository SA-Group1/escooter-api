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

Example Response
```json
{
    "status": true,
    "message": "login success",
    "user": {
        "account": "acc001",
        "userName": "user001",
        "password": "pwd001",
        "creditCard": {
            "cardNumber": "01234567890123",
            "expirationDate": "3012",
            "cardHolderName": ""
        },
        "memberCard": {
            "expirationDate": "",
            "vaild": true,
            "cardNumber": ""
        },
        "rentalRecords": []
    }
}
```