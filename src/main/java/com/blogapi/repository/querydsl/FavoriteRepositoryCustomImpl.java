package com.blogapi.repository.querydsl;

import com.blogapi.dto.object.FavoriteListItem;
import com.blogapi.dto.object.QFavoriteListItem;
import com.blogapi.entity.FavoriteEntity;
import com.blogapi.entity.QFavoriteEntity;
import com.blogapi.entity.QUserEntity;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class FavoriteRepositoryCustomImpl extends QuerydslRepositorySupport implements FavoriteRepositoryCustom {

    public FavoriteRepositoryCustomImpl() {
        super(FavoriteEntity.class);
    }

    @Override
    public List<FavoriteListItem> getFavoriteList(Integer boardNumber) {
        QFavoriteEntity favorite = QFavoriteEntity.favoriteEntity;
        QUserEntity user = QUserEntity.userEntity;

        return from(favorite)
                .join(favorite.userEmail, user)
                .where(favorite.boardNumber.boardNumber.eq(boardNumber))
                .select(new QFavoriteListItem(
                        user.email,
                        user.nickname,
                        user.profileImage
                ))
                .fetch();
    }
}
