1. cd transaction-engine/app

2. compile using ./mvnw clean install 

3. cd target/

run the application using :
java -jar target/app-0.0.1.jar

Details :

The application will start running at port 9010

For simplicity the database used is embedded inmemory db (h2)

The console for h2 will be available at http://127.0.0.1:9010/h2
username : test
password : test
jdbc url : jdbc:h2:mem:transactionsdb


There are 4 apis :

1. Api to create/update transaction for a given data

Curl : To create a transaction with id 2 and amount = 200, type = Type 1 and whose parent is txn with id 1


curl --location --request PUT '127.0.0.1:9010/transactionservice/transaction/2/' \
--header 'Content-Type: application/json' \
--data-raw '{
  "amount": 200,
  "type": "Type1",
  "parent_id": 1
}'

2. APi to get transaction with given id.

Curl : To get transaction details for transaction with id 1

curl --location --request GET '127.0.0.1:9010/transactionservice/transaction/1/'  


3. Api to get a list of transaction ids which are of given type.

Curl: Get all the transaction of type Type1

curl --location --request GET '127.0.0.1:9010/transactionservice/types/Type1/'

4. Api to get sum of all the transaction's amount in a given tree with common ancestor represented by transaction id

Curl : get sum of all transactions amount whose common ancestor is 10

curl --location --request GET '127.0.0.1:9010/transactionservice/sum/10/'
