package com.blogapi.dto.response.board;

import java.util.List;

import com.blogapi.common.ResponseResult;
import com.blogapi.dto.object.BoardListViewItem;
import com.blogapi.dto.response.ResponseDto;
import com.blogapi.entity.BoardListViewEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Getter;

@Getter
public class GetLatestBoardListResponseDto extends ResponseDto {

    private List<BoardListViewItem> latestList;

    public GetLatestBoardListResponseDto(List<BoardListViewEntity> boardEntities) {
        super(ResponseResult.SUCCESS.getCode(), ResponseResult.SUCCESS.getMessage());
        this.latestList = BoardListViewItem.getList(boardEntities);
    }

    public static ResponseEntity<GetLatestBoardListResponseDto> success(List<BoardListViewEntity> boardEntities) {
        return ResponseEntity.status(HttpStatus.OK).body(new GetLatestBoardListResponseDto(boardEntities));
    }


}


