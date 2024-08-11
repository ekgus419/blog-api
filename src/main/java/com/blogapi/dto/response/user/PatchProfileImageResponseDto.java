package com.blogapi.dto.response.user;

import com.blogapi.common.ResponseResult;
import com.blogapi.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class PatchProfileImageResponseDto extends ResponseDto {
    private PatchProfileImageResponseDto() {
        super(ResponseResult.SUCCESS.getCode(), ResponseResult.SUCCESS.getMessage());
    }

    public static ResponseEntity<PatchProfileImageResponseDto> success() {
        return ResponseEntity.status(HttpStatus.OK).body(new PatchProfileImageResponseDto());
    }

    public static ResponseEntity<ResponseDto> noExistUser() {
        return ResponseDto.response(HttpStatus.BAD_REQUEST, ResponseResult.NOT_EXISTED_USER);
    }

}
