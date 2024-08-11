package com.blogapi.dto.response.board;

import com.blogapi.common.ResponseResult;
import com.blogapi.dto.response.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Getter;

@Getter
public class PostCommentResponseDto extends ResponseDto {

    public PostCommentResponseDto() {
        super(ResponseResult.SUCCESS.getCode(), ResponseResult.SUCCESS.getMessage());
    }

    public static ResponseEntity<PostCommentResponseDto> success() {
        return ResponseEntity.status(HttpStatus.OK).body(new PostCommentResponseDto());
    }

    public static ResponseEntity<ResponseDto> noExistBoard() {
        return ResponseDto.response(HttpStatus.BAD_REQUEST, ResponseResult.NOT_EXISTED_BOARD);
    }

    public static ResponseEntity<ResponseDto> noExistUser() {
        return ResponseDto.response(HttpStatus.UNAUTHORIZED, ResponseResult.NOT_EXISTED_USER);
    }
}

