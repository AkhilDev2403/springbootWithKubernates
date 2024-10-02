package com.akhildev.bookmarker.repository;

import com.akhildev.bookmarker.dto.BookMarkerResDTO;
import com.akhildev.bookmarker.entity.BookMarkerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookMarkerRepository extends JpaRepository<BookMarkerEntity, Long> {

    @Query("select  new  com.akhildev.bookmarker.dto.BookMarkerResDTO(b.id, b.title, b.url, b.author, b.createdAt) from BookMarkerEntity b")
    Page<BookMarkerResDTO> findAllBookMarkers(Pageable pageable);
}
