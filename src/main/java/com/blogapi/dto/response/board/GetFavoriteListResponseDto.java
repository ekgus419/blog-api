package com.blogapi.dto.response.board;

import java.util.List;

import com.blogapi.common.ResponseResult;
import com.blogapi.dto.object.FavoriteListItem;
import com.blogapi.dto.response.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Getter;

@Getter
public class GetFavoriteListResponseDto extends ResponseDto {

    private final List<FavoriteListItem> favoriteList;

    public GetFavoriteListResponseDto(List<FavoriteListItem> favoriteList) {
        super(ResponseResult.SUCCESS.getCode(), ResponseResult.SUCCESS.getMessage());
        this.favoriteList = favoriteList;
    }

    public static ResponseEntity<GetFavoriteListResponseDto> success(List<FavoriteListItem> favoriteList) {
        return ResponseEntity.status(HttpStatus.OK).body(new GetFavoriteListResponseDto(favoriteList));
    }

    public static ResponseEntity<ResponseDto> noExistBoard() {
        return ResponseDto.response(HttpStatus.BAD_REQUEST, ResponseResult.NOT_EXISTED_BOARD);
    }

}

