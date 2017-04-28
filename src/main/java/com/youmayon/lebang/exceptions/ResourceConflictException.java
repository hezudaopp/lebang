package com.youmayon.lebang.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Created by Jawinton on 16/12/22.
 */
public class ResourceConflictException extends RuntimeException implements ErrorResponseException {
    private String resourceObjectName;
    private String resourceFieldName;
    private String resourceFieldValue;

    public ResourceConflictException(String resourceObjectName, String resourceFieldName, String resourceFieldValue) {
        this.resourceObjectName = resourceObjectName;
        this.resourceFieldName = resourceFieldName;
        this.resourceFieldValue = resourceFieldValue;
    }

    @Override
    public ErrorResponse getErrorResponse() {
        return new ErrorResponse(HttpStatus.CONFLICT.getReasonPhrase(),
                resourceObjectName + " with " + resourceFieldName + " " + resourceFieldValue + " already exists.");
    }
}
