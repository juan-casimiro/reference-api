package com.juan.referenceapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "academic_references")
public class Reference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Authors is required")
    private String authors;

    @NotBlank(message = "Journal is required")
    private String journal;

    @Min(value = 1900, message = "Year must be after 1900")
    @Max(value = 2100, message = "Year must be realistic")
    @Column(name = "publication_year")
    private int year;

    @Min(value = 0, message = "Citation count cannot be negative")
    private int citationCount;

    private boolean openAccess;

    // constructors
    public Reference() {}

    public Reference(String title, String authors, String journal,
                     int year, int citationCount, boolean openAccess) {
        this.title = title;
        this.authors = authors;
        this.journal = journal;
        this.year = year;
        this.citationCount = citationCount;
        this.openAccess = openAccess;
    }

    // getters and setters
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthors() { return authors; }
    public void setAuthors(String authors) { this.authors = authors; }
    public String getJournal() { return journal; }
    public void setJournal(String journal) { this.journal = journal; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    public int getCitationCount() { return citationCount; }
    public void setCitationCount(int citationCount) { this.citationCount = citationCount; }
    public boolean isOpenAccess() { return openAccess; }
    public void setOpenAccess(boolean openAccess) { this.openAccess = openAccess; }
}