package com.akhildev.bookmarker.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookmarkCreateRequestDTO {
    @NotEmpty(message = "Title should not be null")
    private String title;
    private String author;
    @NotEmpty(message = "url should not be null")
    private String url;
}
