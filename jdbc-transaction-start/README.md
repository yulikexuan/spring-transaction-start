# Spring Transaction Example

## Testing Commands

### POST Data
- ``` curl -v -w '\nStatus: %{http_code}\nTotal: %{time_total} s\n' POST -H "Content-Type: application/json" -d '{ "product": "Spring Security in Action", "price": 2520 }' http://localhost:9097/purchase ```

### List All Records
- ``` curl -v -w '\nStatus: %{http_code}\nTotal: %{time_total} s\n' GET -H "Content-Type: application/json" http://localhost:9097/purchase ```

### List All Accounts
- ``` curl -v -w '\nStatus: %{http_code}\nTotal: %{time_total} s\n' GET -H "Content-Type: application/json" http://localhost:9097/accounts ```

### Find Account by Id
- ``` curl -v -w '\nStatus: %{http_code}\nTotal: %{time_total} s\n' GET -H "Content-Type: application/json" http://localhost:9097/accounts/2 ```

### Find Account by Incorrect Id
- ``` curl -v -w '\nStatus: %{http_code}\nTotal: %{time_total} s\n' GET -H "Content-Type: application/json" http://localhost:9097/accounts/1234567 ```

### Transfer Money between Two Accounts
- ``` curl -v -w '\nStatus: %{http_code}\nTotal: %{time_total} s\n' POST -H "Content-Type: application/json" -d '{ "senderAccountId": 1, "receiverAccountId": 2, "amount": 10000 }' http://localhost:9097/accounts/trans ```