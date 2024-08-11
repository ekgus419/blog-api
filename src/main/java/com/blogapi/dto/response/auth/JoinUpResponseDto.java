package com.blogapi.dto.response.auth;

import com.blogapi.common.ResponseResult;
import com.blogapi.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class JoinUpResponseDto extends ResponseDto {

    public JoinUpResponseDto() {
        super(ResponseResult.SUCCESS.getCode(), ResponseResult.SUCCESS.getMessage());
    }

    public static ResponseEntity<JoinUpResponseDto> success() {
        return ResponseEntity.status(HttpStatus.OK).body(new JoinUpResponseDto());
    }

    public static ResponseEntity<ResponseDto> duplicateEmail() {
        return ResponseDto.response(HttpStatus.BAD_REQUEST, ResponseResult.DUPLICATE_EMAIL);
    }

    public static ResponseEntity<ResponseDto> duplicateNickname() {
        return ResponseDto.response(HttpStatus.BAD_REQUEST, ResponseResult.DUPLICATE_EMAIL);
    }

    public static ResponseEntity<ResponseDto> duplicateTelNumber() {
        return ResponseDto.response(HttpStatus.BAD_REQUEST, ResponseResult.DUPLICATE_TEL_NUMBER);
    }

}
