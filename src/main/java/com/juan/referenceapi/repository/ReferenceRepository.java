package com.juan.referenceapi.repository;

import com.juan.referenceapi.model.Reference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReferenceRepository extends JpaRepository<Reference, Long> {
    List<Reference> findByJournal(String journal);
    List<Reference> findByOpenAccessTrue();
    List<Reference> findByCitationCountGreaterThan(int citationCount);
}
