package com.juan.referenceapi.repository;

import com.juan.referenceapi.model.Reference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReferenceRepository extends JpaRepository<Reference, Long> {
    /**
     * Returns all references with their associated journals loaded eagerly
     * using a JOIN FETCH to avoid the N+1 query problem.
     *
     * <p>Use this method instead of {@link JpaRepository#findAll()} when
     * journal data is required, to avoid triggering N+1 queries.
     *
     * @return list of all references with journals populated
     */
    @Query("SELECT r FROM Reference r JOIN FETCH r.journal")
    List<Reference> findAllWithJournal();
    List<Reference> findByJournal(String journal);
    List<Reference> findByOpenAccessTrue();
    List<Reference> findByCitationCountGreaterThan(int citationCount);
}
