package com.juan.referenceapi.exception;

public class ReferenceNotFoundException extends RuntimeException {
    public ReferenceNotFoundException(Long id) {
        super("Reference not found: " + id);
    }
}
