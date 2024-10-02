package com.akhildev.bookmarker.dto;


import com.akhildev.bookmarker.entity.BookMarkerEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
public class BookMarkerDTO {  //for pagination
    private List<BookMarkerResDTO> data;
    private long totalElements;
    private int totalPages;
    private int currentPage;
    @JsonProperty("isFirst")
    private boolean isFirstPage;
    @JsonProperty("isLast")
    private boolean isLastPage;
    private boolean hasNextPage;
    private boolean hasPreviousPage;

    public BookMarkerDTO(Page<BookMarkerResDTO> bookMarkerPage) {
        this.setData(bookMarkerPage.getContent());
        this.setTotalElements(bookMarkerPage.getTotalElements());
        this.setTotalPages(bookMarkerPage.getTotalPages());
        this.setCurrentPage(bookMarkerPage.getNumber() + 1);
        this.setFirstPage(bookMarkerPage.isFirst());
        this.setLastPage(bookMarkerPage.isLast());
        this.setHasNextPage(bookMarkerPage.hasNext());
        this.setHasPreviousPage(bookMarkerPage.hasPrevious());
    }
}


/**
 * to change this name in the ui - "firstPage": true,
 *                                 "lastPage": false
 *           simply add
 *                 @JsonProperty("isFirst")
             *     private boolean isFirstPage;
             *     @JsonProperty("isLast")
             *     private boolean isLastPage;
 */