package com.br.api.casebank.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiError {

    private HttpStatus status;
    private String errorCode;
    private String message;

    private ApiError() {
    }

    public ApiError(HttpStatus status) {
        this();
        this.status = status;
    }


    public HttpStatus getStatus() {
        return this.status;
    }

    public String getMessage() {
        return message;
    }

    public String getErrorCode() {
        return errorCode;
    }

}
