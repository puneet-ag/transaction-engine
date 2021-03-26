package com.transaction.engine.app.base.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse {

    private String            status ;
    private String            message;


    public BaseResponse(String status) {
        this.status = status;
    }

    public BaseResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
