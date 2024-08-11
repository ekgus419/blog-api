package com.blogapi.dto.response.board;

import com.blogapi.common.ResponseResult;
import com.blogapi.dto.object.BoardListViewItem;
import com.blogapi.dto.response.ResponseDto;
import com.blogapi.entity.BoardListViewEntity;
import lombok.Getter;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class GetTop3BoardListResponseDto extends ResponseDto {
    private List<BoardListViewItem> top3List;

    public GetTop3BoardListResponseDto(List<BoardListViewEntity> boardEntities) {
        super(ResponseResult.SUCCESS.getCode(), ResponseResult.SUCCESS.getMessage());
        this.top3List = BoardListViewItem.getList(boardEntities);
    }

    public static ResponseEntity<GetTop3BoardListResponseDto> success(List<BoardListViewEntity> boardEntities) {
        return ResponseEntity.status(HttpStatus.OK).body(new GetTop3BoardListResponseDto(boardEntities));
    }

}

