package com.akhildev.bookmarker.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bookmarks")
public class BookMarkerEntity {

    @Id
//    @SequenceGenerator(name = "bm_id_seq_gen", sequenceName = "bm_id_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    private String url;

    private Instant createdAt;
}


//Remove the Sequence Mapping from the Entity: Check your entity class where you're defining the primary key. I
// t seems you might have used a @GeneratedValue(strategy = GenerationType.SEQUENCE) annotation,
// which is causing Hibernate to expect a sequence.
// For MySQL, you need to use GenerationType.IDENTITY to match the AUTO_INCREMENT used in the database.