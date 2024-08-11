package com.blogapi.dto.response.board;

import com.blogapi.common.ResponseResult;
import com.blogapi.dto.object.BoardListItem;
import com.blogapi.dto.response.ResponseDto;
import com.blogapi.dto.response.auth.JoinUpResponseDto;
import com.blogapi.entity.ImageEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class GetBoardResponseDto extends ResponseDto {

    private int boardNumber;
    private String title;
    private String content;
    private List<String> boardImageList;
    private LocalDateTime writeDatetime;
    private String writerEmail;
    private String writerNickname;
    private String writerProfileImage;

    public GetBoardResponseDto(BoardListItem boardListItem, List<ImageEntity> imageEntities) {

        super(ResponseResult.SUCCESS.getCode(), ResponseResult.SUCCESS.getMessage());

        List<String> boardImageList = new ArrayList<>();
        for (ImageEntity imageEntity: imageEntities) {
            String boardImage = imageEntity.getImage();
            boardImageList.add(boardImage);
        }

        this.boardNumber = boardListItem.getBoardNumber();
        this.title = boardListItem.getTitle();
        this.content = boardListItem.getContent();
        this.boardImageList = boardImageList;
        this.writeDatetime = boardListItem.getWriteDateTime();
        this.writerEmail = boardListItem.getWriterEmail();
        this.writerNickname = boardListItem.getWriterNickname();
        this.writerProfileImage = boardListItem.getWriterProfileImage();
    }

    public static ResponseEntity<GetBoardResponseDto> success(BoardListItem boardListItem, List<ImageEntity> imageEntities) {
        return ResponseEntity.status(HttpStatus.OK).body(new GetBoardResponseDto(boardListItem, imageEntities));
    }

    public static ResponseEntity<ResponseDto> noExistBoard() {
        return ResponseDto.response(HttpStatus.BAD_REQUEST, ResponseResult.NOT_EXISTED_BOARD);
    }

}

