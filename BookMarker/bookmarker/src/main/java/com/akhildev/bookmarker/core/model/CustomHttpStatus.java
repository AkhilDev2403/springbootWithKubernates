package com.akhildev.bookmarker.core.model;

public enum CustomHttpStatus {
    SUCCESS(0),
    FAILED(1);

    private final int staus;

    CustomHttpStatus(int staus) {
        this.staus = staus;
    }
}
