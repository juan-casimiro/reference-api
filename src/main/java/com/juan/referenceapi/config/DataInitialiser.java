package com.juan.referenceapi.config;

import com.juan.referenceapi.model.Journal;
import com.juan.referenceapi.model.Reference;
import com.juan.referenceapi.repository.JournalRepository;
import com.juan.referenceapi.repository.ReferenceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitialiser {

    @Bean
    CommandLineRunner initData(ReferenceRepository referenceRepository,
                               JournalRepository journalRepository) {
        return args -> {
            Journal nature = journalRepository.save(new Journal("Nature", "111", "Springer"));
            Journal neurips = journalRepository.save(new Journal("NeurIPS", "222", "MIT Press"));
            Journal naacl = journalRepository.save(new Journal("NAACL", "333", "Naacl Publ"));

            referenceRepository.save(new Reference("Deep Learning", "LeCun, Bengio, Hinton", nature, 2015, 50000, true));
            referenceRepository.save(new Reference("Deep Learning", "LeCun, Bengio, Hinton", nature, 2015, 50000, true));
            referenceRepository.save(new Reference("Attention Is All You Need", "Vaswani et al.", neurips, 2017, 80000, true));
            referenceRepository.save(new Reference("BERT", "Devlin et al.", naacl, 2019, 40000, false));
            referenceRepository.save(new Reference("RAG for Knowledge-Intensive NLP", "Lewis et al.", neurips, 2020, 15000, true));
        };
    }
}