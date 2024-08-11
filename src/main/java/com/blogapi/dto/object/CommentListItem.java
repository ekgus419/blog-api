package com.blogapi.dto.object;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentListItem {
    private String nickname;
    private String profileImage;
    private LocalDateTime writeDatetime;
    private String content;

    @QueryProjection
    public CommentListItem(String nickname, String profileImage, LocalDateTime writeDatetime, String content) {
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.writeDatetime = writeDatetime;
        this.content = content;
    }
}
