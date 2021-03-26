package com.transaction.engine.app.base.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.transaction.engine.app.base.models.TransactionDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateTransactionRequest extends TransactionDetails {
}
