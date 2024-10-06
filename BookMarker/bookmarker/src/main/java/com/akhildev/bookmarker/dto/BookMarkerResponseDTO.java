package com.akhildev.bookmarker.dto;

import java.time.Instant;

//interface based DTO projection
public interface BookMarkerResponseDTO {
//    should be java bean Naming conventions
    Long getId();
    String getTitle();
    String getAuthor();
    String getUrl();
    Instant getCreatedAt();
}
