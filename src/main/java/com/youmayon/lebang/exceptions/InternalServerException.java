package com.youmayon.lebang.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Created by Jawinton on 17/3/1.
 */
public class InternalServerException extends RuntimeException implements ErrorResponseException {
    private ErrorResponse errorResponse;

    public InternalServerException(String errorMessage) {
        this.errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), errorMessage);
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }
}
