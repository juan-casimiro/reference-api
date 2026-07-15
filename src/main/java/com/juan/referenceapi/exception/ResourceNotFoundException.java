package com.juan.referenceapi.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resource, String id) {
        super(resource + " not found: " + id);
    }

    public ResourceNotFoundException(String resource, Long id) {
        this(resource, String.valueOf(id));
    }
}
