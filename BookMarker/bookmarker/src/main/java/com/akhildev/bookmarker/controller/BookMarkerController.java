package com.akhildev.bookmarker.controller;

import com.akhildev.bookmarker.core.model.ApiResponse;
import com.akhildev.bookmarker.core.model.Constants;
import com.akhildev.bookmarker.core.model.CustomHttpStatus;
import com.akhildev.bookmarker.dto.BookMarkerDTO;
import com.akhildev.bookmarker.dto.BookMarkerResDTO;
import com.akhildev.bookmarker.dto.request.BookmarkCreateRequestDTO;
import com.akhildev.bookmarker.dto.response.BookmarkCreateResDTO;
import com.akhildev.bookmarker.entity.BookMarkerEntity;
import com.akhildev.bookmarker.service.BookMarkerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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

    /**
     * Response:
     * {
     *     "id": 601,
     *     "title": "Pokemon",
     *     "author": "Test",
     *     "url": "https://www.pokemon.com/us",
     *     "createdAt": "2024-10-07T03:50:55.204348612Z"
     * }
     *
     * **/

//    @PostMapping("/add")
//    @ResponseStatus(HttpStatus.CREATED)
//    public ResponseEntity<BookmarkCreateResDTO> createBookMarker(@RequestBody @Valid BookmarkCreateRequestDTO requestDTO){
//        return ResponseEntity.status(HttpStatus.CREATED).body(bookMarkerService.create(requestDTO));
//    }

    //another way to implement the same above api but used Model mapper
    @PostMapping("/create")
    public ResponseEntity<BookmarkCreateResDTO> create(@RequestBody @Valid BookmarkCreateRequestDTO requestDTO){
        BookmarkCreateResDTO response = bookMarkerService.create(requestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

//    with proper exception handler
    /**
     * your response would look like this now
     *
     * {
     *     "responseData": {
     *         "data": {
     *             "id": 651,
     *             "title": "Pokemon",
     *             "author": "Test",
     *             "url": "https://www.pokemon.com/us",
     *             "createdAt": "2024-10-07T03:49:02.794162452Z"
     *         }
     *     },
     *     "message": "success",
     *     "status": 0
     * }
     * **/
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> createBookMarker(@RequestBody @Valid BookmarkCreateRequestDTO requestDTO){
        Map data = new HashMap();
        data.put(Constants.DATA,bookMarkerService.create(requestDTO));
        return ResponseEntity.ok(new ApiResponse(data, Constants.SUCCESS, CustomHttpStatus.SUCCESS.ordinal()));
    }

}
