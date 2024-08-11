package com.blogapi.dto.response.search;

import com.blogapi.common.ResponseResult;
import com.blogapi.dto.object.RelationSearchListItem;
import com.blogapi.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GetRelationListResponseDto extends ResponseDto {

    private List<String> relativeWordList;

    public GetRelationListResponseDto(List<RelationSearchListItem> searchListItems) {
        super(ResponseResult.SUCCESS.getCode(), ResponseResult.SUCCESS.getMessage());
        List<String> relativeWordList = new ArrayList<>();

        for (RelationSearchListItem searchListItem: searchListItems) {
            String relativeWord = searchListItem.getSearchWord();
            relativeWordList.add(relativeWord);
        }
        this.relativeWordList = relativeWordList;
    }

    public static ResponseEntity<GetRelationListResponseDto> success(List<RelationSearchListItem> searchListItems) {
        return ResponseEntity.status(HttpStatus.OK).body(new GetRelationListResponseDto(searchListItems));
    }

}

