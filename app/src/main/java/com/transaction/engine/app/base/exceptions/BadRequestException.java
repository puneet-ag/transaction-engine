package com.transaction.engine.app.base.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

import  static com.transaction.engine.app.base.exceptions.ExceptionCodes.*;

@Data
@NoArgsConstructor
public class BadRequestException extends BaseException {
    private final String code = BAD_REQUEST_EXCEPTION;

    public BadRequestException(String message) {
        super(message);
    }

}
