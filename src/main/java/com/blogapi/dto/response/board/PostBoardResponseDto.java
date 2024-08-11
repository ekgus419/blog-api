package com.blogapi.dto.response.board;

import com.blogapi.common.ResponseResult;
import com.blogapi.dto.response.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import lombok.Getter;

@Getter
public class PostBoardResponseDto extends ResponseDto {

    public PostBoardResponseDto() {
        super(ResponseResult.SUCCESS.getCode(), ResponseResult.SUCCESS.getMessage());
    }

    public static ResponseEntity<PostBoardResponseDto> success() {
        return ResponseEntity.status(HttpStatus.OK).body(new PostBoardResponseDto());
    }

    public static ResponseEntity<ResponseDto> noExistUser() {
        return ResponseDto.response(HttpStatus.UNAUTHORIZED, ResponseResult.NOT_EXISTED_USER);
    }

}

