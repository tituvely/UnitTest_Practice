package com.sds.teams.msg.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value={RuntimeException.class})
    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
    protected ResponseEntity<Object> handleRuntimeException(RuntimeException ex, WebRequest request){
        logger.info("Invalid Server status()");
        String bodyOfResponse = "Server is not working";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return handleExceptionInternal(ex, bodyOfResponse, httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
