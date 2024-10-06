package com.akhildev.bookmarker.controller;

import com.akhildev.bookmarker.dto.BookMarkerDTO;
import com.akhildev.bookmarker.service.BookMarkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
