package com.juan.referenceapi.config;

import com.juan.referenceapi.model.Reference;
import com.juan.referenceapi.repository.ReferenceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitialiser {

    @Bean
    CommandLineRunner initData(ReferenceRepository repository) {
        return args -> {
            repository.save(new Reference("Deep Learning", "LeCun, Bengio, Hinton", "Nature", 2015, 50000, true));
            repository.save(new Reference("Attention Is All You Need", "Vaswani et al.", "NeurIPS", 2017, 80000, true));
            repository.save(new Reference("BERT", "Devlin et al.", "NAACL", 2019, 40000, false));
            repository.save(new Reference("RAG for Knowledge-Intensive NLP", "Lewis et al.", "NeurIPS", 2020, 15000, true));
        };
    }
}