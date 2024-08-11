package com.blogapi.dto.response.user;

import com.blogapi.common.ResponseResult;
import com.blogapi.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class PatchNicknameResponseDto extends ResponseDto {

    private PatchNicknameResponseDto() {
        super(ResponseResult.SUCCESS.getCode(), ResponseResult.SUCCESS.getMessage());
    }

    public static ResponseEntity<PatchNicknameResponseDto> success() {
        return ResponseEntity.status(HttpStatus.OK).body(new PatchNicknameResponseDto());
    }

    public static ResponseEntity<ResponseDto> noExistUser() {
        return ResponseDto.response(HttpStatus.BAD_REQUEST, ResponseResult.NOT_EXISTED_USER);
    }

    public static ResponseEntity<ResponseDto> duplicateNickname() {
        return ResponseDto.response(HttpStatus.BAD_REQUEST, ResponseResult.DUPLICATE_NICKNAME);
    }
}

