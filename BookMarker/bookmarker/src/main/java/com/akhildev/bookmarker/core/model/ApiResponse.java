package com.akhildev.bookmarker.core.model;

import java.util.Map;

public record ApiResponse(Map responseData, String message, Integer status) {
}
