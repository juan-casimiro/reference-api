package com.juan.referenceapi.controller;

import com.juan.referenceapi.dto.CreateReferenceRequest;
import com.juan.referenceapi.dto.ReferenceResponse;
import com.juan.referenceapi.service.ReferenceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/references")
public class ReferenceController {

    private final ReferenceService referenceService;

    public ReferenceController(ReferenceService referenceService) {
        this.referenceService = referenceService;
    }

    @GetMapping
    public List<ReferenceResponse> getReferences() {
        return referenceService.findAll();
    }

    @GetMapping("/{id}")
    public ReferenceResponse getReference(@PathVariable Long id) {
        return referenceService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReferenceResponse create(@Valid @RequestBody CreateReferenceRequest request) {
        return referenceService.create(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReference(@PathVariable Long id) {
        referenceService.delete(id);
    }
}
