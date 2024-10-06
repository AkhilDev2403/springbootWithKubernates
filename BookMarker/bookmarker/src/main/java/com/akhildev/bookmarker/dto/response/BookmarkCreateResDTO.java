package com.akhildev.bookmarker.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
@Getter
@Setter
public class BookmarkCreateResDTO {
    private Long id;
    private String title;
    private String author;
    private String url;
    private Instant createdAt;
}
