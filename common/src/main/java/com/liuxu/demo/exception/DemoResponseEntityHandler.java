package com.liuxu.demo.exception;

import com.liuxu.demo.result.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class DemoResponseEntityHandler extends ResponseEntityExceptionHandler {

    public static HttpStatus DEFAULT_HTTP_STATUS;

    @ExceptionHandler({DemoException.class})
    public ResponseEntity<Result> handleMyException(DemoException ex) {

        Result result = new Result(ex.getCode() , ex.getMessage());
        HttpStatus httpStatus;
        httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(result, httpStatus);
    }
    
}
