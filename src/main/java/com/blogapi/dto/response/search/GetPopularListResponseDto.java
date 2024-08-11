package com.blogapi.dto.response.search;

import com.blogapi.common.ResponseResult;
import com.blogapi.dto.object.PopularSearchListItem;
import com.blogapi.dto.response.ResponseDto;
import com.blogapi.dto.response.board.DeleteBoardResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class GetPopularListResponseDto extends ResponseDto {

    public List<String> popularWordList;

    public GetPopularListResponseDto(List<PopularSearchListItem> searchListItems) {
        super(ResponseResult.SUCCESS.getCode(), ResponseResult.SUCCESS.getMessage());
        List<String> popularWordList = new ArrayList<>();

        for (PopularSearchListItem searchListItem: searchListItems) {
            String popularWord = searchListItem.getSearchWord();
            popularWordList.add(popularWord);
        }
        this.popularWordList = popularWordList;
    }

    public static ResponseEntity<GetPopularListResponseDto> success(List<PopularSearchListItem> searchListItems) {
        return ResponseEntity.status(HttpStatus.OK).body(new GetPopularListResponseDto(searchListItems));
    }

}
