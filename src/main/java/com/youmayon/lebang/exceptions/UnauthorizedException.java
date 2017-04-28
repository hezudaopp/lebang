package com.youmayon.lebang.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Created by Jawinton on 16/12/22.
 */
public class UnauthorizedException extends RuntimeException implements ErrorResponseException {
    private ErrorResponse errorResponse;

    public UnauthorizedException(String errorMessage) {
        this.errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED.getReasonPhrase(), errorMessage);
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }
}
