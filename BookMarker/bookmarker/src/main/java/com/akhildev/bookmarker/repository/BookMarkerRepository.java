package com.akhildev.bookmarker.repository;

import com.akhildev.bookmarker.entity.BookMarkerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookMarkerRepository extends JpaRepository<BookMarkerEntity, Long> {
}
