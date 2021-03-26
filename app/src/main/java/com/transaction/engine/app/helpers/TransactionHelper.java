package com.transaction.engine.app.helpers;

import com.transaction.engine.app.base.exceptions.BadRequestException;
import com.transaction.engine.app.base.requests.UpdateTransactionRequest;
import com.transaction.engine.app.base.responses.GetTotalAmountResponse;
import com.transaction.engine.app.base.responses.GetTransactionByIdResponse;
import com.transaction.engine.app.base.responses.GetTransactionsByTypeResponse;

public interface TransactionHelper {

    void validateTrasactionId(Long transactionId) throws BadRequestException;
    void validateUpdateTransactionRequest(Long transactionId, UpdateTransactionRequest updateTransactionRequest) throws BadRequestException;
    void validateTrasactionType(String type) throws BadRequestException;

    GetTransactionByIdResponse getTransactionDetailAndPrepareResponse(Long transactionId) throws Exception;
    GetTransactionsByTypeResponse getTransactionsListByTypeAndPrepareResponse(String type) throws Exception ;
    GetTotalAmountResponse getTotalAmountFromTransactionDetail(Long transactionId) throws Exception;

    void updateTransactionDetailsEntityAndSave(Long transactionId, UpdateTransactionRequest updateTransactionRequest) ;




}
