package com.akhildev.bookmarker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
//@NoArgsConstructor - getting error because of Spring Data JPA couldn't find which constructor to chose
public class BookMarkerResDTO {   // class based DTO projection
    private Long id;
    private String title;
    private String author;
    private String url;
    private Instant createdAt;
}
