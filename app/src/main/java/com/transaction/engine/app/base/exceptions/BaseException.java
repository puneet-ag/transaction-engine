package com.transaction.engine.app.base.exceptions;


import lombok.Data;
import lombok.NoArgsConstructor;
import static com.transaction.engine.app.base.exceptions.ExceptionCodes.*;

@Data
@NoArgsConstructor
public class BaseException extends Exception {
    private String code = BASE_EXCEPTION;

    public BaseException(String message) {
        super(message);
    }
}
