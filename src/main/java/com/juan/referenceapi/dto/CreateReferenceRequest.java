package com.juan.referenceapi.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateReferenceRequest(
        @NotBlank(message = "Title is required")
        String title,

        @NotBlank(message = "Authors is required")
        String authors,

        @NotBlank(message = "Journal ISSN is required")
        String journalIssn,

        @NotNull(message = "Year is required")
        @Min(1900) @Max(2100)
        Integer year,

        @Min(0)
        Integer citationCount,

        Boolean openAccess
) {}