package com.juan.referenceapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "journals")
public class Journal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String issn;    // public identifier, used in API

    @NotBlank
    private String name;

    @NotBlank
    private String publisher;

    // constructors, getters, setters
    public Journal() {}

    public Journal(String name, String issn, String publisher) {
        this.name = name;
        this.issn = issn;
        this.publisher = publisher;
    }

    // getters and setters
    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPublisher() { return publisher; }
    public void setPublisher(String publisher) { this.publisher = publisher; }
    public String getIssn() {
        return issn;
    }
    public void setIssn(String issn) {
        this.issn = issn;
    }
}