package com.blogapi.dto.object;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class FavoriteListItem {
    private String email;
    private String nickname;
    private String profileImage;

    @QueryProjection
    public FavoriteListItem(String email, String nickname, String profileImage) {
        this.email = email;
        this.nickname = nickname;
        this.profileImage = profileImage;
    }
}
