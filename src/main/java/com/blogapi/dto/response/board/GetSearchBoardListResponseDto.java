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
public class GetSearchBoardListResponseDto extends ResponseDto {

    private final List<BoardListViewItem> searchList;

    public GetSearchBoardListResponseDto(List<BoardListViewEntity> boardListViewEntities) {
        super(ResponseResult.SUCCESS.getCode(), ResponseResult.SUCCESS.getMessage());
        this.searchList = BoardListViewItem.getList(boardListViewEntities);
    }

    public static ResponseEntity<GetSearchBoardListResponseDto> success(List<BoardListViewEntity> boardEntities) {
        return ResponseEntity.status(HttpStatus.OK).body(new GetSearchBoardListResponseDto(boardEntities));
    }


}