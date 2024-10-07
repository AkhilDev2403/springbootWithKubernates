package com.akhildev.bookmarker.core.advice;

import com.akhildev.bookmarker.core.exception.CustomException;
import com.akhildev.bookmarker.core.model.ApiResponse;
import com.akhildev.bookmarker.core.model.Constants;
import com.akhildev.bookmarker.core.model.CustomHttpStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<ApiResponse> handleCustomException(CustomException customException) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        Map data = new HashMap<>();
        data.put(Constants.MESSAGE, customException.getMessage());
        ApiResponse apiResponse = new ApiResponse(data, Constants.ERROR, CustomHttpStatus.FAILED.ordinal());
        return new ResponseEntity<>(apiResponse, httpStatus);
    }


}
