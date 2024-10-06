package com.akhildev.bookmarker.service;

import com.akhildev.bookmarker.dto.BookMarkerDTO;
import com.akhildev.bookmarker.dto.BookMarkerResDTO;
import com.akhildev.bookmarker.dto.BookMarkerResponseDTO;
import com.akhildev.bookmarker.dto.request.BookmarkCreateRequestDTO;
import com.akhildev.bookmarker.entity.BookMarkerEntity;
import com.akhildev.bookmarker.mapper.BookMarksMapper;
import com.akhildev.bookmarker.repository.BookMarkerRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class BookMarkerService {

    private final BookMarkerRepository bookMarkerRepository;

    //for mapping
    private final BookMarksMapper bookMarksMapper;

    /***
    public List<BookMarkerEntity> getAllBookMarkers(Integer page) {
        int pageNumber = page < 1 ? 0 : page-1;                   //user pov page should starts from 1, in JPA pov starts with 0.
        Pageable pageable = PageRequest.of(pageNumber, 2, Sort.Direction.DESC, "createdAt"); //sort the page in desc order based the time the book are added(private Instant createdAt;) that's why we specified this field in Entity
//        return bookMarkerRepository.findAll(pageable);
        // will get error, to solve it , we just need the content, so use .getContent()
        // Required type: List <BookMarkerEntity>
//      // Provided: Page <BookMarkerEntity>

        return bookMarkerRepository.findAll(pageable).getContent();
     not instead of returning List<BookMarkerEntity> we can now return the DTO given below


     Now after the below changes the output should looks like this:
     {
     "data": [
     {
     "id": 4,
     "title": "Micronaut",
     "author": "Wills",
     "url": "https://micronaut.io/",
     "createdAt": "2024-10-01T03:39:33.457285Z"
     },
     {
     "id": 3,
     "title": "Quarks",
     "author": "Addams",
     "url": "https://quarks.io/",
     "createdAt": "2024-10-01T03:39:33.456560Z"
     }
     ],
     "totalElements": 4,
     "totalPages": 2,
     "currentPage": 1,
     "hasNextPage": true,
     "hasPreviousPage": false,
     "firstPage": true,
     "lastPage": false
     }
     **/



    /**
    public BookMarkerDTO getAllBookMarkers(Integer page) {
        int pageNumber = page < 1 ? 0 : page-1;     //user pov page should starts from 1, in JPA pov starts with 0.
        Pageable pageable = PageRequest.of(pageNumber, 2, Sort.Direction.DESC, "createdAt"); //sort the page in desc order based the time the book are added(private Instant createdAt;) that's why we specified this field in Entity
        return new BookMarkerDTO(bookMarkerRepository.findAll(pageable));
    }
**/

    // in above we're simply reading the data, not modifying. so it's better to read from dto rather than loading the
    // entity class directly to the client -  we can use the concept - DTO PROJECTION   .
    // we are returning all the data directly from Entity. now resolve this by 1. using a mapper class


    // here we are mapping all the entities into the mapper then map it to dto.
    /** 1.
    public BookMarkerDTO getAllBookMarkers(Integer page) {
        int pageNumber = page < 1 ? 0 : page-1;     //user pov page should starts from 1, in JPA pov starts with 0.
        Pageable pageable = PageRequest.of(pageNumber, 2, Sort.Direction.DESC, "createdAt"); //sort the page in desc order based the time the book are added(private Instant createdAt;) that's why we specified this field in Entity
        Page<BookMarkerResDTO> resDTO = bookMarkerRepository.findAll(pageable).map(bookMarksMapper::toDTO);     // or map(bookMark -> bookMarksMapper.toDTO(bookMark)
        return new BookMarkerDTO(resDTO);
    }**/


    //2. instead of passing the entire entity then mapping we can do DTO projection
    @Transactional(readOnly = true)
    public BookMarkerDTO getAllBookMarkers(Integer page) {
        int pageNumber = page < 1 ? 0 : page-1;
        Pageable pageable = PageRequest.of(pageNumber, 5, Sort.Direction.DESC, "createdAt");
        Page<BookMarkerResDTO> resDTO = bookMarkerRepository.findAllBookMarkers(pageable);
        return new BookMarkerDTO(resDTO);
    }


    @Transactional(readOnly = true)  //it's read only operation no database manipulation involved so its true
    public BookMarkerDTO searchBookMarks(Integer page, String query) {    //http://localhost:8080/api/bookmarks/getAll?query=jwt
        int pageNumber = page < 1 ? 0 : page-1;
        Pageable pageable = PageRequest.of(pageNumber, 5, Sort.Direction.DESC, "createdAt");
//        Page<BookMarkerResDTO> resDTO = bookMarkerRepository.searchBookMarks(pageable, query);   or
        Page<BookMarkerResDTO> resDTO = bookMarkerRepository.findByTitleContainsIgnoreCase(pageable, query);
//        Page<BookMarkerResponseDTO> response = bookMarkerRepository.findByTitleContainsIgnoreCase(pageable, query);
        return new BookMarkerDTO(resDTO);
    }

    public BookMarkerResDTO createBookMarker(@Valid BookmarkCreateRequestDTO requestDTO) {
        BookMarkerEntity bookMarker = new BookMarkerEntity(null, requestDTO.getTitle(), requestDTO.getAuthor(), requestDTO.getUrl(), Instant.now());
        BookMarkerEntity bookMarkerEntity = bookMarkerRepository.save(bookMarker);
        return bookMarksMapper.toDTO(bookMarkerEntity);
    }
}
