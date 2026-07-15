package com.juan.referenceapi.service;

import com.juan.referenceapi.dto.CreateReferenceRequest;
import com.juan.referenceapi.dto.ReferenceResponse;
import com.juan.referenceapi.exception.ResourceNotFoundException;
import com.juan.referenceapi.model.Journal;
import com.juan.referenceapi.model.Reference;
import com.juan.referenceapi.repository.JournalRepository;
import com.juan.referenceapi.repository.ReferenceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ReferenceService {

    private final ReferenceRepository repository;
    private final JournalRepository  journalRepository;
    public ReferenceService(ReferenceRepository repository, JournalRepository journalRepository) {
        this.repository = repository;
        this.journalRepository = journalRepository;
    }

    public List<ReferenceResponse> findAll() {
        return repository.findAllWithJournal()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ReferenceResponse findById(Long id) {
        return repository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Reference", id));
    }

    @Transactional
    public ReferenceResponse create(CreateReferenceRequest request) {
        Journal journal = journalRepository.findByIssn(request.journalIssn())
                .orElseThrow( () -> new ResourceNotFoundException("Journal ISSN", request.journalIssn()));

        Reference reference = new Reference(
                request.title(),
                request.authors(),
                journal,
                request.year(),
                request.citationCount() != null ? request.citationCount() : 0,
                Boolean.TRUE.equals(request.openAccess())
        );
        return toResponse(repository.save(reference));
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Reference", id);
        }
        repository.deleteById(id);
    }

    private ReferenceResponse toResponse(Reference ref) {
        return new ReferenceResponse(
                ref.getId(),
                ref.getTitle(),
                ref.getAuthors(),
                ref.getJournal().getIssn(),
                ref.getYear(),
                ref.getCitationCount(),
                ref.isOpenAccess()
        );
    }
}