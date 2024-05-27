package com.br.api.casebank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DateHandlerException extends RuntimeException{

    public DateHandlerException(String message){
        super(message);
    }

}
