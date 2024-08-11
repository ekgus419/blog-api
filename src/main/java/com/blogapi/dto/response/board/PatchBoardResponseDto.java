package com.blogapi.dto.response.board;

import com.blogapi.common.ResponseResult;
import com.blogapi.dto.response.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class PatchBoardResponseDto extends ResponseDto {

    public PatchBoardResponseDto() {
        super(ResponseResult.SUCCESS.getCode(), ResponseResult.SUCCESS.getMessage());
    }

    public static ResponseEntity<PatchBoardResponseDto> success() {
        return ResponseEntity.status(HttpStatus.OK).body(new PatchBoardResponseDto());
    }

    public static ResponseEntity<ResponseDto> noExistBoard() {
        return ResponseDto.response(HttpStatus.BAD_REQUEST, ResponseResult.NOT_EXISTED_BOARD);
    }

    public static ResponseEntity<ResponseDto> noExistUser() {
        return ResponseDto.response(HttpStatus.BAD_REQUEST, ResponseResult.NOT_EXISTED_USER);
    }

    public static ResponseEntity<ResponseDto> noPermission() {
        return ResponseDto.response(HttpStatus.FORBIDDEN, ResponseResult.NO_PERMISSION);
    }
}
