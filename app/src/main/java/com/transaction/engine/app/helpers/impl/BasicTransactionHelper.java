package com.transaction.engine.app.helpers.impl;

import com.transaction.engine.app.base.exceptions.BadRequestException;
import com.transaction.engine.app.base.requests.UpdateTransactionRequest;
import com.transaction.engine.app.base.responses.GetTotalAmountResponse;
import com.transaction.engine.app.base.responses.GetTransactionByIdResponse;
import com.transaction.engine.app.base.responses.GetTransactionsByTypeResponse;
import com.transaction.engine.app.entities.TransactionDetails;
import com.transaction.engine.app.helpers.TransactionHelper;
import com.transaction.engine.app.repositories.TransactionDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

@Service
public class BasicTransactionHelper implements TransactionHelper {

    @Autowired
    TransactionDetailsRepository transactionDetailsRepository;


    @Override
    public void validateTrasactionId(final Long transactionId) throws BadRequestException {
        if(transactionId == null || transactionId < 0) {
            throw  new BadRequestException("Invalid transaction id: Null or Negative");
        }

    }

    @Override
    public void validateUpdateTransactionRequest(final Long transactionId, final UpdateTransactionRequest updateTransactionRequest) throws BadRequestException{
        validateTrasactionId(transactionId);
        if(updateTransactionRequest == null) {
            throw new BadRequestException("Null update request received");
        } else if( updateTransactionRequest.getAmount() == null || updateTransactionRequest.getAmount() < 0.0){
            throw new BadRequestException("Invalid amount received");
        } else if (updateTransactionRequest.getParentId() != null && updateTransactionRequest.getParentId() == transactionId) {
            throw new BadRequestException("A transaction cannot be parent of its own");
        }

        validateTrasactionType(updateTransactionRequest.getType());
    }

    @Override
    public void validateTrasactionType(final String type) throws BadRequestException {
        if(type == null || type.isEmpty()) {
            throw new BadRequestException("Invalid transaction type received");
        }
    }

    @Override
    public GetTransactionByIdResponse getTransactionDetailAndPrepareResponse(final Long transactionId) throws Exception {
        final TransactionDetails transactionDetails = transactionDetailsRepository.findByTransactionId(transactionId);
        final GetTransactionByIdResponse getTransactionByIdResponse = new GetTransactionByIdResponse();
        if(transactionDetails != null) {
            getTransactionByIdResponse.setAmount(transactionDetails.getAmount());
            getTransactionByIdResponse.setType(transactionDetails.getType());
            getTransactionByIdResponse.setParentId(transactionDetails.getParentId());

        } else {
            throw new BadRequestException("Non-existent tansaction id");
        }
        return getTransactionByIdResponse;
    }

    @Override
    @Transactional
    public void updateTransactionDetailsEntityAndSave(final Long transactionId, final UpdateTransactionRequest updateTransactionRequest) {

        TransactionDetails transactionDetails = transactionDetailsRepository.findByTransactionId(transactionId);
        if(transactionDetails == null) {
            transactionDetails = new TransactionDetails();
        }

        transactionDetails.setAmount(updateTransactionRequest.getAmount());
        transactionDetails.setTransactionId(transactionId);
        transactionDetails.setType(updateTransactionRequest.getType());
        if(updateTransactionRequest.getParentId() != null) {
            transactionDetails.setParentId(updateTransactionRequest.getParentId());
        }

        transactionDetailsRepository.save(transactionDetails);

    }

    @Override
    public GetTransactionsByTypeResponse getTransactionsListByTypeAndPrepareResponse(String type) throws Exception{

        final GetTransactionsByTypeResponse getTransactionsByTypeResponse = new GetTransactionsByTypeResponse();

        final List<TransactionDetails> transactionDetailsList = transactionDetailsRepository.findAllByType(type);
        if(!CollectionUtils.isEmpty(transactionDetailsList)) {
            final List<Long> transactionIds = transactionDetailsList
                    .stream()
                    .map(detail -> detail.getTransactionId())
                    .collect(Collectors.toList());

            getTransactionsByTypeResponse.setTransactionIds(transactionIds);
        } else {
            throw new BadRequestException("Non Existent Type");
        }

        return getTransactionsByTypeResponse;

    }


    // Approach 1: Using queue to get sum of amounts of child txns sharing given ancestor
    @Override
    public GetTotalAmountResponse getTotalAmountFromTransactionDetail(Long transactionId) throws Exception{
        final TransactionDetails parentTransactionDetails = transactionDetailsRepository.findByTransactionId(transactionId);

        if(parentTransactionDetails == null) {
            throw new BadRequestException("Transaction with given id doesn't exist");
        }

        Queue<Long> queue = new LinkedList<>();
        queue.add(parentTransactionDetails.getTransactionId());
        Double amount = parentTransactionDetails.getAmount();

        while(!queue.isEmpty()) {
            Long id = queue.poll();
            final List<TransactionDetails> transactionDetails = transactionDetailsRepository.findAllByParentId(id);
            for(TransactionDetails childTransaction : transactionDetails) {
                amount += childTransaction.getAmount();
                queue.add(childTransaction.getTransactionId());
            }

        }

        final GetTotalAmountResponse getTotalAmountResponse = new GetTotalAmountResponse();
        getTotalAmountResponse.setSum(amount);
        return  getTotalAmountResponse;
    }


    /* Approach 2: Using recursion to get sum of amounts of child txns sharing given ancestor
    * Uncomment the code below and comment approach 1 to test this
    * */

//    @Override
//    public GetTotalAmountResponse getTotalAmountFromTransactionDetail(Long transactionId) throws Exception{
//        final TransactionDetails parentTransactionDetails = transactionDetailsRepository.findByTransactionId(transactionId);
//        if(parentTransactionDetails == null) {
//            throw new BadRequestException("Transaction with given id doesn't exist");
//        }
//
//        final List<TransactionDetails> transactionDetails = transactionDetailsRepository.findAllByParentId(transactionId);
//        Double amount = parentTransactionDetails.getAmount();
//        if(!CollectionUtils.isEmpty(transactionDetails)) {
//           for(TransactionDetails childTransactionDetails : transactionDetails) {
//                 amount += getAmountFromTreeOfTransaction(childTransactionDetails, 0.0);
//           }
//       }
//
//
//       final GetTotalAmountResponse getTotalAmountResponse = new GetTotalAmountResponse();
//       getTotalAmountResponse.setSum(amount);
//       return  getTotalAmountResponse;
//    }


//    private Double getAmountFromTreeOfTransaction(TransactionDetails transactionDetails, Double amount) {
//        if(transactionDetails == null){
//            return  0.0;
//        }
//
//        final List<TransactionDetails> childTransactions = transactionDetailsRepository.findAllByParentId(transactionDetails.getTransactionId());
//        if(CollectionUtils.isEmpty(childTransactions)){
//            return transactionDetails.getAmount();
//        }
//
//        for(TransactionDetails childTransaction : childTransactions) {
//            amount += getAmountFromTreeOfTransaction(childTransaction, amount);
//
//        }
//
//        return  transactionDetails.getAmount() + amount;
//    }
}
