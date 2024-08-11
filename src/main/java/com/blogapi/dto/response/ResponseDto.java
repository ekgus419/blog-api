package com.blogapi.dto.response;

import com.blogapi.common.ResponseResult;
import lombok.Getter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@AllArgsConstructor
public class ResponseDto {

    private String code;
    private String message;

    public static ResponseEntity<ResponseDto> response(HttpStatus status, ResponseResult result) {
        ResponseDto responseBody = new ResponseDto(result.getCode(), result.getMessage());
        return ResponseEntity.status(status).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> databaseError() {
        return response(HttpStatus.INTERNAL_SERVER_ERROR, ResponseResult.DATABASE_ERROR);
    }

    public static ResponseEntity<ResponseDto> validationFailed() {
        return response(HttpStatus.BAD_REQUEST, ResponseResult.VALIDATION_FAILED);
    }
}
