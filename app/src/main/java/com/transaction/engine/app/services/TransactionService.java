package com.transaction.engine.app.services;

import com.transaction.engine.app.base.requests.UpdateTransactionRequest;
import com.transaction.engine.app.base.responses.GetTotalAmountResponse;
import com.transaction.engine.app.base.responses.GetTransactionByIdResponse;
import com.transaction.engine.app.base.responses.GetTransactionsByTypeResponse;

public interface TransactionService {
    GetTransactionByIdResponse getTransactionById(Long transactionId) throws Exception;

    void updateTransaction(Long transactionId, UpdateTransactionRequest updateTransactionRequest) throws Exception;

    GetTransactionsByTypeResponse getAllTransactionsByType(String type) throws Exception;

    GetTotalAmountResponse getTotalTrasactionAmount(Long transactionId) throws Exception;
}
