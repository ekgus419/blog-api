package com.blogapi.dto.response.auth;

import com.blogapi.common.ResponseResult;
import com.blogapi.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class LoginResponseDto extends ResponseDto {

    private String token;
    private int expirationTime;

    public LoginResponseDto(String token) {
        super(ResponseResult.SUCCESS.getCode(), ResponseResult.SUCCESS.getMessage());
        this.token = token;
        this.expirationTime = 3600;
    }

    public static ResponseEntity<LoginResponseDto> success(String token) {
        return ResponseEntity.status(HttpStatus.OK).body(new LoginResponseDto(token));
    }

    public static ResponseEntity<ResponseDto> loginFail() {
        return ResponseDto.response(HttpStatus.UNAUTHORIZED, ResponseResult.SIGN_IN_FAIL);
    }
}