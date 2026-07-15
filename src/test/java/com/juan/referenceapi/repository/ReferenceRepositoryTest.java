package com.juan.referenceapi.repository;

import com.juan.referenceapi.model.Journal;
import com.juan.referenceapi.model.Reference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
public class ReferenceRepositoryTest {
    @Autowired
    private ReferenceRepository referenceRepository;
    @Autowired
    JournalRepository journalRepository;

    @BeforeEach
    void setUp() {
        Journal nature = journalRepository.save(
                new Journal("Nature", "0028-0836", "Springer"));
        referenceRepository.save(
                new Reference("Deep Learning", "LeCun", nature, 2015, 50000, true));
        referenceRepository.save(
                new Reference("Another Paper", "Smith", nature, 2020, 100, false));
    }


    @Test
    void findAllWithJournal_returnsSingleQuery_withJournalPopulated() {
        List<Reference> results = referenceRepository.findAllWithJournal();

        assertThat(results).hasSize(2);
        assertThat(results).allSatisfy(ref -> {
            assertThat(ref.getJournal()).isNotNull();
            assertThat(ref.getJournal().getIssn()).isEqualTo("0028-0836");
        });
    }

    @Test
    void findAllWithJournal_journalFieldsAreFullyLoaded() {
        List<Reference> results = referenceRepository.findAllWithJournal();

        assertThat(results.getFirst().getJournal().getName()).isEqualTo("Nature");
        assertThat(results.getFirst().getJournal().getPublisher()).isEqualTo("Springer");
    }
}
