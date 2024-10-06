package com.akhildev.bookmarker.mapper;

import com.akhildev.bookmarker.dto.BookMarkerResDTO;
import com.akhildev.bookmarker.entity.BookMarkerEntity;
import org.springframework.stereotype.Component;

@Component
public class BookMarksMapper {

    // map entity into dto

//    public BookMarkerResDTO toDTO(BookMarkerEntity bookMarkerEntity) {
//        BookMarkerResDTO resDTO = new BookMarkerResDTO();
//        resDTO.setId(bookMarkerEntity.getId());
//        resDTO.setTitle(bookMarkerEntity.getTitle());
//        resDTO.setAuthor(bookMarkerEntity.getAuthor());
//        resDTO.setUrl(bookMarkerEntity.getUrl());
//        resDTO.setCreatedAt(bookMarkerEntity.getCreatedAt());
//        return resDTO;
//    }

    // because we removed @NoArgsConstructor in ResDTO

    public BookMarkerResDTO toDTO(BookMarkerEntity bookMarkerEntity) {
        return new BookMarkerResDTO(bookMarkerEntity.getId(),
                bookMarkerEntity.getTitle(),
                bookMarkerEntity.getAuthor(),
                bookMarkerEntity.getUrl(),
                bookMarkerEntity.getCreatedAt());
    }
}
