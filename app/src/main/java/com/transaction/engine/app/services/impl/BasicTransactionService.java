package com.transaction.engine.app.services.impl;

import com.transaction.engine.app.base.requests.UpdateTransactionRequest;
import com.transaction.engine.app.base.responses.GetTotalAmountResponse;
import com.transaction.engine.app.base.responses.GetTransactionByIdResponse;
import com.transaction.engine.app.base.responses.GetTransactionsByTypeResponse;
import com.transaction.engine.app.entities.TransactionDetails;
import com.transaction.engine.app.helpers.TransactionHelper;
import com.transaction.engine.app.repositories.TransactionDetailsRepository;
import com.transaction.engine.app.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasicTransactionService implements TransactionService {

    @Autowired
    TransactionHelper transactionHelper;

    @Autowired
    TransactionDetailsRepository transactionDetailsRepository;


    @Override
    public GetTransactionByIdResponse getTransactionById(final Long transactionId) throws Exception{
        transactionHelper.validateTrasactionId(transactionId);
        return  transactionHelper.getTransactionDetailAndPrepareResponse(transactionId);
    }

    @Override
    public void updateTransaction(final Long transactionId, final UpdateTransactionRequest updateTransactionRequest) throws Exception{
        transactionHelper.validateUpdateTransactionRequest(transactionId, updateTransactionRequest);
        transactionHelper.updateTransactionDetailsEntityAndSave(transactionId, updateTransactionRequest);
    }

    @Override
    public GetTransactionsByTypeResponse getAllTransactionsByType(final String type) throws Exception {

        transactionHelper.validateTrasactionType(type);
        return  transactionHelper.getTransactionsListByTypeAndPrepareResponse(type);
    }

    @Override
    public GetTotalAmountResponse getTotalTrasactionAmount(final Long transactionId) throws Exception {
        transactionHelper.validateTrasactionId(transactionId);
        return transactionHelper.getTotalAmountFromTransactionDetail(transactionId);
    }
}
