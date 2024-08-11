package com.blogapi.dto.response.board;

import java.util.List;

import com.blogapi.common.ResponseResult;
import com.blogapi.dto.object.CommentListItem;
import com.blogapi.dto.response.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Getter;

@Getter
public class GetCommentListResponseDto extends ResponseDto {

    private final List<CommentListItem> commentList;

    public GetCommentListResponseDto(List<CommentListItem> commentList) {
        super(ResponseResult.SUCCESS.getCode(), ResponseResult.SUCCESS.getMessage());
        this.commentList = commentList;
    }

    public static ResponseEntity<GetCommentListResponseDto> success(List<CommentListItem> commentList) {
        return ResponseEntity.status(HttpStatus.OK).body(new GetCommentListResponseDto(commentList));
    }

    public static ResponseEntity<ResponseDto> noExistBoard() {
        return ResponseDto.response(HttpStatus.BAD_REQUEST, ResponseResult.NOT_EXISTED_BOARD);
    }

}
