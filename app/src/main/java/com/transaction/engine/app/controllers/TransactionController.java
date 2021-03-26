package com.transaction.engine.app.controllers;

import com.transaction.engine.app.base.requests.UpdateTransactionRequest;
import com.transaction.engine.app.base.responses.BaseResponse;
import com.transaction.engine.app.base.responses.GetTotalAmountResponse;
import com.transaction.engine.app.base.responses.GetTransactionByIdResponse;
import com.transaction.engine.app.base.responses.GetTransactionsByTypeResponse;
import com.transaction.engine.app.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(TransactionController.BASE_URL)
public class TransactionController {

    public static final String BASE_URL = "/transactionservice";

    @Autowired
    TransactionService transactionService;


    /**
     * An api that returns the transaction details for a given id
     *
     * @param transactionId : the unique id of the transaction
     * @return transaction details for the id in case of success, error otherwise
     *
     */
    @GetMapping("/transaction/{transaction_id}")
    public ResponseEntity<GetTransactionByIdResponse> getTransactionById(@PathVariable("transaction_id")  Long transactionId) throws Exception{
        GetTransactionByIdResponse response = transactionService.getTransactionById(transactionId);
        return ResponseEntity.ok(response);
    }


    /**
     * Api to create a new transaction or update the transaction details of an existing transaction
     * @param transactionId : the unique id of the transaction
     * @param updateTransactionRequest : request which contains details to be updated such as amount, type or parentid
     * @return ok in case of success, error otherwise
     *
     */
    @PutMapping("/transaction/{transaction_id}")
    public ResponseEntity<BaseResponse> updateTransaction(@PathVariable("transaction_id")  Long transactionId, @RequestBody  UpdateTransactionRequest updateTransactionRequest) throws Exception{
        transactionService.updateTransaction(transactionId, updateTransactionRequest);
        return ResponseEntity.ok(new BaseResponse("ok"));
    }

    /**
     * Api to get a list of transaction ids which are of a given type 'type'
     * @param type : the type of the transactions to be retrieved
     * @return :  list of transaction ids
     *
     */
    @GetMapping("/types/{type}")
    public ResponseEntity<GetTransactionsByTypeResponse> getAllTransactionsByType(@PathVariable String type) throws Exception {
        GetTransactionsByTypeResponse getTransactionsByTypeResponse = transactionService.getAllTransactionsByType(type);
        return ResponseEntity.ok(getTransactionsByTypeResponse);
    }


    /**
     * Api to get sum of all the transaction's amount in a given tree with common ancestor
     * @param transactionId : the unique id of the transaction
     * @return : sum of all the amounts of transactions in the tree which share a common ancestor identified by transaction id
     *
     */
    @GetMapping("/sum/{transaction_id}")
    public ResponseEntity<GetTotalAmountResponse> getTotalTrasactionAmount(@PathVariable("transaction_id") Long transactionId) throws Exception{
        GetTotalAmountResponse getTotalAmountResponse = transactionService.getTotalTrasactionAmount(transactionId);
        return ResponseEntity.ok(getTotalAmountResponse);
    }

}
