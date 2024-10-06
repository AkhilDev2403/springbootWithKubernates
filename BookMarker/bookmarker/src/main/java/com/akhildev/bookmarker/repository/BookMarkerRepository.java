package com.akhildev.bookmarker.repository;

import com.akhildev.bookmarker.dto.BookMarkerResDTO;
import com.akhildev.bookmarker.dto.BookMarkerResponseDTO;
import com.akhildev.bookmarker.entity.BookMarkerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookMarkerRepository extends JpaRepository<BookMarkerEntity, Long> {

    @Query("select  new  com.akhildev.bookmarker.dto.BookMarkerResDTO(b.id, b.title, b.url, b.author, b.createdAt) from BookMarkerEntity b")
    Page<BookMarkerResDTO> findAllBookMarkers(Pageable pageable);

    // 1. custom JPQL query
    /**
    @Query("""
        select  new  com.akhildev.bookmarker.dto.BookMarkerResDTO(b.id, b.title, b.url, b.author, b.createdAt) from BookMarkerEntity b
        where lower(b.title) like lower(concat('%', :query, '%'))
    """)
    Page<BookMarkerResDTO> searchBookMarks(Pageable pageable, String query);   **/
//    Hibernate: select bme1_0.id,bme1_0.title,bme1_0.url,bme1_0.author,bme1_0.created_at from bookmarks bme1_0 where lower(bme1_0.title) like lower(('%'||?||'%')) escape '' order by bme1_0.created_at desc fetch first ? rows only
//    since we're passing Pageable here, spring will take care of the pagination automatically, don't have to specify anything

//    2. Spring Data JPA
    Page<BookMarkerResDTO> findByTitleContainsIgnoreCase(Pageable pageable, String query);

//    interface based projection
//    Page<BookMarkerResponseDTO> findByTitleContainsIgnoreCase(Pageable pageable, String query);

}
