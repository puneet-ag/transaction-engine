Steps to run application :

1. git clone https://github.com/puneet-ag/transaction-engine.git

2. cd transaction-engine/app

3. compile using ./mvnw clean install 
 
4. run the application using command: java -jar target/app-0.0.1.jar


<br /><br /><br />


Details about application :

1. The application will start running at port 9010

2. For simplicity the database used is embedded inmemory db (h2)

3. The console for h2 will be available at  url : http://127.0.0.1:9010/h2 ,  username : test, password : test and jdbc url : jdbc:h2:mem:transactionsdb

<br /><br /><br />


API details:
<br />
There are 4 apis :
<br />
1. Api to create/update transaction for a given data
<br />
Curl : To create a transaction with id 2 and amount = 200, type = Type 1 and whose parent is txn with id 1
<br /><br />
curl --location --request PUT '127.0.0.1:9010/transactionservice/transaction/2/' \
--header 'Content-Type: application/json' \
--data-raw '{
  "amount": 200,
  "type": "Type1",
  "parent_id": 1
}'

<br /><br /><br /><br />


2. APi to get transaction with given id.
<br />
Curl : To get transaction details for transaction with id 1
<br /><br />
curl --location --request GET '127.0.0.1:9010/transactionservice/transaction/1/'  


<br /><br /><br /><br />


3. Api to get a list of transaction ids which are of given type.
<br />
Curl: Get all the transaction of type Type1
<br /><br />
curl --location --request GET '127.0.0.1:9010/transactionservice/types/Type1/'
<br /><br /><br /><br />




4. Api to get sum of all the transaction's amount in a given tree with common ancestor represented by transaction id
<br />
Curl : get sum of all transactions amount whose common ancestor is 10
<br /><br />
curl --location --request GET '127.0.0.1:9010/transactionservice/sum/10/'

<br /><br /><br /><br />


--
Please feel free to reach out to me in case of queries related to this project
To collaborate email at : agarwal.puneet011@gmail.com
