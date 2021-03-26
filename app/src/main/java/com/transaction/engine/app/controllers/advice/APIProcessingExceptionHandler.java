package com.transaction.engine.app.controllers.advice;


import com.fasterxml.jackson.core.JsonParseException;
import com.transaction.engine.app.base.exceptions.BadRequestException;
import com.transaction.engine.app.base.exceptions.BaseException;
import com.transaction.engine.app.base.responses.BaseResponse;
import com.transaction.engine.app.controllers.TransactionController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(assignableTypes = {
        TransactionController.class
})
public class APIProcessingExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BaseResponse> handlerBadRequestException(final BadRequestException exp) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatus(HttpStatus.BAD_REQUEST.name());
        baseResponse.setMessage(exp.getMessage());
        return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse> handlerException(final Exception exp) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.name());
        baseResponse.setMessage(exp.getMessage());
        return new ResponseEntity<>(baseResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
