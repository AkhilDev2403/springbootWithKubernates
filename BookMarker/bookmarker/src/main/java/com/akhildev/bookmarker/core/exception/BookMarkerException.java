package com.akhildev.bookmarker.core.exception;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public class BookMarkerException {
    private final String message;
    private final HttpStatus status;
}
