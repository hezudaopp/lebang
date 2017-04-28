package com.youmayon.lebang.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Created by Jawinton on 16/12/22.
 */
public class ForbiddenException extends RuntimeException implements ErrorResponseException {
    private ErrorResponse errorResponse;

    public ForbiddenException(String errorMessage) {
        this.errorResponse = new ErrorResponse(HttpStatus.FORBIDDEN.getReasonPhrase(), errorMessage);
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }
}
