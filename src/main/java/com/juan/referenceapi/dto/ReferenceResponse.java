package com.juan.referenceapi.dto;

public record ReferenceResponse(
        Long id,
        String title,
        String authors,
        String journal,
        int year,
        int citationCount,
        boolean openAccess
) {}