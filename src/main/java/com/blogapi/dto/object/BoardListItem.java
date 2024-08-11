package com.blogapi.dto.object;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardListItem {
    private int boardNumber;
    private String title;
    private String content;
    private LocalDateTime writeDateTime;
    private String writerEmail;
    private String writerNickname;
    private String writerProfileImage;

    @QueryProjection
    public BoardListItem(int boardNumber, String title, String content, LocalDateTime writeDateTime,
                         String writerEmail, String writerNickname, String writerProfileImage) {
        this.boardNumber = boardNumber;
        this.title = title;
        this.content = content;
        this.writeDateTime = writeDateTime;
        this.writerEmail = writerEmail;
        this.writerNickname = writerNickname;
        this.writerProfileImage = writerProfileImage;
    }
}
