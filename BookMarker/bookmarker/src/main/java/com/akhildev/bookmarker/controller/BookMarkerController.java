package com.akhildev.bookmarker.controller;

import com.akhildev.bookmarker.dto.BookMarkerDTO;
import com.akhildev.bookmarker.dto.BookMarkerResDTO;
import com.akhildev.bookmarker.dto.request.BookmarkCreateRequestDTO;
import com.akhildev.bookmarker.entity.BookMarkerEntity;
import com.akhildev.bookmarker.service.BookMarkerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookmarks/")
@RequiredArgsConstructor
public class BookMarkerController {

    private final BookMarkerService bookMarkerService;

//    @GetMapping("/getAll")    //http://localhost:8080/api/bookmarks/getAll?page=2
//    public BookMarkerDTO getAllBookMarkers(@RequestParam(name = "page", defaultValue = "1") Integer page) {
//        return bookMarkerService.getAllBookMarkers(page);
//    }

    //with pagination and searching
    @GetMapping("/getAll")
    public BookMarkerDTO getAllBookMarkers(@RequestParam(name = "page", defaultValue = "1") Integer page,
                                           @RequestParam(name = "query", defaultValue = "")String query) {     // u can also specify required = false to mark this field as an optional
        if(query == null || query.trim().isEmpty()){
            return bookMarkerService.getAllBookMarkers(page);
        }
        return bookMarkerService.searchBookMarks(page, query);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public BookMarkerResDTO createBookMarker(@RequestBody @Valid BookmarkCreateRequestDTO requestDTO){
        return bookMarkerService.createBookMarker(requestDTO);
    }
}
