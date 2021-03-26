package com.transaction.engine.app.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "TRANSACTION_DETAILS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDetails {

    @Id
    @Column(name = "TRANSACTION_ID")
    private Long transactionId;

    @Column(name = "AMOUNT")
    private Double amount;

    @Column(name = "TYPE")
    private String type;

    @Column(name= "PARENT_ID")
    private Long parentId;
}
