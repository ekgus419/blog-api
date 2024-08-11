package com.blogapi.dto.response.board;

import com.blogapi.common.ResponseResult;
import com.blogapi.dto.object.BoardListViewItem;
import com.blogapi.dto.response.ResponseDto;
import com.blogapi.entity.BoardListViewEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetUserBoardListResponseDto extends ResponseDto {

    private List<BoardListViewItem> userBoardList;

    public GetUserBoardListResponseDto(List<BoardListViewEntity> boardListViewEntities) {
        super(ResponseResult.SUCCESS.getCode(), ResponseResult.SUCCESS.getMessage());
        this.userBoardList = BoardListViewItem.getList(boardListViewEntities);
    }

    public static ResponseEntity<GetUserBoardListResponseDto> success(List<BoardListViewEntity> boardListViewEntities) {
        return ResponseEntity.status(HttpStatus.OK).body(new GetUserBoardListResponseDto(boardListViewEntities));
    }

    public static ResponseEntity<ResponseDto> noExistUser() {
        return ResponseDto.response(HttpStatus.BAD_REQUEST, ResponseResult.NOT_EXISTED_USER);
    }
}
