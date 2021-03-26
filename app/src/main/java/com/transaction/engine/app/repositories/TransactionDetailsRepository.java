package com.transaction.engine.app.repositories;

import com.transaction.engine.app.entities.TransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionDetailsRepository extends JpaRepository<TransactionDetails, Long> {

    TransactionDetails findByTransactionId(long transactionId);

    List<TransactionDetails> findAllByType(String type);

    List<TransactionDetails> findAllByParentId(Long transactionId);
}
