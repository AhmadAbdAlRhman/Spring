package com.project.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false , unique = true)
    private String title;
    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    private int publicationYear;
    private String isbn;
}
