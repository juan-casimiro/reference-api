package com.juan.referenceapi.service;

import com.juan.referenceapi.dto.CreateReferenceRequest;
import com.juan.referenceapi.dto.ReferenceResponse;
import com.juan.referenceapi.exception.ReferenceNotFoundException;
import com.juan.referenceapi.model.Reference;
import com.juan.referenceapi.repository.ReferenceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ReferenceService {

    private final ReferenceRepository repository;

    public ReferenceService(ReferenceRepository repository) {
        this.repository = repository;
    }

    public List<ReferenceResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ReferenceResponse findById(Long id) {
        return repository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new ReferenceNotFoundException(id));
    }

    @Transactional
    public ReferenceResponse create(CreateReferenceRequest request) {
        Reference reference = new Reference(
                request.title(),
                request.authors(),
                request.journal(),
                request.year(),
                request.citationCount() != null ? request.citationCount() : 0,
                Boolean.TRUE.equals(request.openAccess())
        );
        return toResponse(repository.save(reference));
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ReferenceNotFoundException(id);
        }
        repository.deleteById(id);
    }

    private ReferenceResponse toResponse(Reference ref) {
        return new ReferenceResponse(
                ref.getId(),
                ref.getTitle(),
                ref.getAuthors(),
                ref.getJournal(),
                ref.getYear(),
                ref.getCitationCount(),
                ref.isOpenAccess()
        );
    }
}