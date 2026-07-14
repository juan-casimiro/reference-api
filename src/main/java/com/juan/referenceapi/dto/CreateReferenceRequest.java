package com.juan.referenceapi.dto;

import jakarta.validation.constraints.*;

public record CreateReferenceRequest(
        @NotBlank(message = "Title is required")
        String title,

        @NotBlank(message = "Authors is required")
        String authors,

        @NotBlank(message = "Journal is required")
        String journal,

        @NotNull(message = "Year is required")
        @Min(1900) @Max(2100)
        Integer year,

        @Min(0)
        Integer citationCount,

        Boolean openAccess
) {}